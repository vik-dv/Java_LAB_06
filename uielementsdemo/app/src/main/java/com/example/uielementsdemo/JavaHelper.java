package com.example.uielementsdemo;


public class JavaHelper {


    public static String buildSummary(String name, String email,
                                      String date)//, Object notifications)
    {
        return "Имя: " + name + "\n"
                + "Email: " + (email.isEmpty() ? "—" : email) + "\n"
                + "Дата: " + (date.isEmpty() ? "не выбрана" : date);
    }

}