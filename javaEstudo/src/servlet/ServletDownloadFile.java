package servlet;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.OutputBuffer;

import dao.DaoUsuario;
import entidades.Usuario;
import service.RelatorioService;

@WebServlet("/pages/ServletDownloadFile")
public class ServletDownloadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoUsuario daoUsuario = new DaoUsuario();
    private RelatorioService relatorioService = new RelatorioService();

    public ServletDownloadFile() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            ServletContext context = request.getServletContext();
            String tipoExporta = request.getParameter("tipoExportar");
            List<Usuario> usuarios = daoUsuario.listar();
            /*
             * List dados = new ArrayList(); dados.add(usuarios);
             */
            String fileUrl = relatorioService.gerarRelatorio(usuarios, new HashMap(), "rel_usuario", "rel_usuario",
                    request.getServletContext());
            /* Construir o caminho completo e absoluto do arquivo */
            File downloadFile = new File(fileUrl);
            FileInputStream inputStream = new FileInputStream(downloadFile);

            /* Obter o tipo MIME do arquivo */
            String mimeType = context.getMimeType(fileUrl);

            if (mimeType == null) {
                /* Define como tipo binário se mapeamento mime não for encontrado */
                mimeType = "application/octet-stream";
            }

            /* Define atributos para resposta */
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            /* Definir cabeçalhos para a resposta */
            String headerKey = "Content-Dispositon";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            /* Obter fluxo de saída da resposta */
            OutputStream outputStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesReader = -1;

            /* Escrever bytes lidos a partir do fluxo de entrada para o fluxo da saída */
            while ((bytesReader = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesReader);
            }
            inputStream.close();
            outputStream.close();
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
