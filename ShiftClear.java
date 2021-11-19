/**
class ShiftClear implements a shift to a Clear cipher
@author Yao, Stacey
*/
public class ShiftClear extends Clear implements Hash {
  /**
  method getAlgName returns the type of cipher
  @return returns cipher type
  */
  public String getAlgName() {
    return super.getAlgName();
  }

  /**
  method shiftLeft shifts the alg left
  @param message sends a char array to be shifted
  @return message returns original message
  */
  public char[] shiftLeft(char[] message) {
    return message;
  }

  /**
  method hash uses an algorithm to encrypt
  @param message passes a String type message to encrypt
  @return message returns the hashed message
  */
  public String hash(String message) {
    int check;
    for(int i = 0; i < message.length(); i++) {
      check = message.charAt(i);
      if(check < 42 || check > 122) {
        throw new HashException("error " + message.charAt(i) + " not allowed in key");
      }
    }

    for(int i = message.length(); i < 16; i++) {
      message += 'x';
    }

    return message;
  }
}
