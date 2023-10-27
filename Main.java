import java.io.*;  // Import the File class
import java.util.Scanner;

//remember to handle exceptions errors like delete then list....
public class Main{
  public static void main(String[] args) /*throws IOException*/{
    Parser p=new Parser();
    p.parse("rm file1     file2.txt ");
    System.out.println(p.getCommandName());
    for (String str : p.getArgs()) {
      if(str!=null)
        System.out.println(str);
    }
  }
}