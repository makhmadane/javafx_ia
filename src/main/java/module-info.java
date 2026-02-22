module sn.data_ia_fx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.persistence;
    requires org.hibernate.orm.core;

    opens sn.data_ia_fx.entity;
    opens sn.data_ia_fx.repository;
    opens sn.data_ia_fx to javafx.fxml;
    exports sn.data_ia_fx;
}