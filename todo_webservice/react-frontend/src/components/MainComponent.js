import * as React from 'react';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Typography from '@mui/material/Typography'
import dayjs from 'dayjs';

import Grid from '@mui/material/Grid'
import Container from '@mui/material/Container'
import {
    BottomNavigation,
    BottomNavigationAction,
    Divider, FormControl, InputLabel,
    ListItemButton, MenuItem,
    Paper, Select,
    TextField, Tooltip
} from "@mui/material";
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm';
import {useEffect, useReducer, useState} from "react";
import Button from "@mui/material/Button";
import {AddToDrive, CalendarMonth, LowPriority, WbSunny} from "@mui/icons-material";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import AddGoogleCalendarService from "../services/AddGoogleCalendarService";
import DialogComponent from "./DialogComponent";
import PriorityComponent from "./PriorityComponent"
import PropTypes from "prop-types";
import {DesktopDatePicker, LocalizationProvider} from "@mui/x-date-pickers";
import {AdapterDayjs} from "@mui/x-date-pickers/AdapterDayjs";
import Box from "@mui/material/Box";
import SortIcon from '@mui/icons-material/Sort';

export default function MainComponent() {
    const [priority, setPriority] = useState(3)

    const [toDos, setTodos] = useState([])

    const [sortOption, setSortOption] = useState(0)

    const [PendingToDo, setPendingToDo] = useState("")

    const [dueDate, setDueDate] = useState(dayjs(new Date()))

    const [DialogOpen, setDialogOpen] = useState(false)

    const [selectedTodo, setSelectedTodo] = useState()

    const [_, forceUpdate] = useReducer((x) => x + 1, 0);

    const handleListButtonClick = (event, index) => {
        const toDo = toDos.at(index)
        setDialogOpen(true)
        setSelectedTodo(toDo)
    }

    const getTodoInput = (e) => {
        setPendingToDo(e.target.value)
    }

    const addTodo = () => {
        let newTodo = {name: PendingToDo, date: dueDate, priority: priority}
        setTodos([...toDos, newTodo])
        setPendingToDo("")
    }

    const handleAddToDoPress = e => {
        if (e.key === 'Enter' && PendingToDo !== '') {
            addTodo()
        }
    }

    Date.prototype.yyyymmdd = function () {
        var mm = this.getMonth() + 1; // getMonth() is zero-based
        var dd = this.getDate();

        return [this.getFullYear(),
            (mm > 9 ? '' : '0') + +mm,
            (dd > 9 ? '' : '0') + +dd
        ].join('-');
    }

    const getDueDate = (date) => {
        setDueDate(date)
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

    const handleClose = (value) => {
        setDialogOpen(false)
    }

    const sortByDate = () => {
        let sorted = toDos.sort((a, b) => dayjs(a.date).isAfter(dayjs(b.date)) ? 1 : -1)

        setTodos(sorted)

        forceUpdate()
    }

    const sortByPriority = () => {
        let sorted = toDos.sort((a, b) => (a.priority > b.priority) ? 1 : -1)
        setTodos(sorted)
        forceUpdate()
    }


    const updateSortOption = (value) => {
        console.log("updateSortOption " + value.target.value)
        setSortOption(value.target.value)

        handleSort(value.target.value)
    }

    const handleSort = (value) => {
        console.log("sort option is " + sortOption)
        if (toDos.length < 1) {
            return
        }
        switch (value) {
            case 1: sortByPriority(); break
            case 2: sortByDate(); break
            default: break;
        }


        if (value === 2) {
            sortByDate()
        }

        console.log("Value is " + toDos.at(0).name)
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


    useEffect(() => {handleSort(sortOption)}, [toDos])

    return (<div>
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
                    <Grid item sm={2}>
                        <Box display="flex" justifyContent="flex-end">
                            <FormControl fullWidth>
                                <InputLabel id="sort-label">Sort</InputLabel>
                                <Select
                                    labelId="sort-label"
                                    id="sort-select"
                                    //value={age}
                                    label="Sort"
                                    onChange={updateSortOption}
                                >
                                    <MenuItem value={0}>Default</MenuItem>
                                    <MenuItem value={1}>Priority</MenuItem>
                                    <MenuItem value={2}>Due Date</MenuItem>
                                </Select>
                            </FormControl>
                        </Box>
                    </Grid>
                </Container>

                <Container
                    maxWidth={false}
                    sx={{
                        mx: 43, display: 'flex', flexDirection: 'row', alignItems: 'start'
                    }}>
                    <Button sx={{my: 2}}>
                        <AccessAlarmIcon/>
                    </Button>
                    <TextField fullWidth label="Add a Todo" onKeyDown={handleAddToDoPress}
                               variant="standard" onChange={getTodoInput} value={PendingToDo}></TextField>
                </Container>

                <Container sx={{
                    mx: 45, display: 'flex', flexDirection: 'row', alignItems: 'start'
                }}>

                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DesktopDatePicker
                            label="Due Date"
                            inputFormat="MM/DD/YYYY"
                            value={dueDate}
                            onChange={getDueDate}
                            renderInput={(params) => <TextField {...params} />}
                        />
                    </LocalizationProvider>

                    <PriorityComponent setPriority={setPriority} />
                    <Typography sx={{marginTop: 2}}> {intToPriority(priority)} </Typography>
                    <Button sx={{marginLeft: 10, marginTop: 1}} disabled={!PendingToDo} onClick={addTodo}>
                        Add
                    </Button>
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
    );
}