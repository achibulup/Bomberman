module com.uet.int2204.group2 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.uet.int2204.group2 to javafx.fxml;
    exports com.uet.int2204.group2;
    exports com.uet.int2204.group2.component;
    exports com.uet.int2204.group2.entity;
    exports com.uet.int2204.group2.graphics;
}
