package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.ProcessoDao;
import db.DB;
import db.DbException;
import entities.Pessoa;
import entities.PessoaFisica;
import entities.Processo;

public class PessoaFisicaDaoJDBC implements PessoaFisicaDao {
	private Connection conn;

	public PessoaFisicaDaoJDBC(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public void inserir(PessoaFisica obj) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("INSERT INTO Pessoa "
					+ "(nomePes, endPes, telPes, cepPes, baiPes, cidPes, ufPes, emailPes, cpfPes, rgPes  ) " + "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomePes());
			st.setString(2, obj.getEndPes());
			st.setString(3, obj.getTelPes());
			st.setString(4, obj.getCepPes());
			st.setString(5, obj.getBaiPes());
			st.setString(6, obj.getCidPes());
			st.setString(7, obj.getUfPes());
			st.setString(8, obj.getEmailPes());
			st.setString(9, obj.getCpfPes());
			st.setString(10, obj.getRgPes());

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
	public void atualizar(PessoaFisica obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Pessoa "
					+ "SET nomePes = ?, endPes = ?, telPes = ?, cepPes = ?, baiPes = ?, cidPes = ?, ufPes = ?, emailPes = ?, cpfPes = ?, rgPes = ?"
					+ "WHERE idPes = ?");

			st.setString(1, obj.getNomePes());
			st.setString(2, obj.getEndPes());
			st.setString(3, obj.getTelPes());
			st.setString(4, obj.getCepPes());
			st.setString(5, obj.getBaiPes());
			st.setString(6, obj.getCidPes());
			st.setString(7, obj.getUfPes());
			st.setString(8, obj.getEmailPes());
			st.setString(9, obj.getCpfPes());
			st.setString(10, obj.getRgPes());
			st.setInt(11, obj.getIdPes());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletar(PessoaFisica obj) {
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
	public PessoaFisica buscar(String cpf) {
		ResultSet rs = null;
		PreparedStatement st = null;
		PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("SELECT *FROM pessoa WHERE cpfPes = ?");

			st.setString(1, cpf);
			rs = st.executeQuery();
			if (rs.next()) {
				PessoaFisica obj = new PessoaFisica();
				obj.setIdPes(rs.getInt("idPes"));
				obj.setNomePes(rs.getString("nomePes"));
				obj.setEndPes(rs.getString("endPes"));
				obj.setTelPes(rs.getString("telPes"));
				obj.setCepPes(rs.getString("cepPes"));
				obj.setBaiPes(rs.getString("baiPes"));
				obj.setCidPes(rs.getString("cidPes"));
				obj.setUfPes(rs.getString("ufPes"));
				obj.setEmailPes(rs.getString("emailPes"));
				obj.setCpfPes(rs.getString("cpfPes"));
				obj.setRgPes(rs.getString("rgPes"));
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
	public List<PessoaFisica> listar() {
		PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
		/*
		 * ArrayList<PessoaFisica> pf = new ArrayList<PessoaFisica>(); ArrayList<Pessoa>
		 * pessoas = super.listar(); Iterator<Pessoa> itPessoas = pessoas.iterator();
		 * while (itPessoas.hasNext()) { Pessoa pessoa = itPessoas.next(); if(pessoa
		 * instanceof PessoaFisica) { pf.add(pessoas);
		 * 
		 * }
		 * 
		 * }
		 */

		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa " + "WHERE cpfPes >0 ");
			rs = st.executeQuery();

			List<PessoaFisica> list = new ArrayList<>();

			while (rs.next()) {
				PessoaFisica obj = new PessoaFisica();
				obj = pessoaFisicaDao.buscar(rs.getString("cpfPes"));
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
	public void listarProcessosPF(PessoaFisica obj) {
		ProcessoDao processoDao = DaoFactory.criarProcesso();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT processo.*,pessoa.nomePes as NomeAutor, pessoa.cpfPes as CpfAutor "
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

}
