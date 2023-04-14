import processing.core.PApplet;

public class MineSweeper extends PApplet {

  private Board board;
  private Timer timer;
  private int size = 16;
  private double difficulty = 0.2;
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
    // bottom text elms
    fill(0);
    textSize(20);
    textAlign(LEFT, CENTER);
    int size = 16; // max board size for displaying bottom text
    // display timer at bottom left or reset text
    if(state == "reset") {
      text("Click any tile to start", 20, 50 * size + 20);
    } else text(timer.getTime(), 20, 50 * size + 20);
    // display board size at bottom center
    textAlign(CENTER, CENTER);
    String sizeText = "";
    if (this.size == 8) {
      sizeText = "Small";
    } else if (this.size == 12) {
      sizeText = "Medium";
    } else if (this.size == 16) {
      sizeText = "Large";
    }
    text("Size (Press 1, 2 or 3): " + sizeText, 50 * size / 2 - 20, 50 * size + 20);
    // display difficulty at bottom right
    textAlign(RIGHT, CENTER);
    text("Difficulty (Press E, M or H): " + difficulty, 50 * size - 20, 50 * size + 20);
    // display board
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