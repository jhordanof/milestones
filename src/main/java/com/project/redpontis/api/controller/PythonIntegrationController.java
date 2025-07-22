package com.project.redpontis.api.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.project.redpontis.api.interfaces.IPythonIntegrationController;
import com.project.redpontis.client.PythonServiceClient;

@RestController
public class PythonIntegrationController implements IPythonIntegrationController {

    @Autowired
    private PythonServiceClient pythonServiceClient;

    @Override
    public ResponseEntity<String> ping() {
        String response = pythonServiceClient.ping();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<String> convert(Map<String, Object> payload) {
        String result = pythonServiceClient.convert(payload);
        return ResponseEntity.ok(result);
    }
}