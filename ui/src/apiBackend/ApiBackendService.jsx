import ErrorCodes from './ErrorCodes'

class ApiService {


  constructor() {
    this.apiBase = process.env.REACT_APP_API_BASE_URI;
    this.defaultHeaders = {
      'Content-Type': 'application/json'
      // Add any other default headers here
    };
  }

  serializeParams(params) {
    return Object.entries(params)
      .map(([key, value]) => `${encodeURIComponent(key)}=${encodeURIComponent(value)}`)
      .join('&');
  }


  async login(params, data) {

    const url = process.env.REACT_APP_LOGIN_URI

    console.log(this.apiBase);

    const headers = new Headers(this.defaultHeaders);
    const jwtToken = localStorage.getItem(process.env.REACT_APP_JWT_TOKEN);

    if (jwtToken) {
      headers.append('Authorization', `Bearer ${jwtToken}`);
    }

    const options = {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(data),
    };

    const queryString = Object.keys(params).length ? `?${this.serializeParams(params)}` : '';
    const response = await fetch(`${this.apiBase}${url}${queryString}`, options);

    if (!response.ok) {
      const errorCode = response.headers.get(process.env.REACT_APP_ERROR_CODE_HEADER);
      const errorMessage = ErrorCodes[errorCode];
      throw new Error(errorMessage || 'An unknown error occurred');
    }
    return await response.json();
  }

  async getAuthenticatedUserDetails(params, data) {

    const url = process.env.REACT_APP_GET_AUTHENTICATED_USER_DETAILS;

    const headers = new Headers(this.defaultHeaders);
    const jwtToken = localStorage.getItem(process.env.REACT_APP_JWT_TOKEN);

    if (jwtToken) {
      headers.append('Authorization', `Bearer ${jwtToken}`);
    }

    const options = {
      method: 'GET',
      credentials: 'include',
      headers: headers,
    };

    const queryString = Object.keys(params).length ? `?${this.serializeParams(params)}` : '';
    const response = await fetch(`${this.apiBase}${url}${queryString}`, options);

    if (!response.ok) {
      console.log(response);
      const errorCode = response.headers.get(process.env.REACT_APP_ERROR_CODE_HEADER);
      const errorMessage = ErrorCodes[errorCode];
      throw new Error(errorMessage || 'An unknown error occurred');
    }
    return await response.json();
  }

}

export default new ApiService();
