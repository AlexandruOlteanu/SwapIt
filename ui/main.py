import json
import subprocess
import random

# JWT Token
jwt_token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX3JvbGUiOiJBRE1JTklTVFJBVE9SIiwidXNlcl9pZCI6MSwiaWF0IjoxNzE3NDA2OTU3LCJleHAiOjE3MTc0OTMzNTd9.0zVJxSPCp8JAqZaPIxggsuAa-gWNLztPBvG9a-0zH7E"

# URL
url = "http://localhost:8001/api/v1/swapIt/apiGateway/createProduct"

# Constants
start_category_id = 82
end_category_id = 430
number_of_products = 100000

# Sample data
titles = [
    "Queen Bed Frame with Storage Drawers and Headboard Shelf",
    "Stainless Steel Kitchen Knife Set with Wooden Block Holder",
    "Large Bamboo Cutting Board with Deep Juice Groove",
    "High-End Gaming Laptop with NVIDIA Graphics and SSD Storage",
    "Portable Bluetooth Speaker with Long Battery Life and Deep Bass",
    "Wi-Fi Enabled Thermostat with Touchscreen and Remote Control",
    "Ergonomic Leather Office Chair with Adjustable Lumbar Support",
    "Professional DSLR Camera with 24MP Sensor and 4K Video",
    "Microfiber Bedding Set with Duvet Cover and Pillow Shams",
    "Fitness Tracker with Heart Rate Monitor and Sleep Analysis",
    "Water-Resistant Hiking Backpack with Multiple Compartments",
    "Electric Toothbrush with Five Brushing Modes and Timer",
    "Portable Power Bank with Fast Charging and High Capacity",
    "Polarized Sunglasses with UV Protection and Stylish Design",
    "Stand Mixer with Multiple Attachments and Large Capacity Bowl",
    "Noise-Canceling Wireless Headphones with Bluetooth Connectivity",
    "Smart Light Bulbs with App Control and Color Changing Feature",
    "Ergonomic Gaming Chair with Adjustable Armrests and Lumbar Support",
    "Air Purifier with HEPA Filter for Allergies and Pets",
    "Smart Wi-Fi Plug with Voice Control and Energy Monitoring",
    "Coffee Maker with Built-In Grinder and Programmable Timer",
    "Home Security Camera System with Night Vision and Motion Detection",
    "Reusable Silicone Food Storage Bags for Eco-Friendly Living",
    "Ionic Hair Dryer with Multiple Heat and Speed Settings",
    "Spinner Wheel Travel Luggage Set with Hard Shell Case",
    "Smart Doorbell with Video Camera and Two-Way Audio",
    "Memory Foam Mattress Topper with Cooling Gel Technology",
    "Bluetooth Earbuds with Active Noise Cancellation",
    "Ultra HD 4K Smart TV with Built-In Streaming Apps",
    "Breathable Mesh Running Shoes with Cushioned Insoles",
    "Pre-Seasoned Cast Iron Skillet Set for Cooking",
    "Robot Vacuum Cleaner with Self-Emptying Dustbin",
    "Electric Kettle with Variable Temperature Settings",
    "Women's Handbag with Multiple Compartments and Zipper Closure",
    "Electric Pressure Cooker with Slow Cook and Rice Function",
    "Adjustable Dumbbells for Strength Training at Home",
    "Cordless Stick Vacuum Cleaner with Detachable Battery",
    "Men's Shaving Kit with Safety Razor and Brush",
    "Mini Fridge with Freezer Compartment and Adjustable Shelves",
    "Automatic Pet Feeder with Programmable Meal Times",
    "Leather Wallet with RFID Blocking and Multiple Card Slots",
    "Wireless Charging Pad with Fast Charge Technology",
    "Compost Bin with Charcoal Filter for Kitchen Countertop",
    "Car Jump Starter with Built-In Flashlight and USB Ports",
    "Portable Bluetooth Projector for Movies and Presentations",
    "Water Bottle with Time Marker and Leak-Proof Lid",
    "Non-Stick Frying Pan Set with Heat-Resistant Handles",
    "Air Fryer with Rapid Air Technology for Healthy Cooking",
    "Yoga Mat with Alignment Lines and Non-Slip Surface",
    "Foldable Electric Scooter with Long-Lasting Battery",
    "Digital Meat Thermometer with Instant Read and Backlight",
    "LED Desk Lamp with Adjustable Arm and USB Charging Port",
    "Smartwatch with Fitness Tracking and Sleep Monitoring",
    "Acoustic Guitar with Steel Strings and Carrying Case",
    "Adjustable Height Office Desk for Sitting and Standing",
    "Educational Tablet for Kids with Pre-Loaded Learning Apps",
    "Portable Camping Stove with Windshield and Fuel Canister",
    "Dog Bed with Washable Cover and Non-Slip Bottom",
    "Electric Lawn Mower with Adjustable Cutting Heights",
    "Baby Stroller with Adjustable Seat and Canopy",
    "Compact Dishwasher with Multiple Wash Cycles",
    "Smart Ceiling Fan with Remote Control and LED Light",
    "Digital Picture Frame with Wi-Fi and Cloud Storage",
    "Indoor Exercise Bike with Adjustable Resistance",
    "Smart Lock with Keyless Entry and Smartphone App",
    "Video Doorbell with Motion Detection and Two-Way Audio",
    "Stand Up Paddle Board with Adjustable Paddle",
    "Rechargeable Electric Toothbrush with Travel Case",
    "Pet Camera with Treat Dispenser and Two-Way Audio",
    "Electric Blanket with Dual Control Settings",
    "Wireless Security Camera with Solar Panel",
    "Compact Microwave Oven with Touch Control Panel",
    "Gaming Keyboard with RGB Backlit Keys",
    "Smart Bathroom Scale with Body Composition Analysis",
    "Indoor Air Quality Monitor with Mobile App",
    "Digital Piano with Weighted Keys and Pedals",
    "Convertible Car Seat with Safety Harness",
    "Electric Pressure Washer with Adjustable Nozzle",
    "Portable Air Conditioner with Dehumidifier Function",
    "Electric Treadmill with Foldable Design",
    "Handheld Steam Cleaner with Multiple Attachments",
    "Smart Home Hub with Voice Assistant",
    "Digital Wall Clock with Temperature Display",
    "Wireless Meat Thermometer for Grilling and Smoking",
    "Cordless Electric Screwdriver with LED Light",
    "Robotic Pool Cleaner with Programmable Timer",
    "Electric Bike with Pedal Assist and LCD Display",
    "Kids' Trampoline with Safety Net Enclosure",
    "Rechargeable Handheld Vacuum for Car and Home",
    "Digital Kitchen Scale with Nutritional Analysis",
    "Electric Wok with Adjustable Temperature Control",
    "Smart Water Leak Detector with Mobile Alerts",
    "Pet Water Fountain with Filtered Water",
    "Electric Griddle with Non-Stick Surface",
    "Cordless Electric Wine Opener with Charger",
    "Smart Wi-Fi Light Switch with Dimmer",
    "Portable Power Station with Solar Charging",
    "Digital Picture Frame with Remote Control",
    "Bluetooth Car Speakerphone with Noise Reduction",
    "Smart Garage Door Opener with Wi-Fi",
]
descriptions = [
    "Crafted from high-quality materials, this bed frame offers ample storage with built-in drawers, a stylish headboard shelf, and a sturdy design that ensures a good night's sleep.",
    "A professional-grade knife set featuring stainless steel blades, ergonomic handles, and a wooden block holder that keeps your kitchen organized and your tools at the ready.",
    "This bamboo cutting board features a deep juice groove to catch runoff and prevent messes, making it perfect for slicing meats, fruits, and vegetables.",
    "Designed for gamers, this laptop boasts NVIDIA graphics, SSD storage, and a high-refresh-rate display for an immersive gaming experience.",
    "Enjoy your favorite music on the go with this portable Bluetooth speaker, which offers long battery life, deep bass, and a compact, water-resistant design.",
    "Control your home's temperature from anywhere with this Wi-Fi enabled thermostat, featuring a sleek touchscreen interface and remote control via a mobile app.",
    "Work comfortably with this ergonomic office chair, which offers adjustable lumbar support, a breathable mesh back, and a sleek leather finish.",
    "Capture stunning photos and videos with this DSLR camera, equipped with a 24MP sensor, 4K video capabilities, and a variety of shooting modes.",
    "Transform your bedroom with this microfiber bedding set, which includes a duvet cover, pillow shams, and a fitted sheet for a cozy and stylish look.",
    "Track your fitness goals with this advanced fitness tracker, which monitors heart rate, sleep patterns, steps, and calories burned throughout the day.",
    "Perfect for outdoor adventures, this hiking backpack features multiple compartments, a water-resistant design, and comfortable shoulder straps.",
    "Achieve a superior clean with this electric toothbrush, which offers five brushing modes, a built-in timer, and a rechargeable battery.",
    "Keep your devices charged on the go with this high-capacity portable power bank, featuring fast charging capabilities and a compact design.",
    "Protect your eyes from harmful UV rays with these stylish polarized sunglasses, which offer UV protection and a sleek design for any occasion.",
    "Mix, knead, and whip ingredients with ease using this stand mixer, which includes multiple attachments and a large capacity bowl for all your baking needs.",
    "Experience crystal clear sound with these noise-canceling wireless headphones, which offer Bluetooth connectivity, long battery life, and a comfortable fit.",
    "Customize your lighting with these smart light bulbs, which can be controlled via a mobile app to change colors, dim, and set schedules.",
    "Game in comfort with this ergonomic gaming chair, featuring adjustable armrests, lumbar support, and a reclining backrest for extended gaming sessions.",
    "Improve your indoor air quality with this air purifier, which uses a HEPA filter to remove allergens, dust, and pet dander from the air.",
     "Control your devices remotely with this smart Wi-Fi plug, which offers voice control, energy monitoring, and scheduling through a mobile app.",
    "Brew the perfect cup of coffee every time with this coffee maker, which features a built-in grinder, programmable timer, and multiple brewing settings.",
    "Keep your home secure with this advanced security camera system, offering night vision, motion detection, and remote viewing through a mobile app.",
    "Reduce plastic waste with these reusable silicone food storage bags, which are perfect for storing snacks, leftovers, and meal prep items.",
    "Achieve salon-quality results at home with this ionic hair dryer, which features multiple heat and speed settings, and a concentrator nozzle.",
    "Travel in style with this spinner wheel luggage set, featuring a hard shell case, adjustable handles, and plenty of storage space for your essentials.",
    "Monitor your front door with this smart doorbell, which includes a high-definition video camera, two-way audio, and motion detection alerts.",
    "Add an extra layer of comfort to your bed with this memory foam mattress topper, which is infused with cooling gel to regulate your body temperature.",
    "Enjoy your music without distractions with these Bluetooth earbuds, which feature active noise cancellation, a secure fit, and a long battery life.",
    "Experience stunning picture quality with this 4K Smart TV, which includes built-in streaming apps, voice control, and a sleek, slim design.",
    "Stay comfortable on your runs with these breathable mesh running shoes, which offer cushioned insoles, a lightweight design, and durable outsoles.",
    "Cook like a pro with this pre-seasoned cast iron skillet set, which includes multiple sizes for frying, baking, and sautéing.",
    "Keep your floors spotless with this robot vacuum cleaner, which features a self-emptying dustbin, smart navigation, and powerful suction.",
    "Boil water quickly and safely with this electric kettle, which offers variable temperature settings and a sleek stainless steel design.",
    "Stay organized with this women's handbag, which includes multiple compartments, a zipper closure, and a stylish design for any occasion.",
    "Cook meals quickly and easily with this electric pressure cooker, which includes slow cook, rice cook, and sauté functions.",
    "Build your strength with these adjustable dumbbells, which are perfect for a variety of home workouts and fitness levels.",
    "Keep your home clean with this cordless stick vacuum cleaner, which features a detachable battery, powerful suction, and a lightweight design.",
    "Get a close, comfortable shave with this men's shaving kit, which includes a safety razor, brush, and high-quality shaving cream.",
    "Store your food and beverages in this mini fridge, which features a freezer compartment, adjustable shelves, and a compact design.",
    "Feed your pet on schedule with this automatic pet feeder, which includes programmable meal times, portion control, and a voice recording feature.",
    "Protect your personal information with this leather wallet, which includes RFID blocking technology and multiple card slots.",
    "Charge your devices wirelessly with this fast charging pad, which supports multiple devices and offers a sleek, modern design.",
    "Compost your kitchen waste with this compost bin, which features a charcoal filter to reduce odors and a compact design for your countertop.",
    "Jump-start your car battery with this portable jump starter, which includes a built-in flashlight and USB ports for charging devices.",
    "Watch movies and give presentations on the go with this portable Bluetooth projector, which offers high-definition video and a compact design.",
    "Stay hydrated with this water bottle, which features a time marker to track your intake and a leak-proof lid for easy transport.",
    "Cook your favorite meals with this non-stick frying pan set, which includes multiple sizes and heat-resistant handles.",
    "Enjoy healthier meals with this air fryer, which uses rapid air technology to cook food with little to no oil.",
    "Improve your yoga practice with this yoga mat, which features alignment lines, a non-slip surface, and extra thickness for comfort.",
    "Commute in style with this foldable electric scooter, which offers a long-lasting battery, fast speeds, and a lightweight design.",
    "Cook meat to perfection with this digital meat thermometer, which offers an instant read, backlight, and temperature hold function.",
    "Light up your workspace with this LED desk lamp, which features an adjustable arm, multiple brightness levels, and a built-in USB charging port.",
    "Track your health and fitness goals with this smartwatch, which includes fitness tracking, sleep monitoring, and smartphone notifications.",
    "Play beautiful music with this acoustic guitar, which includes steel strings, a carrying case, and a polished finish.",
    "Work comfortably with this adjustable height office desk, which allows you to switch between sitting and standing positions.",
    "Keep your kids entertained and educated with this tablet, which includes pre-loaded learning apps and parental controls.",
    "Cook delicious meals outdoors with this portable camping stove, which features a windscreen and a fuel canister for easy setup.",
    "Give your dog a comfortable place to rest with this dog bed, which features a washable cover and a non-slip bottom.",
    "Maintain your lawn with this electric lawn mower, which offers adjustable cutting heights, a powerful motor, and a grass collection bag.",
    "Take your baby for a stroll with this adjustable baby stroller, which includes a canopy, a reclining seat, and a storage basket.",
    "Clean your dishes effortlessly with this compact dishwasher, which offers multiple wash cycles, a sleek design, and easy installation.",
    "Cool your room with this smart ceiling fan, which features remote control, adjustable speed settings, and an energy-efficient LED light.",
    "Display your favorite photos with this digital picture frame, which includes Wi-Fi connectivity and cloud storage for easy photo sharing.",
    "Stay fit with this indoor exercise bike, which features adjustable resistance, a comfortable seat, and a digital display.",
    "Secure your home with this smart lock, which offers keyless entry, a smartphone app, and a sleek, modern design.",
    "Keep an eye on your front door with this video doorbell, which includes motion detection, two-way audio, and high-definition video.",
    "Enjoy water sports with this stand-up paddle board, which includes an adjustable paddle, a non-slip deck, and a carrying case.",
    "Maintain oral hygiene with this rechargeable electric toothbrush, which features multiple brushing modes, a timer, and a travel case.",
    "Keep an eye on your pet with this pet camera, which includes a treat dispenser, two-way audio, and motion detection.",
    "Stay warm with this electric blanket, which features dual control settings, a soft fabric, and an auto shut-off function.",
    "Monitor your home with this wireless security camera, which includes a solar panel for continuous power and a mobile app for remote viewing.",
    "Heat your food quickly with this compact microwave oven, which features touch control, multiple cooking settings, and a sleek design.",
    "Enhance your gaming experience with this RGB backlit gaming keyboard, which offers customizable keys, a durable design, and fast response times.",
    "Monitor your health with this smart bathroom scale, which includes body composition analysis and syncs with your smartphone.",
    "Keep your indoor air fresh with this air quality monitor, which measures pollutants, temperature, and humidity levels.",
    "Play beautiful music with this digital piano, which features weighted keys, pedals, and a variety of sound options.",
    "Ensure your child's safety with this convertible car seat, which includes a safety harness, adjustable headrest, and side-impact protection.",
    "Clean your outdoor spaces with this electric pressure washer, which features an adjustable nozzle, a powerful motor, and a compact design.",
    "Cool your home with this portable air conditioner, which includes a dehumidifier function, multiple fan speeds, and a remote control.",
    "Stay active with this electric treadmill, which features a foldable design, adjustable speed settings, and a digital display.",
    "Clean your home with this handheld steam cleaner, which includes multiple attachments for various cleaning tasks.",
    "Connect all your smart devices with this smart home hub, which features voice assistant compatibility and a user-friendly app.",
    "Keep track of time and temperature with this digital wall clock, which includes a large display, alarm settings, and a sleek design.",
    "Cook your food to perfection with this wireless meat thermometer, which includes multiple probes, a mobile app, and a long-range connection.",
    "Handle DIY tasks with ease using this cordless electric screwdriver, which features an LED light, multiple bit attachments, and a rechargeable battery.",
    "Keep your pool clean with this robotic pool cleaner, which features a programmable timer, smart navigation, and powerful suction.",
    "Ride in style with this electric bike, which features pedal assist, an LCD display, and a long-lasting battery.",
    "Keep your kids active with this trampoline, which includes a safety net enclosure, a sturdy frame, and a weather-resistant design.",
    "Clean your car and home with this rechargeable handheld vacuum, which offers powerful suction, a compact design, and multiple attachments.",
    "Measure your food with this digital kitchen scale, which includes nutritional analysis, a tare function, and a sleek design.",
    "Cook delicious meals with this electric wok, which features adjustable temperature control, a non-stick surface, and a large cooking area.",
    "Protect your home with this smart water leak detector, which sends mobile alerts when a leak is detected and includes easy installation.",
    "Keep your pet hydrated with this water fountain, which features a filtered water system, a quiet pump, and a large capacity.",
    "Cook meals to perfection with this electric griddle, which includes a non-stick surface, adjustable temperature settings, and a drip tray.",
    "Open wine bottles effortlessly with this cordless electric wine opener, which includes a charging base and a sleek design.",
    "Control your home lighting with this smart Wi-Fi light switch, which features dimmer settings, voice control, and scheduling through a mobile app.",
    "Power your devices anywhere with this portable power station, which supports solar charging and includes multiple output ports.",
    "Display your favorite memories with this digital picture frame, which includes a remote control, slideshow options, and cloud storage.",
    "Stay connected hands-free with this Bluetooth car speakerphone, which features noise reduction, echo cancellation, and easy setup.",
    "Open and close your garage door remotely with this smart garage door opener, which includes Wi-Fi connectivity and a mobile app.",
]
def get_random_element(arr, index):
    return arr[index]

