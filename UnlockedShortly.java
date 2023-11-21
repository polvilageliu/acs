package baseNoStates.doorStates;

import baseNoStates.Door;

public class UnlockedShortly extends DoorState
{
  private boolean open;
  public UnlockedShortly(Door door) {
    super(door);
    this.open = false;
  }
  @Override
  public boolean open() {
    if (!open) {
      this.open = true;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean close() {
    if (open) {
      this.open = false;
      return true;
    } else {
      return false;
    }
  }
  @Override
  public boolean unlock() {
    return true;
  }

  @Override
  public boolean lock() {
    return false;
  }
  public String toString() {
    return "unlock_shortly";
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


