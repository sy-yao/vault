
public class VaultAuthentication {
  private String junk, user, alg, pass;

  public VaultAuthentication(String junk, String user, String alg, String pass) {
    this.junk = junk;
    this.user = user;
    this.alg = alg;
    this.pass = pass;
  }

  public String toString() {
    String vault= new String();
    vault = this.junk + " " + this.user + " " + this.alg + " " + this.pass;
    return vault;
  }

  public String getJunk() {
    return this.junk;
  }

  public String getUser() {
    return this.user;
  }

  public String getAlg() {
    return this.alg;
  }

  public String getPass() {
    return this.pass;
  }
}
