package com.example.common.tools;

import java.util.ArrayList;
import java.util.List;

public class Tranfer {
    /**
     * 换行分割
     * @param str
     * @return
     */
    public static List<String> transfer(String str){
        List<String> list = new ArrayList<>();
        String [] strs = str.split("\r\n");
        for(String temp : strs){
            list.add(temp);
        }
        return list;
    }
}
