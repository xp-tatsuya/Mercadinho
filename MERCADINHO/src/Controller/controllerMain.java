package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.FuncionarioDAO;
import Model.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class controllerMain implements Initializable {

    @FXML
    private Button btClientes;

    @FXML
    private Button btFornecedores;

    @FXML
    private Button btFuncionarios;

    @FXML
    private Button btMain;

    @FXML
    private Button btProdutos;

    @FXML
    private Button btRegistroVenda;

    @FXML
    private Button btRelatorioVenda;

    @FXML
    private Button btSair;

    @FXML
    private TableView<?> tableProdutos;

    @FXML
    private Label txtTotalVendido;

    @FXML
    private Label txtUser;

    @FXML
    void logOut(ActionEvent event) {

    }

    @FXML
    void telaClientes(ActionEvent event) {

    }

    @FXML
    void telaFornecedores(ActionEvent event) {

    }

    @FXML
    void telaFuncionarios(ActionEvent event) {

    }

    @FXML
    void telaMain(ActionEvent event) {

    }

    @FXML
    void telaProdutos(ActionEvent event) {

    }

    @FXML
    void telaRegistroVenda(ActionEvent event) {

    }

    @FXML
    void telaRelatorioVenda(ActionEvent event) {

    }
    
    public static Funcionario funcionario = new Funcionario();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		String totalVendido;
		totalVendido = funcionarioDAO.getTotalVendido(controllerLogin.funcionario.getId());
		
		txtTotalVendido.setText("R$ " + totalVendido);
		
	}

}


