package org.example.util;

import org.example.model.Employee;
import org.example.staticdata.AbstractFIO;
import org.example.staticdata.FIOLists;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeUtil {
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private static final Range AGE_RANGE = new Range(18, 95);
    private static final Range SALARY_RANGE = new Range(22, 250);
    private static final List<String> SEX_LIST = List.of(MALE, FEMALE);

    public static Employee nextFakeEmployee() {
        int age = nextRandomInt(AGE_RANGE.min, AGE_RANGE.max);
        int salary = nextRandomInt(SALARY_RANGE.min, SALARY_RANGE.max);
        String sex = nextRandomObj(SEX_LIST);

        AbstractFIO fio = sex.equals(MALE) ? nextRandomObj(FIOLists.MALE_FIO_LIST) : nextRandomObj(FIOLists.FEMALE_FIO_LIST);
        String name = nextRandomObj(fio.getNames());
        String surname = nextRandomObj(fio.getSurnames());

        Employee employee = new Employee();
        employee.setName(name + " " + surname);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setSex(sex);

        return employee;
    }

    private static int nextRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static <T> T nextRandomObj(List<T> objects) {
        int randomIdx = nextRandomInt(0, objects.size() - 1);

        return objects.get(randomIdx);
    }

    private record Range(
            int min,
            int max
    ) {
    }
}
