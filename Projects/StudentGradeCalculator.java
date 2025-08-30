import java.util.*;
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of students: ");
        int noOfStudents = sc.nextInt();

        System.out.println("Enter the number of subjects: ");
        int noOfSubjects = sc.nextInt();

        for (int s = 1; s <=noOfStudents; s++) {
            System.out.println("\n--- Student " + s + " ---");
            int[] marks = new int[noOfSubjects];
            int total = 0;

            for (int i = 0; i<noOfSubjects; i++) {
                while (true) {
                    System.out.print("Enter marks for Subject" + (i + 1) + ": ");
                    int input = sc.nextInt();
                    if (input >= 0 && input <= 100) {
                        marks[i] = input;
                        total += input;
                        break;
                    } else {
                        System.out.println("Invalid input");
                    }
                }
            }

            int totalmarks = noOfSubjects * 100;
            float percentage = (float)(total * 100) / totalmarks;
            String grade;
            if (percentage >= 90) {
                grade = "O (Outstanding)";
            } else if (percentage >= 80) {
                grade = " E (Excellent)";
            } else if (percentage >= 70) {
                grade = "G (Good)";
            } else if (percentage >= 60) {
                grade = "A (Average)";
            } else if (percentage >= 50) {
                grade = "D (Pass)";
            } else {
                grade = "F (Fail)";
            }

            System.out.println("Total marks scored: " + total);
            System.out.println("The Percentage scored is: " + percentage + "%");
            System.out.println("The grade is: " + grade);

        }
    }
}
