package managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import DAO.DaoTelefone;
import DAO.DaoUsuario;
import Model.TelefoneUser;
import Model.UsuarioPessoa;

@ManagedBean(name = "telefoneManagedBean")
@ViewScoped
public class TelefoneManagerBean implements Serializable{

	private static final long serialVersionUID = 7086270962181638310L;
	private UsuarioPessoa user = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoUser = new DaoUsuario<UsuarioPessoa>();
	private DaoTelefone<TelefoneUser> daoTelefone = new DaoTelefone<TelefoneUser>();
	private TelefoneUser telefoneUser = new TelefoneUser();

	@PostConstruct
	public void init() {
		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		user = daoUser.pesquisar2(Long.parseLong(coduser), UsuarioPessoa.class);
	}

	public String salvar() {
		telefoneUser.setUsuarioPessoa(user);
		daoTelefone.salvar(telefoneUser);
		this.novo();
		user = daoUser.pesquisar2(user.getId(), UsuarioPessoa.class);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
		return "";
	}

	public String novo() {
		telefoneUser = new TelefoneUser();
		return "";
	}

	public String removeTelefone() throws Exception {
		daoTelefone.deletarPoID(telefoneUser);
		user = daoUser.pesquisar2(user.getId(), UsuarioPessoa.class);
		this.novo();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Telefone removido!"));
		return "";
	}

	public void setUser(UsuarioPessoa user) {
		this.user = user;
	}

	public UsuarioPessoa getUser() {
		return user;
	}

	public void setDaoTelefone(DaoTelefone<TelefoneUser> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public DaoTelefone<TelefoneUser> getDaoTelefone() {
		return daoTelefone;
	}

	public void setTelefoneUser(TelefoneUser telefoneUser) {
		this.telefoneUser = telefoneUser;
	}

	public TelefoneUser getTelefoneUser() {
		return telefoneUser;
	}

}
