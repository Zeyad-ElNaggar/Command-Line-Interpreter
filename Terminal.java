
public class Terminal {
   Parser parser;

    //Implement each command in a method, for example:
    //public String pwd(){}
    //public void cd(String[] args){}
    // ...
    //This method will choose the suitable command method to be called
    //public void chooseCommandAction(){}



    public static void main(String[] args) {

        //---testing Parser class----
        Parser p= new Parser();
        p.parse("Hey you how are you ?");

        String arr[]=p.getArgs();

        for (int i=0;i<arr.length;i++)
            System.out.println(arr[i]);

    }
}
