import React, { useState } from 'react';
import '../css/CategoriesMenu.css'; // Import the CSS file for styling

const CategoriesMenu = () => {
    const [showCategories, setShowCategories] = useState(false);
    const [activeCategory, setActiveCategory] = useState(null);

    const categories = [
        {
            name: "Electronics & Appliances",
            subcategories: [
                {
                    name: "Washing Machines",
                    subcategories: ["Front Load", "Top Load", "Washer Dryer", "Compact", "Portable"]
                },
                {
                    name: "Kitchen Appliances",
                    subcategories: ["Microwaves", "Ovens", "Dishwashers", "Refrigerators", "Blenders"]
                },
                {
                    name: "Air Conditioners",
                    subcategories: ["Window AC", "Split AC", "Portable AC", "Inverter AC", "Smart AC"]
                },
                {
                    name: "Home Audio",
                    subcategories: ["Soundbars", "Home Theater Systems", "Speakers", "Receivers", "Subwoofers"]
                },
                {
                    name: "TV & Video",
                    subcategories: ["Smart TVs", "4K TVs", "LED TVs", "OLED TVs", "Projectors"]
                },
                {
                    name: "Individual Care",
                    subcategories: ["Hair Dryers", "Electric Shavers", "Epilators", "Electric Toothbrushes", "Massagers"]
                },
                {
                    name: "Vacuum Cleaners",
                    subcategories: ["Robot Vacuums", "Upright Vacuums", "Stick Vacuums", "Handheld Vacuums", "Canister Vacuums"]
                }
            ]
        },
        {
            name: "Computers & Accessories",
            subcategories: [
                {
                    name: "Laptops",
                    subcategories: ["Gaming Laptops", "Business Laptops", "2-in-1 Laptops", "Ultrabooks", "Chromebooks"]
                },
                {
                    name: "Desktops",
                    subcategories: ["Gaming Desktops", "All-in-One Desktops", "Mini PCs", "Workstations", "Tower Desktops"]
                },
                {
                    name: "Monitors",
                    subcategories: ["Gaming Monitors", "4K Monitors", "Curved Monitors", "Ultrawide Monitors", "Office Monitors"]
                },
                {
                    name: "Computer Accessories",
                    subcategories: ["Keyboards", "Mice", "Webcams", "Headsets", "Docking Stations"]
                },
                {
                    name: "Storage Devices",
                    subcategories: ["External Hard Drives", "SSD Drives", "USB Flash Drives", "Network Storage", "Memory Cards"]
                },
                {
                    name: "Networking",
                    subcategories: ["Routers", "Modems", "Range Extenders", "Switches", "Access Points"]
                },
                {
                    name: "Software",
                    subcategories: ["Operating Systems", "Office Suites", "Security Software", "Design Software", "Development Software"]
                }
            ]
        },
        {
            name: "Home & Garden",
            subcategories: [
                {
                    name: "Furniture",
                    subcategories: ["Sofas", "Beds", "Dining Tables", "Wardrobes", "Office Chairs"]
                },
                {
                    name: "Garden Tools",
                    subcategories: ["Lawn Mowers", "Hedge Trimmers", "Leaf Blowers", "Garden Hoses", "Garden Shears"]
                },
                {
                    name: "Lighting",
                    subcategories: ["LED Bulbs", "Table Lamps", "Floor Lamps", "Ceiling Lights", "Outdoor Lighting"]
                },
                {
                    name: "Decor",
                    subcategories: ["Wall Art", "Mirrors", "Vases", "Clocks", "Rugs"]
                },
                {
                    name: "Kitchenware",
                    subcategories: ["Cookware", "Cutlery", "Glassware", "Dinnerware", "Storage Containers"]
                },
                {
                    name: "Bedding",
                    subcategories: ["Bedsheets", "Pillows", "Comforters", "Mattress Protectors", "Blankets"]
                },
                {
                    name: "DIY & Tools",
                    subcategories: ["Power Tools", "Hand Tools", "Tool Storage", "Ladders", "Safety Equipment"]
                }
            ]
        },
        {
            name: "Sports & Outdoors",
            subcategories: [
                {
                    name: "Fitness Equipment",
                    subcategories: ["Treadmills", "Exercise Bikes", "Dumbbells", "Yoga Mats", "Resistance Bands"]
                },
                {
                    name: "Camping & Hiking",
                    subcategories: ["Tents", "Sleeping Bags", "Backpacks", "Camping Furniture", "Camping Cookware"]
                },
                {
                    name: "Cycling",
                    subcategories: ["Mountain Bikes", "Road Bikes", "Electric Bikes", "Bike Helmets", "Bike Accessories"]
                },
                {
                    name: "Water Sports",
                    subcategories: ["Kayaks", "Paddleboards", "Life Jackets", "Swim Goggles", "Snorkeling Gear"]
                },
                {
                    name: "Team Sports",
                    subcategories: ["Soccer", "Basketball", "Baseball", "Volleyball", "Hockey"]
                },
                {
                    name: "Outdoor Clothing",
                    subcategories: ["Jackets", "Hiking Boots", "Outdoor Pants", "Base Layers", "Gloves"]
                },
                {
                    name: "Fishing",
                    subcategories: ["Fishing Rods", "Reels", "Bait & Lures", "Fishing Line", "Fishing Accessories"]
                }
            ]
        },
        {
            name: "Fashion & Accessories",
            subcategories: [
                {
                    name: "Men's Clothing",
                    subcategories: ["Shirts", "Trousers", "Suits", "Jackets", "T-Shirts"]
                },
                {
                    name: "Women's Clothing",
                    subcategories: ["Dresses", "Tops", "Skirts", "Jeans", "Sweaters"]
                },
                {
                    name: "Shoes",
                    subcategories: ["Sneakers", "Boots", "Sandals", "Formal Shoes", "Casual Shoes"]
                },
                {
                    name: "Bags & Accessories",
                    subcategories: ["Handbags", "Backpacks", "Wallets", "Belts", "Hats"]
                },
                {
                    name: "Jewelry",
                    subcategories: ["Necklaces", "Rings", "Earrings", "Bracelets", "Watches"]
                },
                {
                    name: "Eyewear",
                    subcategories: ["Sunglasses", "Prescription Glasses", "Reading Glasses", "Sports Glasses", "Eyewear Accessories"]
                },
                {
                    name: "Watches",
                    subcategories: ["Smartwatches", "Analog Watches", "Digital Watches", "Luxury Watches", "Sports Watches"]
                }
            ]
        },
        {
            name: "Health & Beauty",
            subcategories: [
                {
                    name: "Skincare",
                    subcategories: ["Cleansers", "Moisturizers", "Serums", "Masks", "Sunscreens"]
                },
                {
                    name: "Haircare",
                    subcategories: ["Shampoos", "Conditioners", "Hair Treatments", "Styling Products", "Hair Accessories"]
                },
                {
                    name: "Makeup",
                    subcategories: ["Foundations", "Lipsticks", "Eyeshadows", "Blushes", "Mascaras"]
                },
                {
                    name: "Fragrances",
                    subcategories: ["Perfumes", "Colognes", "Body Mists", "Roll-ons", "Scented Lotions"]
                },
                {
                    name: "Health Supplements",
                    subcategories: ["Vitamins", "Minerals", "Protein Supplements", "Herbal Supplements", "Omega-3"]
                },
                {
                    name: "Personal Care",
                    subcategories: ["Electric Toothbrushes", "Razors", "Epilators", "Body Scales", "Massagers"]
                },
                {
                    name: "Medical Supplies",
                    subcategories: ["First Aid Kits", "Thermometers", "Blood Pressure Monitors", "Glucose Meters", "Medical Masks"]
                }
            ]
        },
        {
            name: "Toys & Games",
            subcategories: [
                {
                    name: "Action Figures",
                    subcategories: ["Superheroes", "TV & Movie Characters", "Anime Figures", "Video Game Characters", "Collectible Figures"]
                },
                {
                    name: "Board Games",
                    subcategories: ["Strategy Games", "Family Games", "Card Games", "Party Games", "Classic Games"]
                },
                {
                    name: "Building Sets",
                    subcategories: ["LEGO", "Mega Bloks", "K'NEX", "Wooden Blocks", "Magnetic Blocks"]
                },
                {
                    name: "Dolls & Accessories",
                    subcategories: ["Fashion Dolls", "Baby Dolls", "Dollhouses", "Doll Clothing", "Doll Furniture"]
                },
                {
                    name: "Educational Toys",
                    subcategories: ["STEM Toys", "Learning Games", "Science Kits", "Math Toys", "Language Toys"]
                },
                {
                    name: "Outdoor Play",
                    subcategories: ["Playhouses", "Swing Sets", "Trampolines", "Ride-On Toys", "Inflatable Pools"]
                },
                {
                    name: "Puzzles",
                    subcategories: ["Jigsaw Puzzles", "3D Puzzles", "Wooden Puzzles", "Brain Teasers", "Puzzle Accessories"]
                }
            ]
        },
        {
            name: "Automotive",
            subcategories: [
                {
                    name: "Car Electronics",
                    subcategories: ["Car Stereos", "Car Speakers", "Car Cameras", "GPS Systems", "Car Alarms"]
                },
                {
                    name: "Car Accessories",
                    subcategories: ["Seat Covers", "Floor Mats", "Car Covers", "Steering Wheel Covers", "Air Fresheners"]
                },
                {
                    name: "Motorcycle Accessories",
                    subcategories: ["Helmets", "Motorcycle Gloves", "Motorcycle Jackets", "Motorcycle Boots", "Motorcycle Covers"]
                },
                {
                    name: "Car Care",
                    subcategories: ["Car Wash", "Wax & Polish", "Detailing Tools", "Car Vacuum Cleaners", "Tire Care"]
                },
                {
                    name: "Replacement Parts",
                    subcategories: ["Brake Pads", "Oil Filters", "Spark Plugs", "Batteries", "Headlights"]
                },
                {
                    name: "Tires & Wheels",
                    subcategories: ["All-Season Tires", "Winter Tires", "Performance Tires", "Off-Road Tires", "Rims"]
                },
                {
                    name: "Tools & Equipment",
                    subcategories: ["Jump Starters", "Tire Inflators", "Car Jacks", "Tool Kits", "Battery Chargers"]
                }
            ]
        },
        {
            name: "Books & Audible",
            subcategories: [
                {
                    name: "Fiction",
                    subcategories: ["Mystery", "Romance", "Science Fiction", "Fantasy", "Thriller"]
                },
                {
                    name: "Non-Fiction",
                    subcategories: ["Biographies", "Self-Help", "Cookbooks", "History", "Travel"]
                },
                {
                    name: "Children's Books",
                    subcategories: ["Picture Books", "Early Learning", "Chapter Books", "Young Adult", "Educational"]
                },
                {
                    name: "Audible Books",
                    subcategories: ["Fiction", "Non-Fiction", "Classics", "Podcasts", "Languages"]
                },
                {
                    name: "E-Books",
                    subcategories: ["Kindle", "PDF", "ePub", "Interactive", "Free"]
                },
                {
                    name: "Magazines",
                    subcategories: ["Fashion", "Technology", "Lifestyle", "Fitness", "Business"]
                },
                {
                    name: "Comics & Graphic Novels",
                    subcategories: ["Superheroes", "Manga", "Science Fiction", "Fantasy", "Horror"]
                }
            ]
        },
        {
            name: "Music & Instruments",
            subcategories: [
                {
                    name: "Musical Instruments",
                    subcategories: ["Guitars", "Keyboards", "Drums", "Wind Instruments", "String Instruments"]
                },
                {
                    name: "Music Accessories",
                    subcategories: ["Instrument Cases", "Music Stands", "Picks & Strings", "Tuners & Metronomes", "Amplifiers"]
                },
                {
                    name: "Recording Equipment",
                    subcategories: ["Microphones", "Audio Interfaces", "Mixers", "Headphones", "Studio Monitors"]
                },
                {
                    name: "DJ Equipment",
                    subcategories: ["Turntables", "Controllers", "Mixers", "Headphones", "Lighting"]
                },
                {
                    name: "Sheet Music",
                    subcategories: ["Classical", "Pop", "Jazz", "Rock", "Folk"]
                },
                {
                    name: "Vinyl Records",
                    subcategories: ["Classic Rock", "Jazz", "Hip-Hop", "Electronic", "Country"]
                },
                {
                    name: "Digital Music",
                    subcategories: ["MP3", "Streaming", "Downloads", "Music Videos", "Playlists"]
                }
            ]
        }
    ];

    const icons = [
        "fa-solid fa-tv",
        "fa-solid fa-laptop",
        "fa-solid fa-home",
        "fa-solid fa-basketball-ball",
        "fa-solid fa-tshirt",
        "fa-solid fa-heart",
        "fa-solid fa-gamepad",
        "fa-solid fa-car",
        "fa-solid fa-book",
        "fa-solid fa-music",
        "fa-solid fa-briefcase"
    ];

    const handleSubcategoryClick = (name) => {
        window.location.href = `/search/category/${name}`;
    };
    
    return (
        <div className="main-container-categories">
            <div
                className="product-categories-section"
                onMouseEnter={() => setShowCategories(true)}
                onMouseLeave={() => {
                    setShowCategories(false);
                    setActiveCategory(null);
                }}
            >
                <div className="product-categories-header">
                    <i className="fa-solid fa-bars pr-2"></i>
                    <span className="categories-text">Categories</span>
                </div>  
                {showCategories && (
                    <div className="categories-menu">
                        <ul className="main-categories">
                            {categories.map((category, index) => (
                                <li
                                    key={index}
                                    onMouseEnter={() => setActiveCategory(index)}
                                    onMouseLeave={() => setActiveCategory(null)}
                                    className="category-item"
                                >
                                    <i className={`${icons[index % icons.length]} pr-2`}></i> {category.name}

                                </li>
                            ))}
                        </ul>
                        {activeCategory !== null && (
                            <div
                                className="subcategories"
                                onMouseEnter={() => setActiveCategory(activeCategory)}
                                onMouseLeave={() => setActiveCategory(null)}
                            >
                                {categories[activeCategory].subcategories.map((subcat, subIndex) => (
                                    <div key={subIndex} className="subcategory-item">
                                        <div
                                            className="subcategory-name"
                                            onClick={() => handleSubcategoryClick(subcat.name)}
                                        >
                                            {subcat.name}
                                        </div>
                                        <ul className="sub-subcategories">
                                            {subcat.subcategories.map((subSubcat, subSubIndex) => (
                                                <li
                                                    key={subSubIndex}
                                                    className="sub-subcategory-item"
                                                    onClick={() => handleSubcategoryClick(subSubcat)}
                                                >
                                                    {subSubcat}
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                ))}
                            </div>
                        )}
                    </div>
                )}
            </div>
        </div>
    );
};

export default CategoriesMenu;