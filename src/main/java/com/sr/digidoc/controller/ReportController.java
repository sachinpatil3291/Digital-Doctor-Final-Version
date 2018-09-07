package com.sr.digidoc.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sr.digidoc.model.BaseResponse;
import com.sr.digidoc.model.Report;
import com.sr.digidoc.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping(value = "/api/reports/{SFHC}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Report>> getReports(@PathVariable("SFHC")String SFHC) {
		List<Report> reportList = reportService.findBySFHC(SFHC);
		return new ResponseEntity<List<Report>>(reportList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/reports",headers=("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<BaseResponse> saveReports(
			MultipartHttpServletRequest req,@RequestParam("data") String data) throws JsonParseException, 
			JsonMappingException, IOException {
		List<MultipartFile> files = req.getFiles("files");
		ObjectMapper reportObjMapper = new ObjectMapper();
		Report reportObj = reportObjMapper.readValue(data, Report.class);
		reportObj.setReportImages(files);
		reportService.save(reportObj);
		BaseResponse response = new BaseResponse();
		response.setStatusCode(0);
		response.setSuccessMsg("Report submitted successfully");
		return new ResponseEntity<BaseResponse>(new BaseResponse(), HttpStatus.OK);
	}
	
}
