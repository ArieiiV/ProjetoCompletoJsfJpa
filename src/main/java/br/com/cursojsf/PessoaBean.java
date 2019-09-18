package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.dao.DaoGeneric;
import br.com.entidades.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {
	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> daoGeneric = new DaoGeneric<Pessoa>();
	private List<Pessoa> pessoas = new  ArrayList<Pessoa>();

	public String salvar() {
		/*
		 * Mantem os dados da pessoa salva no banco pois o merge retorna a pessoa
		 * (objeto)
		 */
		pessoa = daoGeneric.merge(pessoa);
		carregarPessoas();
		return "";
	}

	public String novo() {
		/*
		 * Limpa da tela os dados quando instancia uma nova pessoa (objeto)
		 */
		pessoa = new Pessoa();
		return "";
	}

	//Depois que o bean é criado ele carrega na tela o metodo que esta anotado com  @PostConstruct
	@PostConstruct
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
	}
	
	public String deletar() {
		//Deleta a pessoa pelo ID
		daoGeneric.deletePorId(pessoa);
		//Depois de deletar a pessoa limpa os dados da tela 
		pessoa = new Pessoa();
		carregarPessoas();
		return "";
	}

	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public DaoGeneric<Pessoa> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Pessoa> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

}
