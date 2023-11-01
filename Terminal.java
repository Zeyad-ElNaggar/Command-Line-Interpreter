import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Terminal{
    Parser parser;
    Path myPath;

    Terminal(){
        parser=new Parser();
        myPath=Paths.get(System.getProperty("user.dir"));
    }
    
    // go to given path or when path = ".." -> it returns returns to previous directory 
    public void cd(String[] args){
        String path="";
        for(int i=0;i<args.length-1;i++){                
            path+=args[i]+" ";
        }
        path+=args[args.length-1];
        
        if(path.equals("..")){
            this.myPath=myPath.getParent();
        }
        else{
            myPath= myPath.resolve(path);
            File file=new File(myPath.toString());
        }
    }

    //lists folder's content in order
    public void ls(String []args){
        File file= new File(myPath.toString());
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
        File file=new File(myPath.toString());
        for (String directoryNamePath : args) {
            if(directoryNamePath!=null && file.isDirectory()){
                
                Path newPath=myPath.resolve(Paths.get(directoryNamePath));
                file=new File(newPath.toString());
                file.mkdir();
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
                    Path newPath= myPath.resolve(fileNamePath);
                    File file=new File(newPath.toString());
                    file.createNewFile();

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
        File file=new File(fileName);
        if(file.isDirectory())
            System.out.println("rm: cannot remove "+fileName+" : Is a directory");
        else if(file.isFile()) {
            file.delete();
            System.out.println(fileName+ " is removed successfully");
        }
        else
            System.out.println("there is no " +fileName+ " in the current directory");
    }

    //cat command 
     public void cat(String []args) {
        File file = new File(args[0]);

        //Handling if user doesn't input .txt in file name
        if(!file.isFile()){
            args[0]+=".txt";
            file = new File(args[0]);
        }
        if(args.length==2 ){
            File file2= new File(args[1]);
            if(!file2.isFile()){
                args[1]+=".txt";
                file2= new File(args[1]);
            }
        }
        
        if (args.length == 1 && file.isFile()) {
            try{
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } 
            catch (IOException e) {
                System.err.println("File not found");
            }
        }
        else if(args.length==2 && file.isFile()){
            try {
                BufferedReader reader=new BufferedReader(new FileReader(args[0]));
                FileWriter myWriter=new FileWriter(args[1], true); //append mode on
                String line;
                myWriter.write(" ");
                while ((line= reader.readLine()) !=null) {
                        myWriter.write(line);
                        System.out.println("Successfully wrote to the file.");
                }
                myWriter.close();
            } 
            catch (IOException e) {
                System.err.println("File not found");
            }

        }
            
        else{
            
            System.out.println("wrong input");
        }
    }

    //word count
    public void wc(String []args){
        File file = new File(args[0]);
        if(!file.isFile()){
            args[0]+=".txt";
            file = new File(args[0]);
        }
        if(file.isFile()){
            int lineNum = 0, wordNum= 0, charNum = 0;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                String line;
                while ((line = reader.readLine()) != null) {
                    lineNum++;
                    String[] words = line.split(" ");
                    wordNum += words.length;
                    for (String word : words) {
                        charNum += word.length();
                    }
                }
                System.out.println("Number of lines: " + lineNum +", " +"Number of words: " + wordNum + ", " + "Number of characters: " + charNum + ", " + args[0]);
            } 
            catch (IOException e) {
                System.err.println("File not found");
            }
        }

        else{
            System.out.println("Please enter a text file name");
        }
    }

    public void exit(){
        System. exit(0);
    }
    // ...
    //This method will choose the suitable command method to be called
    public void chooseCommandAction(){

        Scanner scan=new Scanner(System.in);
        while (true) {
            System.out.println(myPath);
            System.out.print("> ");
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

                else if(command.equals("cat"))
                    cat(parser.getArgs());

                else if(command.equals("wc"))
                    wc(parser.getArgs());

                else if(command.equals("exit"))
                    exit();

                else{
                    System.out.println("Command not found");
                }
        }   
    }
}