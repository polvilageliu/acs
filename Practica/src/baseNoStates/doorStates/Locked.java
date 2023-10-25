package baseNoStates.doorStates;
import baseNoStates.Door;

// This class inherits the doorState class to implement the state locked a Door might have
public class Locked extends DoorState {
  public Locked(Door door) {
    // Super calls the constructor of its inherited class
    super(door);
  }

  // Since the locked state doesnt allow a door to be opened or close
  // (since its always close)
  // any attempt to open or close it will fail as a return false will occur
  @Override
  public boolean open() {
    return false;
  }

  @Override
  public boolean close() {
    return false;
  }

  // A door can change it's state to unlocked (it results in generating a new
  // object of class Unlocked and giving it to the door that changes state)
  @Override
  public boolean unlock() {
    Door door = super.getDoor();
    door.setDoorState(new Unlocked(door));
    return true;
  }

  @Override
  public boolean lock() {
    return false;
  }

  // This is to be used, for example, when using JSON
  @Override
  public String toString() {
    return "locked";
  }

  @Override
  public boolean isClosed() {
    return true;
  }
}
