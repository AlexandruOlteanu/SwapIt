import React, { useState, useEffect } from 'react';
import { Grid, Card, CardContent, CardMedia, Typography, Pagination, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { makeStyles } from '@mui/styles';
import { useNavigate } from 'react-router-dom';
import ApiBackendService from '../apiBackend/ApiBackendService';

const useStyles = makeStyles({
    root: {
        flexGrow: 1,
        padding: '20px',
        display: 'flex',
        backgroundColor: '#1C1E32', // Background color for the entire page
        color: 'white', // Ensure text color is white for readability
    },
    filter: {
        display: 'flex',
        justifyContent: 'end',
        width: '45%',
        paddingTop: '10px',
        paddingLeft: '32px',
        color: 'white',
    },
    productContainer: {
        width: '100%',
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
    noResults: {
        display: 'flex',
        color: 'white',
        alignItems: 'center',
        flexDirection: 'column',
        marginTop: '50px !important',
        width: '100%',
    },
});

const UserProducts = ({ userId, surname, columnItems, totalItems, isUserProfileAuth }) => {
    const classes = useStyles();
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [filter, setFilter] = useState('popularity');
    const [isProductListEmpty, setIsProductListEmpty] = useState(false);
    const itemsPerPage = totalItems; // Fixed value of items per page

    useEffect(() => {
        const fetchProducts = async (page, filter) => {
            try {
                const response = await ApiBackendService.getProductsByUser({
                    userId: userId,
                    sortCriteria: filter.toUpperCase(),
                    chunkNumber: page - 1,
                    nrElementsPerChunk: itemsPerPage,
                });
                setIsProductListEmpty(response.products.length === 0);
                setProducts(response.products);
                setTotalPages(response.totalPages);
            } catch (error) {
                console.log('Error fetching recommended products!');
            }
        };
        if (userId !== -1) {
            fetchProducts(page, filter);
        }
    }, [page, filter, userId, isUserProfileAuth]);



    const handlePageChange = (event, value) => {
        setPage(value);
    };

    const handleProductClick = (title, productId) => {
        const encodedTitle = encodeURIComponent(title.split(' ').join('-'));
        navigate(`/product/${encodedTitle}/${productId}`);
    };

    return (
        <React.Fragment>
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
                            </Select>
                        </FormControl>
                    </div>
                    <div className={classes.root}>
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
                                    <Grid item xs={12} sm={columnItems} key={product.productId} className={classes.gridItem}>
                                        <Card
                                            className={classes.card}
                                            style={{ backgroundColor: '#2B2E4A', boxShadow: '0 2px 8px var(--primary)' }}
                                        >
                                            <CardMedia
                                                className={classes.media}
                                                image={product.productImages[0]?.imageUrl}
                                                title={product.title}
                                                onClick={() => handleProductClick(product.title, product.productId)}
                                                style={{ cursor: 'pointer' }}
                                            />
                                            <CardContent className={classes.cardContent}>
                                                <Typography className={classes.title} gutterBottom variant="h6" component="h4"
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
                    </div>
                </>
            )}
            {(isProductListEmpty && isUserProfileAuth) && (
                <Typography variant="h6" className={classes.noResults}>
                    You haven't added any products yet!
                </Typography>
            )}
            {(isProductListEmpty && !isUserProfileAuth) && (
                < Typography variant="h6" className={classes.noResults}>
                    {surname} doesn't have any added products yet!
                </Typography>
            )}

        </React.Fragment >

    );
};

export default UserProducts;
