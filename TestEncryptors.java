/**
class TestEncryptors is a driver program that matches the
basic input/output functionality of Part 1. As you implement
more Encryptors, you just put an "add" line in for them, and
they become part of the test driver
@author Yao, Stacey
*/
import java.util.*;

public class TestEncryptors {
  /**
  main to test my cipher classes
  @param args passes arguments entered
  @throws java.lang.Throwable throws EncryptException
  */
  public static void main(String[] args) throws Throwable {
    // Create ArrayList of all supported encryptors
    ArrayList<Encryptor> E = new ArrayList<Encryptor>();
    E.add(new Clear());
    E.add(new Caesar());
    E.add(new Vigenere());

    // Get alg,psw,msg from user
    System.out.print("algorithm: ");
    String encalg = System.console().readLine();
    System.out.print("password : ");
    char[] password = System.console().readPassword();
    System.out.print("message  : ");
    String plaintext = System.console().readLine();

    // Find index of encryptor (throw exception if not found)
    int i = -1;
    try {
      while( !E.get(++i).getAlgName().equals(encalg) ) ;
    } catch(IndexOutOfBoundsException e) {
      throw new NoSuchElementException("Unknown algorithm '" + encalg +"'.");
    }

    try {
      // Encrypt, decrypt print sumamry of results
      E.get(i).init(password);
      String ciphertext = E.get(i).encrypt(plaintext);
      String hopefully = E.get(i).decrypt(ciphertext);
      System.out.println("plain : " + plaintext);
      System.out.println("cipher: " + ciphertext);
      System.out.println("decryp: " + hopefully);
    }
    catch(EncryptException e) {
      throw e;
    }

  }//end main
}//end TestEncryptors class
