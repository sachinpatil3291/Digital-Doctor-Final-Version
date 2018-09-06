package com.sr.digidoc.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="image")
public class FileProperties {
	private String imageuploaddir;
	private String imagedownloadpath;
	
	public String getImageuploaddir() {
		return imageuploaddir;
	}
	public void setImageuploaddir(String imageuploaddir) {
		this.imageuploaddir = imageuploaddir;
	}
	public String getImagedownloadpath() {
		return imagedownloadpath;
	}
	public void setImagedownloadpath(String imagedownloadpath) {
		this.imagedownloadpath = imagedownloadpath;
	}
}
