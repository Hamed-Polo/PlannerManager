

public class Course implements Cloneable {
    /**
     * name is for the course name
     * department is for the course department
     * code is for the course code
     * section is for the course section
     * instructor is for the course instructor's name
     */
    String name;
    String department;
    int code;
    byte section;
    String instructor;

    /**
     * Course constructor, giving the variables assigned values.
     */
    public Course(String name, String department, int code, byte section,
                  String instructor){
        this.code = code;
        this.name = name;
        this.department = department;
        this.instructor = instructor;
        this.section = section;
    }
    /**
     * Accessors and mutators for all the instance variables
     *
     * @param n
     *
     * Sets a name to the course given by an argument
     */
    public void setName(String n){
        name = n;
    }

    /**
     * @return
     *
     * returns the name given to the course
     */
    public String getName(){
        return name;
    }

    /**
     * @param d
     *
     * Sets a department to the course given by an argument unless the
     * argument is not a length of 3 or it's a number.
     */
    public void setDepartment(String d){
        department = d;
    }

    /**
     * @return
     * returns the name of the department that was given to the course
     */
    public String getDepartment(){
        return department;
    }
    /**
     * @param s
     *
     * sets the section of the course given by the argument
     * if the argument is a negative number, it throws an exception
     * else it sets the section to the course
     */
    public void setSection(byte s){
        if (s < 0){
            throw new IllegalArgumentException();
        }
        else{
            section = s;
        }
    }

    /**
     * @return
     * returns the section for the course
     */
    public byte getSection() {
        return section;
    }

    /**
     *
     * @param c
     *
     * if the argument is negative, it'll throw an exception
     * else it'll set the argument as the course code
     */
    public void setCode(int c){
        if (c < 0){
            throw new IllegalArgumentException();
        }
        else {
            code = c;
        }
    }

    /**
     *
     * @return
     *
     * returns the course code
     */
    public int getCode(){
        return code;
    }

    /**
     *
     * @param i
     *
     * sets the argument to the instructor variable unless it's a number.
     */
    public void setInstructor(String i) {
        instructor = i;
    }

    /**
     *
     * @return
     *
     * returns the argument given to the instructor
     */
    public String getInstructor(){
        return instructor;
    }

    /**
     *
     * @return
     * it creates and returns a clone of the Course object
     * @throws CloneNotSupportedException
     *
     * it throws this exception if the object doesn't support cloning
     */

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    /**
     *
     * @param o
     * Object o is the Course object that will be used to compare to another to
     * see if it's equal
     *
     * @return
     *
     * returns true if the objects are equal and false if it's not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Course)){ // if it's not a Course, return false
            return false;
        }

        Course course = (Course) o;

        if (getCode() != course.getCode() || getSection() !=
                course.getSection()
                ||!getName().equals(course.getName())
                || !getDepartment().equals(course.getDepartment())){
            return false;
        }
        return getInstructor().equals(course.getInstructor());
        }
}