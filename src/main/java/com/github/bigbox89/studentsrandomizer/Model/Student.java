package com.github.bigbox89.studentsrandomizer.Model;

import javax.persistence.*;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table
public class Student {
    private Integer id;
    private SimpleStringProperty command;
    private SimpleStringProperty secondName;
    private SimpleStringProperty name;
    private SimpleStringProperty homework;
    private SimpleStringProperty comment;
    private SimpleIntegerProperty testBall;
    private SimpleIntegerProperty numSkippings;
    private SimpleBooleanProperty asked;
    private SimpleBooleanProperty answered;
    private SimpleFloatProperty rating;

    public Student() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)

    public Integer getIdUsuario() {
        return this.id;
    }


    /**
     * @return the command
     */

    @Column(name = "command")
    public String getCommand() {
        return command.get();
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command.set(command);
    }

    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return name.get();
    }

    /**
     * @param name the name  to set
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     * @return the homework
     */
    @Column(name = "homework")
    public String getHomework() {
        return homework.get();
    }

    /**
     * @param homework the homework to Set
     */
    public void setHomework(String homework) {
        this.homework.set(homework);
    }

    /**
     * @return the comment
     */
    @Column(name = "comment")
    public String getComment() {
        return comment.get();
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment.set(comment);
    }

    /**
     * @return the rating
     */
    @Column(name = "rating")
    public Float getRating() {
        return rating.get();
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Float rating) {
        this.rating.set(rating);
    }

    /**
     * @return the second name
     */
    @Column(name = "secondName")
    public String getSecondName() {
        return secondName.get();
    }

    /**
     * @param secondName the second name to set
     */
    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    /**
     * @return the test score
     */
    @Column(name = "testBall")
    public Integer getTestBall() {
        return testBall.get();
    }

    /**
     * @param testBall the test score to set
     */
    public void setTestBall(Integer testBall) {
        this.testBall.set(testBall);
    }

    /**
     * @return the number of classes skipping
     */
    @Column(name = "numSkippings")
    public Integer getNumSkippings() {
        return numSkippings.get();
    }

    /**
     * @param numSkippings the number of classes skippings to set
     */
    public void setNumSkippings(Integer numSkippings) {
        this.numSkippings.set(numSkippings);
    }

    /**
     * @return the asked
     */
    @Column(name = "asked")
    public int getAsked() {
        return this.asked.getValue() ? 1 : 0;
    }

    /**
     * @param asked the asked to set
     */
    public void setAsked(int asked) {
        this.asked.set(false);
        if (asked == 1)
            this.asked.set(true);
    }

    /**
     * @return the answered
     */
    @Column(name = "answered")
    public int getAnswered() {
        return this.answered.getValue() ? 1 : 0;
    }

    /**
     * @param answered the answered to set
     */
    public void setAnswered(int answered) {
        this.answered.set(false);
        if (answered == 1)
            this.answered.set(true);
    }

    @Override
    public String toString() {
        int askedNum = 0;
        int answeredNum = 0;
        if (asked.get())
            askedNum = 1;

        if (answered.get())
            answeredNum = 1;
        return command.get() + ";" + secondName.get() + ";" + name.get() + ";" + homework.get() + ";" + comment.get() + ";" + testBall.get() + ";" + numSkippings.get() + ";"
                + askedNum + ";"
                + answeredNum + ";"
                + rating.get() + System.lineSeparator();
    }

    public Student(String command, String secondName, String name, String homework, String comment, String testBall, String numSkippings, int asked, int answered, String rating) {

        if (!testBall.equals("")) {
            this.testBall = new SimpleIntegerProperty(Integer.parseInt(testBall));
        } else {
            this.testBall = new SimpleIntegerProperty(0);
        }
        if (!numSkippings.equals("")) {
            this.numSkippings = new SimpleIntegerProperty(Integer.parseInt(numSkippings));
        } else {
            this.numSkippings = new SimpleIntegerProperty(0);
        }

        if (!rating.equals("")) {
            this.rating = new SimpleFloatProperty(Float.parseFloat(rating));
        } else {
            this.rating = new SimpleFloatProperty(0);
        }

        this.command = new SimpleStringProperty(command);
        this.secondName = new SimpleStringProperty(secondName);

        this.asked = new SimpleBooleanProperty(false);
        if (asked == 1) {
            this.asked = new SimpleBooleanProperty(true);
        }

        this.answered = new SimpleBooleanProperty(false);
        if (answered == 1) {
            this.answered = new SimpleBooleanProperty(true);
        }

        this.name = new SimpleStringProperty(name);
        this.homework = new SimpleStringProperty(homework);
        this.comment = new SimpleStringProperty(comment);
        this.rating = new SimpleFloatProperty(Float.parseFloat(rating));

    }
}
