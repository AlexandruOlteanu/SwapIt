import ErrorCodes from './ErrorCodes';

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
      headers.append('Authorization', `Bearer ${jwtToken}`);
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
    return this.fetchWithAuth(process.env.REACT_APP_GET_PRODUCT_CATEGORY_ID, {
      method: 'POST',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, true);
  }

  createProduct(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_CREATE_PRODUCT, {
      method: 'PUT',
      credentials: 'include',
      body: JSON.stringify(data),
      params: params
    }, true);
  }
}

export default new ApiService();
