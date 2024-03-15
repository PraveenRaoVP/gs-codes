package com.interviewpanel;

import com.interviewpanel.auth.LoginView;

public class InterviewPanelApplication {

    private static InterviewPanelApplication instance;
    private String appName = "Interview Panel Application";
    private String appVersion = "1.0.0";
    private InterviewPanelApplication() {
    }

    public static InterviewPanelApplication getInstance() {
        if (instance == null) {
            instance = new InterviewPanelApplication();
        }
        return instance;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void create() {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public static void main(String[] args) {
        InterviewPanelApplication.getInstance().create();
    }
}