package baseNoStates;

public abstract class DoorState {
  protected DoorState currentState; // Variable para mantener el estado actual

  // Constructor que establece el estado inicial
  public DoorState() {
    currentState = this;
  }

  public abstract void lock();
  public abstract void unlock();
}
