package managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import DAO.DaoEmail;
import DAO.DaoUsuario;
import Model.EmailUser;
import Model.UsuarioPessoa;
import dataTableLazy.LazyDataTableModelUserPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean implements Serializable {

	private static final long serialVersionUID = 4552613806499306438L;
	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private DaoUsuario<UsuarioPessoa> daoGeneric = new DaoUsuario<UsuarioPessoa>();
	private DaoEmail<EmailUser> daoEmail = new DaoEmail<EmailUser>();
	// private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private LazyDataTableModelUserPessoa<UsuarioPessoa> list = new LazyDataTableModelUserPessoa<UsuarioPessoa>();

	private BarChartModel barChartModel = new BarChartModel();
	private EmailUser emailUser = new EmailUser();
	private String campoPesquisa;

	@PostConstruct
	public void init() {
		// list = daoGeneric.listar(UsuarioPessoa.class);
		list.load(0, 5, null, null, null);
		this.montarGrafico();
	}

	private void montarGrafico() {
		barChartModel = new BarChartModel();
		ChartSeries userSalario = new ChartSeries("Salário do usuários");
		userSalario.setLabel("Users");
		// for (UsuarioPessoa usuarioPessoa : list) {
		for (UsuarioPessoa usuarioPessoa : list.list) {
			userSalario.set(usuarioPessoa.getNome(), usuarioPessoa.getSalario());
		}
		barChartModel.addSeries(userSalario); // adiciona grupo
		barChartModel.setTitle("Gráfico de salários");
	}

	public String salvar() {

		// list.remove(usuarioPessoa);
		list.list.remove(usuarioPessoa);
		daoGeneric.salvar(usuarioPessoa);
		this.montarGrafico();
		// list.add(usuarioPessoa);
		list.list.add(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Salvo com sucesso!"));
		return "";
	}

	public String novo() {
		usuarioPessoa = new UsuarioPessoa();
		return "";
	}

	public String remover() {
		try {
			// daoGeneric.deletarPoID(usuarioPessoa);
			daoGeneric.removerUsuario(usuarioPessoa);
			// list.remove(usuarioPessoa);
			list.list.remove(usuarioPessoa);
			this.novo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!"));
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Informação: ", "Existem telefones para o usuario!"));
			}
		}
		return "";
	}

	public void addEmail() {
		emailUser.setUsuarioPessoa(usuarioPessoa);
		emailUser = daoEmail.updateMerge(emailUser);
		usuarioPessoa.getEmails().add(emailUser);
		emailUser = new EmailUser();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Resultado", "Salvo com sucesso!"));

	}

	public void removerEmail() throws Exception {
		String codigoEmail = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigoemail");
		EmailUser remover = new EmailUser();
		remover.setId(Long.parseLong(codigoEmail));
		daoEmail.deletarPoID(remover);
		usuarioPessoa.getEmails().remove(remover);
	}

	public void pesquisar() {
		// list = daoGeneric.pesquisar(campoPesquisa);
		list.pesquisar(campoPesquisa);
		this.montarGrafico();

	}

	public void upload(FileUploadEvent image) {
		String imagem = "data:image/png;base64," + DatatypeConverter.printBase64Binary(image.getFile().getContents());
		usuarioPessoa.setImagem(imagem);
	}

	@SuppressWarnings("static-access")
	public void donwload() throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String fileDownloadID = params.get("fileDownloadId");
		UsuarioPessoa pessoa = daoGeneric.pesquisar2(Long.parseLong(fileDownloadID), UsuarioPessoa.class);
		byte[] imagem = new Base64().decodeBase64(pessoa.getImagem().split("\\,")[1]);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-Disposition", "attachment; filename=donwload.png");
		response.setContentType("application/octet-stream");
		response.setContentLength(imagem.length);
		response.getOutputStream().write(imagem);
		response.getOutputStream().flush();
		FacesContext.getCurrentInstance().getResponseComplete();
	}

	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public List<UsuarioPessoa> getList() {
		return list.list;
	}

	public void setList(LazyDataTableModelUserPessoa<UsuarioPessoa> list) {
		this.list = list;
	}

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public EmailUser getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(EmailUser emailUser) {
		this.emailUser = emailUser;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

}
