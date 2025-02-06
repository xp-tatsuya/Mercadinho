package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Fornecedor;

public class FornecedorDAO {
    
    // CREATE - inserir (insert)
    public void create(Fornecedor fornecedor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Fornecedor (nomeFornecedor, cnpj, telefone, endereco) VALUES (?, ?, ?, ?)");
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEndereco());

            stmt.executeUpdate();
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar fornecedor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // READ - ler (select)
    public ArrayList<Fornecedor> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Fornecedor");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getString("idFornecedor"));
                fornecedor.setNome(rs.getString("nomeFornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEndereco(rs.getString("endereco"));
                
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler informações dos fornecedores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return fornecedores;
    }
    
    // UPDATE - atualizar (update)
    public void update(Fornecedor fornecedor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Fornecedor SET nomeFornecedor = ?, cnpj = ?, telefone = ?, endereco = ? WHERE idFornecedor = ? or cnpj = ?");
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getId());
            stmt.setString(6, fornecedor.getCnpj());

            stmt.executeUpdate();
            System.out.println("Fornecedor atualizado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar fornecedor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // DELETE - deletar (delete)
    public void delete(Fornecedor fornecedor) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Fornecedor WHERE idFornecedor = ? or cnpj = ?");
            stmt.setString(1, fornecedor.getId());
            stmt.setString(2, fornecedor.getCnpj());

            stmt.executeUpdate();
            System.out.println("Fornecedor deletado com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar fornecedor!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
 // SEARCH - procurar
    public ArrayList<Fornecedor> search(Fornecedor fornecedor1) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Fornecedor WHERE cnpj LIKE ? OR nomeFornecedor LIKE ?");
            stmt.setString(1, "%" + fornecedor1.getCnpj() + "%");
            stmt.setString(2, "%" + fornecedor1.getNome() + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(rs.getString("idFornecedor"));
                fornecedor.setNome(rs.getString("nomeFornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setTelefone(rs.getString("telefone"));
                fornecedor.setEndereco(rs.getString("endereco"));
                
                fornecedores.add(fornecedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao ler informações dos fornecedores!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return fornecedores;
    }
    
}
