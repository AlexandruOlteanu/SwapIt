import React, { useState, useEffect } from 'react';
import { Grid, Card, CardContent, Typography, Pagination, FormControl, InputLabel, Select, MenuItem, Avatar, CardMedia } from '@mui/material';
import { makeStyles } from '@mui/styles';
import { useNavigate } from 'react-router-dom';
import ApiBackendService from '../apiBackend/ApiBackendService';

const useStyles = makeStyles({
    root: {
        flexGrow: 1,
        padding: '20px',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        backgroundColor: '#1C1E32', // Background color for the entire page
        color: 'white', // Ensure text color is white for readability
    },
    filter: {
        display: 'flex',
        justifyContent: 'end',
        width: '40%',
        paddingTop: '10px',
        paddingLeft: '32px',
        color: 'white',
    },
    actionsContainer: {
        width: '100%',
    },
    gridItem: {
        display: 'flex',
    },
    card: {
        width: '100%',
        display: 'flex',
        justifyContent: 'space-between',
        backgroundColor: 'var(--dark)', // Background color for the action cards
        color: 'white', // Ensure text color is white for readability
        boxShadow: '0 4px 8px 0 rgba(255, 0, 0, 0.2), 0 6px 20px 0 rgba(255, 0, 0, 0.19)', // Red shadow for admin actions
        marginBottom: '20px',
        padding: '16px', // Added padding for better spacing
    },
    adminCard: {
        backgroundColor: '#641818', // Background color for admin action cards
    },
    cardContent: {
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        flexGrow: 1,
    },
    userAddProductContent: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexGrow: 1,
    },
    textContent: {
        display: 'flex',
        flexDirection: 'column',
        flexGrow: 1,
    },
    mediaContainer: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        width: '100px',
        height: '100px',
        marginLeft: '16px',
        backgroundColor: '#ffffff', // Optional: Add a background color to make sure the container is visible
    },
    media: {
        maxWidth: '100%',
        maxHeight: '100%',
        objectFit: 'contain', // Ensure the image is fully visible within the container
    },
    avatar: {
        width: 60,
        height: 60,
        marginRight: 10,
    },
    title: {
        display: '-webkit-box',
        WebkitBoxOrient: 'vertical',
        overflow: 'hidden',
        textOverflow: 'ellipsis',
        WebkitLineClamp: 3,
        lineClamp: 3,
    },
    dateText: {
        color: 'white',
        marginTop: '10px',
        textAlign: 'left',
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

const WebsiteActionsLog = ({ columnItems, totalItems }) => {
    const classes = useStyles();
    const navigate = useNavigate();
    const [actions, setActions] = useState([]);
    const [page, setPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [filter, setFilter] = useState('include_all');
    const [isActionsListEmpty, setIsActionsListEmpty] = useState(false);
    const [userDetails, setUserDetails] = useState({});
    const [productDetails, setProductDetails] = useState({});
    const itemsPerPage = totalItems; // Fixed value of items per page

    useEffect(() => {
        const fetchWebsiteActions = async (page, filter) => {
            try {
                const response = await ApiBackendService.getUserActions({
                    sortCriteria: filter.toUpperCase(),
                    chunkNumber: page - 1,
                    nrElementsPerChunk: itemsPerPage,
                });
                setIsActionsListEmpty(response.userActions.length === 0);
                setActions(response.userActions);
                setTotalPages(response.totalPages);
            } catch (error) {
                console.log('Error fetching website actions  ');
            }
        };
        fetchWebsiteActions(page, filter);
    }, [page, filter]);

    useEffect(() => {
        actions.forEach((action) => {
            if (!userDetails[action.userId]) {
                fetchUserDetails(action.userId);
            }
            if (action.actionType === 'ADMIN_BAN_USER' && !userDetails[action.actionDetails?.bannedUserId]) {
                fetchUserDetails(action.actionDetails?.bannedUserId);
            }
            if (action.actionType === 'ADMIN_REMOVE_USER_BAN' && !userDetails[action.actionDetails?.unbannedUserId]) {
                fetchUserDetails(action.actionDetails?.unbannedUserId);
            }
            if (action.actionType === 'USER_ADD_PRODUCT' && !productDetails[action.actionDetails?.productId]) {
                fetchProductDetails(action.actionDetails?.productId);
            }
        });
    }, [actions, userDetails, productDetails]);

    const fetchUserDetails = async (userId) => {
        try {
            const response = await ApiBackendService.getUserDetails({ userId });
            setUserDetails((prevDetails) => ({
                ...prevDetails,
                [userId]: response,
            }));
        } catch (error) {
            console.log('Error fetching user details', error);
        }
    };

    const fetchProductDetails = async (productId) => {
        try {
            const response = await ApiBackendService.getProductById({ productId });
            if (response && response.productImages.length !== 0) {
                setProductDetails((prevDetails) => ({
                    ...prevDetails,
                    [productId]: response,
                }));
            } else {
                setProductDetails((prevDetails) => ({
                    ...prevDetails,
                    [productId]: { notFound: true },
                }));
            }
        } catch (error) {
            console.log('Error fetching product details', error);
            setProductDetails((prevDetails) => ({
                ...prevDetails,
                [productId]: { notFound: true },
            }));
        }
    };

    const handlePageChange = (event, value) => {
        setPage(value);
    };

    const handleUserClick = (userId) => {
        navigate(`/users/${userDetails[userId]?.username}`);
    };

    const handleProductClick = (productId, productTitle) => {
        const encodedTitle = encodeURIComponent(productTitle.split(' ').join('-'));
        navigate(`/product/${encodedTitle}/${productId}`);
    };

    const formatDateTime = (dateTimeString) => {
        const options = { day: 'numeric', month: 'short', hour: '2-digit', minute: '2-digit' };
        const date = new Date(dateTimeString);
        return date.toLocaleDateString(undefined, options);
    };

    return (
        <React.Fragment>
            {!isActionsListEmpty && (
                <>
                    <div className={classes.filter}>
                        <FormControl fullWidth variant="outlined" className={classes.formControl}>
                            <InputLabel style={{ color: 'var(--primary)' }}>Filter By</InputLabel>
                            <Select
                                value={filter}
                                onChange={(e) => setFilter(e.target.value)}
                                label="Filter By"
                            >
                                <MenuItem value="include_all">Include All</MenuItem>
                                <MenuItem value="only_user_actions">Users Actions</MenuItem>
                                <MenuItem value="only_admin_actions">Admins Actions</MenuItem>
                            </Select>
                        </FormControl>
                    </div>
                    <div className={classes.root}>
                        <div className={classes.actionsContainer}>
                            <Pagination
                                className={classes.pagination}
                                count={totalPages}
                                page={page}
                                onChange={handlePageChange}
                                variant="outlined"
                                shape="rounded"
                            />
                            <Grid container spacing={4}>
                                {actions.map(action => (
                                    <Grid item xs={12} sm={columnItems} key={action.actionId} className={classes.gridItem}>
                                        <Card
                                            className={`${classes.card} ${action.actionType.startsWith('ADMIN') ? classes.adminCard : ''}`}
                                            style={{ backgroundColor: action.actionType.startsWith('ADMIN') ? '#641818' : 'var(--secondary)', boxShadow: '0 2px 8px var(--primary)' }}
                                            onClick={() => action.actionType === 'USER_REGISTER' && handleUserClick(action.userId)}
                                        >
                                            {action.actionType === 'USER_REGISTER' && userDetails[action.userId] && (
                                                <CardContent className={classes.cardContent}>
                                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                                        <Avatar className={classes.avatar} alt="User Avatar" src={userDetails[action.userId].userImage} />
                                                        <Typography variant="h6" style={{ color: 'white', cursor: 'pointer' }} onClick={() => handleUserClick(action.userId)}>
                                                            @{userDetails[action.userId].username} has registered
                                                        </Typography>
                                                    </div>
                                                    <Typography variant="body2" style={{ color: 'white', marginTop: '10px' }}>
                                                        {formatDateTime(action.createdAt)}
                                                    </Typography>
                                                </CardContent>
                                            )}
                                            {action.actionType === 'USER_ADD_PRODUCT' && userDetails[action.userId] && (
                                                <CardContent className={classes.userAddProductContent}>
                                                    <div className={classes.textContent}>
                                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                                            <Avatar
                                                                className={classes.avatar}
                                                                alt="User Avatar"
                                                                src={userDetails[action.userId].userImage}
                                                                onClick={() => handleUserClick(action.userId)}
                                                                style={{ cursor: 'pointer' }}
                                                            />
                                                            <Typography
                                                                variant="h6"
                                                                style={{ color: 'white', cursor: 'pointer' }}
                                                                onClick={() => handleUserClick(action.userId)}
                                                            >
                                                                @{userDetails[action.userId].username} added a new product
                                                            </Typography>
                                                        </div>
                                                        <Typography
                                                            variant="body2"
                                                            style={{ color: 'white', marginTop: '10px', cursor: 'default' }}
                                                        >
                                                            Product Title: {action.actionDetails.productTitle}
                                                        </Typography>
                                                        <Typography variant="body2" className={classes.dateText}
                                                            style={{ color: 'white', marginTop: '10px', cursor: 'default' }}
                                                        >
                                                            {formatDateTime(action.createdAt)}
                                                        </Typography>
                                                    </div>
                                                    {!productDetails[action.actionDetails.productId]?.notFound && productDetails[action.actionDetails.productId] && (
                                                        <div className={classes.mediaContainer}>
                                                            <CardMedia
                                                                component="img"
                                                                alt={action.actionDetails.productTitle}
                                                                className={classes.media}
                                                                image={productDetails[action.actionDetails.productId].productImages[0].imageUrl}
                                                                title={action.actionDetails.productTitle}
                                                                onClick={() => handleProductClick(action.actionDetails.productId, action.actionDetails.productTitle)}
                                                                style={{ cursor: 'pointer' }}
                                                            />
                                                        </div>
                                                    )}
                                                </CardContent>
                                            )}
                                            {action.actionType === 'ADMIN_BAN_USER' && userDetails[action.userId] && userDetails[action.actionDetails.bannedUserId] && (
                                                <CardContent className={classes.cardContent}>
                                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                                        <Avatar
                                                            className={classes.avatar}
                                                            alt="User Avatar"
                                                            src={userDetails[action.userId].userImage}
                                                            onClick={() => handleUserClick(action.userId)}
                                                            style={{ cursor: 'pointer' }}
                                                        />
                                                        <Typography variant="h6" style={{ color: 'white' }}>
                                                            <Typography
                                                                variant="h6"
                                                                component="span"
                                                                style={{ color: 'white', cursor: 'pointer' }}
                                                                onClick={() => handleUserClick(action.userId)}
                                                            >
                                                                @{userDetails[action.userId].username}
                                                            </Typography>{' '}
                                                            banned user{' '}
                                                            <Typography
                                                                variant="h6"
                                                                component="span"
                                                                style={{ color: 'white', cursor: 'pointer' }}
                                                                onClick={() => handleUserClick(action.actionDetails.bannedUserId)}
                                                            >
                                                                @{userDetails[action.actionDetails.bannedUserId]?.username}
                                                            </Typography>
                                                        </Typography>
                                                    </div>
                                                    <Typography variant="body2" style={{ color: 'white', marginTop: '10px' }}>
                                                        Ban Duration: {action.actionDetails.banDurationDays ? `${action.actionDetails.banDurationDays} days` : 'Permanent'}
                                                    </Typography>
                                                    <Typography variant="body2" className={classes.dateText} style={{ marginTop: '10px' }}>
                                                        {formatDateTime(action.createdAt)}
                                                    </Typography>
                                                </CardContent>
                                            )}

                                            {action.actionType === 'ADMIN_REMOVE_USER_BAN' && userDetails[action.userId] && userDetails[action.actionDetails.unbannedUserId] && (
                                                // <CardContent className={classes.cardContent}>
                                                //     <Typography variant="h6" style={{ color: 'white' }}>
                                                //         Admin {action.userId} removed ban from user {action.actionDetails.unbannedUserId}
                                                //     </Typography>
                                                //     <Typography variant="body2" className={classes.dateText}>
                                                //         {formatDateTime(action.createdAt)}
                                                //     </Typography>
                                                // </CardContent>
                                                <CardContent className={classes.cardContent}>
                                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                                        <Avatar
                                                            className={classes.avatar}
                                                            alt="User Avatar"
                                                            src={userDetails[action.userId].userImage}
                                                            onClick={() => handleUserClick(action.userId)}
                                                            style={{ cursor: 'pointer' }}
                                                        />
                                                        <Typography variant="h6" style={{ color: 'white' }}>
                                                            <Typography
                                                                variant="h6"
                                                                component="span"
                                                                style={{ color: 'white', cursor: 'pointer' }}
                                                                onClick={() => handleUserClick(action.userId)}
                                                            >
                                                                @{userDetails[action.userId].username}
                                                            </Typography>{' '}
                                                            removed ban from user{' '}
                                                            <Typography
                                                                variant="h6"
                                                                component="span"
                                                                style={{ color: 'white', cursor: 'pointer' }}
                                                                onClick={() => handleUserClick(action.actionDetails.unbannedUserId)}
                                                            >
                                                                @{userDetails[action.actionDetails.unbannedUserId]?.username}
                                                            </Typography>
                                                        </Typography>
                                                    </div>
                                                    <Typography variant="body2" className={classes.dateText} style={{ marginTop: '10px' }}>
                                                        {formatDateTime(action.createdAt)}
                                                    </Typography>
                                                </CardContent>
                                            )}
                                            {action.actionType === 'ADMIN_DELETE_PRODUCT' && userDetails[action.userId] && (
                                                <CardContent className={classes.cardContent} onClick={() => handleUserClick(action.userId)}
                                                    style={{ cursor: 'pointer' }}
                                                >
                                                    <div style={{ display: 'flex', alignItems: 'center' }} >
                                                        <Avatar className={classes.avatar} alt="User Avatar" src={userDetails[action.userId].userImage} />
                                                        <Typography variant="h6" style={{ color: 'white' }} >
                                                            @{userDetails[action.userId].username} deleted product {action.actionDetails.productTitle}
                                                        </Typography>
                                                    </div>
                                                    <Typography variant="body2" style={{ color: 'white', marginTop: '10px' }}>
                                                        {formatDateTime(action.createdAt)}
                                                    </Typography>
                                                </CardContent>
                                            )}
                                        </Card>
                                    </Grid>
                                ))}
                            </Grid>
                            <Pagination
                                className={classes.pagination}
                                count={totalPages}
                                page={page}
                                onChange={handlePageChange}
                                variant="outlined"
                                shape="rounded"
                            />
                        </div>
                    </div>
                </>
            )}
            {isActionsListEmpty && (
                <Typography variant="h6" className={classes.noResults}>
                    No actions available.
                </Typography>
            )}
        </React.Fragment>
    );
};

export default WebsiteActionsLog;
