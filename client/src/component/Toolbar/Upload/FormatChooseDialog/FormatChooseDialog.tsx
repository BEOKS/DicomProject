import React from 'react'
import {Button, Dialog, DialogActions, DialogContent, DialogTitle} from "@mui/material";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "../../../../store";
import {FORMAT_TYPE, FormatChooseAction} from "./FormatChooseDialogReducer";
import FileUploadDialog from "../FileUploadDialog/FileUploadDialog";

const FormatChooseDialog = ()=>{
    const open: boolean=useSelector((state : RootState )=>state.FormatChooseReducer.open)
    const selectedFormat : FORMAT_TYPE | null = useSelector((state: RootState)=>state.FormatChooseReducer.selectedFormat)
    const dispatch=useDispatch()
    const handleButtonClick=(format : FORMAT_TYPE)=>()=>{
        dispatch(FormatChooseAction.selectFormat(format))
        dispatch(FormatChooseAction.closeDialog())
    }
    const handleClose=()=>{
        dispatch(FormatChooseAction.closeDialog())
    }
    if (selectedFormat==null){
        return(
            <Dialog
                open={open}
                onClose={handleClose}
            >
                <DialogTitle>
                    {"Choose Upload Format"}
                </DialogTitle>
                <DialogContent>
                    <Button onClick={handleButtonClick(FORMAT_TYPE.DICOM)}>Dicom</Button>
                    <Button onClick={handleButtonClick(FORMAT_TYPE.IMAGE)}>Image</Button>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>취소</Button>
                </DialogActions>
            </Dialog>)
    }
    else if(selectedFormat==FORMAT_TYPE.DICOM){
        alert("dicom!")
        return(
            <div/>
        )
    }
    else{
        alert("image!")
        return(
            <FileUploadDialog/>
        )
    }
}
export default FormatChooseDialog