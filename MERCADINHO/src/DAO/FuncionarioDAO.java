package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Funcionario;
import Util.Alerts;
import javafx.scene.control.Alert.AlertType;

public class FuncionarioDAO {
    
    // CREATE - Inserir um novo funcionário
    public void create(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Funcionario (nomeFuncionario, senha, cpfFuncionario, emailFuncionario, telefoneFuncionario, generoFuncionario, enderecoFuncionario, dataNascFuncionario, cargo, salario, dataDeAdmissao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getCpf());
            stmt.setString(4, funcionario.getEmail());
            stmt.setString(5, funcionario.getTelefone());
            stmt.setString(6, funcionario.getGenero());
            stmt.setString(7, funcionario.getEndereco());
            stmt.setString(8, funcionario.getDataNasc());
            stmt.setString(9, funcionario.getCargo());
            stmt.setString(10, funcionario.getSalario());
            stmt.setString(11, funcionario.getDataAdms());
            
            stmt.executeUpdate();
            System.out.println("Funcionário cadastrado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar funcionário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // READ - Buscar todos os funcionários
    public ArrayList<Funcionario> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionario");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getString("idFuncionario"));
                funcionario.setNome(rs.getString("nomeFuncionario"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setCpf(rs.getString("cpfFuncionario"));
                funcionario.setEmail(rs.getString("emailFuncionario"));
                funcionario.setTelefone(rs.getString("telefoneFuncionario"));
                funcionario.setGenero(rs.getString("generoFuncionario"));
                funcionario.setEndereco(rs.getString("enderecoFuncionario"));
                funcionario.setDataNasc(rs.getString("dataNascFuncionario"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getString("salario"));
                funcionario.setDataAdms(rs.getString("dataDeAdmissao"));
                
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionários!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return funcionarios;
    }
    
    // UPDATE - Atualizar informações do funcionário
    public void update(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Funcionario SET nomeFuncionario = ?, senha = ?, emailFuncionario = ?, telefoneFuncionario = ?, generoFuncionario = ?, enderecoFuncionario = ?, dataNascFuncionario = ?, cargo = ?, salario = ?, dataDeAdmissao = ? WHERE idFuncionario = ? OR cpfFuncionario = ?");
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getTelefone());
            stmt.setString(5, funcionario.getGenero());
            stmt.setString(6, funcionario.getEndereco());
            stmt.setString(7, funcionario.getDataNasc());
            stmt.setString(8, funcionario.getCargo());
            stmt.setString(9, funcionario.getSalario());
            stmt.setString(10, funcionario.getDataAdms());
            stmt.setString(11, funcionario.getId());
            stmt.setString(12, funcionario.getCpf());
            
            stmt.executeUpdate();
            System.out.println("Funcionário atualizado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar funcionário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // DELETE - Remover um funcionário
    public void delete(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Funcionario WHERE idFuncionario = ? OR cpfFuncionario = ?");
            stmt.setString(1, funcionario.getId());
            stmt.setString(2, funcionario.getCpf());
            
            stmt.executeUpdate();
            System.out.println("Funcionário deletado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar funcionário!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // SEARCH - Buscar funcionários pelo CPF ou nome
    public ArrayList<Funcionario> search(Funcionario funcionario) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Funcionario WHERE cpfFuncionario LIKE ? OR nomeFuncionario LIKE ?");
            stmt.setString(1, "%" + funcionario.getCpf() + "%");
            stmt.setString(2, "%" + funcionario.getNome() + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Funcionario func = new Funcionario();
                func.setId(rs.getString("idFuncionario"));
                func.setNome(rs.getString("nomeFuncionario"));
                func.setSenha(rs.getString("senha"));
                func.setCpf(rs.getString("cpfFuncionario"));
                func.setEmail(rs.getString("emailFuncionario"));
                func.setTelefone(rs.getString("telefoneFuncionario"));
                func.setGenero(rs.getString("generoFuncionario"));
                func.setEndereco(rs.getString("enderecoFuncionario"));
                func.setDataNasc(rs.getString("dataNascFuncionario"));
                func.setCargo(rs.getString("cargo"));
                func.setSalario(rs.getString("salario"));
                func.setDataAdms(rs.getString("dataDeAdmissao"));
                
                funcionarios.add(func);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar funcionários!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return funcionarios;
    }
    
    // Autenticar na Tela Login
    public Funcionario autenticarUser(String cpf, String senha) {
    	
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
		Funcionario func = new Funcionario();

    	
    	try {
			stmt = con.prepareStatement("select * from Funcionario where cpfFuncionario = ? and senha = ?");
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
            func.setId(rs.getString("idFuncionario"));
            func.setNome(rs.getString("nomeFuncionario"));
            func.setSenha(rs.getString("senha"));
            func.setCpf(rs.getString("cpfFuncionario"));
            func.setEmail(rs.getString("emailFuncionario"));
            func.setTelefone(rs.getString("telefoneFuncionario"));
            func.setGenero(rs.getString("generoFuncionario"));
            func.setEndereco(rs.getString("enderecoFuncionario"));
            func.setDataNasc(rs.getString("dataNascFuncionario"));
            func.setCargo(rs.getString("cargo"));
            func.setSalario(rs.getString("salario"));
            func.setDataAdms(rs.getString("dataDeAdmissao"));
			}
			
		} catch (SQLException e) {
			Alerts.showAlert("Erro!!", "Erro de conexão", "Falha ao consultar informações no bando de dados", AlertType.ERROR);
			throw new RuntimeException("Erro de autenticação", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
    	
		return func;
    }
    
    public String getTotalVendido(String id) {
    	
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	String TotalVendido = null;
    	
    	try {
    		
			stmt = con.prepareStatement("select SUM(precoTotal) as TotalVendido from Venda where codeFuncionario = ?");
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				TotalVendido = rs.getString(1);
			}
			
		} catch (SQLException e) {
			Alerts.showAlert("Erro!", "Erro de conexão", "Falha ao consultar informações no banco de dados.", AlertType.ERROR);
			throw new RuntimeException("Erro!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
    	
		return TotalVendido;
    }
    
    public ArrayList<String> readVendedorByNome() {
    	
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<String> vendedores = new ArrayList<>();
    	
    	try {
    		
            stmt = con.prepareStatement("SELECT nomeFuncionario FROM Funcionario");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
            	String nomeFuncionario;
            	nomeFuncionario = rs.getString(1);
            	vendedores.add(nomeFuncionario);
                
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os vendedores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
    	
    	return vendedores;
    	
	}
    
}
