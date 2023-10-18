package baseNoStates;

public class Locked extends DoorState {
  public Locked(Door door) {
    super(door); // Llama al constructor de la clase base para configurar el estado inicial
  }

  public void lock() {
    System.out.println("La puerta ya está bloqueada.");
  }

  public void unlock() {
    System.out.println("Desbloqueando la puerta.");
    this.door.changeState(new Unlocked(this.door));
  }

  public String toString() {
    return "locked";
  }
}
