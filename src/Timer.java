import processing.core.PApplet;

/**
 * Timer class for timing events
 */
public class Timer {
  private PApplet p;
  private int startTime;
  private int endTime;

  /**
   * Gets the time in seconds that the timer has been running
   * 
   * @return time in seconds
   */
  public int getTime() {
    if (startTime == 0) {
      return 0;
    }
    if (endTime == 0) {
      return (p.millis() - startTime) / 1000;
    }
    return (endTime - startTime) / 1000;
  }

  /**
   * Starts the timer
   * 
   * @return time in milliseconds
   */
  public void start() {
    startTime = p.millis();
  }

  /**
   * Stops the timer
   */
  public void stop() {
    endTime = p.millis();
  }

  /**
   * Resets the timer
   */
  public void reset() {
    startTime = 0;
    endTime = 0;
  }

  /**
   * Constructor
   * 
   * @param p PApplet
   */
  public Timer(PApplet p) {
    this.p = p;
    startTime = 0;
    endTime = 0;
  }
}