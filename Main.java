package baseNoStates;

// Before executing enable assertions :
// https://se-education.org/guides/tutorials/intellijUsefulSettings.html

import java.time.LocalDateTime;

public class Main {
  public static void main(String[] args) {
    DirectoryAreas.makeAreas();
    DirectoryUserGroups.makeUserGroups();
    // DirectoryDoors.makeDoors
    DirectoryUsers.makeUsers();
    WebServer.getInstance();
  }
}
