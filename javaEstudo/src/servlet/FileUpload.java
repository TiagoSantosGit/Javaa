package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.DaoUsuario;

@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FileUpload() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");
            if (acao.equalsIgnoreCase("carregar")) {
                RequestDispatcher viDispacher = request.getRequestDispatcher("upload.jsp");
                request.setAttribute("listaUserImagem", null);
                viDispacher.forward(request, response);
            } else if (acao.equalsIgnoreCase("domnload")) {
                String iduser = request.getParameter("iduser");
                // esta no projeto curso-jsp
                String imagem = new DaoUsuario().buscaImagem(iduser);
                if (imagem != null) {
                    response.setHeader("Content-Disposition", "attachment;filename=imagem.png");
                    /* Pega somente imagem pura */
                    String imagemPura = imagem.split(",")[1];
                    new Base64();
                    /* Converte base64 em bytes */
                    // byte [] imagemBytes = new Base64().decodeBase64(imagemPura);
                    byte[] imagemBytes = Base64.decodeBase64(imagemPura);
                    /* Coloca os bytes em um objetos de entrada para processar */
                    InputStream is = new ByteArrayInputStream(imagemBytes);
                    /* INICIO - Escrever dados da resposta */
                    int read = 0;
                    byte[] bytes = new byte[1024];
                    OutputStream os = response.getOutputStream();
                    while ((read = is.read(bytes)) != -1) {
                        os.write(bytes, 0, read);
                    }
                    os.flush();
                    os.close();
                    /* FIM - Escrever dados da resposta */
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fileUpload = request.getParameter("fileUpload");
            new DaoUsuario().gravarImagem(fileUpload);
            response.getWriter().write("Upload realizado com sucesso!");
        } catch (Exception e) {
            response.getWriter().write("Erro fatal no upload!");
        }
    }

}
