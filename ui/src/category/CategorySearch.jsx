import React, { useState, useEffect } from 'react';
import { Grid, Card, CardContent, CardMedia, Typography, Button, Pagination, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { makeStyles } from '@mui/styles';
import { useNavigate, useParams } from 'react-router-dom';
import ApiBackendService from '../apiBackend/ApiBackendService';
import Common from '../Common';
import TopbarSection from '../sections/TopbarSection';
import NavbarSection from '../sections/NavbarSection';
import Preloader from '../js/Preloader';
import FooterSection from '../sections/FooterSection';
import BackToTopButton from '../js/BackToTopButton';

const useStyles = makeStyles({
    root: {
        flexGrow: 1,
        padding: '20px',
        display: 'flex',
        backgroundColor: '#1C1E32', // Background color for the entire page
        minHeight: '100vh',
        color: 'white', // Ensure text color is white for readability
    },
    filter: {
        width: '25%',
        padding: '20px',
        color: 'white',
    },
    productContainer: {
        width: '75%',
    },
    gridItem: {
        display: 'flex',
    },
    card: {
        width: '100%',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        backgroundColor: '#2B2E4A', // Background color for the product cards
        color: 'white', // Ensure text color is white for readability
        boxShadow: '0 4px 8px 0 rgba(255, 0, 0, 0.2), 0 6px 20px 0 rgba(255, 0, 0, 0.19)', // Red shadow
    },
    media: {
        height: 180,
    },
    cardContent: {
        flexGrow: 1,
        display: 'flex',
        flexDirection: 'column',
    },
    title: {
        display: '-webkit-box',
        WebkitBoxOrient: 'vertical',
        overflow: 'hidden',
        textOverflow: 'ellipsis',
        WebkitLineClamp: 3,
        lineClamp: 3,
    },
    pagination: {
        display: 'flex',
        justifyContent: 'start',
        marginTop: '20px',
        color: 'white',
        marginBottom: '20px',
        '& .MuiPaginationItem-root': {
            color: 'white',
            '&:hover': {
                backgroundColor: 'rgba(255, 255, 255, 0.1)',
                color: 'white',
            },
        },
        '& .MuiPaginationItem-root.Mui-selected': {
            backgroundColor: 'var(--primary)',
            color: 'white',
            '&:hover': {
                backgroundColor: 'var(--primary_hover)',
            },
        },
    },
    formControl: {
        '& .MuiInputBase-root': {
            color: 'white',
        },
        '& .MuiInputLabel-root': {
            color: 'var(--primary)', // Set the color to var(--primary)
        },
        '& .MuiSelect-icon': {
            color: 'white',
        },
        '& .MuiOutlinedInput-root': {
            '& .MuiOutlinedInput-notchedOutline': {
                borderColor: 'var(--primary)',
            },
            '&:hover .MuiOutlinedInput-notchedOutline': {
                borderColor: 'var(--primary)',
            },
            '&.Mui-focused .MuiOutlinedInput-notchedOutline': {
                borderColor: 'var(--primary)',
            },
        },
    },
    button: {
        color: 'white',
        borderColor: 'white',
        '&:hover': {
            backgroundColor: '#3C3E5A',
        },
    },
    noResults: {
        display: 'flex',
        color: 'white',
        alignItems: 'center',
        flexDirection: 'column',
        marginTop: '50px !important',
        width: '100%',
    },
});

const CategorySearch = () => {
    const { categoryName } = useParams();
    const classes = useStyles();
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [isAdmin, setIsAdmin] = useState(false);
    const [page, setPage] = useState(1);
    const [isProductListEmpty, setIsProductListEmpty] = useState(false);
    const [totalPages, setTotalPages] = useState(0);
    const [filter, setFilter] = useState('popularity');
    const itemsPerPage = 30; // Fixed value of items per page

    useEffect(() => {
        fetchProducts(page, filter);
        setIsLoggedIn(Common.isLoggedIn());
        setIsAdmin(Common.isUserAdmin());
    }, [page, filter, categoryName]);

    const fetchProducts = async (page, filter) => {
        try {
            const categoryId = await ApiBackendService.getProductCategoryId({}, { categoryName: categoryName });
            const response = await ApiBackendService.searchProductsByCategory({
                categoryId: categoryId,
                sortCriteria: filter.toUpperCase(),
                chunkNumber: page - 1,
                nrElementsPerChunk: itemsPerPage,
            });
            setIsProductListEmpty(response.searchProducts.length === 0);
            const loggedIn = Common.isLoggedIn();
            setTotalPages(response.totalPages);
            if (!loggedIn) {
                setProducts(response.searchProducts.map(product => ({
                    ...product,
                    isFavorite: false,
                })));
                return;
            }

            const productIds = response.searchProducts.map(product => product.productId);
            const favoriteStatusResponse = await ApiBackendService.getProductsLikeStatus({}, {productIds});

            const favoriteStatusMap = favoriteStatusResponse.productsLikeStatus.reduce((acc, { productId, likeStatus }) => {
                acc[productId] = likeStatus;
                return acc;
            }, {});

            const productsWithFavorites = response.searchProducts.map(product => ({
                ...product,
                isFavorite: favoriteStatusMap[product.productId] === 'ACTIVE',
            }));

            setProducts(productsWithFavorites);
        } catch (error) {
            console.log('Error fetching category products!');
            // window.location.href = "/error";
        }
    };

    const handlePageChange = (event, value) => {
        setPage(value);
    };

    const handleProductClick = (title, productId) => {
        const encodedTitle = encodeURIComponent(title.split(' ').join('-'));
        navigate(`/product/${encodedTitle}/${productId}`);
    };

    const toggleFavorite = async (productId, isFavorite, popularity) => {
        try {
            await ApiBackendService.changeProductLikeStatus({ productId }, {});
            setProducts(products.map(product =>
                product.productId === productId ? {
                    ...product,
                    isFavorite: !isFavorite,
                    popularity: isFavorite ? product.popularity - 1 : product.popularity + 1
                } : product
            ));
        } catch (error) {
            console.log('Error updating favorite status!');
        }
    };


    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <div className={classes.root}>
                {!isProductListEmpty && (
                    <>
                        <div className={classes.filter}>
                            <FormControl fullWidth variant="outlined" className={classes.formControl}>
                                <InputLabel style={{ color: 'var(--primary)' }}>Filter By</InputLabel>
                                <Select
                                    value={filter}
                                    onChange={(e) => setFilter(e.target.value)}
                                    label="Filter By"
                                >
                                    <MenuItem value="popularity">Popularity</MenuItem>
                                    <MenuItem value="newest">Most Recent</MenuItem>
                                    <MenuItem value="random">Shuffled</MenuItem>
                                </Select>
                            </FormControl>
                        </div>
                        <div className={classes.productContainer}>
                            <Pagination
                                className={classes.pagination}
                                count={totalPages}
                                page={page}
                                onChange={handlePageChange}
                                variant="outlined" shape="rounded"
                            />
                            <Grid container spacing={4}>
                                {products.map(product => (
                                    <Grid item xs={12} sm={4} key={product.productId} className={classes.gridItem}>
                                        <Card
                                            className={classes.card}
                                            style={{ backgroundColor: '#2B2E4A', boxShadow: '0 2px 8px var(--primary)' }}
                                        >
                                            <CardMedia
                                                className={classes.media}
                                                image={product.productImages[0]}
                                                title={product.title}
                                                onClick={() => handleProductClick(product.title, product.productId)}
                                                style={{ cursor: 'pointer' }}
                                            />
                                            <CardContent className={classes.cardContent}>
                                                <Typography className={classes.title} gutterBottom variant="h5" component="h2"
                                                    onClick={() => handleProductClick(product.title, product.productId)}
                                                    style={{ cursor: 'pointer', color: 'var(--light)' }}
                                                >
                                                    {product.title}
                                                </Typography>
                                                <Typography variant="body2" color="textSecondary" component="p"
                                                    style={{ marginBottom: '6px', color: 'var(--light)' }}
                                                >
                                                    Approximate Value: ${product.price}
                                                </Typography>
                                                {product.popularity > 1 && (
                                                    <Typography variant="body2" color="textSecondary" component="p"
                                                        style={{ marginBottom: '6px', color: 'var(--light)' }}
                                                    >
                                                        This product has {product.popularity} appreciations
                                                    </Typography>
                                                )}
                                                {product.popularity === 1 && (
                                                    <Typography variant="body2" color="textSecondary" component="p"
                                                        style={{ marginBottom: '6px', color: 'var(--light)' }}
                                                    >
                                                        This product has {product.popularity} appreciation
                                                    </Typography>
                                                )}
                                                {(isLoggedIn && !isAdmin) && (
                                                    <Button size="small" variant="outlined" className={classes.button}
                                                        onClick={() => toggleFavorite(product.productId, product.isFavorite)}
                                                        style={{ color: 'var(--primary)', borderColor: 'var(--primary)' }}
                                                    >
                                                        <i className={`fa-${product.isFavorite ? 'solid' : 'regular'} fa-lg fa-heart`} style={{ marginRight: '3px' }}></i>
                                                        {product.isFavorite ? 'Remove from Favourites' : 'Add to Favourite'}
                                                    </Button>
                                                )}
                                            </CardContent>
                                        </Card>
                                    </Grid>
                                ))}
                            </Grid>
                            <Pagination
                                className={classes.pagination}
                                count={totalPages}
                                page={page}
                                onChange={handlePageChange}
                                variant="outlined" shape="rounded"
                            />
                        </div>
                    </>
                )}
                {isProductListEmpty && (
                    <Typography variant="h6" className={classes.noResults}>
                        <div>
                            <i class="fa-solid fa-circle-exclamation" style={{ marginRight: '5px' }}></i>No results found for this product category!
                        </div>
                    </Typography>
                )}
            </div>

            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default CategorySearch;
