package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {
    private Connection connection;
    private DaoCategoria cat = new DaoCategoria();

    public DaoProduto() {
        connection = SingleConnection.getConnection();
    }

    public void Salvar(BeanProduto produto) {

        try {
            String sql = "insert into produto(nomeprodu, unidadeprodu, quantidadeprodu, precoprodu, categoria) values(?,?,?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, produto.getNome());
            insert.setString(2, produto.getUnidade());
            insert.setDouble(3, produto.getQuantidade());
            insert.setDouble(4, produto.getPreco());
            insert.setLong(5, new DaoCategoria().consultaNome(produto.getCategoria()));
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

    public List<BeanProduto> listar() throws Exception {
        List<BeanProduto> listar = new ArrayList<BeanProduto>();
        String sql = "select * from produto";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            BeanProduto produto = new BeanProduto();
            produto.setCodigo(resultSet.getLong("codprodu"));
            produto.setNome(resultSet.getString("nomeprodu"));
            produto.setUnidade(resultSet.getString("unidadeprodu"));
            produto.setQuantidade(resultSet.getFloat("quantidadeprodu"));
            produto.setPreco(resultSet.getFloat("precoprodu"));
            produto.setCategoria(cat.consultaId(resultSet.getString("id")));
            listar.add(produto);
        }
        return listar;
    }

    public void delete(String id) {

        try {
            String sql = "delete from produto where codprodu = " + id;
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

    public BeanProduto consultar(String id) throws Exception {

        String sql = "select * from produto where codprodu = " + id;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            BeanProduto produto = new BeanProduto();
            produto.setCodigo(resultSet.getLong("codprodu"));
            produto.setNome(resultSet.getString("nomeprodu"));
            produto.setUnidade(resultSet.getString("unidadeprodu"));
            produto.setQuantidade(resultSet.getFloat("quantidadeprodu"));
            produto.setPreco(resultSet.getFloat("precoprodu"));
            produto.setCategoria(cat.consultaId(resultSet.getString("id")));
            return produto;
        }
        return null;
    }

    public void atualizar(BeanProduto produto) {

        try {
            String sql = "update produto set codprodu = ?, nomeprodu = ?, unidadeprodu= ?, quantidadeprodu = ?, precoprodu = ?, categoria = ? where codprodu = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, produto.getCodigo());
            preparedStatement.setString(2, produto.getNome());
            preparedStatement.setString(3, produto.getUnidade());
            preparedStatement.setDouble(4, produto.getQuantidade());
            preparedStatement.setDouble(5, produto.getPreco());
            preparedStatement.setLong(6, produto.getCodigo());
            preparedStatement.setLong(7, new DaoCategoria().consultaNome(produto.getCategoria()));
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
