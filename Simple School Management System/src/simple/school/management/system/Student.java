
package simple.school.management.system;

class Student 
{
    private String id;
    private String name;
    private String password;
    private String[] enrolledCourses;
    private int courseCount;

    public Student(String id, String name, String password) 
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.enrolledCourses = new String[5];
        this.courseCount = 0;
    }

    // Getters
    public String getId() 
    {
        return id;
    }
    public String getName() 
    {
        return name;
    }
    public String getPassword() 
    {
        return password;
    }
    public String[] getEnrolledCourses() 
    {
        return enrolledCourses;
    }

    // Enroll in a course
    public void enrollCourse(String course) 
    {
        if (courseCount < 5) 
        {
            enrolledCourses[courseCount] = course;
            courseCount++;
            System.out.println("Enrolled in course: " + course);
        } 
        else 
            System.out.println("Maximum course limit reached.");
    }

    // Remove a course
    public void removeCourse(String course) 
    {
        boolean found = false;
        for (int i = 0; i < courseCount; i++) 
        {
            if (enrolledCourses[i].equals(course)) 
            {
                enrolledCourses[i] = enrolledCourses[courseCount - 1];
                enrolledCourses[courseCount - 1] = null;
                courseCount--;
                found = true;
                System.out.println("Course removed: " + course);
                break;
            }
        }
        if (!found)
            System.out.println("Course not found.");
    }

    // Display student info
    public void showStudentInfo() 
    {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Enrolled Courses: ");
        if (courseCount == 0) {
            System.out.println("No courses enrolled.");
        }
        else
            for (int i = 0; i < courseCount; i++)
                System.out.println(enrolledCourses[i]);
        
    }
}
