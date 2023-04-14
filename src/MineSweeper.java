import processing.core.PApplet;

public class MineSweeper extends PApplet {

  private Board board;
  private Timer timer;
  private int size = 12;
  private double difficulty = 0.1;
  String state;
  
  public void settings() {
    size(50 * size, 50 * (size + 1));
  }

  public void setup() {
    fill(0);
    board = new Board (size);
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
    } 
    System.out.println(button + " clicked at: row " + r + ", column " + c);
    // if left click
    if (mouseButton == LEFT) {
      // reveal tile
      board.leftClick(r, c);
    }
    // if right click
    if (mouseButton == RIGHT) {
      // flag tile
      board.flag(r, c);
    }
  }

  /* UI functions */
  public void startGame(int r, int c) {
    // start timer
    timer.start();
    // set mines
    int mines = (int) ((double) size * size * difficulty);
    board.setMines(r, c, mines);
    // set state
    state = "playing";
  }
  public void resetGame() {
    // reset timer
    timer.reset();
    // reset board
    board.create(size);
    // reset state
    state = "reset";
  }
}