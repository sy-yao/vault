/**
class HashException extends EncryptException and passes a String when something goes wrong
relating to a hash
@author Yao, Stacey
*/
public class HashException extends EncryptException {

  /**
  constructor HashException and passes a String
  @param message passes a message
  */
  public HashException(String message) {
    super(message);
  }
}
