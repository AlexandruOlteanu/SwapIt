import React, { useState, lazy } from 'react';
import { TextField, Button, Grid, MenuItem, Typography, Box } from '@mui/material';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import { styled } from '@mui/material/styles';
import { storage } from '../firebase-config';
import { ref, uploadBytesResumable, getDownloadURL } from 'firebase/storage';
import { v4 as uuidv4 } from 'uuid';
import '../css/AddProduct.css';

import Preloader from '../js/Preloader'
import CategoriesMenu from './CategoriesMenu';
import ApiBackendService from '../apiBackend/ApiBackendService';
const TopbarSection = lazy(() => import('./TopbarSection'));
const NavbarSection = lazy(() => import('./NavbarSection'));
const FooterSection = lazy(() => import('./FooterSection'));
const BackToTopButton = lazy(() => import('../js/BackToTopButton'));


const categories = {
    level1: [
        "Electronics & Appliances",
        "Computers & Accessories",
        "Home & Garden",
        "Sports & Outdoors",
        "Fashion & Accessories",
        "Health & Beauty",
        "Toys & Games",
        "Automotive",
        "Books & Audible",
        "Music & Instruments",
        "Office Supplies"
    ],
    level2: {
        "Electronics & Appliances": [
            "Washing Machines",
            "Kitchen Appliances",
            "Air Conditioners",
            "Home Audio",
            "TV & Video",
            "Personal Care",
            "Vacuum Cleaners"
        ],
        "Computers & Accessories": [
            "Laptops",
            "Desktops",
            "Monitors",
            "Computer Accessories",
            "Storage Devices",
            "Networking",
            "Software"
        ],
        "Home & Garden": [
            "Furniture",
            "Garden Tools",
            "Lighting",
            "Decor",
            "Kitchenware",
            "Bedding",
            "DIY & Tools"
        ],
        "Sports & Outdoors": [
            "Fitness Equipment",
            "Camping & Hiking",
            "Cycling",
            "Water Sports",
            "Team Sports",
            "Outdoor Clothing",
            "Fishing"
        ],
        "Fashion & Accessories": [
            "Men's Clothing",
            "Women's Clothing",
            "Shoes",
            "Bags & Accessories",
            "Jewelry",
            "Eyewear",
            "Watches"
        ],
        "Health & Beauty": [
            "Skincare",
            "Haircare",
            "Makeup",
            "Fragrances",
            "Health Supplements",
            "Personal Care",
            "Medical Supplies"
        ],
        "Toys & Games": [
            "Action Figures",
            "Board Games",
            "Building Sets",
            "Dolls & Accessories",
            "Educational Toys",
            "Outdoor Play",
            "Puzzles"
        ],
        "Automotive": [
            "Car Electronics",
            "Car Accessories",
            "Motorcycle Accessories",
            "Car Care",
            "Replacement Parts",
            "Tires & Wheels",
            "Tools & Equipment"
        ],
        "Books & Audible": [
            "Fiction",
            "Non-Fiction",
            "Children's Books",
            "Audible Books",
            "E-Books",
            "Magazines",
            "Comics & Graphic Novels"
        ],
        "Music & Instruments": [
            "Musical Instruments",
            "Music Accessories",
            "Recording Equipment",
            "DJ Equipment",
            "Sheet Music",
            "Vinyl Records",
            "Digital Music"
        ],
        "Office Supplies": [
            "Writing Instruments",
            "Paper Products",
            "Office Electronics",
            "Office Furniture",
            "Calendars & Planners",
            "Mailroom Supplies",
            "Cleaning Supplies"
        ]
    },
    level3: {
        "Washing Machines": ["Front Load", "Top Load", "Washer Dryer", "Compact", "Portable"],
        "Kitchen Appliances": ["Microwaves", "Ovens", "Dishwashers", "Refrigerators", "Blenders"],
        "Air Conditioners": ["Window AC", "Split AC", "Portable AC", "Inverter AC", "Smart AC"],
        "Home Audio": ["Soundbars", "Home Theater Systems", "Speakers", "Receivers", "Subwoofers"],
        "TV & Video": ["Smart TVs", "4K TVs", "LED TVs", "OLED TVs", "Projectors"],
        "Personal Care": ["Hair Dryers", "Electric Shavers", "Epilators", "Electric Toothbrushes", "Massagers"],
        "Vacuum Cleaners": ["Robot Vacuums", "Upright Vacuums", "Stick Vacuums", "Handheld Vacuums", "Canister Vacuums"],
        "Laptops": ["Gaming Laptops", "Business Laptops", "2-in-1 Laptops", "Ultrabooks", "Chromebooks"],
        "Desktops": ["Gaming Desktops", "All-in-One Desktops", "Mini PCs", "Workstations", "Tower Desktops"],
        "Monitors": ["Gaming Monitors", "4K Monitors", "Curved Monitors", "Ultrawide Monitors", "Office Monitors"],
        "Computer Accessories": ["Keyboards", "Mice", "Webcams", "Headsets", "Docking Stations"],
        "Storage Devices": ["External Hard Drives", "SSD Drives", "USB Flash Drives", "Network Storage", "Memory Cards"],
        "Networking": ["Routers", "Modems", "Range Extenders", "Switches", "Access Points"],
        "Software": ["Operating Systems", "Office Suites", "Security Software", "Design Software", "Development Software"],
        "Furniture": ["Sofas", "Beds", "Dining Tables", "Wardrobes", "Office Chairs"],
        "Garden Tools": ["Lawn Mowers", "Hedge Trimmers", "Leaf Blowers", "Garden Hoses", "Garden Shears"],
        "Lighting": ["LED Bulbs", "Table Lamps", "Floor Lamps", "Ceiling Lights", "Outdoor Lighting"],
        "Decor": ["Wall Art", "Mirrors", "Vases", "Clocks", "Rugs"],
        "Kitchenware": ["Cookware", "Cutlery", "Glassware", "Dinnerware", "Storage Containers"],
        "Bedding": ["Bedsheets", "Pillows", "Comforters", "Mattress Protectors", "Blankets"],
        "DIY & Tools": ["Power Tools", "Hand Tools", "Tool Storage", "Ladders", "Safety Equipment"],
        "Fitness Equipment": ["Treadmills", "Exercise Bikes", "Dumbbells", "Yoga Mats", "Resistance Bands"],
        "Camping & Hiking": ["Tents", "Sleeping Bags", "Backpacks", "Camping Furniture", "Camping Cookware"],
        "Cycling": ["Mountain Bikes", "Road Bikes", "Electric Bikes", "Bike Helmets", "Bike Accessories"],
        "Water Sports": ["Kayaks", "Paddleboards", "Life Jackets", "Swim Goggles", "Snorkeling Gear"],
        "Team Sports": ["Soccer", "Basketball", "Baseball", "Volleyball", "Hockey"],
        "Outdoor Clothing": ["Jackets", "Hiking Boots", "Outdoor Pants", "Base Layers", "Gloves"],
        "Fishing": ["Fishing Rods", "Reels", "Bait & Lures", "Fishing Line", "Fishing Accessories"],
        "Men's Clothing": ["Shirts", "Trousers", "Suits", "Jackets", "T-Shirts"],
        "Women's Clothing": ["Dresses", "Tops", "Skirts", "Jeans", "Sweaters"],
        "Shoes": ["Sneakers", "Boots", "Sandals", "Formal Shoes", "Casual Shoes"],
        "Bags & Accessories": ["Handbags", "Backpacks", "Wallets", "Belts", "Hats"],
        "Jewelry": ["Necklaces", "Rings", "Earrings", "Bracelets", "Watches"],
        "Eyewear": ["Sunglasses", "Prescription Glasses", "Reading Glasses", "Sports Glasses", "Eyewear Accessories"],
        "Watches": ["Smartwatches", "Analog Watches", "Digital Watches", "Luxury Watches", "Sports Watches"],
        "Skincare": ["Cleansers", "Moisturizers", "Serums", "Masks", "Sunscreens"],
        "Haircare": ["Shampoos", "Conditioners", "Hair Treatments", "Styling Products", "Hair Accessories"],
        "Makeup": ["Foundations", "Lipsticks", "Eyeshadows", "Blushes", "Mascaras"],
        "Fragrances": ["Perfumes", "Colognes", "Body Mists", "Roll-ons", "Scented Lotions"],
        "Health Supplements": ["Vitamins", "Minerals", "Protein Supplements", "Herbal Supplements", "Omega-3"],
        "Personal Care": ["Electric Toothbrushes", "Razors", "Epilators", "Body Scales", "Massagers"],
        "Medical Supplies": ["First Aid Kits", "Thermometers", "Blood Pressure Monitors", "Glucose Meters", "Medical Masks"],
        "Action Figures": ["Superheroes", "TV & Movie Characters", "Anime Figures", "Video Game Characters", "Collectible Figures"],
        "Board Games": ["Strategy Games", "Family Games", "Card Games", "Party Games", "Classic Games"],
        "Building Sets": ["LEGO", "Mega Bloks", "K'NEX", "Wooden Blocks", "Magnetic Blocks"],
        "Dolls & Accessories": ["Fashion Dolls", "Baby Dolls", "Dollhouses", "Doll Clothing", "Doll Furniture"],
        "Educational Toys": ["STEM Toys", "Learning Games", "Science Kits", "Math Toys", "Language Toys"],
        "Outdoor Play": ["Playhouses", "Swing Sets", "Trampolines", "Ride-On Toys", "Inflatable Pools"],
        "Puzzles": ["Jigsaw Puzzles", "3D Puzzles", "Wooden Puzzles", "Brain Teasers", "Puzzle Accessories"],
        "Car Electronics": ["Car Stereos", "Car Speakers", "Car Cameras", "GPS Systems", "Car Alarms"],
        "Car Accessories": ["Seat Covers", "Floor Mats", "Car Covers", "Steering Wheel Covers", "Air Fresheners"],
        "Motorcycle Accessories": ["Helmets", "Motorcycle Gloves", "Motorcycle Jackets", "Motorcycle Boots", "Motorcycle Covers"],
        "Car Care": ["Car Wash", "Wax & Polish", "Detailing Tools", "Car Vacuum Cleaners", "Tire Care"],
        "Replacement Parts": ["Brake Pads", "Oil Filters", "Spark Plugs", "Batteries", "Headlights"],
        "Tires & Wheels": ["All-Season Tires", "Winter Tires", "Performance Tires", "Off-Road Tires", "Rims"],
        "Tools & Equipment": ["Jump Starters", "Tire Inflators", "Car Jacks", "Tool Kits", "Battery Chargers"],
        "Fiction": ["Mystery", "Romance", "Science Fiction", "Fantasy", "Thriller"],
        "Non-Fiction": ["Biographies", "Self-Help", "Cookbooks", "History", "Travel"],
        "Children's Books": ["Picture Books", "Early Learning", "Chapter Books", "Young Adult", "Educational"],
        "Audible Books": ["Fiction", "Non-Fiction", "Classics", "Podcasts", "Languages"],
        "E-Books": ["Kindle", "PDF", "ePub", "Interactive", "Free"],
        "Magazines": ["Fashion", "Technology", "Lifestyle", "Fitness", "Business"],
        "Comics & Graphic Novels": ["Superheroes", "Manga", "Science Fiction", "Fantasy", "Horror"],
        "Musical Instruments": ["Guitars", "Keyboards", "Drums", "Wind Instruments", "String Instruments"],
        "Music Accessories": ["Instrument Cases", "Music Stands", "Picks & Strings", "Tuners & Metronomes", "Amplifiers"],
        "Recording Equipment": ["Microphones", "Audio Interfaces", "Mixers", "Headphones", "Studio Monitors"],
        "DJ Equipment": ["Turntables", "Controllers", "Mixers", "Headphones", "Lighting"],
        "Sheet Music": ["Classical", "Pop", "Jazz", "Rock", "Folk"],
        "Vinyl Records": ["Classic Rock", "Jazz", "Hip-Hop", "Electronic", "Country"],
        "Digital Music": ["MP3", "Streaming", "Downloads", "Music Videos", "Playlists"],
        "Writing Instruments": ["Pens", "Pencils", "Markers", "Highlighters", "Erasers"],
        "Paper Products": ["Notebooks", "Printing Paper", "Sticky Notes", "Envelopes", "Folders"],
        "Office Electronics": ["Printers", "Scanners", "Copiers", "Calculators", "Projectors"],
        "Office Furniture": ["Desks", "Chairs", "File Cabinets", "Bookcases", "Office Lighting"],
        "Calendars & Planners": ["Wall Calendars", "Desk Calendars", "Planners", "Appointment Books", "Organizers"],
        "Mailroom Supplies": ["Packing Tape", "Shipping Labels", "Mailing Tubes", "Bubble Wrap", "Mail Sorters"],
        "Cleaning Supplies": ["Disinfectants", "Paper Towels", "Trash Bags", "Cleaning Cloths", "Air Fresheners"]
    }
};

