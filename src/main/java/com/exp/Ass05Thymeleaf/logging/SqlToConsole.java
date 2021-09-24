package com.exp.Ass05Thymeleaf.logging;

import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SqlToConsole {

    public void log(String message){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date) + " : " + message); //2016/11/16 12:08:43
    }

}
