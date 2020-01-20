package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.ConnectionDataBase;

public class DaoUsuario {
    private Connection connection;

    public DaoUsuario() {
        connection = ConnectionDataBase.getConnection();
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
