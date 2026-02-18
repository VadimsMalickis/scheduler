package lv.rvt;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        // scheduler.getBrowser().minimizeBrowser();
        scheduler.getBrowser().wait(3);

        List<String> classes = scheduler.getBrowser().getAllClasses();
        List<String> teachers = scheduler.getBrowser().getAllTeachers();
        List<String> timeTableWeeks = scheduler.getBrowser().getAvailableTimeTableWeeks();
        System.out.println(timeTableWeeks);
        
        
       scheduler.getBrowser().quit();
    }
}