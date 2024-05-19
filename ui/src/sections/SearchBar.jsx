import React, { useState } from 'react';
import '../css/SearchBar.css';

const SearchBar = () => {

    const [searchQuery, setSearchQuery] = useState('');

    const handleSearchQueryChange = (e) => {
        setSearchQuery(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        console.log(searchQuery);
    };

    return (
        <div className="wrap">
            <form className="search" onSubmit={handleSearchSubmit}>
                <input type="text" className="searchTerm" placeholder="Search" onChange={handleSearchQueryChange} />
                <button type="submit" className="searchButton">
                    <i className="fa fa-search"></i>
                </button>
            </form>
        </div>
    );
};

export default SearchBar;
