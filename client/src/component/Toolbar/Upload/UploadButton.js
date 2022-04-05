import * as React from 'react';
import { Button, Snackbar,CircularProgress,IconButton } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';
import DicomUploadDialog from './UploadDialog/DicomUploadDialog';
import CircularProgressWithLabel from './UploadDialog/CircularProgressWithLabel';
import ColumnTypeDecisionDialog from "./MetadataColumnTypeDecision/ColumnTypeDesicionDialog";
import FormatChooseDialog from "./FormatChooseDialog/FormatChooseDialog";
import {useDispatch} from "react-redux";
import {FormatChooseAction} from "./FormatChooseDialog/FormatChooseDialogReducer";

let fileHandler;
export default function  UploadButton({projects,getMetaData,metaData}){
    const [open, setOpen] = React.useState(false);
    const [snackbarInfo,setSnackBarInfo]=React.useState({});
    const dispatch=useDispatch()
    const handleClickOpen = () => {
        if(snackbarInfo.open){
            alert('업로드 과정이 진행중입니다.')
        }
        else if(projects.projectId){
            //setOpen(true);
            dispatch(FormatChooseAction.openDialog())
        }
        else{
            alert('프로젝트를 선택해주세요.')
        }
    };
    const handleClose = () => {
        setSnackBarInfo({...snackbarInfo,'open':false});
      };
    const action = (
    <React.Fragment>
        {snackbarInfo.progress ? 
            <CircularProgressWithLabel value={snackbarInfo.progress}/> 
            : snackbarInfo.progress===false ? <div/> :<CircularProgress/>}
        {(snackbarInfo.progress===100 || snackbarInfo.closeButtonOpen) && <IconButton
        size="small"
        aria-label="close"
        color="inherit"
        onClick={handleClose}
        >
            <CloseIcon fontSize="small" />
        </IconButton>}
    </React.Fragment>
    );
    return(
        <div>
            <Button  onClick={handleClickOpen} variant="outlined">Upload</Button>
            {/*<DicomUploadDialog */}
            {/*    open={open} */}
            {/*    setOpen={setOpen}*/}
            {/*    snackbarInfo={snackbarInfo}*/}
            {/*    setSnackBarInfo={setSnackBarInfo}*/}
            {/*    fileHandler={fileHandler}*/}
            {/*    projects={projects}*/}
            {/*    getMetaData={getMetaData}*/}
            {/*    metaData={metaData}*/}
            {/*/>*/}
            <FormatChooseDialog/>
            <Snackbar
                key='DataLoadingMessenger'
                open={snackbarInfo.open}
                message={snackbarInfo.message}
                anchorOrigin={{ 'vertical':'bottom', 'horizontal':'right' }}
                action={action}
            />
            <ColumnTypeDecisionDialog/>
        </div>
    )
}