import processing.core.PApplet;

public class Board {

  private Tile[][] tiles;

  // constructor
  public Board(int size) {
    create(size);
  }

  public boolean isLost() {
    // later
    return false;
  }

  public boolean isWon() {
    // later
    return false;
  }

  public void create(int size) {
    // create a 2D array of tiles
    tiles = new Tile[size][size];
    for (int r = 0; r < size; r++) {
      for (int c = 0; c < size; c++) {
        tiles[r][c] = new Tile();
      }
    }
  }

  public void setMines(int r, int c, int mines) {
    // set 30 mines randomly, ensure that r,c and the 8 squares around it are not mines using a while loop
    int count = 0;
    while (count < mines) {
      int row = (int) (Math.random() * tiles.length);
      int col = (int) (Math.random() * tiles[0].length);
      if (!tiles[row][col].isMine() && !(row >= r - 1 && row <= r + 1 && col >= c - 1 && col <= c + 1)) {
        tiles[row][col].setMine();
        count++;
      }
    }
    for(int row = 0; row < tiles.length; row++) {
      for(int col = 0; col < tiles[0].length; col++) {
        tiles[row][col].setNearMines(getNearMines(row, col));
      }
    }
  }

  private int getNearMines(int r, int c) {
    // checks the 8 tiles around the tile at r,c
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

  private int getNearFlags(int r, int c) {
    // checks the 8 tiles around the tile at r,c
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

  public void leftClick(int r, int c) {
    // reveal tile at r,c
    // if tile is a mine, end game
    // if tile is not a mine, reveal it
    // if tile has no mines around it, reveal surrounding tiles
    // if tile is already open, run sweep
    if(tiles[r][c].isOpen()) {
      sweep(r, c);
      return;
    }
    if (tiles[r][c].isMine()) {
      revealAll();
    } else {
      tiles[r][c].open();
      if (getNearMines(r, c) == 0) {
        revealSurrounding(r, c);
      }
    }
  }

  public void revealSurrounding(int r, int c) {
    // reveal surrounding tiles
    // doesn't work rn as wanted
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

  public void flag(int r, int c) {
    // flag tile at r,c
    tiles[r][c].toggleFlag();
  }

  public void sweep(int r, int c) {
    // if the number of flags around the tile is equal to the number of mines around
    // the tile, reveal surrounding tiles
    if (getNearFlags(r, c) == getNearMines(r, c)) {
      revealSurrounding(r, c);
    }
  }

  public void revealAll() {
    // reveal all tiles
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[0].length; j++) {
        tiles[i][j].open(true);
      }
    }
  }

  public void draw(PApplet p) {
    // draw all tiles
    for (int i = 0; i < tiles.length; i++) {
      for (int j = 0; j < tiles[0].length; j++) {
        tiles[i][j].draw(p, i, j);
      }
    }
  }

}
