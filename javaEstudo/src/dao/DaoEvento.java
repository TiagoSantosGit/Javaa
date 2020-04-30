package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Eventos;

public class DaoEvento {
    private Connection connection;

    public DaoEvento() {
        connection = ConnectionDataBase.getConnection();
    }

    public List<Eventos> listar() throws Exception {
        List<Eventos> listar = new ArrayList<Eventos>();
        String sql = "select * from Eventos";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Eventos Eventos = new Eventos();
            Eventos.setIdEvento(resultSet.getLong("id"));
            Eventos.setDescricaoEvento(resultSet.getString("descricao"));
            Eventos.setDataEvento(resultSet.getString("data"));
            listar.add(Eventos);
        }
        return listar;
    }

    public void gravarImagem(String fileUpload) {

        try {
            String sql = "insert into Eventos(imagem, tipofile) values(?, ?)";
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
            String sql = "select imagem from Eventos where id = ?";
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
