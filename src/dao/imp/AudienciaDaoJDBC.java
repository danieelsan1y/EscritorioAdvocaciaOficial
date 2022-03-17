package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.model.AudienciaDao;
import dao.model.DaoFactory;
import dao.model.ProcessoDao;
import db.DB;
import db.DbException;
import entities.Audiencia;
import entities.Custo;
import entities.Processo;

public class AudienciaDaoJDBC implements AudienciaDao {

	private Connection conn;
	
	public AudienciaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void inserir(Audiencia obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO audiencia "
					+ "(nroAudiencia, dataAudi, parecer, idProcesso ) " + "VALUES "
					+ "(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNroAudi());
			st.setDate(2, new java.sql.Date(obj.getDataAudi().getTime()));
			st.setString(3, obj.getParecer());
			st.setInt(4, obj.getProcesso().getIdProcesso());


			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdAudi(id);

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
	public void atualizar(Audiencia obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE audiencia " + "SET nroAudiencia = ?, dataAudi = ?, parecer = ?, idProcesso = ? "
							+ "WHERE idAudi = ?");

			st.setString(1, obj.getNroAudi());
			st.setDate(2, new java.sql.Date(obj.getDataAudi().getTime()));
			st.setString(3, obj.getParecer());
			st.setInt(4, obj.getProcesso().getIdProcesso());
			st.setInt(5, obj.getIdAudi());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deletar(Audiencia obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM audiencia WHERE idAudi = ?");
			st.setInt(1, obj.getIdAudi());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Audiencia buscar(String numero) {
		ResultSet rs = null;
		ProcessoDao processoDao = DaoFactory.criarProcesso();
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT audiencia.*,processo.nroProcesso as NumeroProcesso "
					+ "FROM audiencia INNER JOIN processo "
					+ "ON audiencia.idProcesso = processo.idProcesso "
					+ "WHERE audiencia.nroAudiencia = ? ");

			st.setString(1, numero);
			rs = st.executeQuery();
			if (rs.next()) {
				Processo processo = new Processo();
				processo = processoDao.buscar(rs.getString("NumeroProcesso"));
				
				Audiencia audi =  new Audiencia();
				audi.setIdAudi(rs.getInt("idAudi"));
				audi.setNroAudi(rs.getString("nroAudiencia"));
				audi.setDataAudi(rs.getDate("dataAudi"));
				audi.setParecer(rs.getString("parecer"));
				audi.setProcesso(processo);
				return audi;
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
	public List<Audiencia> listar() {
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Audiencia> list = new ArrayList<Audiencia>();
		try {
			st = conn.prepareStatement("SELECT * FROM audiencia ");
			rs = st.executeQuery();

			while (rs.next()) {
				Audiencia obj =  buscar("nroAudi");
				list.add(obj);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		return list;
	}

}
