package service;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String FOLDER_RELATORIOS = "/relatorios";
    private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
    private static String SERATOR = File.separator;
    private static String caminhoArquivoRelatorio = null;
    private JRExporter exporter = null;
    private String caminhoSubReport_Dir = "";
    private File arquivoGerado = null;

    public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap parametrosRelatorio,
            String nomeRelatorioJasper, String nomeRelatorioSaida, ServletContext servletContext) throws Exception {
        /*
         * Cria a lista de collectionDataSource de beans que carrega os dados para o
         * relatorio
         */
        JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);
        /* Fornece o ceminho até a pasta que contem os relatorios .jasper */
        String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
        File file = new File(caminhoRelatorio + SERATOR + nomeRelatorioJasper + ".jasper");

        if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
            caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
            SERATOR = "";
        }

        /* Caminho para imegens */
        parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

        /* Caminho completo até o relatório compilado indicado */
        String caminhoArquivosJasper = caminhoRelatorio + SERATOR + nomeRelatorioJasper + ".jasper";

        /* Faz o caregamemto do relatorio */
        JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivoRelatorio);

        /* Gera parâmetros SUBREPORT_DIR com o caminho fisico para subreport */
        caminhoSubReport_Dir = caminhoRelatorio + SERATOR;
        parametrosRelatorio.put("REPORT_PARAMETERS_IMG", caminhoSubReport_Dir);

        /* Carrega o arquivo */
        JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, parametrosRelatorio);
        exporter = new JRPdfExporter();

        /* Caminho do relatorio exportado */
        caminhoArquivoRelatorio = caminhoRelatorio + SERATOR + nomeRelatorioSaida + ".pdf";

        /* Criar novo arquivos exporta */
        arquivoGerado = new File(caminhoArquivoRelatorio);

        /* Prepara a impressão */
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

        /* Executa a exportação */
        exporter.exportReport();

        /* Remove o arquivo do seridor após ser feito no servidor */
        arquivoGerado.deleteOnExit();

        return caminhoArquivoRelatorio;
    }

}
