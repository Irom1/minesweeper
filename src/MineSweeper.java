import processing.core.PApplet;

public class MineSweeper extends PApplet {

  private Board board;
  private Timer timer;
  
  public MineSweeper () {
    board = new Board ();
    timer = new Timer (this);
  }
  
  public void settings() {
    size(500, 500);
  }

  public void setup() {
    fill(255);
    startGame();
  }

  public void draw() {
    //background(0, 0, 0);
    //ellipse(mouseX, mouseY, 25, 25);
    // display timer
    text(timer.getTime(), 10, 10);

  }

  public void mouseClicked () {
    // get row and column
    int r = mouseY / 50;
    int c = mouseX / 50;
    // if left click
    if (mouseButton == LEFT) {
      // reveal tile
      board.reveal(r, c);
    }
    // if right click
    if (mouseButton == RIGHT) {
      // flag tile
      board.flag(r, c);
    }
    // if middle click
    if (mouseButton == CENTER) {
      // reveal surrounding tiles
      board.sweep(r, c);
    }
  }

  /* UI functions */
  public void startGame() {
    // start timer
    timer.start();
    // set mines
    board.setMines(10, 10);
  }
}