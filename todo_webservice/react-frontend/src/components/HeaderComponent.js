import React, {Component, useEffect, useState} from 'react'
import MuiAppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import {ChevronLeft, FileDownload} from "@mui/icons-material";

import AuthorizationService from '../services/AuthorizationService';
import {Divider, Drawer, ListItem, ListItemButton, styled} from "@mui/material";
import MainComponent from "./MainComponent";
import ListItemText from "@mui/material/ListItemText";
import List from "@mui/material/List";
import Container from "@mui/material/Container";

const getToken = (e) => {
    var apiKey = "248432300243-aldppbvkn85sbcurnfgl7ardbfon0ueu.apps.googleusercontent.com"
    var redirect_uri = "http://localhost:8888/oauth2Callback"
    var scope = "https%3A//www.googleapis.com/auth/drive.metadata.readonly https://www.googleapis.com/auth/userinfo.profile"
    var access_type = "offline"
    window.location.href = `https://accounts.google.com/o/oauth2/v2/auth?scope=${scope}&access_type=${access_type}&include_granted_scopes=true&response_type=code&redirect_uri=${redirect_uri}&client_id=${apiKey}`
}

const drawerWidth = 240;

const DrawerHeader = styled('div')(({theme}) => ({
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0, 1),
    // necessary for content to be below app bar
    ...theme.mixins.toolbar,
    justifyContent: 'flex-end',
}));

const Main = styled('main', {shouldForwardProp: (prop) => prop !== 'open'})(
    ({theme, open}) => ({
        flexGrow: 1,
        padding: theme.spacing(3),
        transition: theme.transitions.create('margin', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        marginLeft: `-${drawerWidth}px`,
        ...(open && {
            transition: theme.transitions.create('margin', {
                easing: theme.transitions.easing.easeOut,
                duration: theme.transitions.duration.enteringScreen,
            }),
            marginLeft: 0,
        }),
    }),
);

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({theme, open}) => ({
    transition: theme.transitions.create(['margin', 'width'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        width: `calc(100% - ${drawerWidth}px)`,
        marginLeft: `${drawerWidth}px`,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: theme.transitions.easing.easeOut,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

export default function ButtonAppBar(props) {

    const {myComponent} = props

    const [name, setName] = useState("LOGIN")

    const [open, setOpen] = React.useState(false)

    const handleDrawerOpen = () => {
        setOpen(true)
    }

    const handleDrawerClose = () => {
        setOpen(false)
    }

    const onCategoryClicked = (text) => {
        window.location.href = "/" + text;
    }

    useEffect(() => {
        AuthorizationService.getUserInfo().then((res) => {
            let userName = res.data.name
            if (userName) {
                setName(userName)
            }
        })
    })

    return (
        <Box sx={{flexGrow: 1}}>
            <AppBar position="static" open={open}>
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        onClick={handleDrawerOpen}
                        sx={{mr: 2, ...(open && {display: 'none'})}}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Typography variant="h6" noWrap component="div" sx={{flexGrow: 1}}>
                        Anywhere ToDo
                    </Typography>
                    <Button color="inherit" onClick={getToken}>{name}</Button>
                </Toolbar>
            </AppBar>
            <Drawer
                sx={{
                    width: drawerWidth,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': {
                        width: drawerWidth,
                        boxSizing: 'border-box',
                    },
                }}
                variant="persistent"
                anchor="left"
                open={open}
            >
                <DrawerHeader>
                    <IconButton onClick={handleDrawerClose}>
                        <ChevronLeft/>
                    </IconButton>
                </DrawerHeader>
                <Divider/>

                <Container sx={{
                    display: 'flex', flexDirection: 'row', alignItems: 'start'
                }}>
                    <List>
                        {["Home", "Today", "Planned", "All Tasks"].map((text, index) => (
                            <ListItemButton key={text} disablePadding onClick={(event) => onCategoryClicked(text)}>
                                <ListItemText primary={text}/>
                            </ListItemButton>

                        ))}
                    </List>
                </Container>

            </Drawer>
            <Main open={open}>
                {myComponent}
            </Main>
        </Box>
    );
}
  