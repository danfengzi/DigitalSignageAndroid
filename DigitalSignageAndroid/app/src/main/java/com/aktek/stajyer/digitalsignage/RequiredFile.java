package com.aktek.stajyer.digitalsignage;

import java.util.Date;
import ae.javax.xml.bind.annotation.XmlAccessType;
import ae.javax.xml.bind.annotation.XmlAccessorType;

import ae.javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "RequiredFile")
@XmlAccessorType(XmlAccessType.FIELD)
public class RequiredFile {
	public String FileType;
    public int Id;
    public Date LastChecked;
    public String Md5;
    public String Path;
    public String SaveAs;

    public boolean Downloading;
    public boolean Complete;
    public boolean Http;

    public double ChunkOffset;
    public double ChunkSize;
    public double Size;
    public int Retrys;

    public int LayoutId;
    public String RegionId;
    public String MediaId;
    
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public Date getLastChecked() {
		return LastChecked;
	}
	public void setLastChecked(Date lastChecked) {
		LastChecked = lastChecked;
	}
	public String getMd5() {
		return Md5;
	}
	public void setMd5(String md5) {
		Md5 = md5;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getSaveAs() {
		return SaveAs;
	}
	public void setSaveAs(String saveAs) {
		SaveAs = saveAs;
	}
	public boolean isDownloading() {
		return Downloading;
	}
	public void setDownloading(boolean downloading) {
		Downloading = downloading;
	}
	public boolean isComplete() {
		return Complete;
	}
	public void setComplete(boolean complete) {
		Complete = complete;
	}
	public boolean isHttp() {
		return Http;
	}
	public void setHttp(boolean http) {
		Http = http;
	}
	public double getChunkOffset() {
		return ChunkOffset;
	}
	public void setChunkOffset(double chunkOffset) {
		ChunkOffset = chunkOffset;
	}
	public double getChunkSize() {
		return ChunkSize;
	}
	public void setChunkSize(double chunkSize) {
		ChunkSize = chunkSize;
	}
	public double getSize() {
		return Size;
	}
	public void setSize(double size) {
		Size = size;
	}
	public int getRetrys() {
		return Retrys;
	}
	public void setRetrys(int retrys) {
		Retrys = retrys;
	}
	public int getLayoutId() {
		return LayoutId;
	}
	public void setLayoutId(int layoutId) {
		LayoutId = layoutId;
	}
	public String getRegionId() {
		return RegionId;
	}
	public void setRegionId(String regionId) {
		RegionId = regionId;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
    
    
}
