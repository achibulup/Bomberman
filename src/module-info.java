module com.uet.int2204.group2 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens com.uet.int2204.group2 to javafx.fxml;
    exports com.uet.int2204.group2;
    exports com.uet.int2204.group2.component;
    exports com.uet.int2204.group2.controller;
    exports com.uet.int2204.group2.entity;
    exports com.uet.int2204.group2.entity.movable;
    exports com.uet.int2204.group2.entity.tile;
    exports com.uet.int2204.group2.entity.tile.item;
    exports com.uet.int2204.group2.graphics;
    exports com.uet.int2204.group2.map;
    exports com.uet.int2204.group2.utils;
    exports com.uet.int2204.group2.controller.algorithm;
    exports com.uet.int2204.group2.sound;
}
