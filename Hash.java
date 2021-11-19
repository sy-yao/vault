/**
interface Hash is an interface for objects that provides a hash method added onto
encryption functionality
@author Yao, Stacey
*/
public interface Hash extends Encryptor {
  /**
  method shiftLeft shifts the alg left
  @param message sends a char array to be shifted
  @return message returns shifted message
  */
  public char[] shiftLeft(char[] message);

  /**
  method hash uses an algorithm to encrypt
  @param message passes a String type message to encrypt
  @return message returns the hashed message
  */
  public String hash(String message);

}
