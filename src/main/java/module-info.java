module com.example.new_coin {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jbcrypt;
    requires junit;
    requires org.junit.jupiter.api;

    opens com.example.new_coin to javafx.fxml;
    exports com.example.new_coin;
}