import processing.core.PApplet;

public class Board {

  private Tile[][] tiles;

  /**
   * Constructor
   * 
   * @param size size of the board
   */
  public Board(int size) {
    create(size);
  }

  /**
   * Checks if the game is won by checking if all tiles are open
   * 
   * @return true if won
   */
  public boolean isWon() {
    for (int row = 0; row < tiles.length; row++) {
      for (int col = 0; col < tiles[0].length; col++) {
        if (!tiles[row][col].isMine() && !tiles[row][col].isOpen()) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Checks if the game is lost by checking if a mine is open
   * 
   * @return true if lost
   */
  public boolean isLost() {
    for (int row = 0; row < tiles.length; row++) {
      for (int col = 0; col < tiles[0].length; col++) {
        if (tiles[row][col].isMine() && tiles[row][col].isOpen()) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Creates the board with the given size
   * 
   * @param size size of the board
   */
  public void create(int size) {
    tiles = new Tile[size][size];
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        tiles[r][c] = new Tile();
      }
    }
  }

  /**
   * Randomly sets mines on the board
   * Ensures that r,c and the 8 squares around it are not mines
   * 
   * @param r     Row of the first click
   * @param c     Column of the first click
   * @param mines Number of mines to set
   */
  public void setMines(int r, int c, int mines) {
    int count = 0;
    while (count < mines) {
      int row = (int) (Math.random() * tiles.length);
      int col = (int) (Math.random() * tiles[0].length);
      if (!tiles[row][col].isMine() && !(row >= r - 1 && row <= r + 1 && col >= c - 1 && col <= c + 1)) {
        tiles[row][col].setMine();
        count++;
      }
    }
    for (int row = 0; row < tiles.length; row++) {
      for (int col = 0; col < tiles[0].length; col++) {
        tiles[row][col].setNearMines(getNearMines(row, col));
      }
    }
  }

  /**
   * Gets the number of mines around the tile at r,c
   * 
   * @param r the row of the tile
   * @param c the column of the tile
   * @return the number of mines around the tile
   */
  private int getNearMines(int r, int c) {
    int count = 0;
    for (int i = r - 1; i <= r + 1; i++) {
      for (int j = c - 1; j <= c + 1; j++) {
        if (i >= 0 && i < tiles.length && j >= 0 && j < tiles[0].length) {
          if (tiles[i][j].isMine()) {
            count++;
          }
        }
      }
    }
    return count;
  }

  /**
   * Gets the number of flags around the tile at r,c
   * 
   * @param r the row
   * @param c the column
   * @return the number of flags around the tile
   */
  private int getNearFlags(int r, int c) {
    int count = 0;
    for (int i = r - 1; i <= r + 1; i++) {
      for (int j = c - 1; j <= c + 1; j++) {
        if (i >= 0 && i < tiles.length && j >= 0 && j < tiles[0].length) {
          if (tiles[i][j].isFlagged()) {
            count++;
          }
        }
      }
    }
    return count;
  }

  /**
   * Simulates a left click on the tile at r,c
   * reveal tile at r,c
   * if tile is a mine, end game
   * if tile is not a mine, reveal it
   * if tile has no mines around it, reveal surrounding tiles
   * if tile is already open, sweep
   * 
   * @param r the row
   * @param c the column
   */
  public void leftClick(int r, int c) {
    if (tiles[r][c].isOpen()) {
      sweep(r, c);
    }
    if (tiles[r][c].isMine()) {
      tiles[r][c].open();
    } else {
      tiles[r][c].open();
      if (getNearMines(r, c) == 0) {
        revealSurrounding(r, c);
      }
    }
  }

  /**
   * Reveals the surrounding tiles of the tile at r,c
   * 
   * @param r the row
   * @param c the column
   */
  public void revealSurrounding(int r, int c) {
    for (int row = r - 1; row <= r + 1; row++) {
      for (int col = c - 1; col <= c + 1; col++) {
        if (row >= 0 && row < tiles.length && col >= 0 && col < tiles[0].length) {
          if (!tiles[row][col].isOpen()) {
            leftClick(row, col);
          }
        }
      }
    }
  }

  /**
   * Flags the tile at r,c
   * 
   * @param r the row
   * @param c the column
   */
  public void flag(int r, int c) {
    tiles[r][c].toggleFlag();
  }

  /**
   * Sweeps the tiles around the tile at r,c
   * Reveals surrounding tiles if the number of flags around the tile is equal to
   * the number of mines around the tile
   * 
   * @param r the row
   * @param c the column
   */
  public void sweep(int r, int c) {
    if (getNearFlags(r, c) == getNearMines(r, c)) {
      revealSurrounding(r, c);
    }
  }

  /**
   * Reveals all tiles
   */
  public void revealAll() {
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[0].length; j++) {
        tiles[i][j].open(true);
      }
    }
  }

  /**
   * Draws all tiles
   * 
   * @param p the PApplet to draw on
   */
  public void draw(PApplet p) {
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[0].length; j++) {
        tiles[i][j].draw(p, i, j);
      }
    }
  }

}
