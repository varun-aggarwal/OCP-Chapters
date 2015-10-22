package service;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import domain.Student;
import domain.factory.StudentFactory;

public class StudentService {

    public Set<Student> getDummyStudentSet() {

        Set<Student> studentSet = new TreeSet<Student>();

        int semeterNumber = 1;
        Student student2 = StudentFactory.getStudent();
        student2.setName("Martijn");
        student2.setRollNumber(2);
        Integer[] semester2result = {40, 100, 100, 40, 80};
        student2.addSemesterResults(semeterNumber, Arrays.asList(semester2result));

        studentSet.add(student2);

        Student student3 = StudentFactory.getStudent();
        student3.setName("Howard");
        student3.setRollNumber(3);
        Integer[] semester1Result = {100, 100, 100, 100, 60};
        student3.addSemesterResults(semeterNumber, Arrays.asList(semester1Result));

        Student student1 = StudentFactory.getStudent();
        student1.setName("Varun");
        student1.setRollNumber(1);
        Integer[] semester1result = {40, 40, 40, 40, 40};
        student1.addSemesterResults(semeterNumber, Arrays.asList(semester1result));

        studentSet.add(student1);

        studentSet.add(student3);

        return studentSet;

    }

}
