package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import Model.Cliente;
import Model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class controllerRegistrarVenda implements Initializable{

    @FXML
    private Button btAdicionarProduto;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btFinalizar;

    @FXML
    private ChoiceBox<String> choicePagamento;

    @FXML
    private TextField txtNomeProduto;

    @FXML
    private TableColumn<Produto, String> columnPrecoTotal;

    @FXML
    private TableColumn<Produto, String> columnPrecoUn;

    @FXML
    private TableColumn<Produto, String> columnProduto;

    @FXML
    private TableColumn<Produto, String> columnQuantidade;

    @FXML
    private TableView<String> tableProdutos;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtCliente;

    @FXML
    private TextField txtCodProduto;

    @FXML
    private TextField txtDesconto;

    @FXML
    private TextField txtPrecoUn;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtValorTotal;

    @FXML
    private TextField txtVendedor;

    @FXML
    void actionAdicionar(ActionEvent event) {

    }
    
    @FXML
    void actionCPFClick(MouseEvent event) {
    	
    	if (txtCliente.getText().length() > 3) {
    		
    		ClienteDAO clienteDAO = new ClienteDAO();
    		Cliente cliente = new Cliente();
    		cliente.setNome(txtCliente.getText());
    		
    		ArrayList<Cliente> clientes = new ArrayList<>();
    		clientes = clienteDAO.search(cliente);
    		cliente = clientes.get(0);
    		txtCPF.setText(cliente.getCpf());
    		
    	}

    }

    @FXML
    void actionCPFType(KeyEvent event) {
    	
    	if (txtCliente.getText().length() > 3) {
    		
    		ClienteDAO clienteDAO = new ClienteDAO();
    		Cliente cliente = new Cliente();
    		cliente.setNome(txtCliente.getText());
    		
    		ArrayList<Cliente> clientes = new ArrayList<>();
    		clientes = clienteDAO.search(cliente);
    		cliente = clientes.get(0);
    		txtCPF.setText(cliente.getCpf());
    		
    	} else {
    		txtCPF.setText(null);
    	}

    }

    @FXML
    void actionCancelar(ActionEvent event) {

    }

    @FXML
    void actionFinalizar(ActionEvent event) {

    }
    
    @FXML
    void actionProdutoClick(MouseEvent event) {
    	
    	if (txtNomeProduto.getText().length() > 3) {
    		
    		ProdutoDAO produtoDAO = new ProdutoDAO();
    		Produto produto = new Produto();
    		produto.setNome(txtNomeProduto.getText());
    		
    		ArrayList<Produto> produtos = new ArrayList<>();
    		produtos = produtoDAO.search(produto);
    		produto = produtos.get(0);
    		txtCodProduto.setText(produto.getCodBarra());
    		
    	}

    }

    @FXML
    void actionProdutoType(KeyEvent event) {
    	
    	if (txtNomeProduto.getText().length() > 3) {
    		
    		ProdutoDAO produtoDAO = new ProdutoDAO();
    		Produto produto = new Produto();
    		produto.setNome(txtNomeProduto.getText());
    		
    		ArrayList<Produto> produtos = new ArrayList<>();
    		produtos = produtoDAO.search(produto);
    		produto = produtos.get(0);
    		txtCodProduto.setText(produto.getCodBarra());
    		
    	} else {
    		txtCodProduto.setText(null);
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		choicePagamento.getItems().add("Débito");
		choicePagamento.getItems().add("Dinheiro");
		choicePagamento.getItems().add("Pix");
		txtVendedor.setText(controllerLogin.funcionario.getNome());
		
		
		//Auto-completar Produto
		ProdutoDAO produtoDAO = new ProdutoDAO();
		ArrayList<String> nomeProdutos = new ArrayList<String>();
		nomeProdutos = produtoDAO.readProdutoByNome();
		String[] produto = new String[nomeProdutos.size()];
		
		for (int i = 0; i < nomeProdutos.size(); i++) {
			
			produto[i] = nomeProdutos.get(i);
			
		}
		
		TextFields.bindAutoCompletion(txtNomeProduto, produto);
		
		//Auto-completar Cliente
		ClienteDAO clienteDAO = new ClienteDAO();
		ArrayList<String> nomeClientes = new ArrayList<String>();
		nomeClientes = clienteDAO.readClienteByNome();
		String[] cliente = new String[nomeClientes.size()];
		
		for (int i = 0; i < nomeClientes.size(); i++) {
			
			cliente[i] = nomeClientes.get(i);
			
		}
		
		TextFields.bindAutoCompletion(txtCliente, cliente);
		
		//Auto-completar Vendedor
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		ArrayList<String> nomeFuncionarios = new ArrayList<String>();
		nomeFuncionarios = funcionarioDAO.readVendedorByNome();
		String[] funcionario = new String[nomeFuncionarios.size()];
		
		for (int i = 0; i < nomeFuncionarios.size(); i++) {
			
			funcionario[i] = nomeFuncionarios.get(i);
			
		}
		
		TextFields.bindAutoCompletion(txtVendedor, funcionario);
		
	}

}
