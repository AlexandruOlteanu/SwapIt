import React from 'react'

import '../css/ChallengesWeResolve.css'

import challenges1 from '../img/challenges1.webp'
import challenges2 from '../img/challenges2.webp'
import challenges3 from '../img/challenges3.webp'

function ChallengesWeResolve({ items }) {

    const { title: title1, description: description1, image: image1 } = items[0];
    const { title: title2, description: description2, image: image2 } = items[1];
    const { title: title3, description: description3, image: image3 } = items[2];

    const getImageSrc = (imageName) => {
        switch (imageName) {
            case 'challenges1':
                return challenges1;
            case 'challenges2':
                return challenges2;
            case 'challenges3':
                return challenges3;
            default:
                return challenges1;
        }
    };

    return (
        <React.Fragment>
            <div className="row justify-content-start">
                <div className="col-lg-11 d-flex flex-column justify-content-start align-items-start">
                    <h2 className="display-4 mb-5 mt-5" style={{ color: 'var(--primary)' }}> Business Challenges We Resolve </h2>
                    <span className="resolveSectionWidth" style={{ color: 'var(--light)' }}>Our commitment to delivering exceptional solutions is based on a meticulous understanding of your business's distinct needs and constraints. Irrespective of whether you are a budding startup or a well-established enterprise, we possess the expertise and resources to navigate the path towards an outstanding product, specifically tailored to your requirements</span>
                </div>
            </div>
            <div className="row justify-content-start">
                <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                    <h4 className="display-5 mb-3 mt-3" style={{ color: 'var(--primary)' }}> {title1} </h4>
                    <span className="resolveSectionWidth mb-2" style={{ color: 'var(--light)' }}> {description1} </span>
                </div>
                <div className="col-lg-6 d-flex flex-column justify-content-start align-items-start">
                    <img className="w-100 mt-2" src={getImageSrc(image1)} alt="" />
                </div>
            </div>
            <div className="row justify-content-start">
                <div className="col-lg-6 d-flex flex-column justify-content-start align-items-start">
                    <img className="w-80 showDesktop mt-2" src={getImageSrc(image2)} alt="" />
                    <h4 className="display-5 mb-3 mt-3 showMobile" style={{ color: 'var(--primary)' }}> {title2} </h4>
                    <span className="resolveSectionWidth showMobile mb-2" style={{ color: 'var(--light)' }}> {description2} </span>
                </div>
                <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                    <img className="w-85 showMobile mt-2" style={{ alignSelf: 'center' }} src={getImageSrc(image2)} alt="" />
                    <h4 className="display-5 mb-3 mt-3 showDesktop" style={{ color: 'var(--primary)' }}> {title2} </h4>
                    <span className="resolveSectionWidth showDesktop" style={{ color: 'var(--light)' }}> {description2} </span>
                </div>
            </div>
            <div className="row justify-content-start">
                <div className="col-lg-6 d-flex flex-column justify-content-center align-items-start">
                    <h4 className="display-5 mb-3 mt-3" style={{ color: 'var(--primary)' }}> {title3} </h4>
                    <span className="resolveSectionWidth" style={{ color: 'var(--light)' }}> {description3} </span>
                </div>
                <div className="col-lg-6 d-flex flex-column justify-content-start align-items-start">
                    <img className="w-100 mt-2" src={getImageSrc(image3)} alt="" />
                </div>
            </div>
        </React.Fragment>
    )
}

export default ChallengesWeResolve
