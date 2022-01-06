package repo.tests;

import model.Course;
import org.junit.jupiter.api.Test;
import repo.FileRepositoryModifiers.JSONRepoModifier;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CoursesTest {

    @Test
    void getAll() throws FileNotFoundException {
        JSONRepoModifier<Course> coursesRepository = new JSONRepoModifier<>(new Course(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Courses.json");
        assert(coursesRepository.getAll().size() == 2);
        assert(coursesRepository.getAll().get(0).getCourseID() == 1);
    }

    @Test
    void create() throws FileNotFoundException {
        JSONRepoModifier<Course> coursesRepository = new JSONRepoModifier<>(new Course(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Courses.json");

        List<Integer> studentList = new ArrayList<>();
        studentList.add(1);
        studentList.add(2);
        Course testCourse = new Course(1, "asc", 1, 10, studentList,30);

        try {
            coursesRepository.create(testCourse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(coursesRepository.getAll().size() == 3);

    }

    @Test
    void delete() throws FileNotFoundException {
        JSONRepoModifier<Course> coursesRepository = new JSONRepoModifier<>(new Course(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Courses.json");

        List<Integer> studentList = new ArrayList<>();
        studentList.add(1);
        studentList.add(2);
        Course testCourse = new Course(1, "asc", 1, 10, studentList,30);

        try {
            coursesRepository.delete(testCourse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(coursesRepository.getAll().size() == 2);
    }

    @Test
    void update() throws FileNotFoundException {
        JSONRepoModifier<Course> coursesRepository = new JSONRepoModifier<>(new Course(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Courses.json");

        List<Integer> studentList = new ArrayList<>();
        studentList.add(1);
        studentList.add(2);
        Course oldVersion = new Course(3, "asc", 1, 10, studentList,30);

        try {
            coursesRepository.create(oldVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Course newVersion = new Course(3, "DSA", 1, 10, studentList,30);

        coursesRepository.update(oldVersion, newVersion);

        assert(coursesRepository.getAll().get(2).getName().equals("DSA"));

        try {
            coursesRepository.delete(newVersion);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}