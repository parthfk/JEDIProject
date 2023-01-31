package com.flipkart.bean;

import java.util.List;

public class Catalogue {
	private List<Course> offeredCourseList;
	public List<Course> getOfferedCourseList() {
		return offeredCourseList;
	}
	public void setOfferedCourseList(List<Course> offeredCourseList) {
		this.offeredCourseList = offeredCourseList;
	}
}
