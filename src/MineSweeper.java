import processing.core.PApplet;

public class MineSweeper extends PApplet {

  private Board board;
  private Timer timer;
  
  public MineSweeper () {
    board = new Board ();
    timer = new Timer (this);
  }
  
  public void settings() {
    size(400, 400);
  }

  public void setup() {
    fill(255);
    background(152, 190, 100);
  }

  public void draw() {
    ellipse(mouseX, mouseY, 25, 25);
  }

  public void mouseClicked () {
    fill (random (255));
  }
}