package baseNoStates;

import baseNoStates.Jerarchy.Space;
import baseNoStates.doorStates.*;
import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

import java.util.ArrayList;

// In this clase now the state is stored in an object of a class called DoorState
public class Door {
  private final String id;
  private DoorState doorState;
  private Space from;
  private Space to;

  public Door(String id, Space iFrom, Space iTo) {
    this.id = id;
    this.doorState = new Locked(this);
    this.from = iFrom;
    this.to = iTo;
    this.from.addDoor(this);
    this.to.addDoor(this);
  }

  public void setDoorState(DoorState input_door_state) {
    this.doorState = input_door_state;
  }

  public void processRequest(RequestReader request) {
    // it is the Door that process the request because the door has and knows
    // its state, and if closed or open
    if (request.isAuthorized()) {
      String action = request.getAction();
      doAction(action);
    } else {
      System.out.println("not authorized");
    }
    request.setDoorStateName(getLocked());
  }

  // The function below has changed so it follows the functionality of the DoorState class instead
  // of checking built-in variables to check if it's closed, open, locked or unlocked
  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        if (!this.doorState.open()) {
          System.out.println("Could not open the door, presumably already open or locked");
        }
        break;
      case Actions.CLOSE:
        if (!this.doorState.close()) {
          System.out.println("Could not close the door, presumably already closed or unlocked");
        }
        break;
      case Actions.LOCK:
        if (!this.doorState.lock()) {
          System.out.println("Could not lock the door, presumably already locked or open");
        }
        break;
      case Actions.UNLOCK:
        if (!this.doorState.unlock()) {
          System.out.println("Could not unlock the door, presumably already unlocked or open");
        }
        break;
      case Actions.UNLOCK_SHORTLY:
        // TODO
        System.out.println("Action " + action + " not implemented yet");
        break;
      default:
        assert false : "Unknown action " + action;
        System.exit(-1);
    }
  }

  public boolean isClosed() {
    return this.doorState.isClosed();
  }

  public String getId() {
    return id;
  }
  public ArrayList<Space> getSpaces() {
    ArrayList<Space> ret = new ArrayList<>();
    ret.add(this.from);
    ret.add(this.to);
    return ret;
  }

  public String getLocked() {
    return this.doorState.toString();
  }

  public Space getFromSpace() { return this.from; }
  public Space getToSpace() { return this.to; }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + this.doorState.toString()
        + ", state=" + getLocked()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getLocked());
    json.put("closed", this.doorState.toString());
    return json;
  }

}
