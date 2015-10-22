package domain.factory;

import domain.Student;
import domain.impl.UniversityStudent;

public class StudentFactory {

    public static Student getStudent() {
        return new UniversityStudent();
    }

}
