import * as React from 'react'
import { Button, Dialog, DialogTitle, DialogContent,
    DialogActions,Alert  } from "@mui/material";
import { useState } from 'react';
import DicomUploadBox from './DicomUploadBox/DicomUploadBox';
import MetaUploadBox from './MetaUploadBox';
import FileHandler from '../Utils/FileHandler';

const dialogContentDescrptionText="메타데이터는 csv의 'PatientID' 속성에는 업로드하려는 Dicom 파일의 ID가 존재해야 합니다. "
const dicomUploadErrorMsg="업로드한 Dicom 파일을 확인해주세요 "

let fileHandler;
export default function UploadDialog(props){
    console.log('Build UploadDialog Component.')
    const [dicomFiles, setdicomFiles]=useState([]);
    const [csvFile, setCsvFile]=useState();


    if(fileHandler===undefined){
        fileHandler=new FileHandler(dicomFiles,csvFile)
    }
    else{
        fileHandler.updateFilePath(dicomFiles,csvFile);
    }
    const updatePossibility=fileHandler.checkUpdatePossibility();

    const haldleOKEvent=()=>{

    }
    const handleClearEvent=()=>{
        props.setOpen(false)
        setCsvFile(undefined)
        setdicomFiles(new Set([]))
    }
    return(
        <Dialog open={props.open}>
            <DialogTitle>Dicom 파일 업로드</DialogTitle>
            <DialogContent>
                <Alert severity="info" > 
                    {dialogContentDescrptionText}
                </Alert>
                {
                    updatePossibility.state==='error' && <Alert severity="error">{dicomUploadErrorMsg}</Alert>
                }
                <MetaUploadBox 
                    csvFile={csvFile}
                    setCsvFile={setCsvFile}
                    setdicomFiles={setdicomFiles}/>
                <DicomUploadBox
                    csvFile={csvFile}
                    dicomFiles={dicomFiles}
                    setdicomFiles={setdicomFiles}
                    errorDicomPathList={updatePossibility["errorDicomPathList"]}/>
            </DialogContent>
            <DialogActions>
                <Button onClick={haldleOKEvent}>확인</Button>
                <Button onClick={handleClearEvent}>취소</Button>
            </DialogActions>
        </Dialog>
    )
}