import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import {AddToDrive, WbSunny} from "@mui/icons-material";
import Typography from "@mui/material/Typography";
import dayjs from "dayjs";
import * as React from "react";
import {Divider, ListItemButton, Paper, Tooltip} from "@mui/material";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import DialogComponent from "./DialogComponent";
import ListItemText from "@mui/material/ListItemText";
import {useState} from "react";
import isTomorrow from "dayjs/plugin/isTomorrow";
import isToday from "dayjs/plugin/isToday";

export default function TodayComponent() {
    const [toDos, setTodos] = useState()
    const [DialogOpen, setDialogOpen] = useState(false)
    const [selectedTodo, setSelectedTodo] = useState()

    const handleListButtonClick = (event, index) => {
        const toDo = toDos.at(index)
        setDialogOpen(true)
        setSelectedTodo(toDo)
    }

    const handleClose = (value) => {
        setDialogOpen(false)
    }

    const displayDueDate = (date) => {
        let currDate = new Date()
        const isToday = require('dayjs/plugin/isToday')
        const isTomorrow = require('dayjs/plugin/isTomorrow');
        dayjs.extend(isTomorrow)
        dayjs.extend(isToday)

        if (date.isToday()) {
            return "Today"
        } else if (date.isTomorrow()) {
            return "Tomorrow"
        } else {
            return date.format('YYYY/MM/DD')
        }
    }

    function intToPriority(value) {
        switch (value) {
            case 1: return "Critical"
            case 2: return "High"
            case 3: return "Medium"
            case 4: return "Low"
            case 5: return "Very Low"
        }
    }


    return (
        <div>
            <Grid container>
                <Container sx={{
                    mx: 40, display: 'flex', flexDirection: 'row', alignItems: 'start', gap: 2
                }}>
                    <Grid item sm={10}>
                        <Container sx={{
                            display: 'flex', flexDirection: 'column', alignItems: 'start', gap: 2
                        }}>
                            <Container sx={{
                                display: 'flex', flexDirection: 'row', alignItems: 'start', gap: 2
                            }}>
                                <WbSunny/>
                                <Typography>
                                    Today
                                </Typography>
                            </Container>

                            <Container>
                                {dayjs(new Date()).format('LL')}
                            </Container>
                        </Container>
                    </Grid>
                </Container>
                <Container maxWidth={false} sx={{
                    mx: 45
                }}>
                    <Paper sx={{my: 2}} style={{maxHeight: 600, overflow: 'auto'}}>
                        <List>
                            {toDos?.map((toDo, i) =>
                                <li>
                                    <ListItem>
                                        <Tooltip title="Add to Google Calendar">
                                            <ListItemButton
                                                style={{maxWidth: 60}}
                                                onClick={(event) => handleListButtonClick(event, i)}>
                                                <AddToDrive/>
                                            </ListItemButton>
                                        </Tooltip>
                                        <DialogComponent onClosed={handleClose} open={DialogOpen} todo={selectedTodo}/>
                                        <ListItemText primary={toDo.name}
                                                      secondary={"Due: " + displayDueDate(toDo.date) +
                                                          " Priority: " + intToPriority(toDo.priority) } />
                                    </ListItem>
                                    <Divider component="li"/>
                                </li>
                            )}
                        </List>
                    </Paper>

                </Container>
            </Grid>
        </div>
    )
}