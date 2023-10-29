public class Parser{
    String commandName;
    String[] args;
    
    //constructor
    public Parser(){
    }

    //This method will divide the input into commandName and args
    //where "input" is the string command entered by the user
    public void parse(String input){
        input=input.trim();
        this.commandName= input.split(" ")[0];
        args=new String[input.split(" ").length-1];
        
        for(int i=1;i<input.split(" ").length;i++){
            if(input.split(" ")[i].length()>0 && input.split(" ")[i].charAt(0)!=' ')
                args[i-1]=input.split(" ")[i];
        }
    }


    public String getCommandName(){
        return this.commandName;
    }
    public String[] getArgs(){
        return this.args;
    }
}