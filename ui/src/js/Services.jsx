import React, { useRef, useEffect } from 'react';
import OwlCarousel from 'react-owl-carousel';

import '../css/Services.css';
import '../lib/owlcarousel/assets/owl.carousel.css'
import '../lib/owlcarousel/assets/owl.theme.default.css'

import online_reach_visibility_image from '../img/online-reach-visibility-image.webp'
import software_dev_image from '../img/software-dev-image.webp'
import revenue_and_sales_image from '../img/revenue-and-sales-image.webp'
import memorable_brand_identity_image from '../img/memorable-brand-identity-image.webp'
import business_strategies from '../img/business-strategies.webp'

const Services = () => {
    const carouselRef = useRef(null);

    useEffect(() => {
        setTimeout(() => {

        }, 100);
    }, []);

    return (

        <OwlCarousel
            ref={carouselRef}
            className="vendor-carousel owl-carousel owl-theme"
            style={{ padding: '30px' }}
            loop={true}
            margin={30}
            dots={true}
            center={true}
            autoplay={true}
            smartSpeed={2000}
            nav={false}
            responsive={{
                0: {
                    items: 1,
                }
            }}
        >
            <div className="item active-section">
                <div className="thumb">
                    <div className="row">
                        <div className="col-lg-6 align-self-center">
                            <div className="left-text">
                                <h4>Online Reach & Visibility</h4>
                                <p>Online Reach & Visibility are crucial for establishing an online presence. Improving your reach involves
                                    creating quality content, using effective SEO techniques, leveraging social media, and implementing paid
                                    advertising strategies. A strong online presence is critical in today's digital landscape.</p>
                                <div className="ticks-list"><span><i className="fa fa-check"></i> Increased exposure</span> <span><i className="fa fa-check"></i>Enhanced traffic</span> <span><i className="fa fa-check"></i> SEO Analysis</span>
                                    <span><i className="fa fa-check"></i>Targeting precision</span> <span><i className="fa fa-check"></i> Competitive advantage</span>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 align-self-center">
                            <div className="right-image">
                                <img src={online_reach_visibility_image} alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="item active-section">
                <div className="thumb">
                    <div className="row">
                        <div className="col-lg-6 align-self-center">
                            <div className="left-text">
                                <h4>Revenue and Sales</h4>
                                <p>Optimizing your strategy involves identifying your audience, creating effective campaigns, and using data-driven insights.
                                    Improving revenue and sales can involve targeted advertising, email marketing, social media, and customer feedback. A strong
                                    approach is essential in today's marketplace.</p>
                                <div className="ticks-list"><span><i className="fa fa-check"></i>Consistent revenue</span> <span><i className="fa fa-check"></i>Targeted campaigns</span> <span><i className="fa fa-check"></i>Improved engagement</span>
                                    <span><i className="fa fa-check"></i>Higher conversions</span> <span><i className="fa fa-check"></i>Scalable growth</span>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 align-self-center">
                            <div className="right-image">
                                <img src={revenue_and_sales_image} alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="item active-section">
                <div className="thumb">
                    <div className="row">
                        <div className="col-lg-6 align-self-center">
                            <div className="left-text">
                                <h4>Memorable Brand Identity</h4>
                                <p>Brand Identity is crucial for standing out in the marketplace. It involves creating a brand that resonates with your audience and differentiates you from the competition. A consistent visual identity, unique personality, and strong brand recognition can help build customer loyalty and reputation in your industry.</p>
                                <div className="ticks-list"><span><i className="fa fa-check"></i>Increased recognition</span> <span><i className="fa fa-check"></i>Competitive differentiation</span> <span><i className="fa fa-check"></i>Stronger loyalty</span>
                                    <span><i className="fa fa-check"></i>Enhanced reputation</span> <span><i className="fa fa-check"></i>Better equity</span>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 align-self-center">
                            <div className="right-image">
                                <img src={memorable_brand_identity_image} alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="item active-section">
                <div className="thumb">
                    <div className="row">
                        <div className="col-lg-6 align-self-center">
                            <div className="left-text">
                                <h4>Business Strategies</h4>
                                <p>Developing a comprehensive, well-executed plan can gain a competitive advantage and drive growth. Effective strategies involve identifying your unique selling proposition, creating a strong value proposition, and taking a data-driven approach to optimize your strategy</p>
                                <div className="ticks-list"><span><i className="fa fa-check"></i>Market expansion</span> <span><i className="fa fa-check"></i>Long-term growth</span> <span><i className="fa fa-check"></i>Targeted approach</span>
                                    <span><i className="fa fa-check"></i>Informed decisions</span> <span><i className="fa fa-check"></i>Strategic vision</span>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 align-self-center">
                            <div className="right-image">
                                <img src={business_strategies} alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className="item active-section">
                <div className="thumb">
                    <div className="row">
                        <div className="col-lg-6 align-self-center">
                            <div className="left-text">
                                <h4>Software Solutions</h4>
                                <p>Software Development creates applications that improve processes and the customer experience. Effective development involves identifying needs, designing, developing, and optimizing software. Leveraging technology drives growth and achieves long-term goals.</p>
                                <div className="ticks-list"><span><i className="fa fa-check"></i>Streamlined workflows</span> <span><i className="fa fa-check"></i>Enhanced functionality</span> <span><i className="fa fa-check"></i>Agile development</span>
                                    <span><i className="fa fa-check"></i>Robust solutions</span> <span><i className="fa fa-check"></i>Efficient operations</span>
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6 align-self-center">
                            <div className="right-image">
                                <img src={software_dev_image} alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </OwlCarousel>
    );
};
export default Services