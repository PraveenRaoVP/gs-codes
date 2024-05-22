package com.sample;

import com.sample.auth.login.LoginView;

public class Main {
    private static Main main;
    private Main() {

    }

    public static Main getInstance() {
        if(main == null)  {
            main = new Main();
        }
        return main;
    }

    private static String appName = "OneDha";
    private static String version = "1.0.0";

    public void create() {
        System.out.println("Welcome to " + appName + " v" + version);
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static void main(String[] args) {
        Main.getInstance().create();
    }
}