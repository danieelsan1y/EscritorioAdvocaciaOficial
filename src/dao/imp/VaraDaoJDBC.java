package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.model.DaoFactory;
import dao.model.ProcessoDao;
import dao.model.TribunalDao;
import dao.model.VaraDao;
import db.DB;
import db.DbException;
import entities.Processo;
import entities.Tribunal;
import entities.Vara;

public class VaraDaoJDBC implements VaraDao {
	public static Integer i=0;
	
	private Connection conn;

	public VaraDaoJDBC(Connection con) {
		this.conn = conn;
	}

	@Override
	public void inserir(Vara obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO vara " + "(desVara, idTrib) " + "VALUES " + "(?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getDesVara());
			st.setInt(2, obj.getTribunal().getIdTribunal());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdVara(id);

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
	public void atualizar(Vara obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE vara " +
					"SET desVara = ?, idTrib = ? " +
					"WHERE idVara = ?");

			st.setString(1, obj.getDesVara());
			st.setInt(2, obj.getTribunal().getIdTribunal());
			st.setInt(3, obj.getIdVara());
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} 
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public List<Vara> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Vara obj) {
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement("DELETE FROM vara WHERE idVara = ?");
			st.setInt(1, obj.getIdVara());
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Vara buscar(String nome) {
		ResultSet rs = null;
		TribunalDao tribunalDao = DaoFactory.criarTribunal();
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT vara.*,tribunal.desTrib as TribunalNome "
					+ "FROM vara INNER JOIN tribunal "
					+ "ON vara.idTrib = tribunal.idTrib "
					+ "WHERE vara.desVara = ?");
			
			st.setString(1, nome);
			rs = st.executeQuery();	
			if (rs.next()) {
				Tribunal trib = new Tribunal();
				trib = tribunalDao.buscar(rs.getString("TribunalNome"));
				Vara vara = new Vara();
				vara.setIdVara(rs.getInt("idVara"));
				vara.setDesVara(rs.getString("desVara"));
				vara.setTribunal(trib);
				return vara;
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
	public void listaDeProcessos(Vara obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		ProcessoDao processoDao = DaoFactory.criarProcesso();
		VaraDao varaDao = DaoFactory.criarVara();
		try {
			st = conn.prepareStatement("SELECT processo.*,vara.desVara as DescricaoVara " + "FROM processo INNER JOIN vara "
					+ "ON processo.idVara = vara.idVara " + "WHERE processo.idvara = ? ");
			st.setInt(1, obj.getIdVara());
			rs =st.executeQuery();
			while(rs.next()) {
				Processo pro = processoDao.buscar(rs.getString("nroProcesso"));
				List<Processo> lista = obj.getProcessos();
				for(Processo processos : lista) {
					if(rs.getString("nroProcesso").equals(pro.getNroProcesso())) {
						i=1;
					}
				}
				if(i==0) {
	
				}

				processoDao.listaDeCustos(pro);
				processoDao.listaDeAudiencias(pro);
				obj.addProcessos(pro);
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
