/**
File Line Counter is java code that will count the number of lines in a file,
good for determining array size
*/
import java.util.*;
import java.io.*;
public class FileLineCounter {
  /**
  method countLines is java code that will count the number of lines in a file,
  good for determining array size
  @param filename passes the file to count lines of
  @return lines returns number of lines
  @throws java.io.FileNotFoundException throws if theres an exception
  */
  public static int countLines(String filename) throws FileNotFoundException{
    int lines = 0;

    Scanner sc = new Scanner(new FileReader(filename));
    while (sc.hasNextLine()) {
      sc.nextLine();
      lines++;
    }
    sc.close();
    return lines;
  }
}
