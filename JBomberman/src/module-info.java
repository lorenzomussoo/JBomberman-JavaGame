/**
 * file module info
 */
module JBomberman {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	exports view;
	exports controller;
	opens model to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
}

