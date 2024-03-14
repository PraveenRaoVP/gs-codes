package org.interviewpanel.models.helpers;

public class ContactInfo {
    private String phoneNo;
    private String email;

    public ContactInfo(String phoneNo, String email) {
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
