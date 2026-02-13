package org.doit.ik.mreview.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UploadController {
	@PostMapping("/uploadAjax")
	public void uploadFile(@RequestParam("uploadFiles") MultipartFile[] uploadFiles ) {
		for (MultipartFile uploadFile : uploadFiles) {
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
			log.info("fileName: " + fileName);
		}      
	}
}
