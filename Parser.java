public class Parser{
    String commandName;
    String[] args;
    int argsCnt;
    
    //constructor
    public Parser(){
        argsCnt=-1;
        args=new String[10];
    }

    //This method will divide the input into commandName and args
    //where "input" is the string command entered by the user
    public void parse(String input){
        int cnt=0;
        input=input.trim();
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)==' '){
                cnt++;
                if(cnt==1  ){
                    commandName=input.substring(0, i).trim();
                }
                else{
                    if(input.charAt(0)!=' ')
                       args[++argsCnt]=input.substring(0,i);       
                }     
                input=input.substring(i+1);
                i=-1;           
            }
        }
        if(cnt==0)
            commandName=input;
        else
            args[++argsCnt]=input;
    }


    public String getCommandName(){
        return this.commandName;
    }
    public String[] getArgs(){
        return this.args;
    }
}