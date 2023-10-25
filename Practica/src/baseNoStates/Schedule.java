package baseNoStates;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalTime;

// This class will be used when making a petition for a door to check if it is a valid time. That is, if the petition Date
// is between dates, has been made in a valid day of the week and also at a valid hour.
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

  // Check if the date is between the 2 LocalDateTimes specified in the variables
  // if the day is valid and also if the hour of the request is valid
  public boolean inSchedule(LocalDateTime data) {
    // Comprueba si la fecha está dentro del rango
    if (data.isAfter(fromD) && data.isBefore(toD)) {
      // Comprueba si el día de la semana es válido
      if (days.contains(data.getDayOfWeek())) {
        // Comprueba si la hora y el minuto están dentro del rango
        if (data.toLocalTime().isAfter(LocalTime.of(this.fromHour, this.fromMinute)) &&
            data.toLocalTime().isBefore(LocalTime.of(this.toHour, this.toMinute))) {
          return true;
        }
      }
    }
    return false;
  }

}
