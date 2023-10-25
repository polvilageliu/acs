package baseNoStates;

// A user now has a group they belong to
public class User {
  private final String name;
  private final String credential;
  private UserGroup group;

  public User(String name, String credential, UserGroup uGroup) {
    this.name = name;
    this.credential = credential;
    this.group = uGroup;
  }

  public String getCredential() {
    return credential;
  }
  public UserGroup getGroup() { return group; }

  @Override
  public String toString() {
    return "User{name=" + name + ", credential=" + credential + "}";
  }
}
