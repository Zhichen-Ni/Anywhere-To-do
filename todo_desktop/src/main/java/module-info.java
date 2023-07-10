module com.example.todo_desktop.ui {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires kotlin.stdlib;
    requires java.desktop;
    requires java.base;
    requires tornadofx;
    requires todo.lib.jvm;
    requires kotlinx.serialization.json;
    requires kotlin.reflect;
    requires kotlinx.datetime;
    requires jdk.jpackage;
    requires jdk.jfr;

    opens com.example.todo_desktop.ui;
    exports com.example.todo_desktop.ui;
    exports com.example.todo_desktop.controller;
    exports com.example.todo_desktop.common;
    exports com.example.todo_desktop.app;
    exports com.example.todo_desktop.data;
    exports com.example.todo_desktop.service;
}