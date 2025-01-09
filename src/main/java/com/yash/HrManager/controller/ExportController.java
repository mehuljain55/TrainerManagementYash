package com.yash.HrManager.controller;

import com.yash.HrManager.Entity.models.ApiExportModel;
import com.yash.HrManager.service.ExportService;
import com.yash.HrManager.service.UserAuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExportService exportService;

    @Autowired
    private UserAuthorizationService userAuthorizationService;

    @PostMapping("/trainingList")
    public ResponseEntity<byte[]> exportTrainingList(@RequestBody ApiExportModel exportModel) throws IOException {

        boolean status=userAuthorizationService.validateUserToken(exportModel.getUser().getEmailId(),exportModel.getToken());
     if(status) {
         byte[] excelFile = exportService.exportTrainingList(exportModel.getTrainingList());
         HttpHeaders headers = new HttpHeaders();
         String filename="Training List "+exportModel.getUser().getName();
         headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+filename+".xlsx");
         headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

         return ResponseEntity.ok()
                 .headers(headers)
                 .body(excelFile);
     }else {
         return ResponseEntity.status(401).body("Unauthorized access".getBytes());
     }
    }

}
