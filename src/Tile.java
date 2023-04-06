public class Tile {
    private final int CLOSED = 0;
    private final int FLAGGED = 1;
    private final int OPEN = 2;
    private int state = CLOSED;
    private boolean hasMine;
    private int nearMines;
    
    public boolean isFlagged () {}
    public boolean isOpen () {}
    public boolean isClosed () {}
    public boolean isMine () {}
    public int getNearMines () {}
    public void toggleFlag () {}
    public void setNearMines (int mines) {}
    public void open () {}
  }
