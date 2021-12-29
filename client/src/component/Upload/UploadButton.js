import * as React from 'react';
import { Button } from '@mui/material';
import UploadDialog from './UploadDialog/UploadDialog';

export default function  UploadButton(){
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };
    return(
        <div>
            <Button  onClick={handleClickOpen} variant="outlined">Upload</Button>
            <UploadDialog open={open} setOpen={setOpen}/>
        </div>
    )
}