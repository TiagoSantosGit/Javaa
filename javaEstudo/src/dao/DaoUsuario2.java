package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase2;
import entidades.Usuario;

public class DaoUsuario2 {
    private Connection connection;

    public DaoUsuario2() {
        connection = ConnectionDataBase2.getConnection();
    }

    public List<Usuario> listar() throws Exception {
        List<Usuario> listar = new ArrayList<Usuario>();
        String sql = "select * from usuario where login <> 'admin'";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getLong("id"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            listar.add(usuario);
        }
        return listar;
    }

    public void gravarImagem(String fileUpload) {

        try {
            String sql = "insert into usuario(imagem, tipofile) values(?, ?)";
            String tipoDados = fileUpload.split(",")[0].split(";")[0].split("/")[1];
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, fileUpload);
            insert.setString(1, tipoDados);
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

    public String buscaImagem(String idUser) {

        try {
            String sql = "select imagem from usuario where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("imagem");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
            return null;
        }
    }
}
