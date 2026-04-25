package lv.rvt;

import javax.swing.JFrame;

public class UI {
    public UI() {
        JFrame windows = new JFrame("Schedule helper");
        windows.setSize(1400, 700);
        windows.setVisible(true);
    }
    public static void redOutput() {
        System.out.print("\u001B[31m");
    }
    public static void resetColor() {
        System.out.print("\u001B[0m");
    }
    
}
