module MERCADINHO {
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens Controller to javafx.graphics, javafx.fxml;
	opens Model to javafx.graphics, javafx.fxml, javafx.base;
}
