module ru.nsu.malov {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.malov to javafx.fxml;
    exports ru.nsu.malov;
}