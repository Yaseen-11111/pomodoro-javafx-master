module com.github.yaseen {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires java.logging;

    opens com.github.yaseen to javafx.fxml;
    exports com.github.yaseen;
}