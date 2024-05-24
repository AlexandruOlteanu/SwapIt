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

}

export default new Common();