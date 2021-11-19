/**
class FileException extends EncryptException and passes a String. It is specifcally for file
type errors
@author Yao, Stacey
*/
public class FileException extends EncryptException {

  /**
  constructor FileException and passes a String
  @param message passes a message
  */
  public FileException(String message) {
    super(message);
  }
}
