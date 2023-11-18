package ru.skypro.collections.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.collections.exception.EmployeeAlreadyAddedException;
import ru.skypro.collections.exception.EmployeeNotFoundException;
import ru.skypro.collections.exception.EmployeeStorageIsFullException;
import ru.skypro.collections.model.Employee;
import ru.skypro.collections.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int STORAGE_SIZE = 5;


    private final List<Employee> employees = new ArrayList<>();



    @Override
    public Employee add(String firstName, String lastName) {
    if (employees.size() >= STORAGE_SIZE){
        throw new EmployeeStorageIsFullException("Не можем добавить сотрудника! Хранилища уже полное");
    }
        Employee employee = new Employee(firstName, lastName);
    if (employees.contains(employee)){
        throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName +
                "и с фамилией " + lastName + " " + " уже имеется в хранилище");

    }


        employees.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new  Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName +
                    "и с фамилией " + lastName + " " + " не найден в хранилище");
        }

        employees.remove(employee);
        return employee;


    }

    @Override
    public Employee find(String firstName, String lastName) throws EnumConstantNotPresentException {
        Employee requestedemployee = new Employee(firstName, lastName);
        for (Employee employee: employees) {
            if (employee.equals(requestedemployee)){
                return requestedemployee;
            }
        }

        throw new EmployeeNotFoundException("Сотрудник с именем " + firstName +
                "и с фамилией " + lastName + " " + " не найден в хранилище");

    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
