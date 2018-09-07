package com.sr.digidoc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sr.digidoc.model.Image;
import com.sr.digidoc.model.Report;
import com.sr.digidoc.repository.ReportRepository;
import com.sr.digidoc.util.FileProperties;

@Transactional
@Service
public class ReportService {
	
	@Autowired
	ReportRepository reportRepository;
	
	@Autowired
	FileProperties fileProperties;

	public void save(Report report) {
		List<MultipartFile> reportImages = report.getReportImages();
		reportImages.forEach((element)->{
			Image imageObj = new Image();
			File dir = new File(fileProperties.getImageuploaddir());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File serverFile = new File(dir.getAbsolutePath()+ File.separator +
					element.getOriginalFilename());
			try {
				element.transferTo(serverFile);
				 /* String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			                .path("/downloadFile/")
			                .path(element.getOriginalFilename())
			                .toString();*/
				imageObj.setImageURL(fileProperties.getImagedownloadpath() + "/" + element.getOriginalFilename());
			} catch (IllegalStateException e) {
				System.out.println("Unable to transfer file..");
			} catch (IOException e) {
				System.out.println("Unable to transfer file..");
			}
			reportRepository.save(report);
		});
	}

	public List<Report> findBySFHC(String SFHC) {
		List<Report> reportList = reportRepository.findBySFHC(SFHC);
		return reportList;
	}
}
