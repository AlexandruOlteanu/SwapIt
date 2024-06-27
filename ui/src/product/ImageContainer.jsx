import React from 'react';
import '../css/ImageContainer.css';

const ImageContainer = ({ isVisible, image, onClose }) => {
  if (!isVisible) return null;

  return (
    <div className="image-container-overlay" onClick={onClose}>
      <div className="image-container-content" onClick={(e) => e.stopPropagation()}>
        <button className="image-container-close" onClick={onClose}>
            <i className="fa-solid fa-lg fa-circle-xmark"></i>
        </button>
        <img src={image} alt="Enlarged" className="image-container-image" />
      </div>
    </div>
  );
};

export default ImageContainer;
