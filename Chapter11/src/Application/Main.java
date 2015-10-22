package Application;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import comparators.SemesterMarksComparator;
import domain.Course;
import domain.Student;
import domain.factory.StudentFactory;
import service.CourseServices;
import service.StudentService;

public class Main {

    public static void main(String[] args) {

        Main application = new Main();

        application.createDummydata();

        application.defaultSortingDemo();

        application.customSortingDemo();

        application.duplicateAdditionInHashSetDemo();

        application.searchInCollectionDemo();

        application.customComparatorDemo();

    }

    private void customComparatorDemo() {

        System.out.println("===============customComparatorDemo================================");

        CourseServices courseServices = new CourseServices();
        SemesterMarksComparator semesterMarksComparator = new SemesterMarksComparator();

        List<Student> ocpStudentList = courseServices.getStudentCollection("OCP");

        Collections.sort(ocpStudentList, semesterMarksComparator);

        System.out.println("Printing Student based upon their 1st Semester Marks");

        for (Student student : ocpStudentList) {
            System.out.println(student.getName());
        }

    }

    private void searchInCollectionDemo() {

        System.out.println("===============searchInCollectionDemo================================");

        Student student1 = StudentFactory.getStudent();
        student1.setName("Varun");
        student1.setRollNumber(1);
        Integer[] semester1result = {40, 40, 40, 40, 40};
        int semeterNumber = 1;
        student1.addSemesterResults(semeterNumber, Arrays.asList(semester1result));

        CourseServices courseServices = new CourseServices();

        Object[] OCPStudentArray = (Object[]) courseServices.getStudentCollection("OCP").toArray();

        Arrays.sort(OCPStudentArray);
        System.out.println("index of Varun = " + Arrays.binarySearch(OCPStudentArray, student1));

    }

    private void duplicateAdditionInHashSetDemo() {

        System.out.println("===============duplicateAdditionInHashSetDemo================================");
        StudentService studentService = new StudentService();
        Set<Student> allStudentSet = studentService.getDummyStudentSet();
        Course<Student> OCPCourse = new Course<>("OCP", allStudentSet);
        CourseServices courseServices = new CourseServices();
        System.out.println("=====Number of Current Courses" + courseServices.getCurrentCourses().size());
        courseServices.startNewCourse(OCPCourse);
        System.out.println("=====Number of Current Courses" + courseServices.getCurrentCourses().size());

    }

    private void customSortingDemo() {
        System.out.println("===============customSortingDemo================================");
        CourseServices courseServices = new CourseServices();
        courseServices.takeAttendanceByStudentName("OCP");
    }

    private void defaultSortingDemo() {
        System.out.println("===============defaultSortingDemo================================");
        CourseServices courseServices = new CourseServices();
        courseServices.takeAttendanceByRollNumber("OCP");
    }

    private void createDummydata() {

        StudentService studentService = new StudentService();
        Set<Student> allStudentSet = studentService.getDummyStudentSet();
        Object[] studentArray = (Object[]) allStudentSet.toArray();
        Course<Student> OCPCourse = new Course<>("OCP", allStudentSet);

        Set<Student> paas4SaasStudent = new HashSet<Student>();
        paas4SaasStudent.add((Student) studentArray[0]);
        paas4SaasStudent.add((Student) studentArray[1]);

        Course<Student> paas4SaasCourse = new Course<>("Paas4Saas", paas4SaasStudent);

        CourseServices courseServices = new CourseServices();
        courseServices.startNewCourse(OCPCourse);
        courseServices.startNewCourse(paas4SaasCourse);
    }

}
