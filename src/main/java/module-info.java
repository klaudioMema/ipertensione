module it.univr.ipertensione_hope {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens it.univr.ipertensione_hope to javafx.fxml;
    opens it.univr.ipertensione_hope.Controller to javafx.fxml;
    opens it.univr.ipertensione_hope.Controller.admin to javafx.fxml;
    opens it.univr.ipertensione_hope.Controller.patient to javafx.fxml;
    opens it.univr.ipertensione_hope.Controller.doctor to javafx.fxml;
    opens it.univr.ipertensione_hope.Model to javafx.base;
    exports it.univr.ipertensione_hope.Model;
    exports it.univr.ipertensione_hope;
    exports it.univr.ipertensione_hope.Controller;
    exports it.univr.ipertensione_hope.Controller.admin;
    exports it.univr.ipertensione_hope.Controller.patient;
    exports it.univr.ipertensione_hope.Controller.doctor;


}