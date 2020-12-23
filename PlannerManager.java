import java.util.InputMismatchException;
import java.util.Scanner;

// Driver class

public class PlannerManager {
    public static void main(String[] args) {
        Scanner h = new Scanner(System.in);
        String letter = ""; // options for the user
        Planner mainPlanner = new Planner(); // the planner we'll use
        Planner backUpPlanner = null; // for the backup planner

        // while the user doesn't type in "q" or "Q", keep running the menu
        while(!letter.equalsIgnoreCase("q")){
            System.out.print("(A) Add Course" + "\n" + "(G) Get Course" +
                    "\n" + "(R) Remove Course" + "\n" +
                    "(P) Print Courses in Planner" + "\n" +
                    "(F) Filter by Department Code" + "\n" +
                    "(L) Look For Course" + "\n" + "(S) Size" + "\n" +
                    "(B) Backup" + "\n" + "(PB) Print Courses in Backup" +
                    "\n" + "(RB) Revert to Backup" + "\n" + "(Q) Quit" + "\n");
            System.out.print("Enter a selection: ");
            letter = h.nextLine().toUpperCase(); // turn whatever selection
            // the user inputted to upper case

            // user can type a or A
            if (letter.equalsIgnoreCase("a")){
                try{
                    System.out.print("\n" + "Enter course name: ");
                    String name = h.nextLine();
                    // if it matches this regex code then throw a new
                    // exception
                    if (name.matches("\\d+") || //regex code
                            name.matches("^\\d+[a-z]+") ||
                            name.matches("^[a-z]+\\d+$")){
                        throw new IllegalArgumentException("Invalid name.");
                    }

                    System.out.print("Enter department: ");
                    String department = h.nextLine().toUpperCase();
                    if (department.length() != 3 ||
                            department.matches("\\d+") ||
                            department.matches("^\\d+[a-z]+") ||
                            department.matches("^[a-z]+\\d+$")){
                        throw new IllegalArgumentException("Invalid " +
                                "department code.");
                    }

                    System.out.print("Enter course code: ");
                    String code = h.nextLine();
                    if (code.length() != 3 ||
                            code.matches("^[a-z]+\\d+$")
                            || code.matches("[a-z]+")){
                        throw new IllegalArgumentException("Invalid code.");
                    }

                    System.out.print("Enter course section: ");
                    String section = h.nextLine();
                    if (section.length() != 1 ||
                            section.matches("[a-z]+")){
                        throw new IllegalArgumentException("Invalid section.");
                    }

                    System.out.print("Enter instructor: ");
                    String instructor = h.nextLine();
                    if (instructor.matches("\\d+") ||
                            instructor.matches("^\\d+[a-z]+") ||
                            instructor.matches("^[a-z]+\\d+$")){
                        throw new IllegalArgumentException("Invalid " +
                                "instructor name.");
                    }

                    System.out.print("Enter position: ");
                    String pos = h.nextLine();
                    // create a new course object for the given user values
                    Course c = new Course(name, department,
                            Integer.parseInt(code), Byte.parseByte(section),
                            instructor);
                    mainPlanner.addCourse(c, Integer.parseInt(pos));
                    System.out.print(department + " " + code + "." + "0" +
                            section + " successfully added to planner." +
                            "\n");
                }
                catch (FullPlannerException |
                        IllegalArgumentException e){
                    System.out.print(e.getMessage() + " Returning to main " +
                            "menu" + "\n"); // if an error occurs, catch it
                }
            }
            else if (letter.equalsIgnoreCase("g")){
                    System.out.print("\n" + "Enter position: ");
                    try {
                        String pos = h.nextLine();
                        Course posCourse =
                                mainPlanner.getCourse(Integer.parseInt(pos));
                        String p1 = "No. Course Name               " +
                                "Department Code Section Instructor";
                        p1 += "\n";

                        String p2 = "-----------------------------------" +
                                "---------------------------------";
                        p2 += "\n";

                        String p3 = String.format("  %-2d%-26s%-12s%-8d%" +
                                        "-4s%-2s%n",
                                Integer.parseInt(pos), posCourse.getName(),
                                posCourse.getDepartment(),
                                posCourse.getCode(),
                                "0" + posCourse.getSection(),
                                posCourse.getInstructor());
                        System.out.print(p1 + p2 + p3);
                    }
                catch (InputMismatchException e){
                    System.out.print("Invalid position, Returning to main " +
                            "menu" + "\n");
                }
                catch (IllegalArgumentException e){
                    System.out.print(e.getMessage() + ". Returning to main " +
                            "menu" + "\n");
                }
            }
            else if (letter.equalsIgnoreCase("r")){
                System.out.print("\nEnter position: ");
                String pos = h.nextLine();
                try{
                    Course c = mainPlanner.getCourse(Integer.parseInt(pos));
                    mainPlanner.removeCourse(Integer.parseInt(pos));
                    System.out.print(c.getDepartment() + " " + c.getCode() +
                            "." + c.getSection() + " has been successfully " +
                            "removed from" + " the planner." + "\n");
                }
                catch (IllegalArgumentException e){
                    System.out.print(e.getMessage() + ". Returning to the " +
                            "main menu" + "\n");
                }
            }
            else if (letter.equalsIgnoreCase("p")){
                mainPlanner.printAllCourses();
            }
            else if (letter.equalsIgnoreCase("f")){
                try {
                    System.out.print("\n" + "Enter department code: ");
                    String department = h.nextLine().toUpperCase();
                    if (department.length() != 3 ||
                            department.matches("\\d+") ||
                            department.matches("^\\d+[a-z]+") ||
                            department.matches("^[a-z]+\\d+$")) {
                        throw new IllegalArgumentException("Invalid " +
                                "department code.");
                    }
                    Planner.filter(mainPlanner, department);
                }
                catch (IllegalArgumentException e){
                    System.out.print(e.getMessage() + " Returning to main " +
                            "menu" + "\n");
                }
            }
            else if (letter.equalsIgnoreCase("l")){
                try{
                    System.out.print("\n" + "Enter course name: ");
                    String name = h.nextLine();
                    if (name.matches("\\d+") ||
                            name.matches("^\\d+[a-z]+") ||
                            name.matches("^[a-z]+\\d+$")){
                        throw new IllegalArgumentException("Invalid name.");
                    }

                    System.out.print("Enter department: ");
                    String department = h.nextLine().toUpperCase();
                    if (department.length() != 3 ||
                            department.matches("\\d+") ||
                            department.matches("^\\d+[a-z]+") ||
                            department.matches("^[a-z]+\\d+$")){
                        throw new IllegalArgumentException("Invalid " +
                                "department code.");
                    }

                    System.out.print("Enter course code: ");
                    String code = h.nextLine();
                    if (code.length() != 3 ||
                            code.matches("^[a-z]+\\d+$")
                            || code.matches("[a-z]+")){
                        throw new IllegalArgumentException("Invalid code.");
                    }

                    System.out.print("Enter course section: ");
                    String section = h.nextLine();
                    if (section.length() != 1 ||
                            section.matches("[a-z]+")){
                        throw new IllegalArgumentException("Invalid section.");
                    }

                    System.out.print("Enter instructor: ");
                    String instructor = h.nextLine();
                    if (instructor.matches("\\d+") ||
                            instructor.matches("^\\d+[a-z]+") ||
                            instructor.matches("^[a-z]+\\d+$")){
                        throw new IllegalArgumentException("Invalid " +
                                "instructor name.");
                    }
                    Course c = new Course(name, department,
                            Integer.parseInt(code), Byte.parseByte(section),
                            instructor);
                    mainPlanner.searchCourse(c);
                }
                catch (IllegalArgumentException e){
                    System.out.print(e.getMessage() + " Returning to main " +
                            "menu" + "\n");
                }
            }
            else if (letter.equalsIgnoreCase("s")){
                System.out.print("There are " + mainPlanner.size() + " " +
                        "courses in the planner." + "\n");
            }
            else if (letter.equalsIgnoreCase("b")){
                try{
                    backUpPlanner = (Planner) mainPlanner.clone();
                    System.out.print("Created a backup of the current " +
                            "planner." + "\n");
                }
                catch (CloneNotSupportedException e){
                    System.out.print("Backup couldn't be made, returning to " +
                            "main menu." + "\n");
                }
            }
            else if (letter.equalsIgnoreCase("pb")){
                try{
                    if (backUpPlanner != null) {
                        backUpPlanner.printAllCourses();
                    }
                }
                catch (NullPointerException e){
                    System.out.print("No backup was found" + "\n");
                }
            }
            else if (letter.equalsIgnoreCase("rb")){
                try{
                    mainPlanner = backUpPlanner;
                    backUpPlanner = (Planner) mainPlanner.clone();
                    System.out.print("Planner successfully reverted to the " +
                            "backup copy." + "\n");
                }
                catch (NullPointerException e){
                    System.out.print("No backup was found" + "\n");
                }
                catch (CloneNotSupportedException e){
                    System.out.print("Revert process was not successful, " +
                            "returning to main menu." + "\n");
                }
            }
        }
        //when the user enters "Q" or "q", program ends and prints this message
        System.out.print("\n" + "Program terminating successfully...");
    }
}