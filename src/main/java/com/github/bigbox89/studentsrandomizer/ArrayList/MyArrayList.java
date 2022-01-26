package com.github.bigbox89.studentsrandomizer.ArrayList;

public interface MyArrayList<T> {

    boolean add(T t); // реализован

    boolean add(int index); // реализован

    T get(int index); // реализован

    T get(T t); // реализован

    void set(int index, T t); // реализован

    void remove(int index); //реализован

    void remove(T t);

    int size(); // реализован

    void sort(); // реализован

    T[] randomizeTwo();

}
