package com.flipkart.service;

import com.flipkart.bean.GradeCard;

public interface StudentService {
    public void  registerForSem();
    public void reqGradeCard();
    public void signup();
    public void selectPrimaryCourse();
    public void selectSecondaryCourse();
    public void addCourse();
    public void dropCourse();
    public void payFee();
    public GradeCard displayGradeCard ();

}
