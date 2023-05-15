import processing.core.PApplet;
import processing.core.PConstants;

public class Tile {
  private final int CLOSED = 0;
  private final int FLAGGED = 1;
  private final int OPEN = 2;
  private int state;
  private boolean hasMine;
  private int nearMines;

  /**
   * Constructor
   */
  public Tile() {
    state = CLOSED;
    hasMine = false;
    nearMines = 0;
  }

  /**
   * Returns true if the tile is flagged
   * 
   * @return true if flagged
   */
  public boolean isFlagged() {
    return state == FLAGGED;
  }

  /**
   * Returns true if the tile is open
   * 
   * @return true if open
   */
  public boolean isOpen() {
    return state == OPEN;
  }

  /**
   * Returns true if the tile has a mine
   * 
   * @return true if mine
   */
  public boolean isMine() {
    return hasMine;
  }

  /**
   * Returns the number of mines near the tile
   * 
   * @return number of mines
   */
  public int getNearMines() {
    return nearMines;
  }

  /**
   * Toggles the flag on the tile
   */
  public void toggleFlag() {
    if (state == CLOSED) {
      state = FLAGGED;
    } else if (state == FLAGGED) {
      state = CLOSED;
    }
  }

  /**
   * Sets the tile to have a mine
   */
  public void setMine() {
    hasMine = true;
  }

  /**
   * Sets the number of mines near the tile
   * 
   * @param mines number of mines
   */
  public void setNearMines(int mines) {
    nearMines = mines;
  }

  /**
   * Opens the tile if not flagged
   */
  public void open() {
    if (state != FLAGGED)
      state = OPEN;
  }

  /**
   * Opens the tile
   * 
   * @param force true to force open even if flagged
   */
  public void open(boolean force) {
    if (force || state != FLAGGED)
      state = OPEN;
  }

  /**
   * Draws the tile
   * 
   * @param p PApplet
   * @param y y coordinate
   * @param x x coordinate
   */
  public void draw(PApplet p, int y, int x) {
    int size = 50;
    p.stroke(1);
    p.fill(200);
    p.rect(x * size, y * size, size, size);
    if (state == OPEN) {
      if (hasMine) {
        p.fill(0);
        p.ellipse(x * size + size / 2, y * size + size / 2, size / 2, size / 2);
      } else {
        p.fill(255);
        p.rect(x * size, y * size, size, size);
        p.fill(0);
        p.textSize(size / 2);
        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.text(nearMines, x * size + 25, y * size + 20);
      }
    } else if (state == FLAGGED) {
      p.fill(255, 0, 0);
      p.triangle(x * size, y * size, x * size + size, y * size, x * size + size / 2, y * size + size / 2);
    }
  }
}
