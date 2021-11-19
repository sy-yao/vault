/**
class Vault is a driver for reading files for interface Hash and Encryptor. It reads in a username,
a password, and an algorithm, then applies each accordingly.
@author Yao, Stacey
*/
import java.util.*;
import java.io.*;

public class Vault {
  /**
  method main is the driver for reading files for interface Hash and Encryptor. It reads in a username,
  a password, and an algorithm, then applies each accordingly.
  @param args passes arguments typed in command line
  */
  public static void main(String[] args) {
    ArrayList<Hash> H = new ArrayList<Hash>();
    H.add(new ShiftCaesar());
    H.add(new ShiftClear());
    H.add(new ShiftVigenere());
    String encalg = new String();
    String path = new String();
    boolean check = false;
    boolean access = false;
    boolean bad_ascii = false;
    boolean good_user = false;
    boolean add_user = false;

    if (args.length == 0 ){
      System.out.println("usage: java Vault [-au] <filename>");
      System.exit(0);
    }

    VaultAuthentication[] file = new VaultAuthentication[100];
    if(args[0].equals("-au")) {
      path = args[1];
      file = readFile(path);
      add_user = true;
    }
    else {
      path = args[0];
      file = readFile(path);
    }

    /*
    System.out.println("Array size: " + file.length);
    for(int i = 0; i < file.length; i++) {
      System.out.println(file[i].toString());
    }
    */


    //prompt for username
    Scanner in = new Scanner(System.in);
    System.out.print("username: ");
    String user = System.console().readLine();

    //find algorithm associated with user
    for(int j = 0; j < file.length; j++) {
      if(user.equals(file[j].getUser())) {
        encalg = file[j].getAlg();
        good_user = true;
      }
    }

    //prompt for password
    System.out.print("password: ");
    char[] password = System.console().readPassword();
    String pass = new String(password);
    int checkpass;
    char bad = '\0';
    for(int i = 0; i < password.length; i++) {
      checkpass = password[i];
      if(checkpass < 42 || checkpass > 122) {
        bad_ascii = true;
        bad = password[i];
        break;
      }
    }

    //prompt for a Hash algorithm if -au
    if(add_user) {
      System.out.print("Hash algorithm: ");
      encalg = System.console().readLine();
      for(int k = 0; k < file.length; k++) {
        if(user.equals(file[k].getUser())) {
          System.out.println("Error! Username " + "'" + user + "' already in use.");
          System.exit(0);
        }
      }
      if(bad_ascii) {
        System.out.println("Error! Invalid symbol " + "'" + bad + "' in password.");
        System.exit(0);
      }
    }

    //match the hash
    int i = -1;
    if(good_user || add_user) {
      try {
        while( !H.get(++i).getAlgName().equals(encalg) ) ;
      } catch(IndexOutOfBoundsException e) {
        System.out.println("Error! Hash algorithm " + "'" + encalg + "' not supported.");
        System.exit(0);
      }
    }

    //hashing!
    String plaintext = "GO_NAVY_2018^mid";
    String hash = new String();
    if(good_user || add_user) {
      try {
        // Encrypt, decrypt print sumamry of results
        H.get(i).init(password);
        if(encalg.equals("clear")) {
          hash = H.get(i).hash(pass);
        }
        else {
          hash = H.get(i).hash(plaintext);
        }
        /*
        System.out.println("password read : " + pass);
        System.out.println("hash computed : " + hash);
        */
      }
      catch(EncryptException e) {

      }
    }

    //writeFile
    if(add_user) {
      VaultAuthentication new_user = new VaultAuthentication("user", user, encalg, hash);
      writeFile(path, new_user, true);
      System.exit(0);
    }

    //get user, run encrpt type, and if pass matches output then access granted
    if(good_user) {
      for(int h = 0; h < file.length; h++) {
        check = false;
        access = false;
        if(user.equals(file[h].getUser())) {
          check = true;
          if(hash.equals(file[h].getPass())) {
            access = true;
            break;
          }
        }
      }
    }
    else if(!good_user) {
      if(bad_ascii) {
        access = false;
      }
      else{
        access = false;
      }
    }


    //determine access
    if(access) {
      System.out.println("Access granted!");
      System.out.print(">");
      String cmd = in.next();
      if(cmd.equals("quit")) {
        System.exit(0);
      }
    }
    else if(!access) {
      System.out.println("Access denied!");
      System.exit(0);
    }


  }//end main

  /**
  method readFile read a file
  @param fname passes the file name
  @return vault returns an array of VaultAuthentications
  */
  public static VaultAuthentication[] readFile(String fname) {
    Scanner sc = null;
    int num_lines = 0;
    try {
      sc = new Scanner(new FileReader(fname));
      num_lines = FileLineCounter.countLines(fname);
    } catch(IOException e) {
      System.out.println("Error! File " + "'" + fname + "' could not be opened.");
      System.exit(0);
    }
    //System.out.println(num_lines);
    VaultAuthentication[] vault = new VaultAuthentication[num_lines];
    try {
      for(int i = 0; i < vault.length; i++) {
        String[] split = (sc.nextLine()).split(" ");
        vault[i] = new VaultAuthentication(split[0], split[1], split[2], split[3]);
      }
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("Error! File " + "'" + fname + "' improperly formatted.");
      System.exit(0);
    }

    sc.close();
    return vault;
  }//end readFile

  //https://www.youtube.com/watch?v=k3K9KHPYZFc writing files
  //https://www.youtube.com/watch?v=Wrx-_oPiI90 savetoFile method
  /**
  method writeFile writes into a file
  @param filename passes the file name
  @param user passes VaultAuthentication
  @param append tells whether to append
  */
  public static void writeFile(String filename, VaultAuthentication user, boolean append) {
    // Opens an existing file, and appends to the end of it.
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(filename, append)));
    } catch (FileNotFoundException fnfe) {
      fnfe.printStackTrace();
    }
      catch(IOException e) {

      }

    // Do whatever you need to do
    pw.println(user.toString());

    if (pw != null)
    pw.close();

  }//end writeFile

}//end Vault class
