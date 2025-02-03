package Model;

public class Produto_Venda {
	
	private String id;
	private String codeFornecedor;
	private String codeVenda;
	private String quantidade;
	
	
	
	public Produto_Venda() {
		super();
	}


	public Produto_Venda(String id, String codeFornecedor, String codeVenda, String quantidade) {
		super();
		this.id = id;
		this.codeFornecedor = codeFornecedor;
		this.codeVenda = codeVenda;
		this.quantidade = quantidade;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodeFornecedor() {
		return codeFornecedor;
	}
	public void setCodeFornecedor(String codeFornecedor) {
		this.codeFornecedor = codeFornecedor;
	}
	public String getCodeVenda() {
		return codeVenda;
	}
	public void setCodeVenda(String codeVenda) {
		this.codeVenda = codeVenda;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
