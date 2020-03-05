package com.example.SmallSpace.main;

import javax.persistence.*;


@Entity //告诉springboot这是实体类，有需要，来这找
@Table(name = "db_user") //对应数据库中的表，配合application.properties可以自动生成student表
public class Student {

    @Id //设置主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增
    private Integer id;
    private int userAccount;
    private String userName;

    private String user_password;

    private int  user_age=0;

    private int user_sex=0;

    private long user_birthday=0;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", userAccount='" + userAccount + '\'' +
                ", userName=" + userName +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(int userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public long getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(long user_birthday) {
        this.user_birthday = user_birthday;
    }
}


