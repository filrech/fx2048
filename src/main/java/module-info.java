module com.fx2048 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    exports com.fx2048.Model;
    exports com.fx2048.View;
    exports com.fx2048.Controller;
}