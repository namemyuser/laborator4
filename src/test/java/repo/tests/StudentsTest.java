package repo.tests;

import model.Student;
import org.junit.jupiter.api.Test;
import repo.FileRepositoryModifiers.JSONRepoModifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class StudentsTest {

    @Test
    void getAll() throws FileNotFoundException {
        JSONRepoModifier<Student> studentsRepository = new JSONRepoModifier<>(new Student(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Students.json");
        assert(studentsRepository.getAll().size() == 2);
        assert(studentsRepository.getAll().get(0).getStudentId() == 1);
    }

    @Test
    void create() throws FileNotFoundException {
        JSONRepoModifier<Student> studentsRepository = new JSONRepoModifier<>(new Student(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Students.json");

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Student testStudent = new Student("Andrei", "Lapuste", 5, 20, courses);

        try {
            studentsRepository.create(testStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(studentsRepository.getAll().size() == 3);
    }

    @Test
    void delete() throws FileNotFoundException {
        JSONRepoModifier<Student> studentsRepository = new JSONRepoModifier<>(new Student(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Students.json");

        List<Integer> courses = new ArrayList<>();
        courses.add(1);
        courses.add(2);
        Student testStudent = new Student("Andrei", "Lapuste", 5, 20, courses);

        try {
            studentsRepository.delete(testStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert(studentsRepository.getAll().size() == 2);
    }

    @Test
    void update() throws FileNotFoundException {
        JSONRepoModifier<Student> studentsRepository = new JSONRepoModifier<>(new Student(), "C:\\personal\\an2\\map\\hausaufgabe4\\src\\test\\java\\repo\\data\\Students.json");

        List<Integer> coursesList = new ArrayList<>();
        coursesList.add(1);
        coursesList.add(2);
        Student oldStudent = new Student("Lucas", "Purcsa", 6, 40, coursesList);

        try {
            studentsRepository.create(oldStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Student newStudent = new Student("Andrei", "Raulea", 6, 40, coursesList);

        studentsRepository.update(oldStudent,newStudent);

        assert(studentsRepository.getAll().get(2).getLastName().equals("Raulea"));
        assert(studentsRepository.getAll().get(2).getFirstName().equals("Andrei"));

        try {
            studentsRepository.delete(newStudent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}