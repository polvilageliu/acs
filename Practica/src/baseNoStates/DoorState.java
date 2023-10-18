package baseNoStates;

public abstract class DoorState {
  protected DoorState currentState; // Variable para mantener el estado actual
  protected Door door;

  // Constructor que establece el estado inicial
  public DoorState(Door door) {
    this.door = door;
  }

  public abstract void lock();
  public abstract void unlock();
  public abstract String toString();
}
