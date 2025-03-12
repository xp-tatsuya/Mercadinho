package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Cliente;

public class ClienteDAO {
	
	
	//CREATE - criar (insert)
	public void create(Cliente cliente) {
		
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO Cliente (nomeCliente, cpfCliente, emailCliente, telefoneCliente, generoCliente, enderecoCliente, dataNascCliente) VALUES (?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefone());
			stmt.setString(5, cliente.getGenero());
			stmt.setString(6, cliente.getEndereco());
			stmt.setString(7, cliente.getDataNasc());


			stmt.executeUpdate();
			System.out.println("Cadastrado com sucesso!!");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao cadastrar cliente!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
		
	}
	
	
	//READ - ler (select)
	public ArrayList<Cliente> read() {
		
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("SELECT * FROM Cliente");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setId(rs.getString(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEmail(rs.getString(4));
				cliente.setTelefone(rs.getString(5));
				cliente.setGenero(rs.getString(6));
				cliente.setEndereco(rs.getString(7));
				cliente.setDataNasc(rs.getString(8));
				
				clientes.add(cliente);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler informaçoes!!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return clientes;
		
	}
	
	//UPDATE - atualizar (update)
	public void update(Cliente cliente) {
		
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("UPDATE Cliente SET nomeCliente = ?, cpfCliente = ?, emailCliente = ?, telefoneCliente = ?, generoCliente = ?, enderecoCliente = ?, dataNascCliente = ? where idCliente = ? or cpfCliente = ?");
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getTelefone());
			stmt.setString(5, cliente.getGenero());
			stmt.setString(6, cliente.getEndereco());
			stmt.setString(7, cliente.getDataNasc());
			stmt.setString(8, cliente.getId());
			stmt.setString(9, cliente.getCpf());


			stmt.executeUpdate();
			System.out.println("Cliente atualizado com sucesso!!");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar cliente!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt);
	}
		
	}
	
	//DELETE - deletar (delete)
	public void delete(Cliente cliente) {
		
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("DELETE from Cliente WHERE idCliente = ? or cpfCliente = ?");
			stmt.setString(1, cliente.getId());
			stmt.setString(2, cliente.getCpf());


			stmt.executeUpdate();
			System.out.println("Deletado com sucesso!!");
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao deletar!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt);
	}
		
	}
	
	//SEARCH - procurar
	public ArrayList<Cliente> search(Cliente cliente1) {
		
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("SELECT * FROM Cliente WHERE cpfCliente like ? or nomeCliente like ?");
			stmt.setString(1, "%" + cliente1.getCpf() + "%");
			stmt.setString(2, "%" + cliente1.getNome() + "%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setId(rs.getString(1));
				cliente.setNome(rs.getString(2));
				cliente.setCpf(rs.getString(3));
				cliente.setEmail(rs.getString(4));
				cliente.setTelefone(rs.getString(5));
				cliente.setGenero(rs.getString(6));
				cliente.setEndereco(rs.getString(7));
				cliente.setDataNasc(rs.getString(8));
				
				clientes.add(cliente);
				
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao ler informaçoes!!", e);
		} finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return clientes;
		
	}
	
	public ArrayList<String> readClienteByNome() {
    	
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	ArrayList<String> clientes = new ArrayList<>();
    	
    	try {
    		
            stmt = con.prepareStatement("SELECT nomeCliente FROM Cliente");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                
            	String nomeCliente;
            	nomeCliente = rs.getString(1);
            	clientes.add(nomeCliente);
                
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler os clientes!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
    	
    	return clientes;
    	
	}
	
}



