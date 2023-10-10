package baseNoStates;

public class Locked extends DoorState {
  public Locked() {
    super(); // Llama al constructor de la clase base para configurar el estado inicial
  }

  public void lock() {
    System.out.println("La puerta ya está bloqueada.");
  }

  public void unlock() {
    System.out.println("Desbloqueando la puerta.");
    currentState = new Unlocked(); // Cambia el estado actual a Unlocked
  }
}
