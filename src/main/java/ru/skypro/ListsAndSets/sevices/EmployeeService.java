package ru.skypro.ListsAndSets.sevices;

import ru.skypro.ListsAndSets.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(String firstName, String lastName);
    Employee remove(String firstName, String lastName);
    Employee find(String firstName, String lastName);
    List<Employee> getAll();
}
