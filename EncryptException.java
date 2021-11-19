/**
class EncryptException extends RuntimeException and passes a String
@author Yao, Stacey
*/
public class EncryptException extends RuntimeException {

  /**
  constructor EncryptException and passes a String
  @param message passes a message
  */
  public EncryptException(String message) {
    super(message);
  }
}
