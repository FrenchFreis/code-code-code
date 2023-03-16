import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentModule extends Enrollment {
    Scanner input;

    static String filename;

    public void studentLogin() throws FileNotFoundException {
        File file = new File("C:\\Users\\renza\\Downloads\\StudentAccountDetails.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        Scanner in = new Scanner(System.in);

        System.out.println("Enter username: ");
        String username_ = in.nextLine();

        try {
            String line;
            String username;
            String password_;
            boolean exists = false;
            Scanner inputBuffer = new Scanner(file);
            
           
            while ((line = br.readLine()) != null) {
                String linenum = inputBuffer.nextLine();
                String[] values = linenum.split("\t");
                    username = values[0];
                    if (username.equals(username_)) {
                        exists = true;
                        filename = username_;
                        System.out.println("Enter password: ");
                        password_ = in.nextLine();
                        if (password_.equals(values[1])) {
                            System.out.println("Sucessfully entered the program!");
                            System.out.println("Hi " + filename);

                            studentPage();
                        } else {
                            System.out.println("\nInvalid password.");
                            System.out.println("Try Again.\n");
                            studentLogin();
                        }
                    }

            }
            if (!exists) {
                System.out.println("Invalid username");
                System.out.println("Enter valid username\n");

                System.out.println("Do you have an account? (Y/N)");
                Scanner account = new Scanner(System.in);
                String ans = account.nextLine();

                if (ans.equals("Y")) {
                    studentLogin();
                } else if (ans.equals("N")) {
                    System.out.println("Go Back and Register\n\n");
                    Enrollment.mainPage();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void studentPage() {
        input = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("Welcome to Student Module!");
        System.out.println("1 - Student Details");
        System.out.println("2 - Enroll Programs");
        System.out.println("3 - Enroll Courses");
        System.out.println("4 - Assessment Form");
        System.out.println("5 - Return");
        System.out.print("Enter choice: ");
        int studSelect = input.nextInt();

        switch (studSelect) {
            case 1 -> {
                displayStudentDetails();
                backStudentPage();
            }
            case 2 -> {
                enrollPrograms();
                backStudentPage();
            }
            case 3 -> {
                enrollCourses();
                backStudentPage();
            }
            case 4 -> {
                displayAssessmentForm();
                backStudentPage();
            }
            case 5 -> {
                System.out.println("Returning...");
                mainPage();
            }
            default -> System.out.println("Invalid");
        }

    }

    public void backStudentPage() {
        input = new Scanner(System.in);

        System.out.println(" ");
        System.out.print("Do you want to return in student page? Enter 1 to return, 0 to exit program (1/0): ");
        int backSelection = input.nextInt();

        switch (backSelection) {
            case 1:
                studentPage();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid");
        }
    }

    public void displayStudentDetails() {
        System.out.println("Processing...");
    }

    public void enrollPrograms() {
        System.out.println("Processing...");

        File file = new File("C:\\Users\\renza\\Downloads\\StudentsEnrolledProgram\\" + filename + ".txt");
        String previousChoice = readChoiceFromFile(file);
        
        if (previousChoice != null) {
            System.out.println("\nYour current program is: " + previousChoice);
        } else {
            String userChoice = getUserChoice();
            writeChoiceToFile(userChoice, file);
            System.out.println("Congratulations! You have enrolled to your program choice.");
            previousChoice = userChoice;

            System.out.println("Program choice: " + previousChoice);
            

        }
        
        // Prevent user from making another choice
        System.out.println("You cannot enroll to anything else.");
        
        // Print previous choice even if the program is run again
    }

    //methods of enroll prgram

    private static String getUserChoice() {
        Scanner scprog = new Scanner(System.in);
        String userChoice = null;
        boolean isValidChoice = false;

        ArrayList<String> programList = new ArrayList<String>();
        
        try {
            File file = new File("C:\\Users\\renza\\Downloads\\programs.txt");
            Scanner fileInput = new Scanner(file);

            while (fileInput.hasNextLine()) {
                String program = fileInput.nextLine();
                programList.add(program);
            }

            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found. Skipping subject list.");
        }

        while (!isValidChoice) {
            
            System.out.println("\nHello, " + filename + "! Here are the Programs available for enrollment:");

            for (int j = 0; j < programList.size(); j++) {
                System.out.println((j + 1) + ". " + programList.get(j));
            }

            System.out.print("\nEnter the program number you want to enroll in, or 0 to exit: ");
            int programNumber = scprog.nextInt();
            // scprog.nextLine(); // consume the newline character left by input.nextInt()

            if (programNumber == 0) {

                break;

            } else if (programNumber < 1 || programNumber > programList.size()) {
                System.out.println("\nInvalid program number. Please enter a valid course number.");
            } else {
                userChoice = programList.get(programNumber - 1);
                isValidChoice = true;
            }
        }
        
        return userChoice;
    }
    
    private static String readChoiceFromFile(File file) {
        String previousChoice = null;
        
        try (Scanner scanner = new Scanner(file)) {
            previousChoice = scanner.nextLine();
        } catch (IOException e) {
            System.err.println("Its seem you have not enrolled to a program yet");
        }
        
        return previousChoice;
    }
    
    private static void writeChoiceToFile(String userChoice, File file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(userChoice);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    //end

    public void enrollCourses() {
        System.out.println("Processing...");

        Scanner sc = new Scanner(System.in);

        ArrayList<String> studentscourseList = new ArrayList<String>();

        ArrayList<String> courseList = new ArrayList<String>();

        String dirPath = "C:\\Users\\renza\\Downloads\\Students Enrolled Courses\\";
        // gawa ng folder na may ganyan
        // na name or iba basta tama yung file path

        // create an empty array list to store the file names
        ArrayList<String> fileList = new ArrayList<>();

        // create a file object for the directory
        File dir = new File(dirPath);

        // get a list of all the files in the directory
        File[] files = dir.listFiles();

        // iterate through all the files
        for (File file : files) {
            // check if the file is a text file
            if (file.isFile() && file.getName().endsWith(".txt")) {
                // add the file name to the array list
                fileList.add(file.getName());
            }
        }

        if (fileList.contains(filename + ".txt")) {

            try {
                File currentlist = new File(dirPath + filename + ".txt");
                Scanner fileInput = new Scanner(currentlist);

                while (fileInput.hasNextLine()) {
                    String subject = fileInput.nextLine();
                    studentscourseList.add(subject);
                }

                fileInput.close();
            } catch (FileNotFoundException e) {
                System.out.println("\nFile not found. Skipping subject list.");
            }
        }

        try {
            File file = new File("C:\\Users\\renza\\Downloads\\courses.txt");
            Scanner fileInput = new Scanner(file);

            while (fileInput.hasNextLine()) {
                String subject = fileInput.nextLine();
                courseList.add(subject);
            }

            fileInput.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFile not found. Skipping subject list.");
        }

        while (true) {
            System.out.println("\nHello, " + filename + "! Here are the courses available for enrollment:");

            for (int j = 0; j < courseList.size(); j++) {
                System.out.println((j + 1) + ". " + courseList.get(j));
            }

            System.out.print("\nEnter the course number you want to enroll in, or 0 to exit: ");
            int courseNumber = sc.nextInt();
            sc.nextLine(); // consume the newline character left by input.nextInt()

            if (courseNumber == 0) {

                break;

            } else if (courseNumber < 1 || courseNumber > courseList.size()) {
                System.out.println("\nInvalid course number. Please enter a valid course number.");

            } else {
                if (!studentscourseList.contains(courseList.get(courseNumber - 1))) {
                    System.out.println("\nCongratulations, " + filename + "! You have successfully enrolled in "
                            + courseList.get(courseNumber - 1) + ".");
                    studentscourseList.add(courseList.get(courseNumber - 1));
                } else {
                    System.out.println("\n***\tYou have ALREADY ENROLLED TO THAT COURSE.");
                    System.out.println("***\tPLEASE SELECT A DIFFERENT ONE");
                }
            }
        }

        try {

            // write unique names to a text file
            BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + filename + ".txt", false));

            for (String courses : studentscourseList) {
                writer.write(courses + "\n");
            }
            writer.close();

            System.out.println("\n\n Here are the current courses you have enrolled in: ");

            BufferedReader reader = new BufferedReader(new FileReader(dirPath + filename + ".txt"));
            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayAssessmentForm() {
        System.out.println("Processing...");

    }
}