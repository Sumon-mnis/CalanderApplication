import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Note {
    String date;
    String text;

    public Note(String date, String text) {
        this.date = date;
        this.text = text;
    }
}

class Calendar {
    private List<Note> notes;
    private int maxNotes = 100;

    public Calendar() {
        notes = new ArrayList<>();
    }

    public void addNote(String date, String text) {
        if (notes.size() < maxNotes) {
            notes.add(new Note(date, text));
            System.out.println("Note added successfully.");
        } else {
            System.out.println("Maximum note limit reached.");
        }
    }

    public void viewAllNotes() {
        System.out.println("****** All Notes ******");
        for (Note note : notes) {
            System.out.println("Date: " + note.date);
            System.out.println("Note: " + note.text);
            System.out.println();
        }
    }

    public void deleteNote(String date) {
        boolean found = false;
        for (Note note : notes) {
            if (note.date.equals(date)) {
                notes.remove(note);
                found = true;
                System.out.println("Note deleted successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("Note not found for the given date.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = new Calendar();

        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Enter Your Choice: ");
        int loginChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (loginChoice) {
            case 1:
                registerUser();
                break;
            case 2:
                if (loginUser()) {
                    int choice;
                    do {
                        System.out.println("\n*************** Menu ***************");
                        System.out.println("1. Add a note");
                        System.out.println("2. View all notes");
                        System.out.println("3. Delete a note");
                        System.out.println("4. Calculate age");
                        System.out.println("5. Check for a leap year");
                        System.out.println("6. Exit");
                        System.out.print("Enter Your Choice: ");
                        choice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character

                        switch (choice) {
                            case 1:
                                System.out.print("Enter the date (YYYY-MM-DD): ");
                                String date = scanner.nextLine();
                                System.out.print("Enter your note (up to 200 characters): ");
                                String text = scanner.nextLine();
                                calendar.addNote(date, text);
                                break;
                            case 2:
                                calendar.viewAllNotes();
                                break;
                            case 3:
                                System.out.print("Enter the date of the note to delete (YYYY-MM-DD): ");
                                String deleteDate = scanner.nextLine();
                                calendar.deleteNote(deleteDate);
                                break;
                            case 4:
                                System.out.print("Enter your birthdate (DD MM YYYY): ");
                                String[] birthdate = scanner.nextLine().split(" ");
                                int day = Integer.parseInt(birthdate[0]);
                                int month = Integer.parseInt(birthdate[1]);
                                int year = Integer.parseInt(birthdate[2]);
                                int age = calculateAge(day, month, year);
                                System.out.println("Your age is " + age + " years.");
                                break;
                            case 5:
                                System.out.print("Enter a year: ");
                                int leapYear = scanner.nextInt();
                                scanner.nextLine(); // Consume newline character
                                if (isLeapYear(leapYear)) {
                                    System.out.println(leapYear + " is a leap year.");
                                } else {
                                    System.out.println(leapYear + " is not a leap year.");
                                }
                                break;
                            case 6:
                                System.out.println("Exiting the program.");
                                break;
                            default:
                                System.out.println("Invalid choice. Please select a valid option.");
                                break;
                        }
                    } while (choice != 6);
                } else {
                    System.out.println("Login failed. Please check your username and password.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }

        scanner.close();
    }

    private static void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Save user credentials to a file or database
        System.out.println("User registered successfully!");
    }

    private static boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Authenticate user against stored credentials
        return true; // Replace with actual authentication logic
    }

    private static int calculateAge(int day, int month, int year) {
        LocalDate birthdate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}