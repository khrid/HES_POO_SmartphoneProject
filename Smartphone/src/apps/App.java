package apps;

import smartphone.AppPanel;

public class App {
    private String name;
    private AppPanel panel;
    public App(String name, AppPanel panel) {
        this.name = name;
        this.panel = panel;
    }

    public String getName() {
        return name;
    }

    public AppPanel getPanel() {
        return panel;
    }
}