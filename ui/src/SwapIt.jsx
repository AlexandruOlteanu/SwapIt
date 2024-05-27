import React, { lazy, useEffect, useState, startTransition, Suspense } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import AuthRedirectHandler from './auth/AuthRedirectHandler';
import './css/SwapIt.css';
import ProductPage from './product/ProductPage';
import Common from './Common';
import SearchProducts from './search/SearchProducts';
import CategorySearch from './category/CategorySearch';
import Error from './Error';

const Home = lazy(() => import('./Home'));
const Authentication = lazy(() => import('./auth/Authentication'));
const UserProfile = lazy(() => import('./user/UserProfile'));
const AddProduct = lazy(() => import('./product/AddProduct'));
const UpdateProduct = lazy(() => import('./product/UpdateProduct'));
const Contact = lazy(() => import('./Contact'));

function SwapIt() {
    const [isAdmin, setIsAdmin] = useState(false);

    useEffect(() => {
        // Simulate fetching user role and determining if the user is an admin
        const user = { role: 'admin' }; // Example user object
        startTransition(() => {
            setIsAdmin(Common.isUserAdmin(user));
        });
    }, []);

    return (
        <Router>
            <div className="SwapIt">
                {isAdmin && (
                    <div className="admin-inform" style={{ backgroundColor: '#e2e3e5', color: '#dc3545', padding: '10px', textAlign: 'center', fontWeight: 'bold', border: '1px solid #dc3545' }}>
                        <i className="fa-solid fa-triangle-exclamation" style={{ marginRight: '5px' }}></i>
                        Notice: You have administrator privileges. Please exercise caution in all actions taken on the platform.
                    </div>
                )}
                <Suspense fallback={<div className='fallback'></div>}>
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/user/authHandler" element={<AuthRedirectHandler />} />
                        <Route path="/user/auth" element={<Authentication />} />
                        <Route path="/users/:username" element={<UserProfile />} />
                        <Route path="/product/create" element={<AddProduct />} />
                        <Route path="/product/update/:title/:productId" element={<UpdateProduct />} />
                        <Route path='/product/:title/:productId' element={<ProductPage />} />
                        <Route path="/search/query/:query" element={<SearchProducts />} />
                        <Route path="/search/category/:categoryName" element={<CategorySearch />} />
                        <Route path="/contact" element={<Contact />} />
                        <Route path="/error" element={<Error />} />
                    </Routes>
                </Suspense>
            </div>
        </Router>
    );
}

export default SwapIt;
