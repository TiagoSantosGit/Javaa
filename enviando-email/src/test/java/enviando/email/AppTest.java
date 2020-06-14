package enviando.email;

public class AppTest {

	@org.junit.Test
	public void testeEmail() throws Exception {

		StringBuilder stringBuilderTextoEmail = new StringBuilder();

		stringBuilderTextoEmail.append("Olá , <br/><br/>");
		stringBuilderTextoEmail.append("Você está recebendo o acesso ao curso de Java.<br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo.<br/><br/>");
		stringBuilderTextoEmail.append("<b>Login:<b/> tiago<br/>");
		stringBuilderTextoEmail.append("<b>Senha:<b/> show<br/><br/>");
		stringBuilderTextoEmail.append(
				"<a target=\"_blank\" href=\"https://stackedit.io/editor\" style=\"color:#2525a7;padding:14px 25px;text-align:center;text-decoration:none;display:inline-block;border-radius:30px;font-size:20px;font-family:courier;border:3px solid green;background-color:#99DA39;\">Acessar Stackedit</a><br/><br/>");
		stringBuilderTextoEmail.append("<span style=\"font-size:8px\"> Ass.: Tiago dev</span>");
		ObjetoEnviaEmail objetoEnviaEmail = new ObjetoEnviaEmail("email1, email2, email3, etc", "Curso java Jdev",
				"Chegou e-mail enviado do java", stringBuilderTextoEmail.toString());
		objetoEnviaEmail.enviarEmailAnexo(true);
		// use somente para teste quando o email não chega
		Thread.sleep(1000);
	}
}
