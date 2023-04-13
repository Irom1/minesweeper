import processing.core.PApplet;

public class MineSweeper extends PApplet {

  private Board board;
  private Timer timer;
  String state;
  
  public void settings() {
    size(500, 500);
  }

  public void setup() {
    fill(0);
    board = new Board (10);
    timer = new Timer (this);
    state = "reset";
  }

  public void draw() {
    //background(0, 0, 0);
    //ellipse(mouseX, mouseY, 25, 25);
    // display timer
    text(timer.getTime(), 10, 10);
    board.draw(this);
  }

  public void mouseClicked () {
    // get row and column
    int r = mouseY / 50;
    int c = mouseX / 50;
    // if first click, set the mines
    if (state == "reset") {
      startGame(r, c);
    }
    // convert mouseButton to string
    String button = "";
    if (mouseButton == LEFT) {
      button = "left";
    } else if (mouseButton == RIGHT) {
      button = "right";
    } else if (mouseButton == CENTER) {
      button = "center";
    }
    System.out.println(button + " clicked at: row " + r + ", column " + c);
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
  public void startGame(int r, int c) {
    // start timer
    timer.start();
    // set mines
    board.setMines(r, c);
    // set state
    state = "playing";
  }
  public void resetGame() {
    // reset timer
    timer.reset();
    // reset board
    board.create(10);
    // reset state
    state = "reset";
  }
}