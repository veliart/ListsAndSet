package ru.skypro.ListsAndSets.sevices.impl;

import org.springframework.stereotype.Service;
import ru.skypro.ListsAndSets.exception.EmployeeAlreadyAddedException;
import ru.skypro.ListsAndSets.exception.EmployeeNotFoundException;
import ru.skypro.ListsAndSets.exception.EmployeeStorageIsFullException;
import ru.skypro.ListsAndSets.model.Employee;
import ru.skypro.ListsAndSets.sevices.EmployeeService;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int STORAGE_SIZE = 10;
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= STORAGE_SIZE) {
            throw new EmployeeStorageIsFullException("Превышено количество сотрудников в " + STORAGE_SIZE + " чел.");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник" + firstName + " " + lastName + " уже работает в компании");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник" + firstName + " " + lastName + " не найден в списке.");
        }
        employees.remove(new Employee(firstName, lastName));
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee requestedEmployee = new Employee(firstName, lastName);
        for (Employee empoyee : employees) {
            if (empoyee.equals(requestedEmployee)) {
                return requestedEmployee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник " + firstName + " " + lastName +
                " не найден.");
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
