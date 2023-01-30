package com.flipkart.bean;

import java.util.List;

public class Catalogue {
	private List<Course> offeredCourseList;

	public List<Course> viewCourseList() {
		return offeredCourseList;
	}

	public void setOfferedCourseList(List<Course> offeredCourseList) {
		this.offeredCourseList = offeredCourseList;
	}
	public void addCourse(Course course) {
		
	}
	public void deleteCourse(Course course) {
		
	}
	
}
