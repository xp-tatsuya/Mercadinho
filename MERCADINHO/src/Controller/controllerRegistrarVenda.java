package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import DAO.ClienteDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import DAO.Produto_VendaDAO;
import DAO.VendaDAO;
import Model.Cliente;
import Model.Produto;
import Model.Produto_Venda;
import Model.Venda;
import Util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class controllerRegistrarVenda implements Initializable {

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
    private TextField txtPrecoTotal;

    @FXML
    private TableColumn<Produto, String> columnPrecoTotal;

    @FXML
    private TableColumn<Produto, String> columnPrecoUn;

    @FXML
    private TableColumn<Produto, String> columnProduto;

    @FXML
    private TableColumn<Produto, String> columnQuantidade;

    @FXML
    private TableView<Produto> tableProdutos;

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
    
    double totalVenda;
    double desconto;
    private ArrayList<Produto> ArrayProdutos = new ArrayList<>();

    @FXML
    void actionAdicionar(ActionEvent event) {
    	
        Produto produtoVenda = new Produto();
        produtoVenda.setNome(txtNomeProduto.getText());
        produtoVenda.setEstoque(txtQuantidade.getText());
        produtoVenda.setPrecoUn(txtPrecoUn.getText());
        produtoVenda.setPrecoTotal(txtPrecoTotal.getText());

        String valor = txtPrecoTotal.getText().replace(",", ".");
        double precoTotal = Double.parseDouble(valor);
        totalVenda += precoTotal;
        txtValorTotal.setText(String.format("%.2f", totalVenda));

        ArrayProdutos.add(produtoVenda);

        valor = txtDesconto.getText().replace(",", ".");
        double valorDesconto = Double.parseDouble(valor);
        desconto += valorDesconto;

        carregarTableProdutos(ArrayProdutos);
    }
    
    @FXML
    void actionCPFClick(MouseEvent event) {
        if (txtCliente.getText().length() > 3) {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNome(txtCliente.getText());
            
            ArrayList<Cliente> clientes = clienteDAO.search(cliente);
            if (!clientes.isEmpty()) {
                cliente = clientes.get(0);
                txtCPF.setText(cliente.getCpf());
            }
        }
    }

    @FXML
    void actionCPFType(KeyEvent event) {
        if (txtCliente.getText().length() > 3) {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNome(txtCliente.getText());
            
            ArrayList<Cliente> clientes = clienteDAO.search(cliente);
            if (!clientes.isEmpty()) {
                cliente = clientes.get(0);
                txtCPF.setText(cliente.getCpf());
            }
        } else {
            txtCPF.setText("");
        }
    }

    @FXML
    void actionCancelar(ActionEvent event) throws IOException {
        limparCampos();
        Stage stage = (Stage) btCancelar.getScene().getWindow();
        stage.close();
    }
    

    @FXML
    void actionFinalizar(ActionEvent event) {
        
        String valorDescontoAtual = txtDesconto.getText();
        String valorPrecoTotalAtual = txtPrecoTotal.getText();
        
        if (txtCliente.getText() == null || txtCliente.getText().trim().isEmpty() ||
            txtCPF.getText() == null || txtCPF.getText().trim().isEmpty() ||
            txtVendedor.getText() == null || txtVendedor.getText().trim().isEmpty() ||
            txtNomeProduto.getText() == null || txtNomeProduto.getText().trim().isEmpty() ||
            txtQuantidade.getText() == null || txtQuantidade.getText().trim().isEmpty() ||
            choicePagamento.getValue() == null || choicePagamento.getValue().toString().trim().isEmpty()) {
            
            Alerts.showAlert("Erro!", "Falha ao Efetuar Venda!", 
                "Erro! Reeleia os campos e observe se há algo errado!!!", AlertType.ERROR);
            
            txtDesconto.setText(valorDescontoAtual);
            txtPrecoTotal.setText(valorPrecoTotalAtual);
            
            return;
        }
        
        try {
            
            Venda venda = new Venda();
            VendaDAO vendaDAO = new VendaDAO();
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            Produto produto = new Produto();
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto_Venda produtoVenda = new Produto_Venda();
            Produto_VendaDAO produtoVendaDAO = new Produto_VendaDAO();
            ArrayList<Cliente> clientes = new ArrayList<>();
            ArrayList<Produto> produtos = new ArrayList<>();
            
            cliente.setCpf(txtCPF.getText());
            clientes = clienteDAO.search(cliente);
            if (clientes.isEmpty()) {
                Alerts.showAlert("Erro!", "Falha ao Efetuar Venda!", 
                    "Cliente não encontrado. Verifique os dados.", AlertType.ERROR);
                return;
            }
            cliente = clientes.get(0);
            
            venda.setCodeFuncionario(controllerLogin.funcionario.getId());
            venda.setCodeCliente(cliente.getId());
            venda.setFormaDePagamento(choicePagamento.getValue().toString());
            venda.setDesconto("" + desconto);
            
            String valor = txtValorTotal.getText();
            valor = valor.replace(",", ".");
            double valorTotal = Double.parseDouble(valor);
            venda.setPrecoTotal("" + valorTotal);
            
            vendaDAO.create(venda);
            
            for (int i = 0; i < ArrayProdutos.size(); i++) {
                String idProduto;
                produto = ArrayProdutos.get(i);
                produtos = produtoDAO.searchID(produto);
                if (produtos.isEmpty()) {
                    Alerts.showAlert("Erro!", "Falha ao Efetuar Venda!", 
                        "Produto não encontrado. Verifique os dados.", AlertType.ERROR);
                    return;
                }
                produto = produtos.get(0);
                idProduto = produto.getId();
                produto = ArrayProdutos.get(i);
                
                produto.setId(idProduto);
                produtoVenda.setCodeProduto(idProduto);
                produtoVenda.setQuantidade(produto.getEstoque());
                produtoVenda.setCodeVenda(vendaDAO.readIdVenda());
                produtoVendaDAO.create(produtoVenda);
            }
            
            Alerts.showAlert("SUCESSO!", "Venda Efetuada!!", 
                "Venda concluída com sucesso!", AlertType.INFORMATION);
            limparCampos();
            ArrayProdutos = new ArrayList<>();
            carregarTableProdutos(ArrayProdutos);
        } catch (Exception e) {
            Alerts.showAlert("Erro!", "Falha ao Efetuar Venda!", 
                "Erro! Reeleia os campos e observe se há algo errado!!!", AlertType.ERROR);
            e.printStackTrace();
        }
    }



    
    @FXML
    void actionProdutoClick(MouseEvent event) {
        if (txtNomeProduto.getText().length() > 3) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setNome(txtNomeProduto.getText());
            
            ArrayList<Produto> produtos = produtoDAO.search(produto);
            if (!produtos.isEmpty()) {
                produto = produtos.get(0);
                txtCodProduto.setText(produto.getCodBarra());
                
                double valorUn = Double.parseDouble(produto.getPrecoUn());
                txtPrecoUn.setText("R$ " + String.format("%.2f", valorUn));
            }
            
            txtPrecoTotal.setText("");
            txtDesconto.setText("");
        }
    }

    @FXML
    void actionProdutoType(KeyEvent event) {
        if (txtNomeProduto.getText().length() > 3) {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto produto = new Produto();
            produto.setNome(txtNomeProduto.getText());
            
            ArrayList<Produto> produtos = produtoDAO.search(produto);
            if (!produtos.isEmpty()) {
                produto = produtos.get(0);
                txtCodProduto.setText(produto.getCodBarra());
                
                double valorUn = Double.parseDouble(produto.getPrecoUn());
                txtPrecoUn.setText("R$ " + String.format("%.2f", valorUn));
            }
            
            txtPrecoTotal.setText("");
            txtDesconto.setText("");
        } else {
            txtPrecoUn.setText("");
            txtPrecoTotal.setText("");
        }
    }
    
    @FXML
    void actionDesconto(KeyEvent event) {
        if (txtQuantidade.getText() == null || txtQuantidade.getText().trim().isEmpty()) {
            txtPrecoUn.setText("");
            txtPrecoTotal.setText("");
            txtCodProduto.setText("");
            return;
        }
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto();
        produto.setNome(txtNomeProduto.getText());
        ArrayList<Produto> produtos = produtoDAO.search(produto);
        
        if (produtos.isEmpty()) {
            return;
        }
        produto = produtos.get(0);
        
        txtCodProduto.setText(produto.getCodBarra());
        double precoUn = Double.parseDouble(produto.getPrecoUn());
        txtPrecoUn.setText("R$ " + String.format("%.2f", precoUn));
        
        double quantidade = Double.parseDouble(txtQuantidade.getText());
        
        if (quantidade >= 15) {
            double descontoCalc = (precoUn * quantidade) * 0.05;
            double precoTotal = precoUn * quantidade - descontoCalc;
            txtDesconto.setText(String.format("%.2f", descontoCalc));
            txtPrecoTotal.setText(String.format("%.2f", precoTotal));
        } else {
            double precoTotal = precoUn * quantidade;
            txtDesconto.setText("0,00");
            txtPrecoTotal.setText(String.format("%.2f", precoTotal));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choicePagamento.getItems().addAll("Débito", "Dinheiro", "Pix");
        txtVendedor.setText(controllerLogin.funcionario.getNome());
        
        // Configura autocompletar para nome dos produtos
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ArrayList<String> nomeProdutos = produtoDAO.readProdutoByNome();
        String[] produtosArray = nomeProdutos.toArray(new String[0]);
        TextFields.bindAutoCompletion(txtNomeProduto, produtosArray);
        
        // Configura autocompletar para nome dos clientes
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<String> nomeClientes = clienteDAO.readClienteByNome();
        String[] clientesArray = nomeClientes.toArray(new String[0]);
        TextFields.bindAutoCompletion(txtCliente, clientesArray);
        
        // Configura autocompletar para nome dos vendedores
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ArrayList<String> nomeFuncionarios = funcionarioDAO.readVendedorByNome();
        String[] funcionariosArray = nomeFuncionarios.toArray(new String[0]);
        TextFields.bindAutoCompletion(txtVendedor, funcionariosArray);
    }
    
    private void carregarTableProdutos(ArrayList<Produto> produtosList) {
    	
        if (produtosList == null) {
            produtosList = new ArrayList<>();
        }
        ObservableList<Produto> produtoVendidos = FXCollections.observableArrayList(produtosList);
        
        columnProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
        columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
        
        tableProdutos.setItems(produtoVendidos);
    }
    
    private void limparCampos() {
        txtCliente.setText("");
        txtCPF.setText("");
        txtVendedor.setText("");
        txtCodProduto.setText("");
        txtNomeProduto.setText("");
        txtQuantidade.setText("");
        txtPrecoUn.setText("");
        txtDesconto.setText("");
        txtPrecoTotal.setText("");
        choicePagamento.setValue(null);
        txtValorTotal.setText("");
        totalVenda = 0;
        desconto = 0;
    }

    
}
