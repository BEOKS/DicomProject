package com.knuipalab.dsmp.controller.dicom.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.knuipalab.dsmp.service.orthanc.OrthancService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
public class DicomApiController {

    OrthancService orthancService = new OrthancService();

    @PostMapping("/api/dicom")
    public JsonNode uploadDicom(@RequestPart("dicomfile") MultipartFile file) throws IOException {
        return orthancService.uploadDicom(file);
    }

    @GetMapping("/api/patient/{id}/study")
    public JsonNode getStudyListByPatientID(@PathVariable String id) throws IOException {
         return orthancService.getStudyListByPatientID(id);
    }

    @DeleteMapping("/api/patient/{id}")
    public ResponseEntity deletePatientByPatientID(@PathVariable String id) throws IOException {

        String patientUUID = orthancService.getPatinetUuidByPatientID(id);

        if( patientUUID == "-1" ){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ResponseEntity response = orthancService.deletePatientbyPatientUUID(patientUUID);
        return response;
    }

    @GetMapping("/api")
    public String test(@RequestParam MultipartFile file){
        System.out.println(file.getSize());
        return "Welcome DSMP Service";
    }
}