def get_random_specs():
    return {
        "Specification 1": f"Spec Value {random.randint(1, 100)}",
        "Specification 2": f"Spec Value {random.randint(1, 100)}",
        "Specification 3": f"Spec Value {random.randint(1, 100)}"
    }

def get_random_price():
    return round(random.uniform(10.0, 800.0), 2)

def get_random_product(category_id, product_number, nr):
    return {
        "title": f"{get_random_element(titles, nr)}",
        "description": get_random_element(descriptions, nr),
        "price": get_random_price(),
        "categoryId": category_id,
        "productSpecifications": get_random_specs(),
        "productImages": ["https://firebasestorage.googleapis.com/v0/b/swapit-eb176.appspot.com/o/images%2Fdefault_product_image.jpg?alt=media&token=049bf77c-7901-4f03-95e9-6494c184d564"]
    }

# Generate products
products = []

for i in range(1, number_of_products + 1):
    nr = random.randint(0, 99)
    category_id = random.randint(82, 430)
    products.append(get_random_product(category_id, i, nr))

def send_request(product):
    curl_command = [
        "curl",
        "-X", "PUT",
        url,
        "-H", "Content-Type: application/json",
        "-H", f"Authorization: Bearer {jwt_token}",
        "-d", json.dumps(product)
    ]
    
    result = subprocess.run(curl_command, capture_output=True, text=True)
    if result.returncode == 0:
        print(f"Product {product['title']} sent successfully.")
    else:
        print(f"Error sending product {product['title']}: {result.stderr}")

# Send all requests
for product in products:
    send_request(product)
