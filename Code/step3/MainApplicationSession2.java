package step3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static step3.Course.*;
import static step3.Gender.FEMALE;
import static step3.Gender.MALE;
import static step3.Student.student;

public class MainApplicationSession2 {

    static Student[] students = new Student[]{
            student("Ahmad", 20, MALE, true, MATH, CHEMISTRY, JAVA),
            student("Mohammad", 25, MALE, true, MATH, JAVA),
            student("ESA", 27, MALE, false, MATH, JAVA),
            student("ESRA", 19, FEMALE, true, MATH, ENGLISH),
            student("DANA", 40, FEMALE, true, MATH, ENGLISH, PHYSCICS),
            student("RUBA", 22, FEMALE, true, MATH, PHYSCICS)


    };

    public static void main(String[] args) {

//        Stream
//                .iterate(1,x->x+1)
//                .skip(20)
//                .limit(10)
//                .forEach(System.out::println);

//        Stream
//                .of(students)
//                .skip(4)
//                .forEach(System.out::println);
    }

    public static int add(int a,int b){

        return a==0?b:add(--a,++b);
    }

    public static List<Student> reduceAsList() {

        return Stream.of(students)
                .reduce(new ArrayList<Student>(),
                        (myList, student) -> {
                            myList.add(student);
                            return myList;
                        }
                        , (a, b) -> {
                            a.addAll(b);
                            return a;
                        });
    }

    public static Student getMaxAge(){
        return Stream.of(students)
                .reduce(students[0]
                        ,(stu1,stu2)->(stu1.getAge()>stu2.getAge()?stu1:stu2));
    }


    public static void intStreamReduce() {

        int reduce = IntStream
                .range(0, 101)
                .reduce(0, (a, b) -> a + b);

        System.out.println(reduce);


    }

    public static void intStreamAvg() {

        OptionalDouble sum = IntStream
                .range(0, 101)
                .average();
        System.out.println(sum);


    }

    public static void intStreamSum() {

        int sum = IntStream
                .range(0, 101)
                .sum();
        System.out.println(sum);


    }

    public static void intStream() {

        IntStream
                .range(0, 100)
                .map(x -> x * 2)
                .forEach(System.out::println);
    }

    public static List<Student> sort() {

        return Stream.of(students)
//                .sorted((st1, st2) -> st2.getAge() - st1.getAge())
                .sorted((st1, st2) -> st1.getName().compareTo(st2.getName()))

                .collect(toList());
    }

    public static Optional<Integer> divide(int a, int b) {

        return b == 0 ? Optional.empty() : Optional.of(a / b);
    }


    public static Optional<Student> getOne(Course course) {

        return Stream.of(students)
                .filter(st -> st.getCourses().contains(course))
                .findFirst();

    }

    public static void printActiveUsers() {


//        activeAndMale()
//                .stream()
//                .filter(stu->stu.getName().startsWith("A"))
//                .forEach(System.out::println);

//        System.out.println(numberOfStudentInCourse());

//        System.out.println(courses());

    }



    public static List<Student> active() {

        return Stream.of(students)
                .filter(Student::isActive)
                .collect(toList());

    }

    public static List<Student> activeAndMale() {

        return Stream.of(students)
                .filter(Student::isActive)
                .filter(stu -> stu.getGender().equals(MALE))
                .collect(toList());

    }

    public static List<Student> activeAndHaveMathCourseOrPhysics() {
        return Stream.of(students)
                .filter(Student::isActive)
                .filter(student -> student.getCourses().contains(MATH) ||
                        student.getCourses().contains(PHYSCICS))
                .collect(Collectors.toList());


    }

    public static Map<Course, Long> numberOfStudentInCourse() {

        return Stream.of(students)
                .flatMap(student -> student.getCourses().stream())
                .collect(Collectors.groupingBy(x -> x, Collectors.counting()));

    }

    public static Map<Course,List<Student>> groupStudentByCourse(){



        return Stream
                .of(students)
                .flatMap(stu -> stu.getCourses().stream().map(c -> new Tuple<Course,Student>(c, stu)))
                .collect(groupingBy(t -> t.get_1(), mapping(t -> t.get_2(), toList())));
    }

    public static List<Course> courses() {

        return Stream.of(students)
//                .filter(student -> student.getAge()>30)
                .flatMap(st -> st.getCourses().stream())
                .distinct()
                .collect(Collectors.toList());

    }
}
