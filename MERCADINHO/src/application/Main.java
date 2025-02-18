package application;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import DAO.Produto_VendaDAO;
import Model.Produto_Venda;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private static Stage stage;
	private static Scene login;
	private static Scene main;
	
	
    @Override
    public void start(Stage primaryStage) {
        try {
            
        	stage = primaryStage;
        	primaryStage.setTitle("Login");
        	
        	Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/View/viewlogin.fxml"));
        	login = new Scene(fxmlLogin);
        	
        	Parent fxmlMain = FXMLLoader.load(getClass().getResource("/View/viewMain.fxml"));
        	main = new Scene(fxmlMain);
        	
            primaryStage.setScene(login);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void changeScreen(String tela) {
    	
    	if (tela.equals("main")) {
    		stage.setScene(main);
    		stage.centerOnScreen();
    		stage.setTitle("Menu Principal");
    	}
    	
    }
    
    public static void TelaHome() throws IOException {
    	
    	FXMLLoader fxmlHome = new FXMLLoader();
    	fxmlHome.setLocation(Main.class.getResource("/View/viewMain.fxml"));
    	Parent TelaHome = fxmlHome.load();
    	main = new Scene(TelaHome);
    	
    	stage.setScene(main);
    	stage.setResizable(false);
    	stage.centerOnScreen();
    	stage.show();
    	
    } 
    
    
    public static void main(String[] args) {
    	
        

        launch(args);
    }
}
