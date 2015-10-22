package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import comparators.StudentNameComparator;
import domain.Course;
import domain.Student;

public class CourseServices {

    private static Map<String, Course<Student>> courseMap = new HashMap<String, Course<Student>>();

    public Course<Student> startNewCourse(Course<Student> course) {
        return courseMap.put(course.getCourseName(), course);
    }

    public Map<String, Course<Student>> getCurrentCourses() {
        return courseMap;
    }

    public Course<Student> getCourse(String courseName) {
        return courseMap.get(courseName);
    }

    public Set<Student> getStudentList(String courseName) {
        Course<Student> course = courseMap.get(courseName);
        return course.getStudentList();
    }

    public void takeAttendanceByRollNumber(String courseName) {
        Set<Student> studentList = getStudentList(courseName);
        for (Student student : studentList) {
            System.out.println("Present " + student.getName());
        }
    }

    public void takeAttendanceByStudentName(String courseName) {
        StudentNameComparator nameComparator = new StudentNameComparator();

        Set<Student> studentList = getStudentList(courseName);
        List<Student> newStudentList = new ArrayList<Student>();

        for (Student student : studentList) {
            newStudentList.add(student);
        }
        Collections.sort(newStudentList, nameComparator);

        for (Student student : newStudentList) {
            System.out.println("Present " + student.getName());
        }
    }

    public List<Student> getStudentCollection(String courseName) {
        Set<Student> studentList = getStudentList(courseName);
        List<Student> newStudentList = new ArrayList<Student>();

        for (Student student : studentList) {
            newStudentList.add(student);
        }
        return newStudentList;
    }

}
