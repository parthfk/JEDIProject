package com.flipkart.bean;

import java.util.List;


/**
 * GradeCard Class- to store details to be presented in student grade card
 * Contains variables and their getter setter functions
 */
public class GradeCard {
	private String studentID;
	private float SGPA;
	private String semesterID;
	private List<String> courseList;
	private List<Grade> grades;
	
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public float getSGPA() {
		return SGPA;
	}

	public void setSGPA(float sGPA) {
		SGPA = sGPA;
	}

	public String getSemesterID() {
		return semesterID;
	}

	public void setSemesterID(String semesterID) {
		this.semesterID = semesterID;
	}

	public List<String> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<String> courseList) {
		this.courseList = courseList;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
}
