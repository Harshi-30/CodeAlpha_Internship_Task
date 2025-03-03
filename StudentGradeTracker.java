import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> grades = new ArrayList<>();

        // Welcome message
        System.out.println("📚 Welcome to the Student Grade Tracker!");
        System.out.println("Enter student grades (type -1 to finish):");

        // Collect grades from user
        inputGrades(scanner, grades);

        // Process and display results
        if (grades.isEmpty()) {
            System.out.println("⚠️ No grades were entered.");
        } else {
            displayGradeSummary(grades);
        }

        // Close scanner
        scanner.close();
    }

    /**
     * Method to input grades from the teacher.
     * @param scanner Scanner object for user input
     * @param grades ArrayList to store grades
     */
    private static void inputGrades(Scanner scanner, ArrayList<Integer> grades) {
        while (true) {
            System.out.print("Enter grade: ");
            int grade = scanner.nextInt();

            if (grade == -1) {
                break; // Stop input when user enters -1
            } else if (grade < 0 || grade > 100) {
                System.out.println("❌ Invalid grade! Please enter a value between 0 and 100.");
                continue;
            }

            grades.add(grade);
        }
    }

    /**
     * Method to calculate the average of grades.
     * @param grades List of student grades
     * @return Average grade as double
     */
    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    /**
     * Method to display the summary of grades.
     * @param grades List of student grades
     */
    private static void displayGradeSummary(ArrayList<Integer> grades) {
        int highest = Collections.max(grades);
        int lowest = Collections.min(grades);
        double average = calculateAverage(grades);

        // Display results in a formatted way
        System.out.println("\n📊 Grade Summary:");
        System.out.println("────────────────────────────────────");
        System.out.printf("👥 Total Students: %d%n", grades.size());
        System.out.printf("📉 Lowest Score: %d%n", lowest);
        System.out.printf("📈 Highest Score: %d%n", highest);
        System.out.printf("📊 Average Score: %.2f%n", average);
        System.out.println("────────────────────────────────────");
    }
}
