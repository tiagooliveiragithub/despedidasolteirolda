module com.tiago.despedidasolteirolda {
    opens com.tiago.despedidasolteirolda.entities to javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires net.synedra.validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
        
    opens com.tiago.despedidasolteirolda to javafx.fxml;
    exports com.tiago.despedidasolteirolda;

    exports com.tiago.despedidasolteirolda.controllers to javafx.fxml;
    opens com.tiago.despedidasolteirolda.controllers to javafx.fxml;
}