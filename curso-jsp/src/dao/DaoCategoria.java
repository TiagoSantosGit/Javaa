package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanCategoria;
import connection.SingleConnection;

public class DaoCategoria {
    private Connection connection;

    public DaoCategoria() {
        connection = SingleConnection.getConnection();
    }

    public void Salvar(BeanCategoria categoria) {

        try {
            String sql = "insert into categoria(nomecategoria) values(?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, categoria.getNomeCategoria());
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

    public List<BeanCategoria> listar() throws Exception {
        List<BeanCategoria> listar = new ArrayList<BeanCategoria>();
        String sql = "select * from categoria";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            BeanCategoria categoria = new BeanCategoria();
            categoria.setId(resultSet.getLong("id"));
            categoria.setNomeCategoria(resultSet.getString("nomecategoria"));
            listar.add(categoria);
        }
        return listar;
    }

    public void delete(String id) {

        try {
            String sql = "delete from categoria where id = " + id;
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

    public BeanCategoria consultar(String id) throws Exception {

        String sql = "select * from categoria where id = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            BeanCategoria categoria = new BeanCategoria();
            categoria.setId(resultSet.getLong("id"));
            categoria.setNomeCategoria(resultSet.getString("nomecategoria"));
            return categoria;
        }
        return null;
    }

    public Long consultaNome(String categoria) throws Exception {

        String sql = "select id from categoria where nomeCategoria = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, categoria);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getLong("id");
        }
        return 0L;
    }

    public String consultaId(String id) throws Exception {

        String sql = "select nomecategoria from categoria where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("nomecategoria");
        }
        return null;
    }

    public void atualizar(BeanCategoria categoria) {

        try {
            String sql = "update categoria set nomecategoria = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getNomeCategoria());
            preparedStatement.setLong(2, categoria.getId());
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
}
