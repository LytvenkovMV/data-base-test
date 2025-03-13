package org.example.util;

import org.example.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeUtil {
    private static final Range AGE_RANGE = new Range(18, 95);
    private static final Range SALARY_RANGE = new Range(22, 250);
    private static final Map<Boolean, String> SEX_MAP = Map.of(
            true, "male",
            false, "female");
    private static final Map<Boolean, List<String>> NAMES_MAP = Map.of(
            true, Names.MALE,
            false, Names.FEMALE);
    private static final Map<Boolean, List<String>> SURNAMES_MAP = Map.of(
            true, Surnames.MALE,
            false, Surnames.FEMALE);
    private static final Map<Boolean, Integer> NAMES_LENGTH_MAP = Map.of(
            true, Names.MALE.size(),
            false, Names.FEMALE.size());
    private static final Map<Boolean, Integer> SURNAMES_LENGTH_MAP = Map.of(
            true, Surnames.MALE.size(),
            false, Surnames.FEMALE.size());

    public static Employee nextFakeEmployee() {
        int age = ThreadLocalRandom.current().nextInt(AGE_RANGE.min, AGE_RANGE.max + 1);
        int salary = ThreadLocalRandom.current().nextInt(SALARY_RANGE.min, SALARY_RANGE.max + 1);

        boolean isMale = ThreadLocalRandom.current().nextBoolean();

        int nameIndex = ThreadLocalRandom.current().nextInt(0, NAMES_LENGTH_MAP.get(isMale));
        String name = NAMES_MAP.get(isMale).get(nameIndex);

        int surnameIndex = ThreadLocalRandom.current().nextInt(0, SURNAMES_LENGTH_MAP.get(isMale));
        String surname = SURNAMES_MAP.get(isMale).get(surnameIndex);

        Employee employee = new Employee();
        employee.setName(surname + " " + name);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setSex(SEX_MAP.get(isMale));

        return employee;
    }

    private record Range(
            int min,
            int max
    ) {
    }
}
