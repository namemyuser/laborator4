package repo.tests;

import model.Teacher;
import org.junit.jupiter.api.Test;
import repo.FileRepositoryModifiers.JSONRepoModifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class TeachersTest {

    @Test
    void getAll() throws FileNotFoundException {
        JSONRepoModifier<Teacher> teachersRepory = new JSONRepoModifier<>(new Teacher(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Teachers.json");
        assert(teachersRepory.getAll().size() == 2);
        assert(teachersRepory.getAll().get(0).getTeacherID() == 1);
    }

    @Test
    void create() throws FileNotFoundException {
        JSONRepoModifier<Teacher> teachersRepory = new JSONRepoModifier<>(new Teacher(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Teachers.json");

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Teacher testCaseTeacher = new Teacher(3,"Cristea", "Diana", courses);

        try {
            teachersRepory.create(testCaseTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(teachersRepory.getAll().size() == 3);
    }

    @Test
    void delete() throws FileNotFoundException {
        JSONRepoModifier<Teacher> teachersRepory = new JSONRepoModifier<>(new Teacher(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Teachers.json");

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Teacher testCaseTeacher = new Teacher(3,"Cristea", "Diana", courses);

        try {
            teachersRepory.delete(testCaseTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(teachersRepory.getAll().size() == 2);
    }

    @Test
    void update() throws FileNotFoundException {
        JSONRepoModifier<Teacher> teachersRepory = new JSONRepoModifier<>(new Teacher(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Teachers.json");

        List<Integer> allCourses = new ArrayList<>();
        allCourses.add(1);
        allCourses.add(2);

        Teacher oldTeacher = new Teacher(5, "Sacarea", "Christian", allCourses);

        try {
            teachersRepory.create(oldTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Teacher newTeacher = new Teacher(5, "Mada", "Dicu", allCourses);

        teachersRepory.update(oldTeacher,newTeacher);

        assert(teachersRepory.getAll().get(2).getLastName().equals("Dicu"));
        assert(teachersRepory.getAll().get(2).getFirstName().equals("Mada"));

        try {
            teachersRepory.delete(newTeacher);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}