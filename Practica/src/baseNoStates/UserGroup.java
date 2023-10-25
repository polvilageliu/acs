package baseNoStates;

import baseNoStates.Door;
import baseNoStates.Jerarchy.*;
import baseNoStates.Schedule;
import baseNoStates.User;

import java.time.LocalDateTime;
import java.util.ArrayList;

// Class that will be called to create whenever we want to create a group of users who share the same restrictions when
// it comes to requesting. It will store the name of the group, permited actions, users belonging to this group,
// all the spaces a group has acces to and also all the dates that will have to take into account when making a request
// (stored in schedule)
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

  // this function checks if an action is permited to the group, for example:
  // If an unlock has been requested but this group only allows open and close it will return false as it is not permited
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

  // Function that checks if a door connects two valid spaces to the group, if not the case then false will be returned
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

  // Function that calls its schedule variable to check if the given date is valid or not.
  // Check Schedule to know how it works.
  public boolean isDateValid(LocalDateTime date) {
    if (this.schedule != null) {
      return this.schedule.inSchedule(date);
    }
    return false;
  }

}
