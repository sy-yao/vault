/**
class Caesar is a simple cipher algorithm that is not very secure
The shift is the character
sc = 42 + ( ( 18 + (k0 - 42) + (k1 - 42) + ... + (kn - 42) ) mod 81 )
Note: if any ki has ASCII value outside the range 42-122 it is an ERROR!

String str = "foo";
char[] buff = str.toCharArray(); // convert String to char[]
String rts = new String(buff);   // convert char[] to String
@author Yao, Stacey
*/
public class Caesar implements Encryptor {
  private char sc;

  /**
  method getAlgName returns the type of cipher
  @return returns cipher type
  */
  public String getAlgName() {
    return "caesar";
  }

  //https://stackoverflow.com/questions/5859934/char-initial-value-in-java
  /**
  method init passes a char[] key and calculates to find shift character sc
  @param key passes a char[] key
  */
  public void init(char[] key) {
    //char temp = '\0', total = '\0', total2 = '\0';
    int temp = 0, total = 0, total2 = 0, check;

    temp = 18;
    for(int i = 0; i < key.length; i++) {
      temp += ((int)key[i] - 42);

      check = (int)key[i];
      if(check < 42 || check > 122) {
        throw new EncryptException("error " + key[i] + " not allowed in key");
      }
    }

    temp = (temp % 81);
    sc = (char)(temp + 42);
  }

  /**
  method encrypt takes each single plaintext character pc, given shift value char sc,
  and computes ciphertext character cc
  @param plain passes a plain text string
  @return returns a plain text string
  */
  public String encrypt(String plain) {
    char[] plain_text = plain.toCharArray(); //convert String to char[]
    char cc;
    int k, p, c, check;

    k = (int)sc - 42;
    for(int i = 0; i < plain_text.length; i++) {
      p = (int)plain_text[i] - 42;
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
  @return returns a cipher text string
  */
  public String decrypt(String cipher) {
    char[] cipher_text = cipher.toCharArray();
    char pc;
    int k, p, c;

    k = (int)sc - 42;
    for(int i = 0; i < cipher_text.length; i++) {
      c = (int)cipher_text[i] - 42;
      p = (c + (81 - k)) % 81;
      pc = (char)(p + 42);

      cipher_text[i] = pc;
    }

    cipher = new String(cipher_text);

    return cipher;
  }
}//end Caesar class
