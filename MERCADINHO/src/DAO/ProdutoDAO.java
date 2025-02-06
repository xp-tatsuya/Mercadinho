package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionFactory.ConnectionDatabase;
import Model.Produto;

public class ProdutoDAO {
    
    // CREATE - Inserir um novo produto
    public void create(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO Produto (codeFornecedor, nomeProduto, codBarra, lote, dataFabricacao, dataValidade, marca, categoria, unidadeDeMedida, precoUnitario, estoque) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, produto.getCodFornecedor());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCodBarra());
            stmt.setString(4, produto.getLote());
            stmt.setString(5, produto.getDataFab());
            stmt.setString(6, produto.getDataVal());
            stmt.setString(7, produto.getMarca());
            stmt.setString(8, produto.getCategoria());
            stmt.setString(9, produto.getUnidadeDeMed());
            stmt.setString(10, produto.getPrecoUn());
            stmt.setString(11, produto.getEstoque());
            
            stmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar produto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // READ - Buscar todos os produtos
    public ArrayList<Produto> read() {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Produto");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getString("idProduto"));
                produto.setCodFornecedor(rs.getString("codeFornecedor"));
                produto.setNome(rs.getString("nomeProduto"));
                produto.setCodBarra(rs.getString("codBarra"));
                produto.setLote(rs.getString("lote"));
                produto.setDataFab(rs.getString("dataFabricacao"));
                produto.setDataVal(rs.getString("dataValidade"));
                produto.setMarca(rs.getString("marca"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setUnidadeDeMed(rs.getString("unidadeDeMedida"));
                produto.setPrecoUn(rs.getString("precoUnitario"));
                produto.setEstoque(rs.getString("estoque"));
                
                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
    
    // UPDATE - Atualizar informações do produto
    public void update(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE Produto SET codeFornecedor = ?, nomeProduto = ?, codBarra = ?, lote = ?, dataFabricacao = ?, dataValidade = ?, marca = ?, categoria = ?, unidadeDeMedida = ?, precoUnitario = ?, estoque = ? WHERE idProduto = ? or codBarra = ?");
            stmt.setString(1, produto.getCodFornecedor());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getCodBarra());
            stmt.setString(4, produto.getLote());
            stmt.setString(5, produto.getDataFab());
            stmt.setString(6, produto.getDataVal());
            stmt.setString(7, produto.getMarca());
            stmt.setString(8, produto.getCategoria());
            stmt.setString(9, produto.getUnidadeDeMed());
            stmt.setString(10, produto.getPrecoUn());
            stmt.setString(11, produto.getEstoque());
            stmt.setString(12, produto.getId());
            stmt.setString(13, produto.getCodBarra());
            
            stmt.executeUpdate();
            System.out.println("Produto atualizado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // DELETE - Remover um produto
    public void delete(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM Produto WHERE idProduto = ? or codBarra = ?");
            stmt.setString(1, produto.getId());
            stmt.setString(2, produto.getCodBarra());
            
            stmt.executeUpdate();
            System.out.println("Produto deletado com sucesso!");
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar produto!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt);
        }
    }
    
    // SEARCH - Buscar produtos pelo nome ou código de barras
    public ArrayList<Produto> search(Produto produto) {
        Connection con = ConnectionDatabase.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Produto WHERE nomeProduto LIKE ? OR codBarra LIKE ?");
            stmt.setString(1, "%" + produto.getNome() + "%");
            stmt.setString(2, "%" + produto.getCodBarra() + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getString("idProduto"));
                prod.setCodFornecedor(rs.getString("codeFornecedor"));
                prod.setNome(rs.getString("nomeProduto"));
                prod.setCodBarra(rs.getString("codBarra"));
                prod.setLote(rs.getString("lote"));
                prod.setDataFab(rs.getString("dataFabricacao"));
                prod.setDataVal(rs.getString("dataValidade"));
                prod.setMarca(rs.getString("marca"));
                prod.setCategoria(rs.getString("categoria"));
                prod.setUnidadeDeMed(rs.getString("unidadeDeMedida"));
                prod.setPrecoUn(rs.getString("precoUnitario"));
                prod.setEstoque(rs.getString("estoque"));
                
                produtos.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produtos!", e);
        } finally {
            ConnectionDatabase.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
}
