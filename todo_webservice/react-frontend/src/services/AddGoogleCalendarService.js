import axios from "axios";
import AuthorizationService from "./AuthorizationService";

const CALENDAR_API_URL = "https://www.googleapis.com/calendar/v3/calendars/primary/events?"

let token = ''

class AddGoogleCalendarService {

    addEvent(token, toDo_name,toDo_date) {
        console.log("Access Token is: " + token)
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
        const eventParam = {
            end: {
                timeZone: "America/Toronto",
                dateTime: `${toDo_date}T10:00:00`
            },
            start: {
                timeZone: "America/Toronto",
                dateTime: `${toDo_date}T10:00:00`
            },
            summary: toDo_name,
            description: "A chance to hear more about Google's developer products."
        }
        return axios.post(
            CALENDAR_API_URL,
            eventParam
        )
    }

    postNewEvent(toDo_name, toDo_date) {
        AuthorizationService.getAccessToken().then((res) => {
            console.log("Access Token is: " + res.data)
            token = res.data

            const config = {
                headers: {Authorization: `Bearer ${token}`}
            }

            return this.addEvent(token, toDo_name, toDo_date)
        })
    }
}

export default new AddGoogleCalendarService()