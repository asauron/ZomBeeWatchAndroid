package com.example.phototaker2.model;

import java.text.NumberFormat;

public class Zombees {
	private long id;
	private String title;
	private String numberofbees;
	private String method;

	private double lattitude;
	private double longitude;

	private String image1;
	private String image2;
	private String image3;

	private String notes1;
	private String  notes2;
	private String notes3;

	private String pupae;
	private String flies;

	private String date1;
	private String date2;
	private String date3;



	
	public long getId() {
		return id;
	}
	public void setId(long insertid) {
		this.id = insertid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public String getNumberofbees() {
		return numberofbees;
	}
	public void setNumberofbees(String numberofbees) {
		this.numberofbees = numberofbees;
	}
	
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}


	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude= longitude;
	}


	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getNotes1() {
		return notes1;
	}
	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}

	public String getNotes2() {
		return notes2;
	}
	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}
	public String getNotes3() {
		return notes3;
	}
	public void setNotes3(String notes3) {
		this.notes3 = notes3;
	}

	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}

	public String getDate3() {
		return date3;
	}
	public void setDate3(String date3) {
		this.date3 = date3;
	}

	public String getPuape() {
		return pupae;
	}
	public void setPupae(String pupae) {
		this.pupae = pupae;
	}

	public String getFlies() {
		return flies;
	}
	public void setFlies(String flies) {
		this.flies = flies;
	}
	
	//public 

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return title + "\n(" + nf.format(lattitude) + ")";
	}
}
