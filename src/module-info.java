module template {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires kernel;
	requires itextpdf;
	opens application to javafx.graphics, javafx.fxml,javafx.base;
}
