module copyme.nani {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens copyme.nani to javafx.fxml;
    exports copyme.nani;
}