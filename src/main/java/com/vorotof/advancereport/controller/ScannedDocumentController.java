package com.vorotof.advancereport.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scanned-document")
@AllArgsConstructor
@Slf4j
public class ScannedDocumentController implements ScannedDocumentOperations{

}
