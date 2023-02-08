package com.flipkart.bean;

/**
 * RegisteredCourse Class- to store details about the courses registered by the student
 * Contains variables and their getter setter functions
 */
public class RegisteredCourse {
	private String courseID;
	private String studentID;
	private Grade grade;
	private String semesterID;

	/**
	 * constructor
	 * @param courseID
	 * @param studentID
	 * @param semesterID
	 */
	public RegisteredCourse(String courseID, String studentID,String semesterID){
		this.courseID = courseID;
		this.studentID = studentID;
		this.semesterID = semesterID;
		this.grade = null;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public String getSemesterID() {
		return semesterID;
	}
	public void setSemesterID(String semesterID) {
		this.semesterID = semesterID;
	}

}
