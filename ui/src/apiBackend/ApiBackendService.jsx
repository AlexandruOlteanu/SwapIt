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
      params: params
    }, true);
  }

  register(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_REGISTER_URI, {
      method: 'PUT',
      body: JSON.stringify(data),
      params: params
    }, true);
  }

  sendRegistrationCode(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_SEND_REGISTRATION_CODE_URI, {
      method: 'POST',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  sendPasswordResetCode(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_SEND_PASSWORD_RESET_CODE_URI, {
      method: 'POST',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  passwordReset(params, data) {
    return this.fetchWithAuth(process.env.REACT_APP_PASSWORD_RESET_URI, {
      method: 'PUT',
      body: JSON.stringify(data),
      params: params
    }, false);
  }

  getAuthenticatedUserDetails(params) {
    return this.fetchWithAuth(process.env.REACT_APP_GET_AUTHENTICATED_USER_DETAILS, {
      method: 'GET',
      credentials: 'include',
      params: params
    }, true);
  }
}

export default new ApiService();
