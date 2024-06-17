import ErrorCodes from './ErrorCodes';
import { jwtDecode } from 'jwt-decode';

class ApiService {
  constructor() {
    this.apiBase = process.env.REACT_APP_API_BASE_URI;
    this.defaultHeaders = {
      'Content-Type': 'application/json',
    };
  }

  serializeParams(params) {
    return Object.entries(params)
      .map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`)
      .join('&');
  }

  async fetchWithAuth(url, options, expectJson = true) {
    const headers = new Headers(this.defaultHeaders);
    const jwtToken = localStorage.getItem(process.env.REACT_APP_JWT_TOKEN);
  
    if (jwtToken) {
      const decodedToken = jwtDecode(jwtToken);
      const currentTime = Math.floor(Date.now() / 1000);
  
      if (decodedToken.exp < currentTime) {
        localStorage.removeItem(process.env.REACT_APP_JWT_TOKEN);
        localStorage.setItem('isLoggedIn', 'false');
      } else {
        headers.append('Authorization', `Bearer ${jwtToken}`);
      }
    }
  
    options.headers = headers;
    const queryString = options.params ? `?${this.serializeParams(options.params)}` : '';
    const response = await fetch(`${this.apiBase}${url}${queryString}`, options);
  
    if (!response.ok) {
      const errorCode = response.headers.get(process.env.REACT_APP_ERROR_CODE_HEADER);
      const errorMessage = ErrorCodes[errorCode] || 'An unknown error occurred';
      throw new Error(errorMessage);
    }
    if (expectJson) {
      return await response.json();
    }
    return response;
  }

  login(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_LOGIN_URI, {
      method: 'POST',
      body: JSON.stringify(data),
      credentials: 'include',
      params: params
    }, true);
  }

  register(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_REGISTER_URI, {
      method: 'PUT',
      body: JSON.stringify(data),
      credentials: 'include',
      params: params
    }, true);
  }

  passwordReset(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_PASSWORD_RESET_URI, {
      method: 'PUT',
      body: JSON.stringify(data),
      credentials: 'include',
      params: params
    }, false);
  }

  getAuthenticatedUserDetails(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_AUTHENTICATED_USER_DETAILS_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  getUserDetailsByUsername(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_USER_DETAILS_BY_USERNAME_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  getUserDetails(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_USER_DETAILS_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  logout(params) {
    return this.fetchWithAuth(process.env.REACT_APP_LOGOUT_URI, {
      method: 'POST',
      credentials: 'include',
      params: params
    }, false);
  }

  updateBasicUserDetails(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_UPDATE_BASIC_USER_DETAILS_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  forgottenPasswordReset(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_FORGOTTEN_PASSWORD_RESET_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  passwordReset(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_PASSWORD_RESET_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  usernameReset(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_USERNAME_RESET_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  emailReset(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_EMAIL_RESET_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  getProductCategoryId(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_PRODUCT_CATEGORY_ID_URI, {
      method: 'POST',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, true);
  }

  createProduct(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_CREATE_PRODUCT_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, true);
  }

  getProductById(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_PRODUCT_BY_ID_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  getProductsLikeStatus(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_PRODUCTS_LIKE_STATUS_URI, {
      method: 'POST',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, true);
  }

  changeProductLikeStatus(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_CHANGE_PRODUCT_LIKE_STATUS_URI, {
      method: 'PUT',
      credentials: 'include',
      params: params
    }, false);
  }

  deleteProduct(params) {
    return this.fetchWithAuth(process.env.REACT_APP_DELETE_PRODUCT_URI, {
      method: 'DELETE',
      credentials: 'include',
      params: params
    }, false);
  }

  deleteProductAdmin(params) {
    return this.fetchWithAuth(process.env.REACT_APP_DELETE_PRODUCT_ADMIN_URI, {
      method: 'DELETE',
      credentials: 'include',
      params: params
    }, false);
  }

  getCategoryTree(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_CATEGORY_TREE_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  updateProduct(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_UPDATE_PRODUCT_URI, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  getUserAccountStatus(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_USER_ACCOUNT_STATUS_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  banUser(params) {
    return this.fetchWithAuth(process.env.REACT_APP_BAN_USER_URI, {
      method: 'POST',
      credentials: 'include',
      params: params
    }, false);
  }

  removeUserBan(params) {
    return this.fetchWithAuth(process.env.REACT_APP_REMOVE_USER_BAN_URI, {
      method: 'DELETE',
      credentials: 'include',
      params: params
    }, false);
  }

  getRecommendedProducts(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_RECOMMENDED_PRODUCTS_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  searchProducts(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_SEARCH_PRODUCTS_URI, {
      method: 'POST',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, true);
  }

  getProductsByUser(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_PRODUCTS_BY_USER_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  getLikedProductsByUser(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_LIKED_PRODUCTS_BY_USER_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  searchProductsByCategory(params) {
    return this.fetchWithAuth(process.env.REACT_APP_SEARCH_PRODUCTS_BY_CATEGORY_URI, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

  getUserActions(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_USER_ACTIONS, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }

}

export default new ApiService();
