/**
class TestHashers
@author Yao, Stacey
*/
import java.util.*;

public class TestHashers {
  /**
  main to test my cipher classes
  @param args passes arguments entered
  @throws java.lang.Throwable throws EncryptException
  */
  public static void main(String[] args) throws Throwable {
    // Create ArrayList of all supported encryptors
    ArrayList<Hash> H = new ArrayList<Hash>();
    H.add(new ShiftCaesar());
    H.add(new ShiftClear());
    H.add(new ShiftVigenere());

    // Get alg,psw,msg from user
    System.out.print("algorithm: ");
    String encalg = System.console().readLine();
    System.out.print("password : ");
    char[] password = System.console().readPassword();
    int check;
    for(int i = 0; i < password.length; i++) {
      check = password[i];
      if(check < 42 || check > 122) {
        throw new EncryptException("error " + password[i] + " not allowed in key");
      }
    }
    String stringpass = new String(password);
    String plaintext = "GO_NAVY_2018^mid";

    // Find index of encryptor (throw exception if not found)
    int i = -1;
    try {
      while( !H.get(++i).getAlgName().equals(encalg) ) ;
    } catch(IndexOutOfBoundsException e) {
      throw new NoSuchElementException("Unknown algorithm '" + encalg +"'.");
    }

    String hash = new String();
    try {
      // Encrypt, decrypt print sumamry of results
      H.get(i).init(password);
      if(encalg.equals("clear")) {
        hash = H.get(i).hash(stringpass);
      }
      else {
        hash = H.get(i).hash(plaintext);
      }
      System.out.println("password read : " + stringpass);
      System.out.println("hash computed : " + hash);
    }
    catch(EncryptException e) {
      throw e;
    }
  }
}//end TestHashers class
