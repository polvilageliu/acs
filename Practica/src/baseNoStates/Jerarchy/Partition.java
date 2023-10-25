package baseNoStates.Jerarchy;

import baseNoStates.Door;

import java.util.ArrayList;

// A partition is an area that may contain more Areas, inherits Area class.
public class Partition extends Area {
  // associated_areas stores any Area inside a Partition
  private final ArrayList<Area> associated_areas;
  public Partition(String name, Partition partOf) {
    super(name, partOf);
    this.associated_areas = new ArrayList<Area>();
  }

  // When we call findAreaById to this object firts it will check if it's id is the one we are looking for
  // by calling its super class function
  // if a false is returned, it will then check if any of its children has the id we are looking for
  @Override
  public Area findAreaById(String id) {
    Area returning = super.findAreaById(id);
    if (returning == null) {
      for (Area to_check : associated_areas) {
        returning = to_check.findAreaById(id);
        if (returning != null) {
          break;
        }
      }
    }
    return returning;
  }

  @Override
  public ArrayList<Door> getDoorsGivingAccess() {
    ArrayList<Door> allDoors = new ArrayList<Door>();

    for (Area area : this.associated_areas) {
      ArrayList<Door> areaDoors = area.getDoorsGivingAccess();
      allDoors.addAll(areaDoors);
    }

    return allDoors;
  }

  public void addArea(Area area) {
    associated_areas.add(area);
  }
}
