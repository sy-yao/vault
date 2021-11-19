/**
class ShiftCaesar
String str = "foo";
char[] buff = str.toCharArray(); // convert String to char[]
String rts = new String(buff);   // convert char[] to String
@author Yao, Stacey
*/

public class ShiftCaesar extends Caesar implements Hash {

  /**
  method getAlgName returns the type of cipher
  @return returns cipher type
  */
  public String getAlgName() {
    return "shift+" + super.getAlgName();
  }

  /**
  method shiftLeft shifts the alg left
  @param message sends a char array to be shifted
  @return message returns shifted message
  */
  public char[] shiftLeft(char[] message) {
    int length = message.length;
    //save
    char init = message[0];

    for(int i = 1; i < message.length; i++) {
      message[i-1] = message[i];
    }

    //move to end
    message[length - 1] = init;

    return message;
  }

  /**
  method hash uses an algorithm to encrypt
  @param message passes a String type message to encrypt
  @return message returns the hashed message
  */
  public String hash(String message) {
    char[] message_text = message.toCharArray();
    char c, hc;
    int k, check;
    for(int i = 0; i < message_text.length; i++) {
      check = message_text[i];
      if(check < 42 || check > 122) {
        throw new HashException("error " + message_text[i] + " not allowed in key");
      }
    }


    for(int i = 0; i < 16; i++) {
      c = message_text[i];
      //amount to shift
      k = (int)c % 16;
      hc = (char)((int)c + k);

      for(int j = 0; j < k; j++) {
        //shifts once k times
        message_text = shiftLeft(message_text);
      }

      message = new String(message_text);
      message = encrypt(message);
      message_text = message.toCharArray();
    }

    message = new String(message_text);

    return message;
  }

}//end ShiftCaesar class
