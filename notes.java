package note;                               // first we have defined the package

import java.io.*;                              // we have used these following nessecery classes and utility classes for your project
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Notes {
        public static void main(String[] args) {       // entry point of our program 
            if (args.length < 1 || args[0].equals("-h") || args[0].equals("-help")) {  // If the user didn't provide any arguments (args.length < 1), OR   // If the first argument is "-h" (help command), OR  // If the first argument is "-help" (another way to ask for help),  // Then we should show the help message and stop the program.  
                printUsage(args);
                return;                              // this part will give the user a reminder to use a [collection] for example "$> java Notes.java packing_list" 
            }   
            String collectionName = args[0];       // magic part if the user provides the collection name and if this exist already then it will open automatically. And if it doesn't then it will create a .txt file for them to store their notes
            String fileName = collectionName + ".txt";
        
            System.out.println("Welcome to the notes tool!\n");     // welcome notes for our user 
            System.out.println("Collection: "+ collectionName);      // and also which note book they asked for from the collection


            File file = new File (fileName);                       // This is to check if the file already exist
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    System.err.println("As No file has been found! A new file has been created for you to store your notes.");
                    
                } catch (Exception e) {                 // error handling if in the process somehow the program crash
                    System.err.println("Error on creating a file: " + e.getMessage());
                    return; 
                }
            }
            Scanner scanner = new Scanner (System.in);                  // Creates a Scanner object to read the user input in the keyboard (System.in)

        while (true) {                                                 // this is an infinite loop for the user unless they choose to exit
            System.out.println("\nSelect Operation: \n");
            System.out.println("1. Show notes");
            System.out.println("2. Add a note");
            System.out.println("3. Delete a note");
            System.out.println("4. Exit");

        String noteInput = scanner.nextLine().trim();             // this to store user input as a scanner object and trim and remove all extra spaces 

            if (noteInput.equals("4")) {               // The option 4 will take user out of the program and break the loop
                System.out.println("Thank you for using note!");
                break;
            }
            switch (noteInput) {                        // switch between operation.                          
                case "1":
                showNotes(fileName);
                break;
                case "2":
                addNote(scanner, fileName);
                break;
                case "3":
                deleteNote(scanner, fileName);
                break;
                default:
                System.out.println("Please choose a valid operation (1, 2, 3 or 4) to show/add/delete your notes or exit from the program");
            }
        }
    }
    private static void printUsage(String[] args) {                   //CLI
                System.out.println("Usage: java Notes.java [COLLECTION]\n");
                System.out.println("This tool allows users to manage short single-line notes within a collection.\n");
                System.out.println("Options:");
                System.out.println("-h, --help       Show this help message and exit");
                System.out.println("[COLLECTION]     The name of the collection to manage");
        }
    public static void showNotes(String fileName) {             // this is the method  accepts a parameter fileName (see above)
            File file =  new File (fileName);                  // this is a file object say You’re preparing to inspect it if the is really there.

            if (!file.exists()) {                            //  This line checks whether the (file) exists. If it doesn't, it means there's no file for notes, so the program will let the user know there’s nothing to show.
                System.out.println("No notes found. Please add a note!");
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {         // Now, let’s say the we found the file. Say You took file from the collection and start reading it. The 'BufferedReader; is like a bookmark that helps user read the notes inside.
                String line;                             //  This is like a placeholder where each line (note) from the file will be stored temporarily while user read it.
                int noteNumber = 1;
                System.out.println("Your Notes: ");
                while ((line = reader.readLine()) != null) {            // This line keeps reading each note from the file until there are no more notes to read. 

                    System.out.println(String.format("%03d - %s", noteNumber++, line));                   
                }
             } catch (IOException e) {                          // If there’s a problem reading the file (like an error with the file), this catches the issue and prepares a response.
                System.out.println("Error on reading notes: " + e.getMessage());

            }
        }
    public static void addNote(Scanner scanner, String fileName) {             // we declared the method 
        System.err.println("Enter the note you want to add: ");             // giving user instruction as they can write their note and add them 
        String note = scanner.nextLine().trim();                             // input is to be store into a scanner object as it will also going to remove all the extra spaces 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {      // BufferedWriter writer is a writing tool we borrowed from  java.io.*;   
            writer.write(note + "\n");                    // The "\n" means "jump to a new line" after writing, so each note appears separately.
            System.err.println("Your note has been added successfully");
        } catch (IOException e) {                       // this catch function handel any problem that the program may have during the operation and notify user.
            System.err.println("Error on adding notes: " + e.getMessage());
        }
    }
      public static void deleteNote(Scanner scanner, String fileName) { 
        List<String> notes = new ArrayList<>();                      // This line of code ensure to Create a list to store all notes temporarily
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {                                // Show an error message if the file cannot be read  

            System.err.println("Error reading notes: " + e.getMessage());
            return;
        }

        if (notes.isEmpty()) {                                  // once again we are checking isEmpty == If there are no notes, let the user know and exit 
            System.out.println("No notes available to delete.");
            return;
        }
                                                        // its function Shows all notes with numbers so the user can choose which one to delete  (they can only call by note numbers)
        System.out.println("Enter the note number to delete the note or 0 to cancel:");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i));
        }

        try {                                       // Get user input a scanner object which is to hold user choice, convert it to an index (subtract 1 because lists start at 0)
            int noteIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (noteIndex == - 1) {              // this part has been added as to give a user an option to return without deleting any of the notes. 
                return;
            }
            if (noteIndex < 0 || noteIndex >= notes.size()) {       // user input validation  
                System.out.println("Invalid note number. Please provide a valid note number.");
                return;
            }
            notes.remove(noteIndex);                    // if everything looks good  now the program can remove the selected note from the list

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                for (String note : notes) {             // as we do not want to see the deleted note here we Save the updated list back to the file, overwriting the old notes  
                    writer.write(note + "\n");
                }
                System.out.println("Your note has been deleted successfully.");
            }
        } catch (NumberFormatException | IOException e) {      // this to handle errors if the input is not a number or if file writing fails 
            System.out.println("Error deleting note: " + e.getMessage());
        }
      }
}
