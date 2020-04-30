package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Projetos;
import entidades.Series;

public class DaoGanttChart {
	private Connection connection;

	public DaoGanttChart() {
		connection = ConnectionDataBase.getConnection();
	}

	public List<Projetos> listarProjetos() throws Exception {
		List<Projetos> listar = new ArrayList<Projetos>();
		String sql = "select * from projeto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Projetos projetos = new Projetos();
			projetos.setId(resultSet.getLong("id"));
			projetos.setNome(resultSet.getString("nome"));
			projetos.setSeries(this.listarSeries(resultSet.getLong("id")));
			listar.add(projetos);
		}
		return listar;
	}

	public List<Series> listarSeries(Long projeto) throws Exception {
		List<Series> listar = new ArrayList<Series>();
		String sql = "select * from series where projeto = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, projeto);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Series series = new Series();
			series.setId(resultSet.getLong("id"));
			series.setNome(resultSet.getString("nome"));
			series.setDataInicial(resultSet.getString("start"));
			series.setDataFinal(resultSet.getString("end"));
			listar.add(series);
		}
		return listar;
	}

	public boolean atualizar(Series series) throws Exception {

		try {
			String sql = "update series set start = ?, \"end\" = ? where id = ?";
			PreparedStatement stat = connection.prepareStatement(sql);
			stat.setString(1, series.getDataInicial());
			stat.setString(2, series.getDataFinal());
			stat.setLong(3, series.getId());
			return stat.executeUpdate() == 1;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
