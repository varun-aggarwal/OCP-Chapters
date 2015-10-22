package comparators;

import java.util.Comparator;
import java.util.List;

import domain.Student;

public class SemesterMarksComparator implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        int sumOfStudent1 = 0;
        int sumOfStudent2 = 0;
        sumOfStudent1 = getSumOfMarksFor1stSemester(student1);
        sumOfStudent2 = getSumOfMarksFor1stSemester(student2);
        return sumOfStudent2 - sumOfStudent1;
    }

    private int getSumOfMarksFor1stSemester(Student student) {
        int sumOfStudent = 0;
        List<Integer> semester1result = student.getSemesterResults(1);
        for (Integer marks : semester1result) {
            sumOfStudent += marks;
        }
        return sumOfStudent;
    }

}
