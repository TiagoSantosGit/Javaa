package br.com.cursojsf;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import com.google.gson.Gson;

import br.com.JPAUtil.JPAUtil;
import br.com.dao.DaoGeneric;
import br.com.entidades.Cidades;
import br.com.entidades.Estados;
import br.com.entidades.Pessoa;
import br.com.repositor.IDaoPessoa;

//@ViewScoped
//@ManagedBean(name = "pessoaBean")
@ViewScoped
@Named(value = "pessoaBean")
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = -7294913556827565378L;
	@Inject
	private DaoGeneric<Pessoa> daoGeneric;
	@Inject
	private IDaoPessoa iDaoPessoa;
	@Inject
	private JPAUtil jpaUtil;
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private List<SelectItem> estados;
	private List<SelectItem> cidades;
	private BufferedReader br;
	private Part arquivoFoto;

	public String salvar() throws IOException {

		if (arquivoFoto != null) {
			pegaImagem();
		}
		pessoa = daoGeneric.merge(pessoa);
		editar();
		carregarPessoas();
		mostrarMsg("Cadastrado com sucesso!");
		return "";
	}

	public String novo() {
		return limpar();
	}

	public String limpar() {
		pessoa = new Pessoa(); // deixa o formulário limpo
		return "";
	}

	public String remove() {
		daoGeneric.deletePorId(pessoa);
		limpar();
		carregarPessoas();
		return "";
	}

	@PostConstruct // carrega automático com a página
	public void carregarPessoas() {
		pessoas = daoGeneric.getListEntity(Pessoa.class);
		estados = iDaoPessoa.listaEstados();
	}

	public String logar() {
		Pessoa pessoaUser = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		if (pessoaUser != null) {
			// achou o usuário
			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);
			return "primeiraPagina.jsf";
		}
		return "index.jsf";
	}

	public String deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");
		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
		httpServletRequest.getSession().invalidate();
		return "index.jsf";
	}

	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		return pessoaUser.getPerfilUser().equals(acesso);
	}

	private void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);

	}

	public void pesquisaCEP(AjaxBehaviorEvent event) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + pessoa.getCep() + "/json/");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String cep = "";
			StringBuilder jsonCep = new StringBuilder();
			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}
			Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
			pessoa.setLocalidade(gsonAux.getLogradouro());
			pessoa.setComplemento(gsonAux.getComplemento());
			pessoa.setBairro(gsonAux.getBairro());
			pessoa.setLocalidade(gsonAux.getLocalidade());
			pessoa.setUf(gsonAux.getUf());
			pessoa.setIbge(gsonAux.getIbge());
			pessoa.setGia(gsonAux.getGia());
		} catch (Exception e) {
			mostrarMsg("Erro ao consultar o CEP: " + e.getMessage());
		}
	}

	public void carregaCidades(AjaxBehaviorEvent event) {
		// pega só o id do objeto
		// String codigoEstado =
		// event.getComponent().getAttributes().get("submittedValue").toString();
		Estados estado = (Estados) ((HtmlSelectOneMenu) event.getSource()).getValue();
		// if (codigoEstado != null) {
		if (estado != null) {
			// Estados estado = JPAUtil.getEntityManager().find(Estados.class,
			// Long.parseLong(codigoEstado));
			if (estado != null) {
				pessoa.setEstados(estado);
				@SuppressWarnings("unchecked")
				List<Cidades> cidades = jpaUtil.getEntityManager()
						.createQuery("from Cidades where estados.id = " + estado.getId()).getResultList();
				List<SelectItem> selectItemsCidade = new ArrayList<SelectItem>();
				for (Cidades cidade : cidades) {
					// selectItemsCidade.add(new SelectItem(cidade.getId(), cidade.getNome()));
					selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
				}
				setCidades(selectItemsCidade);
			}
		}
	}

	public String editar() {
		if (pessoa.getCidades() != null) {
			Estados estado = pessoa.getCidades().getEstados();
			pessoa.setEstados(estado);
			@SuppressWarnings("unchecked")
			List<Cidades> cidades = jpaUtil.getEntityManager()
					.createQuery("from Cidades where estados.id = " + estado.getId()).getResultList();
			List<SelectItem> selectItemsCidade = new ArrayList<SelectItem>();
			for (Cidades cidade : cidades) {
				// selectItemsCidade.add(new SelectItem(cidade.getId(), cidade.getNome()));
				selectItemsCidade.add(new SelectItem(cidade, cidade.getNome()));
			}
			setCidades(selectItemsCidade);
		}
		return "";
	}

	private void pegaImagem() throws IOException {
		// Processar imagem
		byte[] imagemByte = getByte(arquivoFoto.getInputStream());
		pessoa.setFotoIconBase64Original(imagemByte);
		// Transformar em bufferImage
		BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagemByte));
		// Pega o tipo da imagem
		int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
		int largura = 200;
		int altura = 200;
		// Cria a miniatura
		BufferedImage resizeImage = new BufferedImage(altura, altura, type);
		Graphics2D g = resizeImage.createGraphics();
		g.drawImage(bufferedImage, 0, 0, largura, altura, null);
		g.dispose();
		// Escreve novamente a imagem em tamanho menor
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String extencao = arquivoFoto.getContentType().split("\\/")[1];// image/png
		ImageIO.write(resizeImage, extencao, baos);
		String miniImagem = "data:" + arquivoFoto.getContentType() + ";base64,"
				+ DatatypeConverter.printBase64Binary(baos.toByteArray());
		// Processar a imagem
		pessoa.setFotoIconBase64(miniImagem);
		pessoa.setExtencao(extencao);
	}

	// converte inputStream para array de bytes
	private byte[] getByte(InputStream is) throws IOException {
		int len;
		int size = 1024;
		byte[] buf = null;
		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1) {
				bos.write(buf, 0, len);
			}
			buf = bos.toByteArray();
		}
		return buf;
	}

	public void download() throws IOException {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String fileDownloadId = params.get("fileDownloadId");
		Pessoa pessoa = daoGeneric.consultar(Pessoa.class, Long.parseLong(fileDownloadId));
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		response.addHeader("Content-Disposition", "attachment; filename=donwload." + pessoa.getExtencao());
		response.setContentType("application/octet-stream");
		response.setContentLength(pessoa.getFotoIconBase64Original().length);
		response.getOutputStream().write(pessoa.getFotoIconBase64Original());
		response.getOutputStream().flush(); // Confirma
		FacesContext.getCurrentInstance().getResponseComplete();
	}

	public void registraLog() {
		System.out.println("método registraLog");
		// Criar rotina para criação de log
	}

	public void mudancaDeValor(ValueChangeEvent evento) {
		System.out.println("Valor antigo:" + evento.getOldValue());
		System.out.println("Valor novo:" + evento.getNewValue());
	}

	public void mudancaDeValorSobrenome(ValueChangeEvent evento) {
		System.out.println("Valor antigo:" + evento.getOldValue());
		System.out.println("Valor novo:" + evento.getNewValue());
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<SelectItem> getEstados() {
		return estados;
	}

	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}

	public List<SelectItem> getCidades() {
		return cidades;
	}

	public void setCidades(List<SelectItem> cidades) {
		this.cidades = cidades;
	}

	public Part getArquivoFoto() {
		return arquivoFoto;
	}

	public void setArquivoFoto(Part arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

}
