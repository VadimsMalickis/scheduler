package lv.rvt;

public class Scheduler {
    private Browser browser;

    public Scheduler(boolean headless) {
        this.browser = new Browser(headless);
    }
    public Browser getBrowser() {
        return this.browser;
    }

    
}
