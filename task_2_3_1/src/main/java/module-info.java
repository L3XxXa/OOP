module ru.nsu.malov.another_snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens ru.nsu.malov.another_snake to javafx.fxml;
    exports ru.nsu.malov.another_snake;
}