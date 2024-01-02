
# Command Line Interpreter

This is a simple command line file manager implemented in Java. It allows users to navigate directories, create folders, create files, delete files, and perform other basic file operations using command-line commands.

## Features

- **cd:** Change the current working directory. Use ".." to move to the previous directory.
  
  Example:
cd /path/to/directory



- **ls:** List the contents of the current directory. Use the "-r" option to list in reverse order.

Example:
ls
ls -r



- **mkdir:** Create a new directory. You can specify the name or provide a path.

Example:
mkdir newFolder
mkdir /path/to/newFolder



- **touch:** Create a new empty file. You can specify the name or provide a path.

Example:
touch newFile.txt
touch /path/to/newFile.txt



- **pwd:** Print the current working directory.

Example:
pwd


- **echo:** Display the given arguments.

Example:
echo Hello World


- **rm:** Remove a file. Only works with files, not directories.

Example:
rm fileName.txt


- **rmdir:** Remove an empty directory. Use "*" to remove all empty directories.

Example:
rmdir emptyFolder
rmdir *


- **cat:** Display the contents of a file or concatenate two files.

Example:
cat file1.txt
cat file1.txt file2.txt



- **wc:** Display the number of lines, words, and characters in a text file.

Example:
wc file.txt



- **cp:** Copy a file to another location.

Example:
cp sourceFile.txt destination/



- **history:** Display a list of previously entered commands.

Example:
history


- **exit:** Exit the file manager.

## Usage

1. Compile the Java file: `javac Terminal.java`
2. Run the program: `java Main`
3. Enter commands and explore the functionality of the command line file manager.

## Note

- Ensure that Java is installed on your system to run this program.
- Use the `exit` command to exit the file manager.

Feel free to explore and enhance the functionality of this simple command line file manager.
