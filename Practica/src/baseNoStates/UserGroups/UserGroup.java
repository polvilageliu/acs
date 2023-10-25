package baseNoStates.UserGroups;

import baseNoStates.Door;
import baseNoStates.Jerarchy.*;
import baseNoStates.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserGroup {
  private final String name;
  private final ArrayList<String> actions;
  private ArrayList<User> users;
  private ArrayList<Space> validSpaces;
  private Schedule schedule;


  public UserGroup(String name2, ArrayList<String> actions2, ArrayList<Space> spaces, Schedule schedule2) {
    this.name = name2;
    this.actions = actions2;
    this.users = new ArrayList<>();
    this.validSpaces = spaces;
    this.schedule = schedule2;
  }
  public String getName() { return this.name; }
  public void addUser(User user) {
    users.add(user);
  }

  public boolean isActionValid(String action) {
    if (this.actions != null) {
      for (String act : actions) {
        if (act.equals(action)) {
          return true;
        }
      }
      return false;
    }
    return false;
  }

  public boolean isDoorValid(Door door) {
    if (this.validSpaces != null) {
      Space space1 = door.getSpaces().get(0);
      Space space2 = door.getSpaces().get(1);
      if (validSpaces.contains(space1) && validSpaces.contains(space2)) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public boolean isDateValid(LocalDateTime date) {
    if (this.schedule != null) {
      return this.schedule.inSchedule(date);
    }
    return false;
  }

}
