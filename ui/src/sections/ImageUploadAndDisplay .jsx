import React, { useState } from 'react';
import { storage } from '../firebase-config'; // Import your Firebase configuration
import { ref, uploadBytes, getDownloadURL } from 'firebase/storage';
import { v4 as uuidv4 } from 'uuid';

const ImageUploadAndDisplay = () => {
  const [selectedImage, setSelectedImage] = useState(null);
  const [displayedImage, setDisplayedImage] = useState('');
  const [productId, setProductId] = useState('');

  const handleImageUpload = () => {
    if (!selectedImage || !productId) {
      alert('Please select an image and enter a product ID.');
      return;
    }
    const imageRef = ref(storage, `images/${uuidv4()}`);
    uploadBytes(imageRef, selectedImage).then((snapshot) => {
      getDownloadURL(snapshot.ref).then((url) => {
        console.log('Image URL:', url);
        // Send the URL and productId to your backend
        // sendImageUrlToBackend(url, productId);
      });
    });
  };

  const sendImageUrlToBackend = (imgUrl, productId) => {
    const jwtToken = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuZXdVc2VyOTgiLCJpYXQiOjE3MTE3Mzc2MDUsImV4cCI6MTcxMTgyNDAwNX0.Wm8SyDj6vyPHvJuAKZtq6v96DuRK_OZylfJCCzoibc8';
    fetch('http://localhost:8001/api/v1/swapIt/apiGateway/uploadProductImage', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwtToken}`
      },
      body: JSON.stringify({ url: imgUrl, productId }),
    })
    .then(response => response.json())
    .then(data => {
      console.log('Success:', data);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
  };

  const handleGetImage = () => {
    fetch(`YOUR_BACKEND_ENDPOINT_TO_GET_IMAGE_URL/${productId}`)
      .then(response => response.json())
      .then(data => {
        setDisplayedImage(data.imageUrl);
      })
      .catch(error => console.error('Error fetching image:', error));
  };

  return (
    <div>
      <input 
        type="number" 
        placeholder="Enter Product ID" 
        value={productId} 
        onChange={(e) => setProductId(e.target.value)} 
      />
      <input type="file" onChange={(e) => setSelectedImage(e.target.files[0])} />
      <button onClick={handleImageUpload}>Upload Image</button>
      <button onClick={handleGetImage}>Get Picture</button>
      {displayedImage && <img src={displayedImage} alt="Fetched from backend" />}
    </div>
  );
};

export default ImageUploadAndDisplay;
