package com.haha.myself.mvvm;

/**
 * @author xj
 * Created by xj on 2018/8/3.
 */
public class User {

    private String UserId;
    private String userNmae;
    private int age;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserNmae() {
        return userNmae;
    }

    public void setUserNmae(String userNmae) {
        this.userNmae = userNmae;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", userNmae='" + userNmae + '\'' +
                ", age=" + age +
                '}';
    }
}
