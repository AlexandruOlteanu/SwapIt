import React from 'react'

function TopbarSection() {
    return (
        <React.Fragment>
            <div className="container-fluid bg-dark py-3 d-none d-lg-block">
                <div className="row">
                    <div className="col-md-6 text-center text-lg-left mb-2 mb-lg-0">
                        <div className="d-inline-flex align-items-center">
                            <a className="text-body pr-3" aria-label="Phone" href="tel:+40729868263">
                                <i className="fa fa-phone-alt mr-2"></i>+40 729 868 263
                            </a>
                            <span className="text-body">|</span>
                            <a className="text-body px-3" href="mailto:swapit@gmail.com" aria-label="Mail"><i className="fa fa-envelope mr-2"></i>support@swapit.com</a>
                        </div>
                    </div>
                    <div className="col-md-6 text-center text-lg-right">
                        <div className="d-inline-flex align-items-center">
                            <a className="text-body px-3" href="/" aria-label="Facebook">
                                <i className="fab fa-facebook-f smIcon"></i>
                            </a>
                            <a className="text-body px-3" href="/" aria-label="Twitter">
                                <i className="fab fa-twitter smIcon"></i>
                            </a>
                            <a className="text-body px-3" href="/" aria-label="Instagram">
                                <i className="fab fa-instagram smIcon"></i>
                            </a>
                            <a className="text-body pl-3" href="/" aria-label="Youtube">
                                <i className="fab fa-youtube smIcon"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </React.Fragment>
    )
}

export default TopbarSection;