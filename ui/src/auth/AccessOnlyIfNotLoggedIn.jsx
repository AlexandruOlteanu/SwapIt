import React from 'react';
import { Navigate } from 'react-router-dom';

const AccessOnlyIfNotLoggedIn = ({ element: Element, ...rest }) => {
  const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

  return isLoggedIn ? <Navigate to="/" /> : <Element {...rest} />;
};

export default AccessOnlyIfNotLoggedIn;
