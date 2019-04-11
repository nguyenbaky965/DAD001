package com.example.kynguyenba.training;

import java.io.Serializable;

/**
 * Created by ky.nguyenba on 4/11/2019.
 */

public class Candidate implements Serializable {

    String fullname = "Nguyen Ba Ky";
    String email = "ngbaky47@gmail.com";
    String phone = "0905893508";
    String gender = "Male";
    String postion = "Programmer";
    String location = "Da Nang";
    String language = "English";

    public Candidate(String fullname, String email, String phone, String gender, String postion, String location, String language) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.postion = postion;
        this.location = location;
        this.language = language;
    }

    @Override
    public String toString() {
        return "Full Name: " +fullname+ "\n" + "Email: "+ email + "\n" + "Phone: "+ phone + "\n" + "Gender: "+ gender + "\n" + "Position: "+ postion + "\n" + "Location: "+ location + "\n" + "Language: "+ language + "\n";
    }
}
