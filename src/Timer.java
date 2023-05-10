import processing.core.PApplet;

public class Timer {
  private PApplet p;
  private int startTime;
  private int endTime;

  public int getTime () {
    // return in seconds
    if (startTime == 0) {
      return 0;
    }
    if (endTime == 0) {
      return (p.millis () - startTime) / 1000;
    }
    return (endTime - startTime) / 1000;
  }
  public void start () {
    startTime = p.millis ();
  }
  public void stop () {
    endTime = p.millis ();
  }
  public void reset () {
    startTime = 0;
    endTime = 0;
  }

  public Timer (PApplet p) {
    this.p = p;
    startTime = 0;
    endTime = 0;
  }
}