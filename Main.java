import java.io.*;  // Import the File class
import java.util.Scanner;

//remember to handle exceptions errors 
public class Main{
  public static void main(String[] args) {
    Parser p=new Parser();
    p.parse("rm file1     file2.txt ");
    System.out.println(p.getCommandName());
    for (String str : p.getArgs()) {
      if(str!=null)
        System.out.println(str);
    }
  }
}
