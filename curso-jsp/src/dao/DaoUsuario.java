package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCursoJsp;
import connection.SingleConnection;

public class DaoUsuario {
    private Connection connection;

    public DaoUsuario() {
        connection = SingleConnection.getConnection();
    }

    public void Salvar(BeanCursoJsp usuario) {

        try {
            String sql = "insert into usuario(login, senha, nome, telefone, cep, cidade, fotobase64, contentype, curriculobase64, contentypecurriculo, fotobase64miniatura, ativo, sexo) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, usuario.getLogin());
            insert.setString(2, usuario.getSenha());
            insert.setString(3, usuario.getNome());
            insert.setString(4, usuario.getTelefone());
            insert.setString(5, usuario.getCep());
            insert.setString(6, usuario.getCidade());
            insert.setString(7, usuario.getFotoBase64());
            insert.setString(8, usuario.getContenType());
            insert.setString(9, usuario.getCurriculoBase64());
            insert.setString(10, usuario.getContenTypeCurriculo());
            insert.setString(11, usuario.getFotoBase64Miniatura());
            insert.setString(12, usuario.getSexo());
            insert.setBoolean(13, usuario.isAtivo());
            insert.execute();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public List<BeanCursoJsp> listar() throws Exception {
        List<BeanCursoJsp> listar = new ArrayList<BeanCursoJsp>();
        String sql = "select * from usuario where login <> 'admin'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
            beanCursoJsp.setId(resultSet.getLong("id"));
            beanCursoJsp.setLogin(resultSet.getString("login"));
            beanCursoJsp.setSenha(resultSet.getString("senha"));
            beanCursoJsp.setNome(resultSet.getString("nome"));
            beanCursoJsp.setTelefone(resultSet.getString("telefone"));
            beanCursoJsp.setCep(resultSet.getString("cep"));
            beanCursoJsp.setCidade(resultSet.getString("cidade"));
            beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
            beanCursoJsp.setContenType(resultSet.getString("contentype"));
            beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
            beanCursoJsp.setContenTypeCurriculo(resultSet.getString("contentypecurriculo"));
            beanCursoJsp.setFotoBase64Miniatura(resultSet.getString("fotobase64miniatura"));
            beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));
            beanCursoJsp.setSexo(resultSet.getString("sexo"));
            listar.add(beanCursoJsp);
        }
        return listar;
    }

    public void delete(String id) {

        try {
            String sql = "delete from usuario where id = '" + id + "' and login <> 'login'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public BeanCursoJsp consultar(String id) throws Exception {

        String sql = "select * from usuario where id='" + id + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            BeanCursoJsp beanCursoJsp = new BeanCursoJsp();
            beanCursoJsp.setId(resultSet.getLong("id"));
            beanCursoJsp.setLogin(resultSet.getString("login"));
            beanCursoJsp.setSenha(resultSet.getString("senha"));
            beanCursoJsp.setNome(resultSet.getString("nome"));
            beanCursoJsp.setTelefone(resultSet.getString("telefone"));
            beanCursoJsp.setCep(resultSet.getString("cep"));
            beanCursoJsp.setCidade(resultSet.getString("cidade"));
            beanCursoJsp.setFotoBase64(resultSet.getString("fotobase64"));
            beanCursoJsp.setContenType(resultSet.getString("contentype"));
            beanCursoJsp.setCurriculoBase64(resultSet.getString("curriculobase64"));
            beanCursoJsp.setContenTypeCurriculo(resultSet.getString("contentypecurriculo"));
            beanCursoJsp.setAtivo(resultSet.getBoolean("ativo"));            
            beanCursoJsp.setSexo(resultSet.getString("sexo"));            
            return beanCursoJsp;
        }
        return null;
    }

    public void atualizar(BeanCursoJsp usuario) {

        try {
            String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, cidade = ?, fotobase64 = ?, contentype = ?, curriculobase64 = ?, contentypecurriculo = ?, fotobase64miniatura = ?, ativo = ?, sexo = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getLogin());
            preparedStatement.setString(2, usuario.getSenha());
            preparedStatement.setString(3, usuario.getNome());
            preparedStatement.setString(4, usuario.getTelefone());
            preparedStatement.setString(5, usuario.getCep());
            preparedStatement.setString(6, usuario.getCidade());
            preparedStatement.setString(7, usuario.getFotoBase64());
            preparedStatement.setString(8, usuario.getContenType());
            preparedStatement.setString(9, usuario.getCurriculoBase64());
            preparedStatement.setString(10, usuario.getContenTypeCurriculo());
            preparedStatement.setString(11, usuario.getFotoBase64Miniatura());
            preparedStatement.setBoolean(12, usuario.isAtivo());
            preparedStatement.setString(13, usuario.getSexo());
            preparedStatement.setLong(14, usuario.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    public boolean validarLogin(String login) throws Exception {

        String sql = "select count(login) as qtd from usuario where login ='" + login + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("qtd") <= 0;
        }
        return false;
    }
}
