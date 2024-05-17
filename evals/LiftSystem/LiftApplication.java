package evals.LiftSystem;

import evals.LiftSystem.UI.MainMenu;

// lift system
// consider 5 lifts and 10 floors

public class LiftApplication {
    private static LiftApplication LiftApplication = null;

    private LiftApplication() {}

    public static LiftApplication getInstance() {
        if(LiftApplication == null) {
            LiftApplication = new LiftApplication();
        }
        return LiftApplication;
    }

    public static void main(String[] args) {
        MainMenu menu = new MainMenu();
        menu.init();
    }
}
