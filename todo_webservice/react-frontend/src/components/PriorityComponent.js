import * as React from 'react';
import Button from '@mui/material/Button';
import ClickAwayListener from '@mui/material/ClickAwayListener';
import Grow from '@mui/material/Grow';
import Paper from '@mui/material/Paper';
import Popper from '@mui/material/Popper';
import MenuItem from '@mui/material/MenuItem';
import MenuList from '@mui/material/MenuList';
import {LowPriority} from "@mui/icons-material";
import {Tooltip} from "@mui/material";

export default function PriorityComponent(props) {

    const {setPriority} = props

    const [open, setOpen] = React.useState(false);
    const anchorRef = React.useRef(null);

    const handleToggle = () => {
        setOpen((prevOpen) => !prevOpen);
    };

    const handleClose = (event) => {
        if (anchorRef.current && anchorRef.current.contains(event.target)) {
            return;
        }
        console.log("priority is " + priorityToInt(event.target.innerText))
        setPriority(priorityToInt(event.target.innerText))

        setOpen(false);
    };

    function priorityToInt(priority) {
        switch (priority) {
            case "Critical": return 1
            case "High": return 2
            case "Medium": return 3
            case "Low": return 4
            case "Very Low": return 5
            default: return 3
        }
    }

    function handleListKeyDown(event) {
        if (event.key === 'Tab') {
            event.preventDefault();
            setOpen(false);
        } else if (event.key === 'Escape') {
            setOpen(false);
        }
    }

    // return focus to the button when we transitioned from !open -> open
    const prevOpen = React.useRef(open);
    React.useEffect(() => {
        if (prevOpen.current === true && open === false) {
            anchorRef.current.focus();
        }

        prevOpen.current = open;
    }, [open]);

    return (
        <div>
            <Button sx={{marginLeft: 5, marginTop: 1}}
                ref={anchorRef}
                id="composition-button"
                aria-controls={open ? 'composition-menu' : undefined}
                aria-expanded={open ? 'true' : undefined}
                aria-haspopup="true"
                onClick={handleToggle}
            >
                <Tooltip title={"Priority"}>
                    <LowPriority/>
                </Tooltip>
            </Button>
            <Popper
                open={open}
                anchorEl={anchorRef.current}
                role={undefined}
                placement="top-start"
                transition
                disablePortal
            >
                {({TransitionProps, placement}) => (
                    <Grow
                        {...TransitionProps}
                        style={{
                            transformOrigin:
                                placement === 'bottom-start' ? 'left top' : 'left bottom',
                        }}
                    >
                        <Paper>
                            <ClickAwayListener onClickAway={handleClose}>
                                <MenuList
                                    autoFocusItem={open}
                                    id="composition-menu"
                                    aria-labelledby="composition-button"
                                    onKeyDown={handleListKeyDown}
                                >
                                    <MenuItem onClick={handleClose}>Critical</MenuItem>
                                    <MenuItem onClick={handleClose}>High</MenuItem>
                                    <MenuItem onClick={handleClose}>Medium</MenuItem>
                                    <MenuItem onClick={handleClose}>Low</MenuItem>
                                    <MenuItem onClick={handleClose}>Very Low</MenuItem>
                                </MenuList>
                            </ClickAwayListener>
                        </Paper>
                    </Grow>
                )}
            </Popper>
        </div>

    );
}
