package Model;

public class Funcionario {
	
	private String id;
	private String nome;
	private String cpf;
	private String dataNasc;
	private String email;
	private String genero;
	private String endereco;
	private String telefone;
	private String cargo;
	private String salario;
	private String dataAdms;
	private String senha;
	
	public Funcionario() {
		super();
	}


	public Funcionario(String id, String nome, String cpf, String dataNasc, String email, String genero,
			String endereco, String telefone, String cargo, String salario, String dataAdms, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.email = email;
		this.genero = genero;
		this.endereco = endereco;
		this.telefone = telefone;
		this.cargo = cargo;
		this.salario = salario;
		this.dataAdms = dataAdms;
		this.senha = senha;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getSalario() {
		return salario;
	}
	public void setSalario(String salario) {
		this.salario = salario;
	}
	public String getDataAdms() {
		return dataAdms;
	}
	public void setDataAdms(String dataAdms) {
		this.dataAdms = dataAdms;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
