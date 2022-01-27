module com.github.bigbox89.studentsrandomizer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.prefs;

    opens com.github.bigbox89.studentsrandomizer to javafx.fxml;
    exports com.github.bigbox89.studentsrandomizer;
    exports com.github.bigbox89.studentsrandomizer.Model;
    opens com.github.bigbox89.studentsrandomizer.Model to javafx.fxml;
    exports com.github.bigbox89.studentsrandomizer.Repository;
    opens com.github.bigbox89.studentsrandomizer.Repository to javafx.fxml;
    exports com.github.bigbox89.studentsrandomizer.View;
    opens com.github.bigbox89.studentsrandomizer.View to javafx.fxml;
}