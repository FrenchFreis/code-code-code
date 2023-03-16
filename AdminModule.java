import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminModule extends Enrollment {
    Scanner input;
    ArrayList programList;
    ArrayList courseList;

    public void adminLogin() {
        input = new Scanner(System.in);

        System.out.println("Login");

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.println("Login successful!");

    }

    public void adminPage() {
        input = new Scanner(System.in);

        System.out.println("Welcome to Admin Module!");
        System.out.println("1 - Student List");
        System.out.println("2 - Programs");
        System.out.println("3 - Courses");
        System.out.println("4 - Return");
        System.out.print("Enter choice: ");
        int adSelect = input.nextInt();

        switch (adSelect) {
            case 1 -> {
                studentList();
                break;
            }
            case 2 -> {
                editPrograms();
                break;
            }
            case 3 -> {
                editCourses();
                break;
            }
            case 4 -> {
                System.out.println("Returning...");
                mainPage();
                break;
            }
            default -> System.out.println("Invalid");
        }
    }

    // public void backAdminPage() {
    // input = new Scanner(System.in);
    //
    // System.out.print("Do you want to return in admin page? Enter 1 to return, or
    // 0 to exit program (1/0): ");
    // int backSelection = input.nextInt();
    //
    // switch (backSelection) {
    // case 1 -> {
    // adminPage();
    // break;
    // }
    // case 0 -> {
    // System.out.println("Exiting...");
    // break;
    // }
    // default ->
    // System.out.println("Invalid");
    // }
    // }

    public void studentList() {
        System.out.println("Encoding...");
    }

    public void editPrograms() {
        String programfilename = "C:\\Users\\renza\\Downloads\\programs.txt";
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> programList = new ArrayList<>();

        // Load names from file
        try {
            File file = new File(programfilename);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String coursename = fileScanner.nextLine();
                programList.add(coursename);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        System.out.println(" ");
        System.out.println("Programs");
        System.out.println("1 - Add Programs");
        System.out.println("2 - Delete Programs");
        System.out.println("3 - View Programs");
        System.out.println("4 - Return");
        System.out.print("Enter choice: ");
        int progSelect = scanner.nextInt();
        scanner.nextLine();

        switch (progSelect) {
            case 1 -> {
                System.out.println("Program List:");
                for (String name : programList) {
                    System.out.println(name);
                }

        System.out.print("Enter program to add: ");
        String programToAdd = scanner.nextLine();

        if (programList.contains(programToAdd)) {
            System.out.println("Program already exists in the list. Enter a different course name.");
        } else {
            programList.add(programToAdd);
            try {
                FileWriter fileWriter = new FileWriter(programfilename, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(programToAdd);
                bufferedWriter.newLine();
                bufferedWriter.close();
                System.out.println("Progam added to the list.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
            }
        }
        editPrograms();
                break;
            }
            case 2 -> {
                System.out.println(" ");
                System.out.println("Program List:");
                    for (String name : programList) {
                        System.out.println(name);
                    }

                    System.out.print("Enter course to delete: ");
                    String programToDelete = scanner.nextLine();
                    if (programList.remove(programToDelete)) {
                        try {
                            FileWriter fileWriter = new FileWriter(programfilename);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            for (String name : programList) {
                                bufferedWriter.write(name);
                                bufferedWriter.newLine();
                            }
                            bufferedWriter.close();
                            System.out.println("Program deleted from the list.");
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file.");
                        }
                    } else {
                        System.out.println("Program not found in the list.");
                    }
                editPrograms();
                break;
            }
            case 3 -> {
                System.out.println(" ");
                if (programList.isEmpty()) {
                    System.out.println("The program list is empty.");
                } else {
                    System.out.println("Program List:");
                    for (String name : programList) {
                        System.out.println(name);
                    }
                }
                editPrograms();
                break;
            }
            case 4 -> {
                System.out.println("Exiting program...");
                adminPage();
                System.exit(0);
            }

            default -> System.out.println("Invalid");
        }
    }

    public void editCourses() {
        String filename = "C:\\Users\\renza\\Downloads\\courses.txt";
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> coursesList = new ArrayList<>();

        // Load names from file
        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String coursename = fileScanner.nextLine();
                coursesList.add(coursename);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        while (true) {
            System.out.println(" ");
            System.out.println("1. Add course");
            System.out.println("2. Delete course");
            System.out.println("3. Print course list");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(" ");
                    System.out.println("Course List:");
                    for (String name : coursesList) {
                        System.out.println(name);
                    }
                    System.out.print("Enter course to add: ");
                    String courseToAdd = scanner.nextLine();
                    if (coursesList.contains(courseToAdd)) {
                        System.out.println("Course already exists in the list. Enter a different course name.");
                    } else {
                        coursesList.add(courseToAdd);
                        try {
                            FileWriter fileWriter = new FileWriter(filename, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            bufferedWriter.write(courseToAdd);
                            bufferedWriter.newLine();
                            bufferedWriter.close();
                            System.out.println("Course added to the list.");
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file.");
                        }
                    }
                    
                    break;
                case 2:
                    System.out.println(" ");
                    System.out.println("Course List:");
                    for (String name : coursesList) {
                        System.out.println(name);
                    }

                    System.out.print("Enter course to delete: ");
                    String courseToDelete = scanner.nextLine();
                    if (coursesList.remove(courseToDelete)) {
                        try {
                            FileWriter fileWriter = new FileWriter(filename);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            for (String name : coursesList) {
                                bufferedWriter.write(name);
                                bufferedWriter.newLine();
                            }
                            bufferedWriter.close();
                            System.out.println("Course deleted from the list.");
                        } catch (IOException e) {
                            System.out.println("An error occurred while writing to the file.");
                        }
                    } else {
                        System.out.println("Course not found in the list.");
                    }
                    break;
                case 3:
                System.out.println(" ");
                    if (coursesList.isEmpty()) {
                        System.out.println("The course list is empty.");
                    } else {
                        System.out.println("Course List:");
                        for (String name : coursesList) {
                            System.out.println(name);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    adminPage();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    break;
            }
        }

        // input = new Scanner(System.in);

        // System.out.println("Courses");
        // System.out.println("1 - Add Courses");
        // System.out.println("2 - Delete Courses");
        // System.out.println("3 - Return");
        // System.out.print("Enter choice: ");
        // int courseSelect = input.nextInt();

        // switch (courseSelect) {
        // case 1 -> {
        // addCourses();
        // break;
        // }
        // case 2 -> {
        // deleteCourses();
        // break;
        // }
        // case 3 -> {
        // System.out.println("Returning...");
        // adminPage();
        // break;
        // }
        // default -> System.out.println("Invalid");
        // }
    }

    // public void addPrograms() {
    //     input = new Scanner(System.in);

    //     System.out.print("Enter program: ");
    //     String program = input.nextLine();

    //     try {
    //         File file = new File("Programs.txt");
    //         programList = new ArrayList<>();
    //         String programs = " ";

    //         while (!program.isEmpty()) {
    //             if (!programs.isEmpty())
    //                 programList.add(program);
    //         }

    //         FileWriter fw = new FileWriter(file);
    //         BufferedWriter bw = new BufferedWriter(fw);

    //         int size = programList.size();
    //         for (int i = 0; i < size; i++) {
    //             bw.write(programList.get(i) + "\n");
    //         }

    //         System.out.println("Registered Program Successful!");

    //     } catch (IOException e) {

    //     }

    //     System.out.print("Do you want to add more programs? (Enter Y for yes, N for no): ");
    //     String addProgSelect = input.nextLine();

    //     switch (addProgSelect) {
    //         case "Y" -> {
    //             addPrograms();
    //             break;
    //         }
    //         case "N" -> {
    //             editPrograms();
    //             break;
    //         }
    //         default -> System.out.println("Invalid");
    //     }
        
    // }

    // public void deletePrograms() {
    //     System.out.println("Deleting programs...");
    // }

    // public void addCourses() {
    // input = new Scanner(System.in);

    // System.out.print("Enter course: ");
    // String course = input.nextLine();

    // try {
    // File file = new File("Course.txt");
    // courseList = new ArrayList<>();
    // String courses = " ";

    // while (!courses.isEmpty()) {
    // if (!courses.isEmpty())
    // courseList.add(course);
    // }

    // FileWriter fw = new FileWriter(file);
    // BufferedWriter bw = new BufferedWriter(fw);

    // int size = courseList.size();
    // for (int i = 0; i < size; i++) {
    // bw.write(courseList.get(i) + "\n");
    // }

    // System.out.println("Registered Course Successful!");

    // } catch (IOException e) {

    // }

    // System.out.print("Do you want to add more course? (Enter Y for yes, N for
    // no): ");
    // String addCourseSelect = input.nextLine();

    // switch (addCourseSelect) {
    // case "Y" -> {
    // addCourses();
    // break;
    // }
    // case "N" -> {
    // editCourses();
    // break;
    // }
    // default -> System.out.println("Invalid");
    // }
    // }

    // public void deleteCourses() {
    // input = new Scanner(System.in);

    // String filePath = "Courses.txt";
    // System.out.print("Enter course to delete: ");
    // String courseDelete = input.nextLine();

    // try {
    // File inputFile = new File(filePath);
    // StringBuilder stringBuilder;
    // try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
    // stringBuilder = new StringBuilder();
    // // Copy the file contents, omitting the specified text string
    // String line = "";
    // while ((line = reader.readLine()) != null) {
    // line = line.replaceAll(courseDelete, "");
    // stringBuilder.append(line);
    // stringBuilder.append(System.getProperty("line.separator"));
    // }
    // }
    // // Write the modified contents back to the file
    // try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
    // writer.write(stringBuilder.toString());
    // }

    // System.out.println("Course \"" + courseDelete + "\" deleted from course
    // list.");
    // System.out.print("Do you want to delete more course? (Enter Y for yes, N for
    // no): ");
    // String addCourseSelect = input.nextLine();

    // switch (addCourseSelect) {
    // case "Y" -> {
    // deleteCourses();
    // break;
    // }
    // case "N" -> {
    // editCourses();
    // break;
    // }
    // default -> System.out.println("Invalid");
    // }
    // } catch (IOException e) {

    // }
}
