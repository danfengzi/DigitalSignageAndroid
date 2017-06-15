package com.aktek.stajyer.digitalsignage;




import ae.javax.xml.bind.annotation.XmlAccessType;
import ae.javax.xml.bind.annotation.XmlAccessorType;
import ae.javax.xml.bind.annotation.XmlElement;
import ae.javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RequiredFiles")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequiredFiles {
	
	@XmlElement(name = "RequiredFileList")
	RequiredFileList requiredFileList = null;

	public RequiredFileList getRequiredFileList() {
		return requiredFileList;
	}

	public void setRequiredFileList(RequiredFileList requiredFileList) {
		this.requiredFileList = requiredFileList;
	}

}
