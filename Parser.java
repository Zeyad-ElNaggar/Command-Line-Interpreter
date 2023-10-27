public class Parser {
    String commandName;
    String[] args;

    //This method will divide the input into commandName and args
    //where "input" is the string command entered by the user
    public Boolean parse(String input){

        args= input.split(" ");  //splitting the entered command into words
        commandName=args[0];          //equaling the first word with the commandName

        return true;
    }
    public String getCommandName(){return commandName;}
    public String[] getArgs(){return args;}

}
