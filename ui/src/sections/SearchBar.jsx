import React, { useState, useEffect } from 'react';
import '../css/SearchBar.css';

const SearchBar = ({ searchQuery: initialSearchQuery }) => {
    const [searchQuery, setSearchQuery] = useState(initialSearchQuery || '');

    useEffect(() => {
        setSearchQuery(initialSearchQuery || '');
    }, [initialSearchQuery]);

    const handleSearchQueryChange = (e) => {
        setSearchQuery(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        window.location.href = `/search/query/${searchQuery}`;
    };

    return (
        <div className="wrap">
            <form className="search" onSubmit={handleSearchSubmit}>
                <input type="text" className="searchTerm" placeholder="Search" value={searchQuery} onChange={handleSearchQueryChange} />
                <button type="submit" className="searchButton">
                    <i className="fa fa-search"></i>
                </button>
            </form>
        </div>
    );
};

export default SearchBar;
