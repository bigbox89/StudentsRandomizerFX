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

    private String command;
    private String secondName;
    private String name;
    private String homework;
    private String comment;
    private Integer testBall;
    private Integer numSkippings;
    private Boolean asked;
    private Boolean answered;
    private Float rating;

   /* private SimpleStringProperty forTxtcommand;
    private SimpleStringProperty forTxtsecondName;
    private SimpleStringProperty forTxtname;
    private SimpleStringProperty homework;
    private SimpleStringProperty comment;
    private SimpleIntegerProperty testBall;
    private SimpleIntegerProperty numSkippings;
    private SimpleBooleanProperty asked;
    private SimpleBooleanProperty answered;
    private SimpleFloatProperty rating;*/

    public Student() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the command
     */

    @Column(name = "command")
    public String getCommand() {
        return this.command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * @return the name
     */
    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name  to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the homework
     */
    @Column(name = "homework")
    public String getHomework() {
        return this.homework;
    }

    /**
     * @param homework the homework to Set
     */
    public void setHomework(String homework) {
        this.homework = homework;
    }

    /**
     * @return the comment
     */
    @Column(name = "comment")
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the rating
     */
    @Column(name = "rating")
    public Float getRating() {
        return this.rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * @return the second name
     */
    @Column(name = "secondName")
    public String getSecondName() {
        return this.secondName;
    }

    /**
     * @param secondName the second name to set
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * @return the test score
     */
    @Column(name = "testBall")
    public Integer getTestBall() {
        return Integer.valueOf(this.testBall);
    }

    /**
     * @param testBall the test score to set
     */
    public void setTestBall(Integer testBall) {
        this.testBall = testBall;
    }

    /**
     * @return the number of classes skipping
     */
    @Column(name = "numSkippings")
    public Integer getNumSkippings() {
        return Integer.valueOf(this.numSkippings);
    }

    /**
     * @param numSkippings the number of classes skippings to set
     */
    public void setNumSkippings(Integer numSkippings) {
        this.numSkippings = numSkippings;
    }

    /**
     * @return the asked
     */
    @Column(name = "asked")
    public int getAsked() {
        return this.asked ? 1 : 0;
    }

    /**
     * @param asked the asked to set
     */
    public void setAsked(Boolean asked) {
        this.asked = asked;

    }

    /**
     * @return the answered
     */
    @Column(name = "answered")
    public int getAnswered() {
        return this.answered ? 1 : 0;
    }

    /**
     * @param answered the answered to set
     */
    public void setAnswered(Boolean answered) {
        this.answered = answered;

    }

    @Override
    public String toString() {
        int askedNum = 0;
        int answeredNum = 0;
        if (asked)
            askedNum = 1;

        if (answered)
            answeredNum = 1;
        return command + ";" + secondName + ";" + name + ";" + homework + ";" + comment + ";" + testBall + ";" + numSkippings + ";"
                + askedNum + ";"
                + answeredNum + ";"
                + rating + System.lineSeparator();
    }

    public Student(String command, String secondName, String name, String homework, String comment, String testBall, String numSkippings, int asked, int answered, String rating) {
        this.command = command;
        this.secondName = secondName;
        this.name = name;
        this.homework = homework;
        this.comment = comment;
        this.testBall = Integer.valueOf(testBall);
        this.numSkippings = Integer.valueOf(numSkippings);
        this.asked = asked == 1;
        this.answered = answered == 1;
        this.rating = Float.valueOf(rating);
    }
}
