import java.util.ArrayList;
import java.util.Scanner;

public class StudentGrades {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> grades = new ArrayList<>(); // List to store grades

        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();

        // Input grades for each student
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.print("Enter grade for student " + i + ": ");
            double grade = scanner.nextDouble();
            grades.add(grade);
        }

        // Compute average, highest, and lowest scores
        double sum = 0;
        double highest = grades.get(0);
        double lowest = grades.get(0);

        for (double grade : grades) {
            sum += grade;

            if (grade > highest) {
                highest = grade;
            }

            if (grade < lowest) {
                lowest = grade;
            }
        }

        double average = sum / grades.size();

        // Output results
        System.out.println("\n--- Grade Report ---");
        System.out.println("Total Students: " + numberOfStudents);
        System.out.printf("Average Grade: %.2f%n", average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);

        scanner.close();
    }
}