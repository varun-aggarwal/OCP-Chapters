package domain;

import java.util.List;

public interface Student {

    public void setName(String name);

    public String getName();

    public Integer getRollNumber();

    public void setRollNumber(Integer rollNumber);

    public List<Integer> getSemesterResults(Integer semesterNumber);

    public void addSemesterResults(Integer semeterNumber, List<Integer> semesterResult);
}
