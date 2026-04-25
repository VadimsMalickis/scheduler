package lv.rvt;

import java.util.List;

import org.openqa.selenium.WebElement;

import lv.rvt.SvgTreeBuilder.SvgNode;

public class Main {

    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler(true);
        // scheduler.getBrowser().minimizeBrowser();
        scheduler.getBrowser().wait(3);

        List<String> classes = scheduler.getBrowser().getAllClasses();
        List<String> teachers = scheduler.getBrowser().getAllTeachers();
        List<String> timeTableWeeks = scheduler.getBrowser().getAvailableTimeTableWeeks();

        WebElement svgElement = scheduler.getBrowser().getSVGTimeTable();
        
        String svgContent = svgElement.getAttribute("outerHTML");

        String ansiRedColor = "\u001B[31m";
        String ansiResetColor = "\u001B[0m";
        System.out.println(ansiRedColor); // Print red color

        SvgNode svgTree = null;
        try {
            svgTree = SvgTreeBuilder.parseSvgToTree(svgContent);
        } catch (Exception e) {
            e.printStackTrace();
            scheduler.getBrowser().quit();
            return;
        }


        System.err.println(ansiResetColor); // Reset color to default

        scheduler.getBrowser().quit();
    }
}