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
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked" })
public class RelatorioService implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String FOLDER_RELATORIOS = "/relatorio";
    private static final String SUBREPORT_DIR = "SUBREPORT_DIR";
    private String SERATOR = File.separator;
    private static String caminhoArquivoRelatorio = null;
    private JRExporter exporter = null;
    private String caminhoSubReport_Dir = "";
    private File arquivoGerado = null;

    public String gerarRelatorio(List<?> listaDataBeanCollection, HashMap hashMap, String nomeRelatorioJasper,
	    String nomeRelatorioSaida, ServletContext servletContext, String tipoExportar) throws Exception {
	/*
	 * Cria a lista de collectionDataSource de beans que carrega os dados para o
	 * relatorio
	 */
	JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDataBeanCollection);

	/* Fornece o caminho até a pasta que contem os relatorios .jasper */
	String caminhoRelatorio = servletContext.getRealPath(FOLDER_RELATORIOS);
	File file = new File(caminhoRelatorio + SERATOR + nomeRelatorioJasper + ".jasper");

	if (caminhoRelatorio == null || (caminhoRelatorio != null && caminhoRelatorio.isEmpty()) || !file.exists()) {
	    caminhoRelatorio = this.getClass().getResource(FOLDER_RELATORIOS).getPath();
	    SERATOR = "";
	}

	/* Caminho para imegens */
	hashMap.put("REPORT_PARAMETERS_IMG", caminhoRelatorio);

	/* Caminho completo até o relatório compilado indicado */
	String caminhoArquivosJasper = caminhoRelatorio + SERATOR + nomeRelatorioJasper + ".jasper";

	/* Faz o caregamemto do relatorio */
	JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(caminhoArquivosJasper);

	/* Gera parâmetros SUBREPORT_DIR com o caminho fisico para subreport */
	caminhoSubReport_Dir = caminhoRelatorio + SERATOR;
	hashMap.put(SUBREPORT_DIR, caminhoSubReport_Dir);

	/* Carrega o arquivo */
	JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, hashMap, jrbcds);

	switch (tipoExportar) {
	case "pdf":
	    exporter = new JRPdfExporter();
	    break;
	case "xls":
	    exporter = new JRXlsExporter();
	    break;
	case "csv":
	    exporter = new JRCsvExporter();
	    break;
	}
	
	/* Caminho do relatorio exportado */
	caminhoArquivoRelatorio = caminhoRelatorio + SERATOR + nomeRelatorioSaida + "." + tipoExportar;

	/* Criar novo arquivos exporta */
	arquivoGerado = new File(caminhoArquivoRelatorio);

	/* Prepara a impressão */
	exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
	exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);

	/* Executa a exportação */
	exporter.exportReport();

	/* Remove o arquivo do servidor após ser feito no download */
	arquivoGerado.deleteOnExit();

	return caminhoArquivoRelatorio;
    }
}
