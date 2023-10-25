package baseNoStates.doorStates;
import baseNoStates.Door;

import java.util.concurrent.locks.Lock;

// This class slightly resembles its Locked counterpart but this class
// has to remember if it's open or close, since an unlocked door might be closed
// or opened, thats why whe now have a variable called "open"
public class Unlocked extends DoorState {
  private boolean open;
  public Unlocked(Door door) {
    super(door);
    this.open = false;
  }

  // If a door is called to be opened, if it's opened, it fails
  // if it's close, then it opens
  @Override
  public boolean open() {
    if (!open) {
      this.open = true;
      return true;
    } else {
      return false;
    }
  }

  // Opposed to above
  @Override
  public boolean close() {
    if (open) {
      this.open = false;
      return true;
    } else {
      return false;
    }
  }

  // An unlocked door cannot be unlocked
  @Override
  public boolean unlock() {
    return false;
  }

  // An unlocked door can change to locked state
  // but only if it's already closed
  @Override
  public boolean lock() {
    if (!open) {
      Door door = super.getDoor();
      door.setDoorState(new Locked(door));
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "unlocked";
  }

  @Override
  public boolean isClosed() {
    if (this.open) {
      return false;
    } else {
      return true;
    }
  }
}
