import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Terminal{
    Parser parser;
    File file;
    Path myPath;

    Terminal(){
        parser=new Parser();
        file=new File(System.getProperty("user.dir"));
        myPath=Paths.get(file.getAbsolutePath());
    }
    
    // go to given path or when path = ".." -> it returns returns to previous directory 
    public void cd(String[] args){
        //String currentPath= file.getAbsolutePath();
        String path="";
        for(String str: args){                
            path+=str;
        }

        // if(args.length==0){
        //     file=new File(System.getProperty("user.dir"));
        //     myPath=Paths.get(file.getAbsolutePath());
        // }

        if(path.equals("..")){
            this.myPath=myPath.getParent();
        }
        else{
            file=new File(path);
            myPath= Paths.get(file.getAbsolutePath());
        }
    }

    //lists folder's content in order
    public void ls(String []args){
        String [] listFolders = file.list(); 
        if(args.length==0){ 
            for (String folder : listFolders) {
                System.out.println(folder);
            }
            
        }
        else if(args[0].equals("-r")){//lists folder's content in reverse order
            for (int i=listFolders.length-1;i>=0;i--) {
                System.out.println(listFolders[i]);
            }
        }
        else{
            System.out.println("Wrong Command");
        }
        
    }


    //create folder by giving its name or its name and path
    public void mkdir(String []args){   
        File file1=new File(myPath.toString());
        for (String directoryNamePath : args) {
            if(directoryNamePath!=null && file1.isDirectory()){
                myPath=myPath.resolve(Paths.get(directoryNamePath));
                this.file=new File(myPath.toString());
                this.file.mkdir();
            }
            else
                System.out.println("You can't create a folder !");
        }
    }

    //creates a file by giving its name or path
    public void touch(String []args){
        for (String fileNamePath : args) {
            if(fileNamePath!=null){
                try{
                    myPath=myPath.resolve(fileNamePath);
                    this.file=new File(myPath.toString());
                    this.file.createNewFile();
                    
                }
                catch(Exception fileNotCreated){
                    System.out.println("Error file couldn't be created..try again\n");
                }
            }
        }
        
    }

    //Implement each command in a method, for example:
    public void pwd(){
        System.out.println(myPath.toString());
    }

    //echo method takes argument and prints it
    public void echo(String []args){
        for (String argument : args) {
            System.out.println(argument);
        }
    }

    //delete file
    public void rm(String fileName){
        this.file=new File(fileName);
        if(!file.isDirectory())
            System.out.println("rm: cannot remove "+fileName+" : Is a directory");
        else
            file.delete();
    }


    public void exit(){}
    // ...
    //This method will choose the suitable command method to be called
    public void chooseCommandAction(){

        Scanner scan=new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            System.out.println(myPath);
            String input=scan.nextLine();
            parser.parse(input);
            String command= parser.getCommandName();
                if(command.equals("echo"))
                    echo(parser.getArgs());

                else if(command.equals("pwd"))
                    pwd();

                else if(command.equals("ls"))
                    ls(parser.getArgs());

                else if(command.equals("cd"))
                    cd(parser.getArgs());

                else if(command.equals("mkdir"))
                    mkdir(parser.getArgs());

                else if(command.equals("touch"))
                    touch(parser.getArgs());

                else if(command.equals("rm"))
                    rm(parser.getArgs()[0]);

                else{
                    System.out.println("Command not found");
                }
            }
        
        
   
    }
}
