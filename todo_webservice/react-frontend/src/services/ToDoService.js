import axios from "axios";

const ToDo_API_BASE_URL = "http://localhost:8888/api/v1/todo"
const INFO_URL = "/todoInfo"


class ToDoService {

    getTodos() {
        return axios.get(ToDo_API_BASE_URL + INFO_URL)
    }
}

export default new ToDoService()