package baseNoStates;

public class Unlocked extends DoorState {
  public Unlocked() {
    super(); // Llama al constructor de la clase base para configurar el estado inicial
  }

  public void lock() {
    System.out.println("Bloqueando la puerta.");
    currentState = new Locked(); // Cambia el estado actual a Locked
  }

  public void unlock() {
    System.out.println("La puerta ya está desbloqueada.");
  }
}
