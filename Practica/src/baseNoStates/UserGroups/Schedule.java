package baseNoStates.UserGroups;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Schedule {
  private LocalDateTime fromD;
  private LocalDateTime toD;
  private int fromHour;
  private int fromMinute;
  private int toHour;
  private int toMinute;
  private ArrayList<DayOfWeek> days;
  public Schedule(LocalDateTime start, LocalDateTime end, int startHour, int startMinute, int endHour, int endMinute, ArrayList<DayOfWeek> validDays) {
    this.fromD = start;
    this.toD = end;
    this.fromHour = startHour;
    this.fromMinute = startMinute;
    this.toHour = endHour;
    this.toMinute = endMinute;
    this.days = validDays;
  }
  public boolean inSchedule(LocalDateTime data) {
    if (data.isAfter(fromD) && data.isBefore(toD)) {
      if (days.contains(data.getDayOfWeek())) {
        if (data.getHour() >= this.fromHour && data.getMinute() >=  this.fromMinute) {
          if (data.getHour() <= this.toHour && data.getMinute() <= this.toMinute) {
            if (days.contains(data.getDayOfWeek())) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
