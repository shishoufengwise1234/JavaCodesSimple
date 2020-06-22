package com.java_simple.codes.reflect;

import com.java_simple.codes.PKt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class User extends Person{
    private final String TAG = this.getClass().getSimpleName();

    private int userId;
    private String userName;

    User(){
        PKt.out("---User()---");
    }

//    private User(Integer userId, String userName) {
//        PKt.out("---User()---userId = "+userId + " userName = "+userName);
//        this.userId = userId;
//        this.userName = userName;
//    }
    private User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private boolean isVipUser(int vip){
        if (vip == 0){
            return false;
        }else if (vip == 1) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public class VipData{

    }

    private class NormalData{

    }

    public interface IUserData{

    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UserType{

    }
}
