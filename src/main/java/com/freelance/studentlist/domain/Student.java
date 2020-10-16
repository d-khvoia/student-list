package com.freelance.studentlist.domain;

import com.freelance.studentlist.exception.*;

import com.sun.istack.NotNull;

import javax.persistence.*;

import java.util.Objects;

@Entity
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "studentseq")
@Table(name = "STUDENT")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private Integer age;

    public Student() { }

    public Student(String name, String surname, Integer age) {

        validateInput(name, surname, age);

        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    private void validateInput(String name, String surname, Integer age) {

        if (name == null || name.isBlank()) throw new InvalidNewStudentNameException(name);

        if (surname == null || surname.isBlank()) throw new InvalidNewStudentSurnameException(surname);

        if (age == null || age < 16) throw new InvalidNewStudentAgeException(age);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Student))
            return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name)
               && Objects.equals(surname, student.surname) && Objects.equals(age, student.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age);
    }

    @Override
    public String toString() {
        return "Student # " + id + " | Name: " + name + " | Surname: " + surname + " | Age: " + age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
