📉 Overview
This is a simple command-line tool that allows users to store and manage notes in different collections. Each collection is stored in a separate text file for persistence.

🛠️ Features
- Manage multiple collections, each stored as a `.txt` file.
- Add, delete, and display notes in a specific collection.
- Automatically creates a new collection file if it doesn’t exist.
- CLI-based interaction with user-friendly prompts.

🔍 How It Works

 START
   |
   ▼
User runs the program: `java Notes.java [COLLECTION]`
   |
   ▼
Does [COLLECTION].txt exist?
   ├── Yes → Load existing notes
   ├── No  → Create a new text file
   |
   ▼
Display menu:
1. Show notes
2. Add a note
3. Delete a note
4. Exit
   |
   ▼
User selects an option
   ├── 1 → Show notes from file
   ├── 2 → Ask for a new note & save to file
   ├── 3 → Ask for note number & remove from file
   ├── 4 → Exit program
   |
   ▼
LOOP UNTIL USER CHOOSES EXIT
   |
   ▼
END
```

📂 File Structure
```
notes_app/
|-- Notes.java          # Main program
|-- README.md           # Project documentation
|-- packing_list.txt    # Notes for packing
|-- grocery_list.txt    # Notes for grocery shopping
|-- work_notes.txt      # Notes for work tasks
```
🌟 Example Usage
1️⃣ Running the Program
java Notes.java packing_list

2️⃣ Adding a Note
$> 2
Enter the note:
$> self-lacing shoes

3️⃣ Showing Notes
$> 1
Notes:
001 - delorean
002 - flux capacitor


4️⃣ Deleting a Note
$> 3
Enter note number to delete:
$> 001


5️⃣ Exiting the Program
$> 4
Thank you for using Kood/note!
