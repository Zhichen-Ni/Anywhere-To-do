import Button from "@mui/material/Button";
import * as React from 'react'
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Slide} from "@mui/material";
import AddGoogleCalendarService from "../services/AddGoogleCalendarService";

const Transition = React.forwardRef(function Transition(props, ref) {
    return <Slide direction="up" ref={ref} {...props} />;
});

export default function DialogComponent(props) {

    const {onClosed, open, todo } = props

    const DayToDate = (day) => {
        return day.format('YYYY-MM-DD')
    }

    const handleClickClose = () => {
        onClosed()
    }

    const handleClickApproval = () => {
        addTodoGoogleCalendar(todo)
        handleClickClose()
    }

    const addTodoGoogleCalendar = (toDo) => {
        AddGoogleCalendarService.postNewEvent(toDo.name,DayToDate(toDo.date) )
    }

    return (
        <div>
            <Dialog
                open={open}
                TransitionComponent={Transition}
                keepMounted={true}
                onClose={handleClickClose}
                aria-describedby="alert-dialog-slide-description">
                <DialogTitle>{"Add This Event to Google Calendar?"}</DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-slide-description">
                        By Clicking Yes, this Todo will be added to your Google Calendar
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClickApproval}>Yes</Button>
                    <Button onClick={handleClickClose}>No</Button>
                </DialogActions>
            </Dialog>
        </div>
    )

}