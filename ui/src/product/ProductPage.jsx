import React, { useState, useEffect, lazy } from 'react';
import { useParams } from 'react-router-dom';
import '../css/ProductPage.css'; // Import the CSS file for styling
import ImageContainer from './ImageContainer'; // Import the ImageContainer component
import DeleteIcon from '@mui/icons-material/Delete';

import Preloader from '../js/Preloader';
import CategoriesMenu from '../sections/CategoriesMenu';
import ApiBackendService from '../apiBackend/ApiBackendService';
import Common from '../Common';
const TopbarSection = lazy(() => import('../sections/TopbarSection'));
const NavbarSection = lazy(() => import('../sections/NavbarSection'));
const FooterSection = lazy(() => import('../sections/FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));
const ConfirmationDialog = lazy(() => import('./ConfirmationDialog'));

const ProductPage = () => {
    const { title, productId } = useParams();

    const [isUserAuth, setIsUserAuth] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const [productImages, setProductImages] = useState([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [isImageContainerVisible, setIsImageContainerVisible] = useState(false);
    const [isFavourite, setIsFavourite] = useState(false);
    const [isConfirmationVisible, setIsConfirmationVisible] = useState(false);
    const [productData, setProductData] = useState({
        userId: 0,
        categoryId: 0,
        title: '',
        description: '',
        price: 0,
        popularity: '',
        specifications: {}
    });

    useEffect(() => {
        const fetchData = async () => {
            try {
                // Product Fetch
                const productResponse = await ApiBackendService.getProductById({ productId });

                const encodedResponseTitle = encodeURIComponent(productResponse.title.split(' ').join('-'));
                const encodedTitle = encodeURIComponent(title.split(' ').join('-'));

                if (encodedResponseTitle !== encodedTitle) {
                    const correctUrl = `/product/${encodedResponseTitle}/${productId}/`;
                    window.location.href = correctUrl;
                    return;
                }

                setProductData({
                    userId: productResponse.userId,
                    categoryId: productResponse.categoryId,
                    title: productResponse.title,
                    description: productResponse.description,
                    price: productResponse.price,
                    popularity: productResponse.popularity,
                    specifications: productResponse.productSpecifications.reduce((acc, spec) => {
                        acc[spec.key] = spec.value;
                        return acc;
                    }, {})
                });

                setProductImages(productResponse.productImages.map(image => image.imageUrl));

                // Fetch Auth User Data
                if (isLoggedIn) {
                    const authUserData = await ApiBackendService.getAuthenticatedUserDetails({});
                    if (authUserData.userId === productResponse.userId) {
                        setIsUserAuth(true);
                    }
                    else {
                        setIsUserAuth(false);
                    }
                }

                setIsAdmin(Common.isUserAdmin());

                // Fetch Product Like
                const likeResponse = await ApiBackendService.getProductLikeStatus({ productId });
                const responseBody = await likeResponse.text(); // Read the response body as text
                if (responseBody === 'ACTIVE') {
                    setIsFavourite(true);
                }

                const storedIsLoggedIn = localStorage.getItem("isLoggedIn");
                if (storedIsLoggedIn !== null && storedIsLoggedIn === 'true') {
                    setIsLoggedIn(true);
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, [productId, title, isLoggedIn]);

    const handlePrevClick = (e) => {
        e.stopPropagation();
        setCurrentIndex((prevIndex) => (prevIndex === 0 ? productImages.length - 1 : prevIndex - 1));
    };

    const handleNextClick = (e) => {
        e.stopPropagation();
        setCurrentIndex((prevIndex) => (prevIndex === productImages.length - 1 ? 0 : prevIndex + 1));
    };

    const handleImageClick = (index) => {
        setCurrentIndex(index);
    };

    const openImageContainer = () => {
        setIsImageContainerVisible(true);
    };

    const closeImageContainer = () => {
        setIsImageContainerVisible(false);
    };

    const handleFavouriteClick = async () => {
        try {
            await ApiBackendService.changeProductLikeStatus({}, { productId });
            setIsFavourite(!isFavourite);
        } catch (error) {
            console.error('Error updating favourite status:', error);
        }
    };

    const handleUpdateProduct = async () => {
        const encodedTitle = encodeURIComponent(title.split(' ').join('-'));
        window.location.href = `/product/update/${encodedTitle}/${productId}`;
    };

    const handleDeleteProduct = async () => {
        setIsConfirmationVisible(true);
    };

    const confirmDeleteProduct = async () => {
        try {
            if (!isAdmin) {
                await ApiBackendService.deleteProduct({ productId });
            } else {
                await ApiBackendService.deleteProductAdmin({ productId });
            }
            window.location.href = '/';
        } catch (error) {
            console.error('Error deleting product:', error);
        }
    };

    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <CategoriesMenu />
            <div className="product-page">
                <div className="image-section">
                    <div className="main-image" onClick={openImageContainer}>
                        <button className="slider-button left" onClick={handlePrevClick}>{"<"}</button>
                        <img src={productImages[currentIndex]} alt="Selected Product" />
                        <button className="slider-button right" onClick={handleNextClick}>{">"}</button>
                    </div>
                    <div className="image-thumbnails">
                        {productImages.map((imageUrl, index) => (
                            <img
                                key={index}
                                src={imageUrl}
                                alt={`Product Image ${index + 1}`}
                                onClick={() => handleImageClick(index)}
                                className={currentIndex === index ? 'active' : ''}
                            />
                        ))}
                    </div>
                    <div className="specifications-section">
                        <h2>Product Specifications</h2>
                        <table className="specifications-table">
                            <tbody>
                                {Object.entries(productData.specifications).map(([key, value]) => (
                                    <tr key={key} className="specification-item">
                                        <td className="spec-key">{key}</td>
                                        <td className="spec-value">{value}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="product-details">
                    <h1>{productData.title}</h1>
                    <p>{productData.description}</p>
                    <div className="price">
                        Approximate value:
                        <span className="price-value"> ${productData.price}</span>
                    </div>
                    {isLoggedIn && (
                        <>
                            {!isUserAuth && !isAdmin && (
                                <div className="product-button">
                                    <button className={`favourite-button ${isFavourite ? 'active' : ''}`} onClick={handleFavouriteClick}>
                                        <span className="heart-icon">{isFavourite ? <i className="fa-solid fa-heart"></i> : <i className="fa-regular fa-heart"></i>}</span>
                                        {isFavourite ? ' Remove from Favourite' : ' Add To Favourite'}
                                    </button>
                                </div>
                            )}
                            <div className="product-buttons">
                                {isUserAuth && (
                                    <button className="update-product-button" onClick={handleUpdateProduct}>
                                        <i className="fa-solid fa-wrench" style={{ marginRight: '5px' }}></i>
                                        Update Product
                                    </button>
                                )}
                                {(isUserAuth || isAdmin) && (
                                    <button className="delete-product-button" onClick={handleDeleteProduct}>
                                        <DeleteIcon />
                                        Delete Product
                                    </button>
                                )}
                            </div>
                        </>
                    )}
                </div>
            </div>
            <FooterSection />
            <BackToTopButton />
            <ImageContainer isVisible={isImageContainerVisible} image={productImages[currentIndex]} onClose={closeImageContainer} />
            <ConfirmationDialog
                isVisible={isConfirmationVisible}
                onCancel={() => setIsConfirmationVisible(false)}
                onConfirm={confirmDeleteProduct}
            />
        </React.Fragment>
    );
};

export default ProductPage;