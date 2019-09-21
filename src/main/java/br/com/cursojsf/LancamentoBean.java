package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.dao.DaoGeneric;
import br.com.entidades.Lancamento;
import br.com.entidades.Pessoa;
import br.com.repository.IDaoLancamento;
import br.com.repository.IDaoLancamentoImpl;

@ViewScoped
@ManagedBean(name = "lacamentoBean")
public class LancamentoBean {
	private Lancamento lancamento = new Lancamento();
	private DaoGeneric<Lancamento> daoDaoGeneric = new DaoGeneric<Lancamento>();
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	private IDaoLancamento daoDaoLancamento = new IDaoLancamentoImpl();

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		lancamento.setUsuario(pessoaUser);
		daoDaoGeneric.salvar(lancamento);
		carregarLancamentos();
		return "";

	}

	@PostConstruct
	private void carregarLancamentos() {
		// apenas carrega lançamentos do usuário logado
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		lancamentos = daoDaoLancamento.consultar(pessoaUser.getId());

	}

	public String novo() {
		lancamento = new Lancamento();
		return "";
	}

	public String deletar() {
		daoDaoGeneric.deletePorId(lancamento);
		lancamento = new Lancamento();
		carregarLancamentos();
		return "";
	}

	public String clean() {
		return "";
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public DaoGeneric<Lancamento> getDaaoDaoGeneric() {
		return daoDaoGeneric;
	}

	public void setDaaoDaoGeneric(DaoGeneric<Lancamento> daaoDaoGeneric) {
		this.daoDaoGeneric = daaoDaoGeneric;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
