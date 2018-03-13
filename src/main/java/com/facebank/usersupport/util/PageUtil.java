package com.facebank.usersupport.util;

public class PageUtil {

    public static int getLastPage(int counts,int totalNumber ){
        int lastPage = totalNumber/counts;
        if (totalNumber%counts>0){
            lastPage++;
        }
        return lastPage;
    };


}
