package com.flipkart.bean;

import java.util.List;

/**
 * Catalogue Class- to store details about all the courses present in this semester
 * Contains variables and their getter setter functions
 */
public class Catalogue {
	private List<Course> offeredCourseList;
	public List<Course> getOfferedCourseList() {
		return offeredCourseList;
	}
	public void setOfferedCourseList(List<Course> offeredCourseList) {
		this.offeredCourseList = offeredCourseList;
	}
}
