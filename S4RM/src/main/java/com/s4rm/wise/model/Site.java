package com.s4rm.wise.model;

public class Site {
	
	/**
	 * Class Name:	Site.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.14
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 *  CREATE TABLE `Site` (
	 *  `SiteID` int(11) NOT NULL AUTO_INCREMENT,
	 *  `SiteName` varchar(50) NOT NULL,
	 *  `LandType` varchar(25) NOT NULL,
	 *  `Latitude` float NOT NULL,
	 *  `Longitude` float NOT NULL,
	 *  `Address` varchar(200) NOT NULL,
	 *  `Image` varchar(200) DEFAULT NULL,
	 *  `Description` varchar(100) DEFAULT '0',
	 *  PRIMARY KEY (`SiteID`)
	 *  ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
	 *  
	 */
	private int SiteID;
	private String SiteName;
	private String LandType;
	private String Latitude;
	private String Longitude;
	private String Address;
	private String ImageLink;
	private String Description;
	
	public int getSiteID() {
		return SiteID;
	}
	public void setSiteID(int siteID) {
		SiteID = siteID;
	}
	public String getSiteName() {
		return SiteName;
	}
	public void setSiteName(String siteName) {
		SiteName = siteName;
	}
	
	public String getLandType() {
		return LandType;
	}
	public void setLandType(String landType) {
		LandType = landType;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

	public String getImageLink() {
		return ImageLink;
	}
	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String toString() {
		return "Site [SiteID=" + SiteID + ", SiteName=" + SiteName + ", LandType=" + LandType + ", Latitude=" + Latitude
				+ ", Longitude=" + Longitude + ", Address=" + Address + ", ImageLink=" + ImageLink + ", Description="
				+ Description + "]";
	}
		
}
