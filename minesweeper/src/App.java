
import processing.core.PApplet;

public class App extends PApplet {
    public void settings() {
        size(200, 200);
    }

    public void draw() {
        background(0);
        ellipse(mouseX, mouseY, 20, 20);
    }

    public static void main(String... args) {
        PApplet.main("App");
    }
}
