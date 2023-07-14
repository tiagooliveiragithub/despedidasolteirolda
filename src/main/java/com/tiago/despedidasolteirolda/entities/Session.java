package com.tiago.despedidasolteirolda.entities;

public class Session {
    public static Person user;
    public static void printUser(){
        System.out.println(user.toString());
    }

    public static void setUser(Person user) {
        Session.user = user;
    }
}
