package projeto.spring.data.aula.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {

	@Transactional(readOnly = true) // somente leitura do banco de dados
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);

	@Lock(LockModeType.READ) // bloqueia o registro se alguem que atualizar o registro
	@Transactional(readOnly = true)
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);

	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		// processar qualquer coisa
		return save(entity);
	}

	@Modifying // indica que o metodo vai alterar o banco de dados
	@Transactional(timeout = 5) // indica uma operação transacional em ate 5 segundos
	// @Transactional(rollbackFor = SQLException) executa o rollback se ocorrer esta
	// excessão
	@Query("delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);

	@Modifying
	@Transactional
	@Query("update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);

}
