package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.model.DaoFactory;
import dao.model.PessoaDao;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import db.DB;
import db.DbException;
import entities.Pessoa;
import entities.PessoaFisica;
import entities.PessoaJuridica;

public class PessoaDaoJDBC implements PessoaDao {

	private Connection conn;

	public PessoaDaoJDBC() {
	}

	public PessoaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public ArrayList<Pessoa> listarPessoas() {
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Pessoa> list = new ArrayList<Pessoa>();
		try {
			st = conn.prepareStatement("SELECT * FROM pessoa ");
			rs = st.executeQuery();

			Pessoa obj;
			while (rs.next()) {
				if (rs.getString("cpfPes") != null) {
					obj = new PessoaFisica();
					((PessoaFisica) obj).setRgPes(rs.getString("rgPes"));
					((PessoaFisica) obj).setCpfPes(rs.getString("cpfPes"));
				} else {
					obj = new PessoaJuridica();
					((PessoaJuridica) obj).setCpnjPes(rs.getString("cnpjPes"));

				}

				obj.setIdPes(rs.getInt("idPes"));
				obj.setNomePes(rs.getString("nomePes"));
				obj.setEndPes(rs.getString("endPes"));
				obj.setTelPes(rs.getString("telPes"));
				obj.setCepPes(rs.getString("cepPes"));
				obj.setBaiPes(rs.getString("baiPes"));
				obj.setCidPes(rs.getString("cidPes"));
				obj.setUfPes(rs.getString("ufPes"));
				obj.setEmailPes(rs.getString("emailPes"));
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

	@Override
	public Pessoa ListarProcessosAutor(String numero) {
		int tamanho = numero.length();
		PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
		PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();

		System.out.println(tamanho);
		if (tamanho == 11) {
			PessoaFisica pes = pessoaFisicaDao.buscar(numero);
			pessoaFisicaDao.listarProcessosPF(pes);
			return pes;
		}
		if (tamanho == 14) {
			PessoaJuridica pes = pessoaJuridicaDao.buscar(numero);
			pessoaJuridicaDao.listaDeProcessosPJAutor(pes);
			return pes;
		}
		return null;
	}




}
