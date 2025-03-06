package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import Model.Cliente;
import Util.Alerts;
import Util.cpfValidator;
import Util.emailValidator;
import Util.telefoneValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class controllerCadastroCliente implements Initializable {

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCancelar;
    
    @FXML
    private Label labelCliente;

    @FXML
    private ChoiceBox<String> choiceGenero;

    @FXML
    private DatePicker dpDataNasc;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtTelefone;

    @FXML
    void actionCadastrar(ActionEvent event) {
    	
    		Cliente cliente = new Cliente();
    		ClienteDAO clienteDAO = new ClienteDAO();
    		
    		if(txtCPF.getText() == "" || cpfValidator.validarCPF(txtCPF.getText()) == false) {
    			
    			Alerts.showAlert("Erro!", "CPF inválido", "Verifique o CPF e tente novamente", AlertType.ERROR);
    			
    		} else if(txtEmail.getText() != "" && emailValidator.validarEmail(txtEmail.getText()) == false) {
    			
    			Alerts.showAlert("Erro!", "E-mail inválido", "Verifique o E-mail e tente novamente", AlertType.ERROR);
    			
    		} else if (txtTelefone.getText() == "" || telefoneValidator.validarTelefone(txtTelefone.getText()) == false) {
    			
    			Alerts.showAlert("Erro!", "Telefone inválido", "Verifique o número de telefone e tente novamente", AlertType.ERROR);
    			
    		} else if(txtNome.getText() == "" || txtTelefone.getText() == "" || choiceGenero.getValue() == null || dpDataNasc.getValue() == null || txtEndereco.getText() == "") {
    			
    			Alerts.showAlert("Erro!!", "Informações imcompletas!", "Verifique se preencheu todos os campos e tente novamente", AlertType.ERROR);
    			
    		} else {
    			
    			cliente.setNome(txtNome.getText());
    			cliente.setCpf(txtCPF.getText());
    			cliente.setEmail(txtEmail.getText());
    			cliente.setEndereco(txtEndereco.getText());
    			cliente.setTelefone(txtTelefone.getText());
    			cliente.setDataNasc(dpDataNasc.getValue().toString());
    			cliente.setGenero(choiceGenero.getValue());
    			
    			txtNome.setText("");
				txtCPF.setText("");
				txtEmail.setText("");
				txtEndereco.setText("");
				txtTelefone.setText("");
				dpDataNasc.setValue(null);
				choiceGenero.setValue(null);
    			
    			if (controllerCliente.clienteEditar == null) {
    				
    				clienteDAO.create(cliente);
    				Alerts.showAlert("Sucesso!", "Cliente Cadastrado!!!", "O Cliente " + cliente.getNome() +  " foi cadastrado com sucesso :D", AlertType.INFORMATION);
    	    	
    				Stage stage = (Stage) btCancelar.getScene().getWindow();
    				stage.close();
    				
    			} else if (controllerCliente.clienteEditar != null) {
    				
    				clienteDAO.update(cliente);
    				Alerts.showAlert("Sucesso!", "Cliente editado!!", "O Cliente " + cliente.getNome() +  " foi editado com sucesso :3", Alert.AlertType.INFORMATION);
    				
    				Stage stage = (Stage) btCancelar.getScene().getWindow();
    				stage.close();
    				
    			}
    			
    		}

    }

    @FXML
    void actionCancelar(ActionEvent event) {
    	
    	txtNome.setText("");
    	txtCPF.setText("");
    	txtEmail.setText("");
    	txtEndereco.setText("");
    	txtTelefone.setText("");
    	dpDataNasc.setValue(null);
    	choiceGenero.setValue(null);
    	controllerCliente.clienteEditar = null;
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelCliente.setText("CADASTRAR CLIENTE");
		choiceGenero.getItems().add("Masculino");
		choiceGenero.getItems().add("Feminino");
		
		if (controllerCliente.clienteEditar != null) {
			
			btCadastrar.setText("SALVAR");
			labelCliente.setText("EDITAR CLIENTE");
			
			Cliente clienteEditar = new Cliente();
			clienteEditar = controllerCliente.clienteEditar;
			
			txtCPF.setText(clienteEditar.getCpf());
			txtCPF.setEditable(false);
			txtNome.setText(clienteEditar.getNome());
			txtEndereco.setText(clienteEditar.getEndereco());
			txtTelefone.setText(clienteEditar.getTelefone());
			txtEmail.setText(clienteEditar.getEmail());
			choiceGenero.setValue(clienteEditar.getGenero());
			LocalDate dataNasc = LocalDate.parse(clienteEditar.getDataNasc());
			dpDataNasc.setValue(dataNasc);
			
		}
		
	}

}
