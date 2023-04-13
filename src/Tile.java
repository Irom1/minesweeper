import processing.core.PApplet;

public class Tile {
    private final int CLOSED = 0;
    private final int FLAGGED = 1;
    private final int OPEN = 2;
    private int state;
    private boolean hasMine;
    private int nearMines;

    // constructor - sets all the instance variables
    public Tile() {
      state = CLOSED;
      hasMine = false;
      nearMines = 0;
    }
    // ac
    public boolean isFlagged () {
      return state == FLAGGED;
    }
    public boolean isOpen () {
      return state != CLOSED;
    }
    public boolean isMine () {
      return hasMine;
    }
    public int getNearMines () {
      return nearMines;
    }
    // mutators
    public void toggleFlag () {
      if (state == CLOSED) {
        state = FLAGGED;
      } else if (state == FLAGGED) {
        state = CLOSED;
      }
    }
    public void setMine () {
      hasMine = true;
    }
    public void setNearMines (int mines) {
      nearMines = mines;
    }
    public void open () {
      state = OPEN;
    }
    /* Draw functionality */
    public void draw (PApplet p, int y, int x) {
      int size = 50;
      p.stroke(1);
      p.fill(200);
      p.rect(x * size, y * size, size, size);
      if (state == OPEN) {
        if (hasMine) {
          p.fill(0);
          p.ellipse(x * size+ size/2, y * size+ size/2, size/2, size/2);
        } else {
          p.fill(255);
          p.rect(x * size, y * size, size, size);
          p.fill(0);
          p.text(nearMines, x * size + size/2, y * size + size/2);
        }
      } else if (state == FLAGGED) {
        p.fill(255, 0, 0);
        p.triangle(x * size, y * size, x * size, y * size, x, y * size);
      }
      //System.out.println("Drawing tile at " + x + ", " + y);
    }
  }
