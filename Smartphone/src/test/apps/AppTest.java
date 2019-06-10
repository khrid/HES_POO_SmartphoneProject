package apps;

import org.junit.jupiter.api.Test;
import smartphone.AppPanel;

class AppTest {

    @Test
    public void testApp() {
        App app = new App("testApp", new AppPanel("testApp"));
        assert "testApp".equals(app.getName());
    }
}