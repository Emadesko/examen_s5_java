module com.emadesko {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.yaml.snakeyaml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;

    opens com.emadesko.controllers to javafx.fxml;
    opens com.emadesko.entities to org.hibernate.orm.core,javafx.base;

    exports com.emadesko;
}

