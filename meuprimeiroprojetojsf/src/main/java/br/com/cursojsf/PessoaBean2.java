package br.com.cursojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;

//@RequestScoped //mantém o bean até o request
@ViewScoped // mantém o bean até a página for redirecionada
//@SessionScoped // mantém o bean na mesma sessão (navegador)
//@ApplicationScoped // mantém o bean de todos os usúarios de todas as sessões
//@ManagedBean(name = "pessoaBean")
public class PessoaBean2 {

	private String nome;
	private String senha;
	private String texto;
	private List<String> nomes = new ArrayList<String>();
	private HtmlCommandButton commandButton;

	public String addNome() {
		nomes.add(nome);
		if (nomes.size() > 3) {
			commandButton.setDisabled(true);
		}
		return "paginaNavegada?faces-redirect=true";
		// chamado pelo action se retorna null ou vazio fica na mesma página
		// no action actionListener não exige retorno
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<String> getNomes() {
		return nomes;
	}

	public void setNomes(List<String> nomes) {
		this.nomes = nomes;
	}

	public HtmlCommandButton getCommandButton() {
		return commandButton;
	}

	public void setCommandButton(HtmlCommandButton commandButton) {
		this.commandButton = commandButton;
	}

}
