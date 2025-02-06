package application;

import java.sql.Connection;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import DAO.Produto_VendaDAO;
import Model.Produto_Venda;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	
        Connection con = ConnectionDatabase.getConnection();
        ConnectionDatabase.closeConnection(con);
        
        Produto_Venda produtoVenda = new Produto_Venda();
        Produto_VendaDAO produtoVendaDAO = new Produto_VendaDAO();
        ArrayList<Produto_Venda> produtosVenda = new ArrayList<>();
        produtoVenda.setQuantidade("6");
        produtosVenda.addAll(produtoVendaDAO.search(produtoVenda));

        for (Produto_Venda pv : produtosVenda) {
            System.out.println("");
            System.out.print(pv.getId() + " | ");
            System.out.print(pv.getCodeProduto() + " | ");
            System.out.print(pv.getCodeVenda() + " | ");
            System.out.print(pv.getQuantidade() + " | ");
        }
        
        /*produtoVenda.setCodeProduto("15");
        produtoVenda.setCodeVenda("73");
        produtoVenda.setQuantidade("10");
        //produtoVenda.setId("69");
        
        produtoVendaDAO.create(produtoVenda);*/

        launch(args);
    }
}
