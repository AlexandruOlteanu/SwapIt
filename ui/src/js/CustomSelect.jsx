import React from 'react';
import Select from 'react-select';

function CustomSelect({ options, classes, myCustomStyles }) {
    const customStyles = myCustomStyles !== null ? myCustomStyles : {
        control: styles => ({
            ...styles,
            height: '50px',
            borderRadius: '10px',
            backgroundColor: 'var(--secondary)',
        }),
        control: (styles, { menuIsOpen }) => ({
            ...styles,
            height: '50px',
            borderRadius: '10px',
            backgroundColor: 'var(--secondary)',
            border: menuIsOpen ? 'var(--primary)' : 'white',
            boxShadow: menuIsOpen ? '0 0 0 2px var(--primary)' : '0 0 0 1px white',
            color: 'white'
        }),
        option: (styles) => ({
            ...styles,
            backgroundColor: 'var(--secondary)',
            borderRadius: '10px',
            border: 'var(--secondary)',
            color: 'white',
            ':hover': {
                backgroundColor: 'var(--primary)'
            }
        }),
        menu: provided => ({
            ...provided,
            backgroundColor: 'var(--secondary)',
        }),
        singleValue: styles => ({
            ...styles,
            color: 'white'
        }),
        dropdownIndicator: styles => ({
            ...styles,
            color: 'white',
            '&:hover': {
                color: 'white'
            }
        })
    };

    return (
        <Select
            options={options}
            defaultValue={options[0]}
            className={classes}
            styles={customStyles}
            inputMode="none"
        />
    );
}

export default CustomSelect;