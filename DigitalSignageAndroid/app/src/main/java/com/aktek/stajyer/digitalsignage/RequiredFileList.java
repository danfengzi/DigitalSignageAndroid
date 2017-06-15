package com.aktek.stajyer.digitalsignage;

import java.util.List;

import ae.javax.xml.bind.annotation.XmlAccessType;

import ae.javax.xml.bind.annotation.XmlAccessorType;
import ae.javax.xml.bind.annotation.XmlElement;
import ae.javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RequiredFileList")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequiredFileList {
	
	@XmlElement(name = "RequiredFile")
	List<RequiredFile> requiredFileAll = null;

	public List<RequiredFile> getRequiredFileAll() {
		return requiredFileAll;
	}

	public void setRequiredFileAll(List<RequiredFile> requiredFileAll) {
		this.requiredFileAll = requiredFileAll;
	}

	
}
