import java.io.*;  // Import the File class
import java.util.Scanner;

//remember to handle exceptions errors like delete then list....
public class Main{
  public static void main(String[] args) /*throws IOException*/{
    System.out.println("Maram's Terminal");
    Terminal terminal=  new Terminal();
    terminal.chooseCommandAction();

  }
}
