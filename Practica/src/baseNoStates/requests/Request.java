package baseNoStates.requests;

import org.json.*;

public interface Request {
  JSONObject answerToJson();

  String toString();

  void process();
}
