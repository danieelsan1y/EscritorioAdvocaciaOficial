package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.model.DaoFactory;
import dao.model.TribunalDao;
import dao.model.VaraDao;
import db.DB;
import db.DbException;
import entities.Tribunal;
import entities.Vara;

public class TribunalDaoJDBC implements TribunalDao {
	private Connection conn;
	public static Integer i=0;

	public TribunalDaoJDBC(Connection con) {
		this.conn = conn;
	}

	@Override

	public void inserir(Tribunal obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO tribunal " + "(desTrib, endTrib) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getDesTrib());
			st.setString(2, obj.getEndTrib());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdTribunal(id);

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
	public Tribunal buscar(String nome) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("SELECT *FROM tribunal WHERE desTrib = ?");

			st.setString(1, nome);
			rs = st.executeQuery();
			if (rs.next()) {
				Tribunal trib = new Tribunal();
				trib.setIdTribunal(rs.getInt("idTrib"));
				trib.setDesTrib(rs.getString("desTrib"));
				trib.setEndTrib(rs.getString("endTrib"));
				return trib;
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
	public List<Tribunal> listar() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tribunal ORDER BY desTrib");
			rs = st.executeQuery();

			List<Tribunal> list = new ArrayList<>();

			while (rs.next()) {
				Tribunal trib = new Tribunal();
				trib.setIdTribunal(rs.getInt("idTrib"));
				trib.setDesTrib(rs.getString("desTrib"));
				trib.setEndTrib(rs.getString("endTrib"));
				list.add(trib);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void atualizar(Tribunal obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tribunal " + "SET desTrib = ?, endTrib = ? " + "WHERE idTrib = ?");

			st.setString(1, obj.getDesTrib());
			st.setString(2, obj.getEndTrib());
			st.setInt(3, obj.getIdTribunal());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletar(Tribunal obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM tribunal WHERE idTrib = ?");
			st.setInt(1, obj.getIdTribunal());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void listaDeVaras(Tribunal obj) {

		PreparedStatement st = null;
		ResultSet rs = null;
		VaraDao varaDao = DaoFactory.criarVara();
		try {
			st = conn.prepareStatement("SELECT vara.*,tribunal.desTrib as Descricao " + "FROM vara INNER JOIN tribunal "
					+ "ON vara.idTrib = tribunal.idTrib " + "WHERE vara.idTrib = ? ");
			st.setInt(1, obj.getIdTribunal());
			rs =st.executeQuery();
			while(rs.next()) {
				Vara vara = varaDao.buscar(rs.getString("desVara"));
				List<Vara> lista = obj.getVaras();
				for(Vara varas : lista) {
					if(rs.getString("desVara").equals(varas.getDesVara()) ) {
						i=1;
					}
				}
				if(i==0) {
					varaDao.listaDeProcessos(vara);
					obj.addVara(vara);	
				}
				i=0;
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
