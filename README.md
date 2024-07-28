# JavaFX Text Processing Tool Documentation
# 1. Introduction

•   **Project Name:** JavaFX Text Processing Tool

•   **Description:** The JavaFX Text Processing Tool is a desktop application that allows users to perform text processing operations such as pattern matching and text replacement using regular expressions. It also provides functionality to manage a list of data, and offers save and load options to handle data persistence. An additional feature allows users to save and load text data files, enabling easy storage and retrieval of processed text.

•   **Authors:** Ivan Muhumuza and David Manzi

•   **Version:** 1.0

•   **Date:** 7/26/2024

# 2. Features

•   **Text Input:** Users can input text for processing.

•   **Regex Matching:** Matches regular expressions against the input text.

•   **Text Replacement:** Replaces matched text with specified replacement text.

•   **Data Management:** Allows adding, updating, deleting, and clearing items in a list.

•   **Data Persistence:** Supports saving and loading data to/from files.

•   **Text File Operations:** Allows saving text data to files and loading text data from files.

•   **User-Friendly Interface:** Provides a modern and intuitive user interface for enhanced user experience.

# 3. Architecture

**3.1. Overview**

The application follows a Model-View-Controller (MVC) architecture:

•   **Model:** Manages the application data and logic.

•   **View:** Consists of FXML files defining the UI layout.

•   **Controller:** Handles user input and interacts with the model to update the view.

**3.2. Component Description**

•   **Model Classes:** Handle the data operations, including regex processing, list management, and text file handling.

•   **Controller Class:** (TextProcessorController.java) Manages UI events and user actions.

•   **View (FXML):** Defines UI components and their layout.

# 4. User Interface Design

**4.1. Layout**

**• Input Section:** Text area for user input.

**• Regex Section:** Text fields for regex pattern and replacement text.

**• Action Buttons:** Buttons for match, replace, clear operations.

**• Data Management:** Text field and buttons for managing data.

**• File Operations:** Buttons for saving/loading list data and saving/loading text data.

**4.2. Styling**

The UI uses inline CSS for styling to create a visually appealing and consistent look. Buttons and text fields have customized styles for enhanced interactivity and feedback.

# 5. Implementation Details

**5.1. Regex Processing**

**• Pattern Matching:** Utilizes Java's Pattern and Matcher classes for regex operations.

**• Text Replacement:** Replaces matched patterns using String.replaceAll().

**5.2. Data Management**

**• Collection Type:** Uses Observable List for maintaining a list of data.

**• CRUD Operations:** Implements methods for adding, updating, deleting, and clearing items.

**5.3. File Operations**

**Data Persistence:**

**• Saving Data:** Serializes list data to a file using FileWriter and BufferedWriter.

**• Loading Data:** Reads data from a file using BufferedReader and updates the list.

**Text File Operations:**

**• Saving Text Data:** Writes processed text to a file using FileWriter.

**• Loading Text Data:** Reads text data from a file using BufferedReader.

**5.4. Error Handling**

•   Implements basic error handling for file operations and regex processing to ensure the application handles exceptions gracefully and provides user feedback.

# 6. Usage Instructions

**6.1. Prerequisites**

**• Java Development Kit (JDK):** you can use Version 11 or higher.

**• JavaFX SDK:** Ensure JavaFX is set up in your development environment.

**6.2. Setup and Run**

1.  **Clone the Repository:**

             https://github.com/manziirw/Scrum-TextProcessor-Flow.git

2.  **Build the Project:** Use your IDE to build the project. Ensure the JavaFX libraries are properly configured.

3.  **Run the Application:** Execute the main() method in TetxProcessorApp file.

**6.3. Application Workflow**

1.  **Input Text:** Enter the text you want to process in the input area.

2.  **Regex Operations:**

    o   Enter a regex pattern in the "Regex Pattern" field.

    o   Enter replacement text in the "Replacement Text" field.

    o   Click "Match" to highlight matches, or "Replace" to substitute matches.

3.  **Manage Data:**

    o   Enter data in the "Data Input" field.

    o   Use "Add", "Update", "Delete", and "Clear" to manage the list.

4.  **File Operations:**

    o   List Data: Click "Save" to write data to a file. Click "Load List" to read data from a file.

    o   Text Data: Click "Save" to save processed text to a file. Click "Load Text" to load text data from a file.
# 7. Conclusion
The JavaFX Text Processing Tool offers a robust solution for regex-based text manipulation and data management. With a user-friendly interface, the application is designed for both novice and experienced users looking to perform quick text processing tasks.

