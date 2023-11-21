package baseNoStates;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

// Clock class extending Observable
class Clock extends Observable {

  private Clock instant=null;

  public Clock getInstance()
  {
   if (instant==null)
   {
     instant=new Clock();
     return instant;
   } else
     return instant;
  }
  public void runClock() {
    Timer timer = new Timer();

    // Schedule a TimerTask to notify observers every second
    timer.scheduleAtFixedRate(new TimerTask() {
      public void run() {
        setChanged();
        notifyObservers();
      }
    }, 0, 1000); // Notify every 1000 milliseconds (1 second)
  }
}