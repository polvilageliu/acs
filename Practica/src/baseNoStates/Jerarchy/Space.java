package baseNoStates.Jerarchy;

import baseNoStates.Door;

import java.util.ArrayList;

// Similar to Partitions class, but this class though it also inherits Area, stores associated doors.
public class Space extends Area {
  private final ArrayList<Door> associated_doors;
  public Space(String name, Partition partOf) {
    super(name, partOf);
    associated_doors = new ArrayList<Door>();
  }

  // Since a Space does not have sub-Areas, findAreaById will stop at this point if this object does not share de id
  @Override
  public Area findAreaById(String id) {
    return super.findAreaById(id);
  }

  // Returns an array of all associated doors to this space
  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    return this.associated_doors;
  }

  // Just a way of adding doors post creation.
  public boolean addDoor(Door input_door) {
    if (!this.associated_doors.contains(input_door)) {
      this.associated_doors.add(input_door);
      return true;
    } else {
      return false;
    }
  }
}
