package com.java_simple.codes.reflect;

import com.java_simple.codes.PKt;

public class User {
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
}
