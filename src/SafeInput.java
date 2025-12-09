import java.util.Scanner;

public class SafeInput {

    /**
     * Gets a non-zero length string from the user
     *
     * @param pipe   - Scanner to use for input
     * @param prompt - Prompt to display ot the user to indicate what to input
     * @return - a non-empty string
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print(prompt + ": ");
            retString = pipe.nextLine();
            if (retString.isEmpty()) {
                System.out.println("Error: String must be at least one character long.");
            }
        } while (retString.isEmpty());

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                pipe.nextLine(); // clear the newline character from the buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Illegal number: " + trash + ". Try again.");
            }
        } while (!done);
        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double retVal = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the newline character from the buffer
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("Illegal number: " + trash + ". Try again.");
            }
        } while (!done);
        return retVal;
    }

    /**
     * @param pipe
     * @param prompt
     * @param low
     * @param high
     * @return
     */

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int retVal = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine(); // clear the newline character from the buffer
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Error: number must be between " + low + " and " + high + ". Try again.");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Illegal number: " + trash + ". Try again.");
            }
        } while (!done);
        return retVal;
    }

    /**
     * get a double within a range
     *
     * @param pipe    - Scanner to use for input
     * @param prompt- prompt to display to the user to indicate what to input
     * @param low     - lowest acceptable number
     * @param high    - highest acceptable number
     * @return
     */

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retVal = 0;
        boolean done = false;
        String trash = "";
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                retVal = pipe.nextDouble();
                pipe.nextLine(); // clear the newline character from the buffer
                if (retVal >= low && retVal <= high) {
                    done = true;
                } else {
                    System.out.println("Error: number must be between " + low + " and " + high + ". Try again.");
                }
            } else {
                trash = pipe.nextLine();
                System.out.println("Illegal number: " + trash + ". Try again.");
            }
        } while (!done);
        return retVal;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        String response = "";
        boolean retVal = false;
        boolean done = false;
        do {
            System.out.print(prompt + " [Y/N]: ");
            response = pipe.nextLine().toUpperCase();
            if (response.equals("Y")) {
                retVal = true;
                done = true;
            } else if (response.equals("N")) {
                retVal = false;
                done = true;
            } else {
                System.out.println("Invalid response. Please enter Y or N.");
            }
        } while (!done);

        return retVal;

        /**
         * get a yes/no confirmation from the user
         * @param pipe - Scanner to use for input
         * @param prompt - prompt to display to the user to indicate what to input
         * @return - true for yes, false for no
         */

    }

    /**
     * get a string that matches a regular expression
     *
     * @param pipe    - Scanner to use for input
     * @param prompt- prompt to display to the user to indicate what to input and the RegEx
     * @param regEx   - regular expression the input must match
     * @return - string that matches the regular expression
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String retString = "";
        boolean done = false;
        do {
            System.out.print(prompt + "[" + regEx + "]: ");
            retString = pipe.nextLine();
            if (retString.matches(regEx)) {
                done = true;
            } else {
                System.out.println("Error: String does not match the required format: " + regEx + "Please try again.");
            }
        } while (!done);

        return retString;
    }

    public static void prettyHeader(String msg) {
        final int WIDTH = 60;


        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();


        int totalSpaces = WIDTH - 6 - msg.length(); // 3 stars on each side
        int spacesBefore = totalSpaces / 2;
        int spacesAfter = totalSpaces - spacesBefore;


        System.out.print("***");
        for (int i = 0; i < spacesBefore; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < spacesAfter; i++) {
            System.out.print(" ");
        }
        System.out.println("***");


        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}
