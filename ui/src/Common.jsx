import { jwtDecode } from 'jwt-decode';


class Common {

    isUserAdmin = () => {
        const token = localStorage.getItem(process.env.REACT_APP_JWT_TOKEN);
        if (token !== null) {
            const decodedToken = jwtDecode(token);
            const userRole = decodedToken.user_role;
            if (userRole === "ADMINISTRATOR") {
                return true;
            }
        }
        return false;
    };

    isLoggedIn = () => {
        const token = localStorage.getItem(process.env.REACT_APP_IS_LOGGED_IN);
        if (token === null) {
            return false;
        }
        return token;
    };

    formatDate = (isoString) => {
        const date = new Date(isoString);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
        const year = date.getFullYear();
    
        return `${day}-${month}-${year}`;
      };

}

export default new Common();