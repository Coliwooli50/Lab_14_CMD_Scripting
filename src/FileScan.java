import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileScan {

    public static void main(String[] args) {


        if (args.length > 0) {
            String fileName = args[0];
            File file = new File(fileName);


            if (!file.exists()) {
                Path currentRelativePath = Paths.get("");
                String absPath = currentRelativePath.toAbsolutePath().toString();
                file = new File(absPath + "\\src\\" + fileName);
            }

            if (file.exists()) {
                System.out.println("Command-line argument detected – scanning file: " + file.getName());
                processFile(file);
            } else {
                System.out.println("Error: Could not find file \"" + fileName + "\"");
            }

            return;  // exit so program does not launch the JFileChooser
        }


        JFileChooser chooser = new JFileChooser();

        // Set the default directory to the project's src folder
        Path currentRelativePath = Paths.get("");
        String absPath = currentRelativePath.toAbsolutePath().toString();
        chooser.setCurrentDirectory(new File(absPath + "\\src"));

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            System.out.println("Interactive file selection – scanning: " + selectedFile.getName());
            processFile(selectedFile);
        } else {
            System.out.println("File selection cancelled by the user.");
        }
    }


    private static void processFile(File file) {

        String fileName = file.getName();
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            System.out.println("\n--- FILE CONTENTS ---");
            System.out.println("Reading file: " + fileName + "\n");

            String line;
            while ((line = reader.readLine()) != null) {

                // echo line
                System.out.println(line);

                // line count
                lineCount++;

                // word count
                String[] words = line.trim().split("\\s+");
                if (!words[0].isEmpty()) {
                    wordCount += words.length;
                }

                // char count
                charCount += line.length();
            }

            System.out.println("\n--- SUMMARY REPORT ---");
            System.out.println("File Name: " + fileName);
            System.out.println("Lines:     " + lineCount);
            System.out.println("Words:     " + wordCount);
            System.out.println("Chars:     " + charCount);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
