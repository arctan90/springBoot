package com.irisking.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileTransfor {
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(@RequestParam("file")MultipartFile file, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		
		if (!file.isEmpty()) {
			String fileName = file.getName()+"_"+System.currentTimeMillis()+".clu";
			try (BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File(fileName)))){
				outputStream.write(file.getBytes());
				outputStream.flush();
				result.put("code", 0);
				result.put("msg", "OK");
			} catch (Exception e) {
				// TODO: handle exception
				result.put("code", 1);
				result.put("msg", file.getName()+" upload Failed.");
			}
		}
		return result;
	}
}
