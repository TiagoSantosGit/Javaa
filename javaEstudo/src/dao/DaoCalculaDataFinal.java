package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Usuario;

public class DaoCalculaDataFinal {
    private Connection connection;

    public DaoCalculaDataFinal() {
        connection = ConnectionDataBase.getConnection();
    }

    public List<Usuario> lista() throws Exception {
        List<Usuario> listar = new ArrayList<Usuario>();
        String sql = "select * from finalprojetos";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getLong("id"));
            usuario.setLogin(resultSet.getString("datafinal"));
            listar.add(usuario);
        }
        return listar;
    }

    public void gravaDataFinal(String data) {

        try {
            String sql = "insert into finalprojetos(datafinal) values(?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, data);
            insert.execute();
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

    public String buscaDataFinal(String idUser) {

        try {
            String sql = "select * from finalprojetos where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("datafinal");
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
