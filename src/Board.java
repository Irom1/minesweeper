import processing.core.PApplet;

public class Board {

  private Tile[][] tiles;

  public boolean isLost() {
    // later
    return false;
  }

  public boolean isWon() {
    // later
    return false;
  }

  public void setMines(int r, int c) {
    // set mines randomly
    tiles = new Tile[r][c];
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (Math.random() < 0.1) {
          tiles[i][j] = new Tile(true);
        } else {
          tiles[i][j] = new Tile(false);
        }
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

  public void reveal(int r, int c) {
    // reveal tile at r,c
    // if tile is a mine, end game
    // if tile is not a mine, reveal it
    // if tile has no mines around it, reveal surrounding tiles
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
    for (int i = r - 1; i <= r + 1; i++) {
      for (int j = c - 1; j <= c + 1; j++) {
        if (i >= 0 && i < tiles.length && j >= 0 && j < tiles[0].length) {
          if (!tiles[i][j].isOpen()) {
            reveal(i, j);
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
        tiles[i][j].open();
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
