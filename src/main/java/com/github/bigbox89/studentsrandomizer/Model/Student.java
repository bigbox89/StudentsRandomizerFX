package com.github.bigbox89.studentsrandomizer.Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleStringProperty command, secondName, name, homework, comment;
    private SimpleIntegerProperty testBall, numPropuskov;
    private SimpleBooleanProperty asked;
    private SimpleBooleanProperty answered;
    private SimpleFloatProperty rating;

    public Student(String command, String secondName, String name, String homework, String comment, String testBall, String numPropuskov, int asked, int answered, String rating) {

        if (!testBall.equals("")) {
            this.testBall = new SimpleIntegerProperty(Integer.parseInt(testBall));
        } else {
            this.testBall = new SimpleIntegerProperty(0);
        }
        if (!numPropuskov.equals("")) {
            this.numPropuskov = new SimpleIntegerProperty(Integer.parseInt(numPropuskov));
        } else {
            this.numPropuskov = new SimpleIntegerProperty(0);
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

    /**
     * @return the command
     */
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
     * @return the number of classes skippings
     */
    public Integer getNumPropuskov() {
        return numPropuskov.get();
    }

    /**
     * @param numPropuskov the number of classes skippings to set
     */
    public void setNumPropuskov(Integer numPropuskov) {
        this.numPropuskov.set(numPropuskov);
    }

    /**
     * @return the asked
     */
    public int getAsked() {
        int i = 0;
        if (this.asked.getValue()) {
            i = 1;
        }
        return i;
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
    public int getAnswered() {
        int i = 0;
        if (this.answered.getValue()) {
            i = 1;
        }
        return i;
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
        return command.get() + ";" + secondName.get() + ";" + name.get() + ";" + homework.get() + ";" + comment.get() + ";" + testBall.get() + ";" + numPropuskov.get() + ";"
                + askedNum + ";"
                + answeredNum + ";"
                + rating.get() + System.lineSeparator();
    }
}
