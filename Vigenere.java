/**
class Vigenere is a bit more complicated than the caesar algorithm
and is a bit more secure

String str = "foo";
char[] buff = str.toCharArray(); // convert String to char[]
String rts = new String(buff);   // convert char[] to String
@author Yao, Stacey
*/
public class Vigenere implements Encryptor {
  private char[] key;

  /**
  method getAlgName returns the type of cipher
  @return returns cipher type
  */
  public String getAlgName() {
    return "vigenere";
  }

  /**
  method init passes a char[] key
  @param key passes the key
  */
  public void init(char[] key) {
    int check;
    this.key = key;

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
  @return returns a plain string
  */
  public String encrypt(String plain) {
    char[] plain_text = plain.toCharArray();
    char sc, pc, cc;
    int k, p, c, check;

    for(int i = 0; i < plain_text.length; i++) {
      pc = plain_text[i];
      sc = key[i % key.length];
      k = (int)sc - 42;
      p = (int)pc - 42;
      c = (p + k) % 81;
      cc = (char)(c + 42);

      check = (int)plain_text[i];
      if(check < 42 || check > 122) {
        throw new EncryptException("error " + plain_text[i] + " not allowed in plaintext");
      }

      plain_text[i] = cc;
    }

    plain = new String(plain_text);

    return plain;
  }

  /**
  method decrypt takes shift value character sc and a single ciphertext character cc,
  computes plaintext character pc
  @param cipher passes a cipher text string
  @return returns a plain cipher text
  */
  public String decrypt(String cipher){
    char[] cipher_text = cipher.toCharArray();
    char sc, pc, cc;
    int k, p, c;

    for(int i = 0; i < cipher_text.length; i++) {
      cc = cipher_text[i];
      sc = key[i % key.length];
      k = (int)sc - 42;
      c = (int)cc - 42;
      p = (c + (81 - k)) % 81;
      pc = (char)(p + 42);

      cipher_text[i] = pc;
    }

    cipher = new String(cipher_text);

    return cipher;
  }
} //end Vigenere class
