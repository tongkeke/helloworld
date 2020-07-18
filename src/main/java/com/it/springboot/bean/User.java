package com.it.springboot.bean;

public class User {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String userName;
    private Integer age;
    private String address;
    private String mobilePhone;
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public Integer getAge() {
        return age;
    }
 
    public void setAge(Integer age) {
        this.age = age;
    }
 
    public String getAddress() {
        return address;
    }
 
    public void setAddress(String address) {
        this.address = address;
    }
 
    public String getMobilePhone() {
        return mobilePhone;
    }
 
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                '}';
    }
}
