package com.flipkart.bean;


/**
 * Course Class- to store details about all the courses present
 * Contains variables and their getter setter functions
 */
public class Course {
	private String courseID;
	private String name;
	private String professorID;
	private int availableSeats = 10;

	/**
	 * constructor
	 * @param courseID
	 * @param name
	 * @param professorID
	 * @param availableSeats
	 */
	public Course(String courseID, String name, String professorID, int availableSeats) {
		this.courseID = courseID;
		this.name = name;
		this.professorID = professorID;
		this.availableSeats = availableSeats;
	}
	public Course(){

	}
	public Course(String courseID, String name, int availableSeats) {
		this.courseID = courseID;
		this.name = name;
		this.availableSeats = availableSeats;
	}
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfessorID() {
		return professorID;
	}
	public void setProfessorID(String professorID) {
		this.professorID = professorID;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	@Override
	public String toString() {
		return "Course ID: " + courseID + "; Course Name: " + name + "; Professor ID: " + professorID
				+ "; available seats: " + availableSeats + "\n";
	}
}
