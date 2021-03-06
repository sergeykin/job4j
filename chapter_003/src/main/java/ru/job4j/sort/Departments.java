package ru.job4j.sort;

import java.util.*;

/**
 * Class Departments.
 *
 * @author John Ivanov (johnivo@mail.ru)
 * @version $Id$
 * @since 25.02.2018
 */
public class Departments {

    /**
     * departments список кодов подразделений.
     */
    private ArrayList<String> departments;

    /**
     * Конструктор.
     *
     * @param departments .
     */
    public Departments(ArrayList<String> departments) {
        this.departments = departments;
    }

    /**
     * Метод добавляет недостающие в иерархии коды подразделений.
     *
     * @param departments входящий список кодов.
     * @return полнная иерархия кодов (несортированное множество).
     */
    public Set<String> addDep(ArrayList<String> departments) {
        Set<String> deps = new HashSet<>(departments);
        for (String dep : departments) {
            for (int i = 0; i < dep.length(); i++) {
                if (dep.charAt(i) == '\\') {
                    deps.add(dep.substring(0, i));
                }
            }
        }
        return deps;
    }

    /**
     * Метод выполняет сортировку по возрастанию.
     *
     * @param departments входящий список кодов.
     * @return отсортированное множество кодов.
     */
    public Set<String> sortUp(ArrayList<String> departments) {
        Set<String> sortedDep = new TreeSet<>(departments);
        sortedDep.addAll(addDep(departments));
        return sortedDep;
    }

    /**
     * Метод выполняет сортировку по убыванию.
     *
     * @param departments входящий список кодов.
     * @return отсортированный по убыванию список кодов.
     */
    public Set<String> sortDown(ArrayList<String> departments) {
        Set<String> sortedDep = new TreeSet<>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int result = 0;
                        int min = Math.min(o1.length(), o2.length());
                        String a = o1.substring(0, min);
                        String b = o2.substring(0, min);
                        for (int i = 0; i < min; i++) {
                            result = b.compareTo(a);
                            if (result != 0) {
                                break;
                            }
                        }
                        return result != 0 ? result : Integer.compare(o1.length(), o2.length());
                    }
                }
                //или Comparator.reverseOrder()
        );
        sortedDep.addAll(addDep(departments));
        return sortedDep;
    }
}