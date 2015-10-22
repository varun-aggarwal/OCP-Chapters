package domain;

import java.util.HashSet;
import java.util.Set;

public class Course<T extends Student> implements Comparable<Course> {

    private String courseName;

    private Set<T> studentList = new HashSet<T>();

    public Course(String courseName, Set<T> studentList) {
        this.courseName = courseName;
        this.studentList = studentList;
    }

    /**
     * @return the key
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.courseName = key;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(Set<T> studentList) {
        this.studentList = studentList;
    }

    public Set<T> getStudentList() {
        return this.studentList;
    }

    public boolean equals(Object course) {
        if (course instanceof Course<?>) {
            Course<? extends Student> newCourse = (Course<? extends Student>) course;
            return newCourse.getCourseName().equals(this.courseName);
        }
        return false;
    }

    public int hashCode() {
        return this.courseName.length();
    }

    @Override
    public int compareTo(Course course) {
        return this.courseName.compareTo(course.getCourseName());
    }

}
