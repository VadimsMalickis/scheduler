package lv.rvt;

public class Scheduler {
    private UI ui;
    private Browser browser;

    public Scheduler() {
        // this.ui = new UI();
        this.browser = new Browser();
    }
    public Browser getBrowser() {
        return this.browser;
    }

    
}
