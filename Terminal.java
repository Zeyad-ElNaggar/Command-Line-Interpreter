import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

                System.out.println(directoryNamePath + " is created successfully");
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
                    System.out.println( fileNamePath+" is created successfully");
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
    public void rm(String []args) {
        if (args.length != 1) {
            System.out.println("Wrong input for this command");
        }
        else {
            String fileName=args[0];
            File file = new File(fileName);
            if (file.isDirectory())
                System.out.println("rm: cannot remove " + fileName + " : Is a directory");
            else if (file.isFile()) {
                file.delete();
                System.out.println(fileName + " is removed successfully");
            } else
                System.out.println("there is no " + fileName + " in the current directory");
        }
    }
    //Removes each given directory only if it is empty
    public void rmdir(String [] args) {
        char ch='*';
        if (args.length == 0 || args.length>1) {
            System.out.println("Wrong input for this command");
        }
        else if (args[0].equals(String.valueOf(ch))) {  //ch='*' -> delete all  empty folders
            File originalFile = new File(myPath.toString());
            String[] listFolders = originalFile.list();
            Boolean flag=false; //to check if there is "at least" an empty folder in the directory

            for (String folder : listFolders) {               //searching for every folder in the main path
                File folderFile = new File(myPath.resolve(folder).toString());
                if (folderFile.isDirectory()) {            //checking if the directory is folder
                    String[] listofInnerFolders = folderFile.list();
                    if (listofInnerFolders.length == 0) {               //checking if the folder is empty
                        folderFile.delete();
                        System.out.println(folder + " is deleted successfully");
                        flag = true;
                    }
                }
            }
            if (flag==false) {
                System.out.println("There is not any empty folders in the current directory");
            }

        } else {                                       //removing a specific empty folder in the directory
            File originalFile = new File(myPath.toString());
            String[] listFolders = originalFile.list();
            Boolean flag = false; //to check if a folder exists or not
            for (String folder : listFolders) {
                if (folder.equals(args[0])) {
                    flag = true;
                    File foundFile = new File(myPath.resolve(folder).toString());
                    if (foundFile.isDirectory()) {
                        String[] listofInnerFolders = foundFile.list();
                        if (listofInnerFolders.length == 0) {
                            foundFile.delete();
                            System.out.println(args[0] + " is deleted successfully");
                        } else {
                            System.out.println(args[0] + " is not an empty folder");
                        }
                    } else {
                        System.out.println(args[0] + " is not a folder");
                    }
                }
            }
            if (flag == false) {
                System.out.println(args[0] + " does not exist in the directory");
            }
        }
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
    //cp cmnd
    public void cp(String srcFile, String dstFile) {
        try {
            Files.copy(Paths.get(srcFile), Paths.get(dstFile), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied successfully.");
        } catch (IOException e) {
            System.out.println("Error while copying." + e.getMessage());
            e.printStackTrace();
        }
    }
//cpr cmnd
    public void cpr(String srcDirc, String dstDircٍٍ) {
        if (parser.getArgs()[0]== null) {
            System.out.println("Src is Null");
            return;
        }
        if (parser.getArgs()[1]== null) {
            System.out.println("Dst is Null");
            return;
        }
        try {
            File srcDir = new File(srcDirc);
            File destDir = new File(dstDircٍٍ);

            if (srcDir == null) {
                System.out.println("Src is Null");
                return;
            }

            if (destDir == null) {
                System.out.println("Dst is Null");
                return;
            }

            if (!srcDir.exists() || !srcDir.isDirectory()) {
                System.out.println("Source directory does not exist or is not a directory");
                return;
            }

            if (!destDir.exists()) {
                // Create the destination directory mkdir function
                mkdir(new String[]{dstDircٍٍ});
            }

            if (!destDir.isDirectory()) {
                System.out.println("Dest is not a directory");
                return;
            }

            File[] sourceFiles = srcDir.listFiles();

            if (sourceFiles != null) {
                for (File source : sourceFiles) {
                    if (source != null) {
                        File newDestination = new File(destDir, source.getName());

                        if (newDestination.exists()) {
                            System.out.println("Dst exists! " + newDestination);
                        } else {
                            if (source.isDirectory()) {
                                mkdir(new String[]{newDestination.getPath()});
                                cpr(source.getPath(), newDestination.getPath()); // Recursively copy the subdirectory
                            } else {
                                try {
                                    Files.copy(source.toPath(), newDestination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    System.out.println("Copying failed");
                                }
                            }
                        }
                    } else {
                        System.out.println("Src = null.");
                    }
                }
            }

            System.out.println("Copied successfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Copying failed");
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
                    rm(parser.getArgs());

                else if(command.equals("rmdir"))
                    rmdir(parser.getArgs());

                else if(command.equals("cat"))
                    cat(parser.getArgs());

                else if(command.equals("wc"))
                    wc(parser.getArgs());

                else if(command.equals("exit"))
                    exit();

                else if (command.equals("cp")) {
                    if (parser.getArgs().length > 2 && parser.getArgs()[0].equals("-r")) {
                        String sourcePath = parser.getArgs()[1];
                        String destinationPath = parser.getArgs()[2];
                        cpr(sourcePath, destinationPath);
                    } else if (parser.getArgs().length == 2) {
                        cp(parser.getArgs()[0], parser.getArgs()[1]);
                    } else {
                        System.out.println("Invalid 'cp' or 'cp -r' command. Usage: cp <sourceFile> <destinationFile> or cp -r <sourceDirectory> <destinationDirectory>");
                    }
                }
                else{
                    System.out.println("Command not found");
                }
        }   
    }
}