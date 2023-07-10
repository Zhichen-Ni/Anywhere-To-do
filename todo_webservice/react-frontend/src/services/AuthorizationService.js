import axios from "axios"

const GOOGLE_OAUTH_URL = "https://oauth2.googleapis.com/token"
const AUTH_API_BASE_URL = "http://localhost:8888/api/v1/auth"

const INFO_URL = "/userinfo"

const TOKEN_URL = "/token"

class AuthorizationService {
    
    getAccessToken() {
        return axios.get(AUTH_API_BASE_URL + TOKEN_URL)
    }

    getUserInfo() {
        return axios.get(AUTH_API_BASE_URL + INFO_URL)
    }
}

export default new AuthorizationService()