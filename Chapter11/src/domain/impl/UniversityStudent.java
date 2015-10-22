package domain.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import domain.Student;

public class UniversityStudent implements Student, Comparable<UniversityStudent> {

    private String name;

    private Integer rollNumber;

    public Map<Integer, List<Integer>> semesterResults = new TreeMap<Integer, List<Integer>>();

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the rollNumber
     */
    public Integer getRollNumber() {
        return rollNumber;
    }

    /**
     * @param rollNumber the rollNumber to set
     */
    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;
    }

    /**
     * @return the semesterResults
     */
    public List<Integer> getSemesterResults(Integer semesterNumber) {
        return semesterResults.get(semesterNumber);
    }

    /**
     * @param semesterResults the semesterResults to set
     */
    public void addSemesterResults(Integer semeterNumber, List<Integer> semesterResult) {
        this.semesterResults.put(semeterNumber, semesterResult);
    }

    @Override
    public boolean equals(Object newObject) {
        Student newStudent = (Student) newObject;
        return this.rollNumber.equals(newStudent.getRollNumber());
    }

    @Override
    public int hashCode() {
        return this.rollNumber.intValue() * 3;
    }

    @Override
    public int compareTo(UniversityStudent o) {
        return rollNumber.compareTo(o.getRollNumber());
    }

}
