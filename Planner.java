
public class Planner implements Cloneable {
    final int MAX_COURSES = 50;
    int totalCourses;
    Course[] courseList = new Course[MAX_COURSES];

    /**
     * Planner constructor
     */
    public Planner() {

    }

    /**
     *
     * @return the size of the planner
     * tells you how many courses are in the planner
     */
    public int size() {
        return totalCourses;
    }

    /**
     *
     * @param newCourse - the new course to add to the list
     * @param position - the position (preference) of this course on the list
     * @throws IllegalArgumentException when the user inputs an invalid
     * position
     * @throws FullPlannerException when the planner is full
     *
     * Adds a new course to the planner and place it in a position
     * if there's already a course in the position, the course originally
     * there moves up a position.
     */
    public void addCourse(Course newCourse, int position)
            throws IllegalArgumentException, FullPlannerException {
        if (size() > MAX_COURSES) {
            throw new FullPlannerException("There is no more room in the" +
                    " planner to record an additional course.");
        }

        position += -1;
        // if position is a negative or greater than the total throw exception
        if (position < 0 || position >= totalCourses + 1) {
            throw new IllegalArgumentException("Position is not within a " +
                    "valid range.");
        }
        if (position != totalCourses + 1) {
            if (totalCourses - position >= 0) {
                // copies the new list
                System.arraycopy(courseList, position, courseList,
                        position + 1, totalCourses - position);
            }
        }
        courseList[position] = newCourse;
        totalCourses++;
    }

    /**
     *
     * @param newCourse - the new course to add to the list.
     * Works just like the last one, only difference is, this one adds a
     *                  course at the very end of the planner.
     */
    public void addCourse(Course newCourse) {
        try {
            addCourse(newCourse, size() + 1);
        }
        catch (FullPlannerException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param position - the position in the Planner where the
     * Course will be removed from.
     *
     * if the position is valid, it'll remove the course supported by the
     * position.
     *
     * else if it's not valid, it throws an exception telling the user it's
     * not in a valid range.
     *
     */
    public void removeCourse(int position) {
        if (position < 1 || position > totalCourses) {
            throw new IllegalArgumentException("Position is not within the" +
                    " valid range");
        }
        if (position - 1 != totalCourses - 1) {
            System.arraycopy(courseList, position, courseList,
                    position - 1, totalCourses - position);
        }
        courseList[totalCourses - 1] = null; // deletes the course
        totalCourses--;
    }

    /**
     *
     * @param position - position of the Course to retrieve.
     * @return The Course at the specified position in this Planner object.
     *
     * throws IllegalArgumentException if the position is invalid
     */
    public Course getCourse(int position) {
        if (position < 1 || position > totalCourses) {
            throw new IllegalArgumentException("Position is not within the" +
                    " valid range");
        }
        return courseList[position - 1];
    }

    /**
     * Checks whether a certain Course is already in the list.
     * @param course - the Course we are looking for
     * @return True if the Planner contains this Course, false otherwise.
     */
    public boolean exists (Course course) {
        for (Course value : courseList) {
            return value.equals(course);
        }
        return false;
    }

    /**
     * Creates a copy of this Planner. Subsequent changes to the copy will not
     * affect the original and vice versa.
     * @return A copy (backup) of this Planner object.
     * @throws CloneNotSupportedException - if the object could not be cloned
     */
    public Object clone() throws CloneNotSupportedException{ // deep copy
        Planner clone = (Planner) super.clone();
        clone.courseList = courseList.clone();
        return clone;
    }

    /**
     * Prints all Courses that are within the specified department.
     * @param planner - the list of courses to search in
     * @param department - the 3 letter department code for a Course
     */
    public static void filter(Planner planner, String department) {
        System.out.println("No. Course Name               " +
                "Department Code Section Instructor");
        System.out.print("--------------------------------------------------" +
                "------------------");
        System.out.print("\n");

        for (int h = 0; h < planner.size(); h++) {
            if (planner.getCourse(h + 1).getDepartment()
                    .equals(department)) {
                System.out.printf("  %-2d%-26s%-12s%-8d%-4s%" +
                                "-2s%n%n", h + 1, planner.getCourse(h + 1)
                                .getName(),
                        planner.getCourse(h + 1).getDepartment(),
                        planner.getCourse(h + 1).getCode(),
                        "0" + planner.getCourse(h + 1).getSection(),
                        planner.getCourse(h + 1).getInstructor());
            }
        }
    }

    /**
     * Gets the String representation of this Planner object, which is a neatly
     * formatted table of each Course in the Planner on its own line with its
     * position number as shown in the sample output.
     * @return The String representation of this Planner object.
     */
    public String toString(){
        String p1 = "No. Course Name               " +
                "Department Code Section Instructor";
        p1 += "\n";

        String p2 = "--------------------------------------------------" +
                "------------------";
        p2 += "\n";

        StringBuilder p3 = new StringBuilder();
        for (int h = 0; h < this.size(); h++){
            p3.append(String.format("  %-2d%-26s%-12s%-8d%-4s%-2s%n",
                    h + 1, courseList[h].getName(),
                    courseList[h].getDepartment(), courseList[h].getCode(),
                    "0" + courseList[h].getSection(),
                    courseList[h].getInstructor()));
        }
        return p1 + p2 + p3;
    }

    /**
     * Prints a neatly formatted table of each item in the list with its
     * position number
     */
    public void printAllCourses() {
        System.out.print(this.toString());
    }

    /**
     *
     * @param c - the Course we're searching for
     */
    public void searchCourse(Course c) { // basically a linear search

        for (int h = 0; h < totalCourses; h++) { // loop through the courses
            if (courseList[h].equals(c)) { // if we found our match
                //print this statement with the position
                System.out.print(courseList[h].getDepartment() + " " +
                        courseList[h].getCode() + "." + "0" +
                        courseList[h].getSection() + " is found in the " +
                        "planner at position " + (h + 1) + "." + "\n");
            }
        }
    }
}