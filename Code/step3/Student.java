package step3;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class Student {

    private final String name;
    private final int age;
    private final Gender gender;
    private final boolean active;
    private final List<Course> courses;

    public Student(String name, int age, Gender gender, boolean active, List<Course> courses) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.active = active;
        this.courses = courses;
    }

    public static Student student(String name, int age, Gender gender, boolean active,Course... courses){
        return new Student(name,age,gender,active, asList(courses));
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isActive() {
        return active;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", active=" + active +
                ", courses=" + courses +
                "\n}";
    }
}
