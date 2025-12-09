import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean continueInput = true; // Use a clearer variable name


        do {
            System.out.println("\n--- Enter New Record ---");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");


            int idNumInt = SafeInput.getRangedInt(in, "Enter ID Number (1-999999)", 1, 999999);
            String idNumber = String.format("%06d", idNumInt);

            String email = SafeInput.getNonZeroLenString(in, "Enter Email Address");
            int yob = SafeInput.getRangedInt(in, "Enter Year of Birth (e.g., 1980)", 1900, 2024);


            String csvRecord = String.join(",", firstName, lastName, idNumber, email, String.valueOf(yob));
            records.add(csvRecord);


            continueInput = SafeInput.getYNConfirm(in, "Do you want to add another record?");

        } while (continueInput);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name to save (add .csv extension)");


        Path currentRelativePath = Paths.get("");
        String absPath = currentRelativePath.toAbsolutePath().toString();
        File file = new File(absPath + "\\src\\" + fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String record : records) {
                writer.write(record);
                writer.newLine();
            }
            System.out.println("\nSuccessfully saved " + records.size() + " records to " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}