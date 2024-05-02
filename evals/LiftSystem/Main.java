package evals.LiftSystem;

import evals.LiftSystem.UI.MainMenu;

// lift system
// consider 5 lifts and 10 floors

public class Main {
    private static Main main = null;

    private Main() {}

    public static Main getInstance() {
        if(main == null) {
            main = new Main();
        }
        return main;
    }

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.init();
    }
}
