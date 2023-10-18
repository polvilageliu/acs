package baseNoStates;

public class Unlocked extends DoorState {
  public Unlocked(Door door) {
    super(door); // Llama al constructor de la clase base para configurar el estado inicial

  }

  public void lock() {
    System.out.println("Bloqueando la puerta.");
    this.door.changeState(new Locked(this.door));
  }

  public void unlock() {
    System.out.println("La puerta ya está desbloqueada.");
  }

  public String toString() {
    return "unlocked";
  }
}
