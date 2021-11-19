/**
interface Encryptor is an interface for objects that provide
encryption functionality
@author Yao, Stacey
*/
public interface Encryptor {
  /**
  method getAlgName returns the type of cipher
  @return returns a string
  */
  public String getAlgName();
  /**
  method init passes a char[] key and calculates to find shift character sc
  @param key passes a char[] key
  */
  public void   init(char[] key);
  /**
  method encrypt takes each single plaintext character pc, given shift value char sc,
  and computes ciphertext character cc
  @param plain passes a plain text string
  @return returns a plain text string
  */
  public String encrypt (String plain);
  /**
  method decrypt takes shift value character sc and a single ciphertext character cc,
  computes plaintext character pc
  @param cipher passes a cipher text string
  @return returns a cipher string
  */
  public String decrypt(String cipher);


}
