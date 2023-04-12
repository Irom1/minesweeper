import processing.core.PApplet;

public class Tile {
    private final int CLOSED = 0;
    private final int FLAGGED = 1;
    private final int OPEN = 2;
    private int state = CLOSED;
    private boolean hasMine;
    private int nearMines;

    // constructor
    public Tile(boolean hasMine) {
      this.hasMine = hasMine;
      this.nearMines = 0;
    }
    
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
    public void toggleFlag () {
      if (state == CLOSED) {
        state = FLAGGED;
      } else if (state == FLAGGED) {
        state = CLOSED;
      }
    }
    public void setNearMines (int mines) {
      nearMines = mines;
    }
    public void open () {
      state = OPEN;
    }

    /* Draw function */
    public void draw (PApplet p, int x, int y) {
      int size = 50;
      p.stroke(1);
      // get a random color
      p.fill((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
      //p.fill(200);
      p.rect(x, y, size, size);
      if (state == OPEN) {
        if (hasMine) {
          p.fill(0);
          p.ellipse(x + size / 2, y + size / 2, size / 2, size / 2);
        } else {
          p.fill(255);
          p.rect(x, y, size, size);
          p.fill(0);
          p.text(nearMines, x + size / 2, y + size / 2);
        }
      } else if (state == FLAGGED) {
        p.fill(255, 0, 0);
        p.triangle(x + size / 2, y, x + size, y + size, x, y + size);
      }
    }
  }
