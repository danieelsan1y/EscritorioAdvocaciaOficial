package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.model.CustoDao;
import dao.model.DaoFactory;
import dao.model.ProcessoDao;
import db.DB;
import db.DbException;
import entities.Custo;
import entities.Processo;

public class CustoDaoJDBC implements CustoDao {

	private Connection conn;

	public CustoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Custo obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO custo " + "(nroCusto, dataCusto, desCusto, valCusto, idProcesso ) "
					+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNroCusto());
			st.setDate(2, new java.sql.Date(obj.getDataCusto().getTime()));
			st.setString(3, obj.getDesCusto());
			st.setDouble(4, obj.getValCusto());
			st.setInt(5, obj.getProcesso().getIdProcesso());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdCusto(id);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void atualizar(Custo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE custo " + "SET nroCusto = ?, dataCusto = ?, desCusto = ?, valCusto = ?, idProcesso = ? "
							+ "WHERE idCusto = ?");

			st.setString(1, obj.getNroCusto());
			st.setDate(2, new java.sql.Date(obj.getDataCusto().getTime()));
			st.setString(3, obj.getDesCusto());
			st.setDouble(4, obj.getValCusto());
			st.setInt(5, obj.getProcesso().getIdProcesso());
			st.setInt(6, obj.getIdCusto());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletar(Custo obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM custo WHERE idCusto = ?");
			st.setInt(1, obj.getIdCusto());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Custo buscar(String numero) {
		ResultSet rs = null;
		ProcessoDao processoDao = DaoFactory.criarProcesso();
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT custo.*,processo.nroProcesso as NumeroProcesso " + "FROM custo INNER JOIN processo "
							+ "ON custo.idProcesso = processo.idProcesso " + "WHERE custo.nroCusto = ? ");

			st.setString(1, numero);
			rs = st.executeQuery();
			if (rs.next()) {
				Processo processo = new Processo();
				processo = processoDao.buscar(rs.getString("NumeroProcesso"));

				Custo custo = new Custo();
				custo.setIdCusto(rs.getInt("idCusto"));
				custo.setNroCusto(rs.getString("nroCusto"));
				custo.setDataCusto(rs.getDate("dataCusto"));
				custo.setDesCusto(rs.getString("desCusto"));
				custo.setValCusto(rs.getDouble("valCusto"));
				custo.setProcesso(processo);
				return custo;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return null;
	}

	@Override
	public List<Custo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
