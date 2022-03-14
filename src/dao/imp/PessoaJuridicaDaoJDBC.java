package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.model.DaoFactory;
import dao.model.PessoaJuridicaDao;
import dao.model.ProcessoDao;
import db.DB;
import db.DbException;
import entities.PessoaJuridica;
import entities.Processo;

public class PessoaJuridicaDaoJDBC implements PessoaJuridicaDao {
	private Connection conn;

	@Override
	public void inserir(PessoaJuridica obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO Pessoa "
					+ "(nomePes, endPes, telPes, cepPes, baiPes, cidPes, ufPes, emailPes, cnpjPes) " + "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomePes());
			st.setString(2, obj.getEndPes());
			st.setString(3, obj.getTelPes());
			st.setString(4, obj.getCepPes());
			st.setString(5, obj.getBaiPes());
			st.setString(6, obj.getCidPes());
			st.setString(7, obj.getUfPes());
			st.setString(8, obj.getEmailPes());
			st.setString(9, obj.getCpnjPes());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdPes(id);

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
	public void atualizar(PessoaJuridica obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Pessoa "
					+ "SET nomePes = ?, endPes = ?, telPes = ?, cepPes = ?, baiPes = ?, cidPes = ?, ufPes = ?, emailPes = ?, cnpjPes = ? "
					+ "WHERE idPes = ?");

			st.setString(1, obj.getNomePes());
			st.setString(2, obj.getEndPes());
			st.setString(3, obj.getTelPes());
			st.setString(4, obj.getCepPes());
			st.setString(5, obj.getBaiPes());
			st.setString(6, obj.getCidPes());
			st.setString(7, obj.getUfPes());
			st.setString(8, obj.getEmailPes());
			st.setString(9, obj.getCpnjPes());
			st.setInt(10, obj.getIdPes());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletar(PessoaJuridica obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM pessoa WHERE idPes = ?");
			st.setInt(1, obj.getIdPes());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public PessoaJuridica buscar(String cnpj) {
		ResultSet rs = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("SELECT *FROM pessoa WHERE cnpjPes = ?");
			st.setString(1, cnpj);
			rs = st.executeQuery();
			if (rs.next()) {
				PessoaJuridica obj = new PessoaJuridica();
				obj.setIdPes(rs.getInt("idPes"));
				obj.setNomePes(rs.getString("nomePes"));
				obj.setEndPes(rs.getString("endPes"));
				obj.setTelPes(rs.getString("telPes"));
				obj.setCepPes(rs.getString("cepPes"));
				obj.setBaiPes(rs.getString("baiPes"));
				obj.setCidPes(rs.getString("cidPes"));
				obj.setUfPes(rs.getString("ufPes"));
				obj.setEmailPes(rs.getString("emailPes"));
				obj.setCpnjPes(rs.getString("cnpjPes"));
				return obj;
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
	public List<PessoaJuridica> listar() {
		PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa " + "WHERE cnpjPes >0 ");
			rs = st.executeQuery();

			List<PessoaJuridica> list = new ArrayList<>();

			while (rs.next()) {
				PessoaJuridica obj = new PessoaJuridica();
				obj = pessoaJuridicaDao.buscar(rs.getString("cnpjPes"));
				list.add(obj);
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
	public void listaDeProcessosPJAutor(PessoaJuridica obj) {
		ProcessoDao processoDao = DaoFactory.criarProcesso();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT processo.*,pessoa.nomePes as NomeAutor, pessoa.cpfPes as CpfAutor, pessoa.CnpjPes as CnpjAutor "
							+ "FROM processo INNER JOIN pessoa " + "ON processo.idPesAutor = pessoa.idPes "
							+ "WHERE processo.idPesAutor = ? ");
			st.setInt(1, obj.getIdPes());
			rs = st.executeQuery();

			while (rs.next()) {

				Processo pro = processoDao.buscar(rs.getString("nroProcesso"));
				processoDao.listaDeAudiencias(pro);
				processoDao.listaDeCustos(pro);
				obj.addProcessosAutor(pro);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public void listaDeProcessosPJReu() {
		// TODO Auto-generated method stub

	}
}
