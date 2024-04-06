import React, { lazy } from 'react'
import Datetime from 'react-datetime';

import 'react-datetime/css/react-datetime.css';
import 'react-time-picker/dist/TimePicker.css';
import '../css/SearchSection.css'

const CustomSelect = lazy(() => import('../js/CustomSelect'));

function SearchSection() {

    return (
        <React.Fragment>
            {/* <!-- Search Start --> */}
            <div className="container-fluid bg-dark pt-3 px-lg-5">
                <div className="row mx-n2">

                    <CustomSelect
                        options={[
                            { value: 'business-size', label: 'Business Size' },
                            { value: 'startup', label: 'Startup (< 5k revenue)' },
                            { value: 'medium', label: 'Medium (5k-50k revenue)' },
                            { value: 'enterprise', label: 'Enterprise (> 50k revenue)' },
                        ]}
                        classes="mb-3 br-10 col-xl-2 col-lg-4 col-md-6 px-2"
                        myCustomStyles={null}
                    />

                    <CustomSelect
                        options={[
                            { value: 'service', label: 'Service' },
                            { value: 'software-development', label: 'Software Development' },
                            { value: 'business-names', label: 'Business Names & Slogans' },
                            { value: 'facebook-ads', label: 'Facebook Ads' },
                            { value: 'seo-ecommerce', label: 'SEO & Ecommerce' },
                            { value: 'copywriting', label: 'Copywriting' },
                            { value: 'graphic-design', label: 'Graphic Design' },
                            { value: 'instagram-growth', label: 'Instagram Growth' },
                            { value: 'business-plans', label: 'Business Plans' },
                            { value: 'google-ads', label: 'Google Ads' }
                        ]}
                        classes="mb-3 br-10 br-10 col-xl-2 col-lg-4 col-md-6 px-2"
                        myCustomStyles={null}
                    />
                    <div className="col-xl-2 col-lg-4 col-md-6 px-2 mb-3">
                        <Datetime
                            inputProps={{
                                className: 'form-control p-4 px-4',
                                placeholder: 'Starting Date',
                                style: {
                                    borderRadius: '10px',
                                },
                            }}
                            timeFormat={false}
                        />

                    </div>
                    <div className="col-xl-2 col-lg-4 col-md-6 px-2 mb-3">
                        <Datetime
                            inputProps={{
                                className: 'form-control p-4 px-4',
                                placeholder: 'Starting Time',
                                style: { borderRadius: '10px' }
                            }}
                            dateFormat={false}
                        />
                    </div>

                    <CustomSelect
                        options={[
                            { value: 'main-challenge', label: 'Main Challenge' },
                            { value: 'increase-sales-outreach', label: 'Increase Sales & Outreach' },
                            { value: 'automate-improve-software', label: 'Automate & Improve Software' },
                            { value: 'brand-identity-vision', label: 'Brand Identity & Vision' },
                            { value: 'improve-business-ecom', label: 'Improve Business Ecom' },
                            { value: 'convert-more', label: 'Convert More' },
                            { value: 'convert-more', label: 'Launch Startup' },
                            { value: 'target-specific-audience', label: 'Target Specific Audience' },
                        ]}
                        classes="mb-3 br-10 col-xl-2 col-lg-4 col-md-6 px-2"
                        myCustomStyles={null}
                    />

                    <div className="col-xl-2 col-lg-4 col-md-6 px-2">
                        <a href="https://calendly.com/swapit/30min" target="_blank" rel="noopener noreferrer" aria-label="Search">
                            <button className="btn btn-primary btn-block mb-3" type="submit" style={{ height: '50px', borderRadius: '10px' }}>Search</button>
                        </a>
                    </div>
                </div>
            </div>
            {/* <!-- Search End --> */}
        </React.Fragment>
    )
}

export default SearchSection