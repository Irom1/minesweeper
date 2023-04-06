import processing.core.PApplet;

public class Timer {
  private PApplet p;
  private int startTime;
  private int endTime;

  public int getTime () {}
  public void start () {}
  public void end () {}
  public void reset () {}

  public Timer (PApplet p) {
    this.p = p;
    startTime = 0;
    endTime = 0;
  }
}