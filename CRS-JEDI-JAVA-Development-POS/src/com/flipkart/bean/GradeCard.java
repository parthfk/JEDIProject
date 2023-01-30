package com.flipkart.bean;

import java.util.List;

public class GradeCard {
	private String studentID;
	private float SGPA;
	private String semesterID;
	private List<RegisteredCourse> courseList;
	private List<Grade> grades;
	private boolean gradeCardApproved;
	
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

	public List<RegisteredCourse> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<RegisteredCourse> courseList) {
		this.courseList = courseList;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public boolean isGradeCardApproved() {
		return gradeCardApproved;
	}

	public void setGradeCardApproved(boolean gradeCardApproved) {
		this.gradeCardApproved = gradeCardApproved;
	}

	
	public void displayGradeCard() {
		//implementation
	}
}
