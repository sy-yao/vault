/**
class Clear is an implementation of Encryptor providing
the clear algorithm, which is to not change the plaintext
at all
@author Yao, Stacey
*/
public class Clear implements Encryptor {

  /**
  method getAlgName returns the type of cipher
  @return returns cipher type
  */
  public String getAlgName() {
    return "clear";
  }

  /**
  method init passes a char[] key and calculates to find shift character sc
  @param key passes a char[] key
  */
  public void init(char[] key) {
    int check;

    for(int i = 0; i < key.length; i++) {
      check = (int)key[i];
      if(check < 42 || check > 122) {
        throw new EncryptException("error " + key[i] + " not allowed in key");
      }
    }

  }

  /**
  method encrypt takes each single plaintext character pc, given shift value char sc,
  and computes ciphertext character cc
  @param plain passes a plain text string
  @return returns a plain text string
  */
  public String encrypt(String plain) {
    char[] plain_text = plain.toCharArray();
    int check;

    for(int i = 0; i < plain_text.length; i++) {
      check = (int)plain_text[i];
      if(check < 42 || check > 122) {
        throw new EncryptException("error " + plain_text[i] + " not allowed in plaintext");
      }
    }

    return plain;
  }

  /**
  method decrypt takes shift value character sc and a single ciphertext character cc,
  computes plaintext character pc
  @param cipher passes a cipher text string
  @return returns a cipher text string
  */
  public String decrypt(String cipher) {
    return cipher;
  }

}//end Clear class
