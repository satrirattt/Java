import javax.swing.JOptionPane;

public class hw7_2 {
    final int MAX_STUDENTS = 3;
    String[] studentNames = new String[MAX_STUDENTS];
    double[] studentScores = new double[MAX_STUDENTS];
    int numOfStudents = 0;

    private void displayMenu() {
        String menu = "1. Add Student\n2. Display All Students\n3. Search Student by Name\n4. Calculate Average Score\n5. Exit";
        char choice;
        do {
            String userInput = JOptionPane.showInputDialog(menu);
            choice = userInput.charAt(0);

            switch (choice) {
                case '1':
                    addStudent();
                    break;
                case '2':
                    displayAllStudents();
                    break;
                case '3':
                    searchStudentByName();
                    break;
                case '4':
                    calculateAverageScore();
                    break;
            }
        } while (choice != '5');

        JOptionPane.showMessageDialog(null, "Exiting the program...", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addStudent() {
        if (numOfStudents < MAX_STUDENTS) {
            String name = JOptionPane.showInputDialog("Enter student name:");

            String scoreInput = JOptionPane.showInputDialog("Enter student score:");
            Double score = convertToDouble(scoreInput);

            if (score != null) {
                studentNames[numOfStudents] = name;
                studentScores[numOfStudents] = score;
                numOfStudents++;

                JOptionPane.showMessageDialog(null, "Student added successfully!", "Message",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid score format. Please enter a valid number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cannot add more students. Database is full.", "Message",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private Double convertToDouble(String input) {
        Double result = null;
        if (input != null && !input.trim().isEmpty()) {
            try {
                result = Double.valueOf(input);
            } catch (NumberFormatException e) {
            }
        }
        return result;
    }

    private void displayAllStudents() {
        if (numOfStudents == 0) {
            JOptionPane.showMessageDialog(null, "No students to display.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String displayMessage = "List of All Students:\n";

            for (int i = 0; i < numOfStudents; i++) {
                displayMessage += "Name: " + studentNames[i] + ", Score: " + studentScores[i] + "\n";
            }

            JOptionPane.showMessageDialog(null, displayMessage, "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchStudentByName() {
        if (numOfStudents == 0) {
            JOptionPane.showMessageDialog(null, "No students to search.", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String searchName = JOptionPane.showInputDialog("Enter the name of the student you want to search:");
            boolean found = false;

            for (int i = 0; i < numOfStudents; i++) {
                if (studentNames[i].equalsIgnoreCase(searchName)) {
                    JOptionPane.showMessageDialog(null,
                            "Student found!\nName: " + studentNames[i] + ", Score: " + studentScores[i], "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(null, "Student not found.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void calculateAverageScore() {
        if (numOfStudents == 0) {
            JOptionPane.showMessageDialog(null, "No students to calculate average.", "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            double totalScore = 0;

            for (int i = 0; i < numOfStudents; i++) {
                totalScore += studentScores[i];
            }

            double averageScore = totalScore / numOfStudents;

            JOptionPane.showMessageDialog(null, "Average Score of All Students: " + averageScore, "Message",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        hw7_2 studentDatabase = new hw7_2();
        studentDatabase.displayMenu();
    }

}