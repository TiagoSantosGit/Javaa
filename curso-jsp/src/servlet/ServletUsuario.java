package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

/**
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/salvarUsuario")
@MultipartConfig
public class ServletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;
    DaoUsuario daoUsuario = new DaoUsuario();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUsuario() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            String user = request.getParameter("user");
            if (acao.equalsIgnoreCase("delete")) {
                daoUsuario.delete(user);
                RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            } else {
                if (acao.equalsIgnoreCase("editar")) {
                    BeanCursoJsp beanCursoJsp = daoUsuario.consultar(user);
                    RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
                    request.setAttribute("user", beanCursoJsp);
                    request.setAttribute("usuarios", daoUsuario.listar());
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("listartodos")) {
                    RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
                    request.setAttribute("usuarios", daoUsuario.listar());
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("download")) {
                    BeanCursoJsp usuario = daoUsuario.consultar(user);
                    if (usuario != null) {
                        String contentType = "";
                        byte[] imageBytes = null;
                        String tipo = request.getParameter("tipo");

                        if (tipo.equalsIgnoreCase("imagem")) {
                            contentType = usuario.getContenType();
                            new Base64();
                            imageBytes = Base64.decodeBase64(usuario.getFotoBase64());
                        } else if (tipo.equalsIgnoreCase("curriculo")) {
                            contentType = usuario.getContenTypeCurriculo();
                            new Base64();
                            imageBytes = Base64.decodeBase64(usuario.getCurriculoBase64());
                        }
                        response.setHeader("Content-Disposition",
                                "attachment;filename=arquivo." + contentType.split("\\/")[1]);

                        /* coloca os bytes em objeto de entrada para processar */
                        InputStream is = new ByteArrayInputStream(imageBytes);

                        /* inicio da resposta para o navegador */
                        int read = 0;
                        byte[] bytes = new byte[1024];
                        OutputStream os = response.getOutputStream();
                        while ((read = is.read(bytes)) != -1) {
                            os.write(bytes, 0, read);
                        }
                        os.flush();
                        os.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao != null && acao.equalsIgnoreCase("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            String id = request.getParameter("id");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String cep = request.getParameter("cep");
            String cidade = request.getParameter("cidade");

            BeanCursoJsp usuario = new BeanCursoJsp();
            usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setTelefone(telefone);
            usuario.setCep(cep);
            usuario.setCidade(cidade);

            if (ServletFileUpload.isMultipartContent(request)) {

                Part imagemFoto = request.getPart("foto");

                if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
                    String fotoBase64 = Base64.encodeBase64String(converteIstremParaByte(imagemFoto.getInputStream()));
                    String contentType = imagemFoto.getContentType();
                    usuario.setFotoBase64(fotoBase64);
                    usuario.setContenType(contentType);
                    /* Inicio miniatura */

                    /* Transformar em um BufferedImagem */
                    byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));

                    /* Pega tipo da imagem */
                    int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();

                    /* Cria imagem em miniatura */
                    BufferedImage resizeImage = new BufferedImage(100, 100, type);
                    Graphics2D g = resizeImage.createGraphics();
                    g.drawImage(bufferedImage, 0, 0, 100, 100, null);
                    g.dispose();
                    /* Escrever imagem novamente */
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(resizeImage, "png", baos);
                    String miniaturaBase64 = "data:image/png;base64,"
                            + DatatypeConverter.printBase64Binary(baos.toByteArray());
                    usuario.setFotoBase64Miniatura(miniaturaBase64);
                    /* Fim miniatura */
                } else {
                    usuario.setFotoBase64(request.getParameter("fotoTemp"));
                    usuario.setContenType(request.getParameter("contenTypeTemp"));
                }
                Part imagemCurriculo = request.getPart("curriculo");
                if (imagemCurriculo != null && imagemCurriculo.getInputStream().available() > 0) {
                    new Base64();
                    String curriculoBase64 = Base64
                            .encodeBase64String(converteIstremParaByte(imagemCurriculo.getInputStream()));
                    String contentTypeCurriculo = imagemCurriculo.getContentType();
                    usuario.setCurriculoBase64(curriculoBase64);
                    usuario.setContenTypeCurriculo(contentTypeCurriculo);
                } else {
                    usuario.setCurriculoBase64(request.getParameter("curriculoTemp"));
                    usuario.setContenTypeCurriculo(request.getParameter("contenTypeCurriculoTemp"));
                }
            }
            try {
                if (id == null || id.isEmpty()) {
                    if (daoUsuario.validarLogin(login)) {
                        /*
                         * // Inicio file upload de imagens if
                         * (ServletFileUpload.isMultipartContent(request)) { List<FileItem> fileItems =
                         * new ServletFileUpload(new DiskFileItemFactory())
                         * .parseRequest((RequestContext) request); for (FileItem fileItem : fileItems)
                         * if (fileItem.getFieldName().contentEquals("foto")) { String fotoBase64 = new
                         * Base64().encodeBase64String(fileItem.get()); String contentType =
                         * fileItem.getContentType(); usuario.setFotoBase64(fotoBase64);
                         * usuario.setContenType(contentType); } } // Fim file upload
                         */
                        daoUsuario.Salvar(usuario);
                        request.setAttribute("msg", "Usuário gravado!");
                    } else {
                        request.setAttribute("msg", "Usuário já existe com o mesmo login!");
                    }
                } else {
                    daoUsuario.atualizar(usuario);
                    request.setAttribute("msg", "Usuário atualizado!");
                }
                request.setAttribute("user", usuario);
                RequestDispatcher view = request.getRequestDispatcher("cadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

//Converte a entrada de fluxo de dados para um array de bytes
    private byte[] converteIstremParaByte(InputStream imagem) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = imagem.read();
        while (reads != -1) {
            baos.write(reads);
            reads = imagem.read();
        }
        return baos.toByteArray();
    }
}