const CustomTextField = styled(TextField)(({ theme }) => ({
    '& .MuiInputBase-root': {
        color: '#fff', // Text color
        '&.Mui-focused': {
            color: 'var(--primary)', // Text color when focused
        },
    },
    '& .MuiInputLabel-root': {
        color: '#fff', // Label color
        '&.Mui-focused': {
            color: 'var(--primary)', // Label color when focused
        },
    },
    '& .MuiOutlinedInput-root': {
        '& fieldset': {
            borderColor: '#fff', // Normal state border color
        },
        '&:hover fieldset': {
            borderColor: '#fff', // Hover state border color
        },
        '&.Mui-focused fieldset': {
            borderColor: 'var(--primary)', // Focus state border color
        },
    },
}));

const ImageContainer = styled('div')(({ theme }) => ({
    position: 'relative',
    display: 'inline-block',
    '&:hover img': {
        filter: 'blur(4px)',
    },
    '&:hover button': {
        display: 'block',
    },
}));

const DeleteButton = styled(IconButton)(({ theme }) => ({
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    color: 'red',
    display: 'none',
}));

const AddProduct = () => {
    const [product, setProduct] = useState({
        title: '',
        description: '',
        price: '',
        categoryId: '',
        categoryLevel1: '',
        categoryLevel2: '',
        categoryLevel3: '',
        productSpecifications: [{ key: '', value: '' }],
        productImages: []
    });

    const [uploading, setUploading] = useState(false);
    const [progress, setProgress] = useState(0);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct((prev) => ({ ...prev, [name]: value }));
    };

    const handleRemoveSpecification = (index) => {
        setProduct((prev) => ({
            ...prev,
            productSpecifications: prev.productSpecifications.filter((_, specIndex) => specIndex !== index),
        }));
    };

    const handleSpecificationChange = (index, event) => {
        const { name, value } = event.target;
        const newSpecifications = product.productSpecifications.map((spec, specIndex) => {
            if (index !== specIndex) return spec;
            return { ...spec, [name]: value };
        });
        setProduct((prev) => ({ ...prev, productSpecifications: newSpecifications }));
    };

    const handleRemoveImage = (index) => {
        setProduct((prev) => ({
            ...prev,
            productImages: prev.productImages.filter((_, imgIndex) => imgIndex !== index),
        }));
    };

    const handleAddSpecification = () => {
        setProduct((prev) => ({
            ...prev,
            productSpecifications: [...prev.productSpecifications, { key: '', value: '' }]
        }));
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            uploadImage(file);
        }
    };

    const uploadImage = (file) => {
        setUploading(true);
        const storageRef = ref(storage, `images/${uuidv4()}`);
        const uploadTask = uploadBytesResumable(storageRef, file);

        uploadTask.on(
            'state_changed',
            (snapshot) => {
                const progress = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                setProgress(progress);
            },
            (error) => {
                console.error(error);
            },
            () => {
                getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
                    setProduct((prev) => ({
                        ...prev,
                        productImages: [...prev.productImages, { imageUrl: downloadURL }]
                    }));
                    setUploading(false);
                    setProgress(0);
                });
            }
        );
    };

    const handleBlur = () => {
        setProduct((prev) => {
            let price = parseFloat(prev.price);
            if (isNaN(price) || price < 0) {
                price = 0;
            }
            return { ...prev, price: `${price} $` };
        });
    };

    const transformSpecifications = (specificationsArray) => {
        return specificationsArray.reduce((acc, spec) => {
            acc[spec.key] = spec.value;
            return acc;
        }, {});
    };

    const extractImageUrls = (imagesArray) => {
        return imagesArray.reduce((acc, imageObj) => {
            if (imageObj.imageUrl) {
                acc.push(imageObj.imageUrl);
            }
            return acc;
        }, []);
    };

    const extractNumericValue = (value) => {
        return parseFloat(value.replace(/[^0-9.-]+/g, ''));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const categoryId = await ApiBackendService.getProductCategoryId({}, {categoryName: product.categoryLevel3});
            product.categoryId = categoryId;
            const transformedSpecifications = transformSpecifications(product.productSpecifications);
            const productImages = extractImageUrls(product.productImages);
            const data = {
                title: product.title,
                description: product.description,
                price: extractNumericValue(product.price),
                categoryId: product.categoryId,
                productSpecifications: transformedSpecifications,
                productImages: productImages
            }
            console.log(data);
            const productId = await ApiBackendService.createProduct({}, data);
            console.log(productId);
        } catch (error) {
            console.error('Error creating new product:', error);
        }
    };
    

    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <CategoriesMenu />
            <div className="form-container">
                <Box className="form">
                    <Typography variant="h4" className="form-title">
                        Create new product
                    </Typography>
                    <Grid container spacing={2} component="form" onSubmit={handleSubmit}>
                        <Grid item xs={12}>
                            <CustomTextField
                                fullWidth
                                label="Title"
                                name="title"
                                value={product.title}
                                onChange={handleChange}
                                required
                                className="input form-field"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <CustomTextField
                                fullWidth
                                label="Description"
                                name="description"
                                value={product.description}
                                onChange={handleChange}
                                required
                                className="input form-field"
                                multiline
                                rows={4}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <Typography className="form-subtitle">
                                Product Specifications
                            </Typography>
                            {product.productSpecifications.map((spec, index) => (
                                <Grid container spacing={2} key={index} alignItems="center">
                                    <Grid item xs={5}>
                                        <CustomTextField
                                            fullWidth
                                            label="Specification"
                                            name="key"
                                            value={spec.key}
                                            onChange={(e) => handleSpecificationChange(index, e)}
                                            required
                                            className="input"
                                            style={{ paddingBottom: '10px' }}
                                        />
                                    </Grid>
                                    <Grid item xs={5}>
                                        <CustomTextField
                                            fullWidth
                                            label="Value"
                                            name="value"
                                            value={spec.value}
                                            onChange={(e) => handleSpecificationChange(index, e)}
                                            required
                                            className="input"
                                            style={{ paddingBottom: '10px' }}
                                        />
                                    </Grid>
                                    <Grid item xs={2}>
                                        <IconButton onClick={() => handleRemoveSpecification(index)} style={{ color: 'red' }}>
                                            <DeleteIcon />
                                        </IconButton>
                                    </Grid>
                                </Grid>
                            ))}
                            <Button
                                variant="contained"
                                onClick={handleAddSpecification}
                                className="button"
                                style={{ marginBottom: '10px' }}
                            >
                                Add Product Specification
                            </Button>
                        </Grid>
                        <Grid item xs={12}>
                            <CustomTextField
                                fullWidth
                                label="Value Reference"
                                name="price"
                                type="text"
                                value={product.price}
                                onChange={handleChange}
                                onBlur={handleBlur}
                                required
                                className="input form-field"
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <CustomTextField
                                select
                                required
                                label="Main Category"
                                name="categoryLevel1"
                                value={product.categoryLevel1}
                                onChange={handleChange}
                                variant="outlined"
                                fullWidth
                                className="input form-field"
                            >
                                {categories.level1.map((cat) => (
                                    <MenuItem key={cat} value={cat}>
                                        {cat}
                                    </MenuItem>
                                ))}
                            </CustomTextField>
                        </Grid>
                        {product.categoryLevel1 && (
                            <Grid item xs={12}>
                                <CustomTextField
                                    select
                                    required
                                    label="Subcategory"
                                    name="categoryLevel2"
                                    value={product.categoryLevel2}
                                    onChange={handleChange}
                                    variant="outlined"
                                    fullWidth
                                    className="input form-field"
                                >
                                    {categories.level2[product.categoryLevel1].map((cat) => (
                                        <MenuItem key={cat} value={cat}>
                                            {cat}
                                        </MenuItem>
                                    ))}
                                </CustomTextField>
                            </Grid>
                        )}
                        {product.categoryLevel2 && (
                            <Grid item xs={12}>
                                <CustomTextField
                                    select
                                    required
                                    label="Specific Category"
                                    name="categoryLevel3"
                                    value={product.categoryLevel3}
                                    onChange={handleChange}
                                    variant="outlined"
                                    fullWidth
                                    className="input form-field"
                                >
                                    {categories.level3[product.categoryLevel2].map((cat) => (
                                        <MenuItem key={cat} value={cat}>
                                            {cat}
                                        </MenuItem>
                                    ))}
                                </CustomTextField>
                            </Grid>
                        )}
                        {product.productImages.length > 0 && (
                            <Grid item xs={12}>
                            <div style={{ display: 'flex', flexWrap: 'wrap', gap: '10px' }}>
                                {product.productImages.map((img, index) => (
                                    <ImageContainer key={index}>
                                        <img src={img.imageUrl} alt={`Product ${index}`} width="300" />
                                        <DeleteButton onClick={() => handleRemoveImage(index)}>
                                            <DeleteIcon />
                                        </DeleteButton>
                                    </ImageContainer>
                                ))}
                            </div>
                        </Grid>
                        )}
                        <Grid item xs={12}>
                            <Button
                                variant="contained"
                                component="label"
                                className="button"
                            >
                                Add New Image
                                <input
                                    type="file"
                                    hidden
                                    onChange={handleFileChange}
                                />
                            </Button>
                            {uploading && <p>Uploading... {Math.round(progress)}%</p>}
                        </Grid>
                        <Grid item xs={12}>
                            <Box display="flex" justifyContent="center">
                                <Button type="submit" variant="contained" className="button">
                                    Create Product
                                </Button>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>
            </div>
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>
    );
};

export default AddProduct;