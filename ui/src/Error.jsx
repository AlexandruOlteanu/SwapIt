import React from 'react';
import { Typography } from '@mui/material';
import { makeStyles } from '@mui/styles';
import Preloader from './js/Preloader';
import TopbarSection from './sections/TopbarSection';
import NavbarSection from './sections/NavbarSection';
import FooterSection from './sections/FooterSection';
import BackToTopButton from './js/BackToTopButton';

const useStyles = makeStyles({
    noResults: {
        display: 'flex',
        color: 'red',
        backgroundColor: 'var(--dark)',
        minHeight: '100vh',
        alignItems: 'center',
        flexDirection: 'column',
        paddingTop: '50px !important',
        width: '100%',
    },
});

const Error = () => {
    const classes = useStyles();

    return (
        <React.Fragment>
            <Preloader />
            <TopbarSection />
            <NavbarSection />
            <Typography variant="h6" className={classes.noResults}>
                <div>
                    <i class="fa-solid fa-circle-exclamation" style={{marginRight:'5px'}}></i> Oops! Something went wrong. Please try again soon
                </div>
            </Typography>
            <FooterSection />
            <BackToTopButton />
        </React.Fragment>

    );
};

export default Error;
