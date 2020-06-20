package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring-config.xml" })
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	@Autowired
	private InterfaceTelefone interfaceTelefone;

	@Test
	public void testeInsert() {

		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		usuarioSpringData.setEmail("javaavancado@javaanvacado.com");
		usuarioSpringData.setIdade(31);
		usuarioSpringData.setLogin("teste 123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("Egidio");
		interfaceSpringDataUser.save(usuarioSpringData);
		System.out.println("Usuario cadastrado -> " + interfaceSpringDataUser.count());
	}

	@Test
	public void testeConsulta() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println(usuarioSpringData.get().getId());
		System.out.println("------------------------");

		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getId());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println("-------------------");
		}
	}

	@Test
	public void testeConsultaTodos() {

		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		for (UsuarioSpringData usuarioSpringData : lista) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("---------------------------------------------");
		}
	}

	@Test
	public void testeUpdate() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);
		UsuarioSpringData data = usuarioSpringData.get();
		data.setNome("Aparecido");
		data.setIdade(25);
		interfaceSpringDataUser.save(data);
	}

	@Test
	public void testeDelete() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(8L);
		interfaceSpringDataUser.delete(usuarioSpringData.get());
		interfaceSpringDataUser.deleteById(6L);
	}

	@Test
	public void testeConsultaNome() {

		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Tiago");
		for (UsuarioSpringData usuarioSpringData : list) {
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("---------------------------------------------");
		}
	}

	@Test
	public void testeConsultaNomeParam() {

		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Egidio");
		System.out.println(usuarioSpringData.getEmail());
		System.out.println(usuarioSpringData.getIdade());
		System.out.println(usuarioSpringData.getLogin());
		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getSenha());
		System.out.println(usuarioSpringData.getId());
	}

	@Test
	public void testeDeletePorNome() {
		interfaceSpringDataUser.deletePorNome("Egidio");
	}

	@Test
	public void testeEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("javaavancado@show.com", "Aparecido");
	}

	@Test
	public void testeInsertTelefone() {

		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(4L);
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("9778162");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		interfaceTelefone.save(telefone);
	}

}
