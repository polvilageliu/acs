package base.requests;

import base.Actions;
import base.DirectoryAreas;
import base.Door;
import base.jerarchy.Area;
import ch.qos.logback.classic.Logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

// This class will be called when a request is made for an area (Space or Partition) and the
// following will happen:
// This class will build all parameters that will be taking into account and then
// it will get all doors involved in that area and will call a requestHeader for each
// door container in the area
public class RequestArea implements Request {
  private final String credential;
  private final String action;
  private final String areaId;
  private final LocalDateTime now;
  private final ArrayList<RequestReader> requests = new ArrayList<>();
  private static final Logger logger = (Logger) LoggerFactory.getLogger("Request.Area");


  public RequestArea(String credential, String action, LocalDateTime now, String areaId) {
    logger.info("RequestArea() called, " + credential + ", " + action + ", " + now + ", " + areaId);
    this.credential = credential;
    this.areaId = areaId;
    assert action.equals(Actions.LOCK) || action.equals(Actions.UNLOCK)
            : "invalid action " + action + " for an area request";
    this.action = action;
    this.now = now;
  }

  public String getAction() {
    return action;
  }

  @Override
  public JSONObject answerToJson() {
    JSONObject json = new JSONObject();
    json.put("action", action);
    json.put("areaId", areaId);
    JSONArray jsonRequests = new JSONArray();
    for (RequestReader rd : requests) {
      jsonRequests.put(rd.answerToJson());
    }
    json.put("requestsDoors", jsonRequests);
    return json;
  }

  @Override
  public String toString() {
    String requestsDoorsStr;
    if (requests.size() == 0) {
      requestsDoorsStr = "";
    } else {
      requestsDoorsStr = requests.toString();
    }
    return "Request{"
            + "credential=" + credential
            + ", action=" + action
            + ", now=" + now
            + ", areaId=" + areaId
            + ", requestsDoors=" + requestsDoorsStr
            + "}";
  }

  // processing the request of an area is creating the corresponding door requests and forwarding
  // them to all of its doors. For some it may be authorized and action will be done, for others
  // it won't be authorized and nothing will happen to them.
  public void process() {
    logger.info("Processing request");
    // commented out until Area, Space and Partition are implemented

    // make the door requests and put them into the area request to be authorized later and
    // processed later
    Area area = DirectoryAreas.findAreaById(areaId);
    // an Area is a Space or a Partition
    if (area != null) {
      logger.debug("Area is not null");
      logger.debug("Area : " + area.getId());
      // is null when from the app we click on an action but no place is selected because
      // there (flutter) I don't control like I do in javascript that all the parameters
      // are provided

      // Make all the door requests, one for each door in the area, and process them.
      // Look for the doors in the spaces of this area that give access to them.
      for (Door door : area.getDoorsGivingAccess()) {
        logger.info("Generating request for door " + door.getId());
        RequestReader requestReader = new RequestReader(credential, action, now, door.getId());
        requestReader.process();
        // after process() the area request contains the answer as the answer
        // to each individual door request, that is read by the simulator/Flutter app
        requests.add(requestReader);
      }
    }
  }
}
