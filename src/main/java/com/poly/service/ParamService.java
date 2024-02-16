package com.poly.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ParamService {

	@Autowired
	HttpServletRequest req;

	public String getString(String name, String defaulValue) {
		if (name != null) {
			return req.getParameter(name);
		}
		return defaulValue;
	}

	public int getInt(String name, int defaulValue) {
		if (name != null) {
			return Integer.parseInt(req.getParameter(name));
		}
		return defaulValue;
	}

	public double getDouble(String name, double defaulValue) {
		if (name != null) {
			return Double.parseDouble(req.getParameter(name));
		}
		return defaulValue;
	}

	public boolean getBoolean(String name, boolean defaulValue) {
		if (name != null) {
			return Boolean.parseBoolean(req.getParameter(name));
		}
		return defaulValue;
	}

	public Date getDate(String name, Date pattern) throws ParseException {
		String value = req.getParameter(name);
		if (value == null) {
			return pattern;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(value);
	}

	public File save(MultipartFile file, String path) {
		File dir = new File(req.getServletContext().getRealPath(path));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try {
			File saveFile = new File(dir, file.getOriginalFilename());
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
