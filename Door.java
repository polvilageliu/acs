package baseNoStates;

import java.lang.System;
import java.lang.String;

import baseNoStates.requests.RequestReader;
import org.json.JSONObject;

public class Door {
  private final String id;
  private boolean closed; // physically
  private DoorState doorState;

  public Door(String id) {
    this.id = id;
    closed = true;
    this.doorState = new Unlocked(this);
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
    request.setDoorStateName(getStateName());
  }

  private void doAction(String action) {
    switch (action) {
      case Actions.OPEN:
        System.out.println("Opening door");

        if(doorState.toString()!="locked") {
          if (closed) {

            closed = false;
            //} else if (this.getDoorState() == Locked(this)) {
            // System.out.println("Can't open door " + id + " because it's locked");
          } else {
            System.out.println("Can't open door " + id + " because it's already open");
          }
        }
        break;
      case Actions.CLOSE:
        if (closed) {
          System.out.println("Can't close door " + id + " because it's already closed");
        } else {
          closed = true;
        }
        break;
      case Actions.LOCK:
        if(closed) {
          System.out.println("Locking door");
          // TODO
          this.doorState.lock();
          // fall through
        }
        break;
      case Actions.UNLOCK:
        // TODO
        this.doorState.unlock();
        // fall through
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
    return closed;
  }

  public String getId() {
    return id;
  }

  public String getStateName() {
    return doorState.toString();
  }

  @Override
  public String toString() {
    return "Door{"
        + ", id='" + id + '\''
        + ", closed=" + closed
        + ", state=" + getStateName()
        + "}";
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("id", id);
    json.put("state", getStateName());
    json.put("closed", closed);
    return json;
  }

  //Metodo para obtener el estado actual de la puerta
  public DoorState getDoorState() {
    return doorState;
  }

  public void changeState(DoorState doorState) {
    this.doorState = doorState;
  }
}
