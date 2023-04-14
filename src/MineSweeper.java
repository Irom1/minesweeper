import processing.core.PApplet;

public class MineSweeper extends PApplet {

  private Board board;
  private Timer timer;
  private int size = 12;
  private double difficulty = 0.1;
  String state;
  
  public void settings() {
    size(50 * 16, 50 * (16 + 1));
  }

  public void setup() {
    fill(0);
    board = new Board (size);
    timer = new Timer (this);
    state = "reset";
  }

  public void draw() {
    background(163, 163, 194);
    // display timer at bottom left
    text(timer.getTime(), 20, 50 * size + 20);
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
    // if game is lost or won, reset game
    if (board.isLost() || board.isWon()) {
      resetGame();
    }
    // check if click on board
    if (r >= size) {
      System.out.println("click outside of board");
      // manage here
      return;
    }
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

  public void keyPressed() {
    // e, m, h = easy, medium, hard (change difficulty)
    if (key == 'e') {
      difficulty = 0.1;
    } else if (key == 'm') {
      difficulty = 0.2;
    } else if (key == 'h') {
      difficulty = 0.3;
    }
    // 1, 2, 3 = change board size
    if (key == '1') {
      size = 8;
    } else if (key == '2') {
      size = 12;
    } else if (key == '3') {
      size = 16;
    }
    // r = reset game
    if ("emh123r".contains(key + "")) {
      resetGame();
    }
  }

  /* UI functions */
  public void startGame(int r, int c
  
  
  ) {
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