package com.github.bigbox89.studentsrandomizer.ArrayList;

import com.github.bigbox89.studentsrandomizer.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crew<T> implements MyArrayList {
    private static final int DEFAULT_SIZE = 10;
    private T[] student;
    private int size;
    private List<Integer> randomizeListV = new ArrayList<>();
    private List<Integer> randomizeListO = new ArrayList<>();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public List<Integer> getRandomizeListV() {
        return randomizeListV;
    }

    public void setRandomizeListV(List<Integer> randomizeListV) {
        this.randomizeListV = randomizeListV;
    }

    public List<Integer> getRandomizeListO() {
        return randomizeListO;
    }

    public void setRandomizeListO(List<Integer> randomizeListO) {
        this.randomizeListO = randomizeListO;
    }

    public Crew() {
        this.student = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Добавляет нового интерна(студента) в конец списка (В случае успешного добавления выводит true иначе false)
     *
     * @param t добавляемый студент
     * @return
     */
    @Override
    public boolean add(Object t) {
        try {
            // если массив уже заполнен
            if (isFullArray()) {
                resize();
            }
            this.student[size] = (T) t;
            size++;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Увеличивает размер массива до величины index (В случае успешного добавления выводит true иначе false)
     *
     * @param index размер массива
     * @return
     */

    @Override
    public boolean add(int index) {
        try {
            // если размер больше index то не увеличиваем
            if (size > index) return false;
            else
                this.size = index;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Метод меняет размер массива
     *
     * @Return
     */
    private void resize() {
        // запоминаем старый массив
        T[] oldElements = this.student;
        // создаем новый массив, который в полтора раза больше предыдущего
        this.student = (T[]) new Object[oldElements.length + oldElements.length / 2];
        // копируем все элементы из старого массива в новый
        for (int i = 0; i < size; i++) {
            this.student[i] = oldElements[i];
        }
    }

    /**
     * Метод проверяет массив на заполненность
     *
     * @Return
     */
    private boolean isFullArray() {
        return size == student.length;
    }

    /**
     * Получить студента по индексу
     *
     * @param index индекс искомого студента
     * @return студент под заданным индексом
     */
    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return student[index];
        }
        return null;
    }

    /**
     * Метод получает обьект из массива
     *
     * @Return
     */
    @Override
    public T get(Object o) {
        for (T t : student
        ) {
            if (t.equals(o))
                return t;
        }
        return null;
    }

    /**
     * Метод заменяет обьект в массиве по индексу
     *
     * @Return
     */
    @Override
    public void set(int index, Object o) {
        if (index >= 0 && index < size) {
            student[index] = (T) o;
        } else System.out.println("Нет такого элемента, который вы хотели заменить");
    }

    /**
     * Метод удаляет обьект из массива по индексу
     *
     * @Return
     */
    @Override
    public void remove(int index) {
        rangeCheck(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(student, index + 1, student, index, numMoved);
        }
        student[--size] = null;

    }

    /**
     * Метод проверяет выход за размеры массива
     *
     * @Return
     */
    public void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод удаляет обьект из массива по обьекту
     *
     * @Return
     */
    @Override
    public void remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (((T) get(i)).equals((T) o)) {
                remove(i);
            }
        }
    }

    /**
     * Метод возвращает размер массива
     *
     * @Return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Пузырьковая сортировка
     */
    @Override
    public void sort() {
        for (int i = 0; i < this.size; ++i) {
            for (int j = i + 1; j < this.size; ++j) {
                if (((Comparable) this.get(i)).compareTo(((Comparable) this.get(j))) < 0) {
                    Comparable tempData = (Comparable) this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, tempData);
                }

            }
        }
    }

    @Override
    public T[] randomizeTwo() {
        T[] values = (T[]) new Object[2];
        List<Integer> listV = getRandomizeListV();
        int last_num = listV.size();

        //добавляем номера в список
        if (last_num == 0)
            for (int y = 0; y <= size; y++) {
                listV.add(y);
            }
        //получаем номер первого студента*/
        values[0] = rundomStudent();
        values[1] = rundomStudent();
        return values;
    }

    public T[] randomTwo() {
        T[] values = (T[]) new Object[2];
        List<Integer> listV = getRandomizeListV();
        List<Integer> listO = getRandomizeListO();
        int last_numV = listV.size();
        int last_numO = listO.size();

        //добавляем номера в список
        if (last_numV == 0)
            for (int y = 0; y < size; y++) {
                listV.add(y);
            }
        if (last_numO == 0)
            for (int y = 0; y < size; y++) {
                listO.add(y);
            }
        //получаем номер первого студента*/
        values = rundomStudents();
        if (values != null)
            return values;
        else{
            System.out.println("Попытка опроса из одной команды");
            return null;
        }

    }

    private T rundomStudent() {
        Integer studentNum = 0;
        Random b = new Random();
        List<Integer> listV = getRandomizeListV();
        //получаем случайный номер студента
        int v = b.nextInt(listV.size());
        try {
            studentNum = listV.get(v);
            listV.remove(v);
            if (listV.size() == 0) {
                System.out.println("Все варианты перебраны");
                System.exit(0);
            }
            setRandomizeListV(listV);
        } catch (Exception t) {
        }
        return student[studentNum];
    }

    private T[] rundomStudents() {
        Integer studentV = 0;
        Integer studentO = 0;
        Random a = new Random();
        Random b = new Random();
        List<Integer> listV = getRandomizeListV();
        List<Integer> listO = getRandomizeListO();
        //получаем случайный номер студента
        int v = a.nextInt(listV.size());
        int o = b.nextInt(listO.size());
        studentV = listV.get(v);
        studentO = listO.get(o);
        if (((Student) student[studentV]).getCommand() == ((Student) student[studentO]).getCommand()) {
            System.out.println("Попали одинаковые команды...");
            return null;
        } else
            try {
                listV.remove(v);
                listO.remove(o);

                if (listV.size() == 0) {
                    System.out.println("Все студенты задали вопросы");
                    System.exit(0);
                }
                if (listO.size() == 0) {
                    System.out.println("Все студенты ответили на вопросы");
                    System.exit(0);
                }
                setRandomizeListV(listV);
                setRandomizeListO(listO);
            } catch (Exception t) {
            }
        T[] values = (T[]) new Object[2];
        values[0] = student[studentV];
        values[1] = student[studentO];
        return values;
    }

    /**
     * Печать в консоль данных студента
     *
     * @param t - печатаемый студент
     */
    public void print(T t) {
        System.out.println(t.toString());
    }

    /**
     * Печать в консоль списка студентов
     *
     * @return
     */

    public void printAll() {
        for (int i = 0; i < size; ++i) {
            print((T) this.get(i));
        }
    }

    /**
     * Печатает в списке капитанов (у кого второй тест >= 60)
     *
     * @return
     */
    public void isCapitan() {

        int porog = 60;
        for (int i = 0; i < this.size; ++i) {
            if (((Student) this.get(i)).getTestBall() >= porog) System.out.println(this.get(i));
        }
    }


    /**
     * Метод очищает список
     *
     * @return
     */
    public void clean() {
        size = 0;
    }
}
