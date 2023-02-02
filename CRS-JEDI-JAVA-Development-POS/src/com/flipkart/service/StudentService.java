package com.flipkart.service;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;

public interface StudentService {
    public void registerForSem();
    public void signup();
    public void selectPrimaryCourse();
    public void selectSecondaryCourse();
    public void addCourse();
    public void dropCourse();
    public void payFee();
    public GradeCard displayGradeCard ();

}
