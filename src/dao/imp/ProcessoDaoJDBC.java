package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dao.model.AudienciaDao;
import dao.model.CustoDao;
import dao.model.DaoFactory;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import dao.model.ProcessoDao;
import dao.model.VaraDao;
import db.DB;
import db.DbException;
import entities.Audiencia;
import entities.Custo;
import entities.PessoaFisica;
import entities.PessoaJuridica;
import entities.Processo;
import entities.Vara;
import entities.enums.SituacaoStatus;

public class ProcessoDaoJDBC implements ProcessoDao {
	private Connection conn;

	public ProcessoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void inserir(Processo obj) {
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"INSERT INTO processo " + "(nroProcesso, dataAbertura,situacao, idPesAutor, idPesReu, idVara) "
							+ "VALUES " + "(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			//classes amigas friendly class
			st.setString(1, obj.getNroProcesso());
			st.setDate(2, new java.sql.Date(obj.getDataAbertura().getTime()));
			st.setString(3, String.valueOf(obj.getSitucacao()));
			st.setInt(4, obj.getPessoaAutor().getIdPes());
			st.setInt(5, obj.getPessoaReu().getIdPes());
			System.out.println("d: " + obj.getVara().getIdVara());
			st.setInt(6, obj.getVara().getIdVara());
			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdProcesso(id);

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
	public void atualizar(Processo obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE processo "
					+ "SET nroProcesso = ?, dataAbertura = ?, dataConclusao = ?, situacao = ?, idPesAutor = ?, idPesReu = ?, idVara = ?  "
					+ "WHERE idProcesso = ?");


			
			st.setString(1, obj.getNroProcesso());
			st.setDate(2, new java.sql.Date(obj.getDataAbertura().getTime()));
			st.setDate(3, new java.sql.Date(obj.getDataConclusao().getTime()));
			st.setString(4, String.valueOf(obj.getSitucacao()));
			st.setInt(5, obj.getPessoaAutor().getIdPes());
			st.setInt(6, obj.getPessoaReu().getIdPes());
			st.setInt(7, obj.getVara().getIdVara());
			st.setInt(8, obj.getIdProcesso());
			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deletar(Processo obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("DELETE FROM processo WHERE idProcesso = ?");
			st.setInt(1, obj.getIdProcesso());
			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Processo buscar(String numeroProcesso) {
		ResultSet rs = null;
		;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement("SELECT *FROM processo WHERE nroProcesso = ?");
			st.setString(1, numeroProcesso);

			rs = st.executeQuery();
			if (rs.next()) {
				Processo pro = new Processo();
				buscarVara(numeroProcesso, pro);
				buscarPessoaAutor(numeroProcesso, pro);
				buscarPessoaReu(numeroProcesso, pro);

				pro.setIdProcesso(rs.getInt("idProcesso"));
				pro.setNroProcesso(rs.getString("nroProcesso"));
				pro.setDataAbertura(rs.getDate("dataAbertura"));
				pro.setSitucacao(SituacaoStatus.valueOf(rs.getString("situacao")));

				return pro;
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

	private void buscarVara(String numeroProcesso, Processo pro) {
		VaraDao varaDao = DaoFactory.criarVara();
		ResultSet rs = null;

		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT processo.*,vara.desVara as DescricaoVara " + "FROM processo INNER JOIN vara "
							+ "ON processo.idVara = vara.idVara " + "WHERE processo.nroProcesso = ?");
			st.setString(1, numeroProcesso);
			rs = st.executeQuery();

			if (rs.next()) {
				Vara vara = varaDao.buscar(rs.getString("DescricaoVara"));
				pro.setVara(vara);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public void buscarPessoaAutor(String numeroProcesso, Processo processo) {
		ResultSet rs = null;
		PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
		PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT processo.*,pessoa.cpfPes as CpfAutor, pessoa.cnpjPes as CnpjAutor "
							+ "FROM processo INNER JOIN pessoa " + "ON processo.idPesAutor = pessoa.idPes "
							+ "WHERE processo.nroProcesso = ?");
			st.setString(1, numeroProcesso);
			rs = st.executeQuery();

			if (rs.next()) {
				if (rs.getString("CpfAutor") == null) {
					PessoaJuridica pes = pessoaJuridicaDao.buscar(rs.getString("CnpjAutor"));
					processo.setPessoaAutor(pes);

				}
				else {
					PessoaFisica pes = pessoaFisicaDao.buscar(rs.getString("CpfAutor"));
					processo.setPessoaAutor(pes);


				
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	public void buscarPessoaReu(String numeroProcesso, Processo processo) {
		ResultSet rs = null;
		PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
		PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT processo.*,pessoa.cpfPes as CpfReu,pessoa.cnpjPes as CnpjReu "
							+ "FROM processo INNER JOIN pessoa " + "ON processo.idPesReu = pessoa.idPes "
							+ "WHERE processo.nroProcesso = ?");
			st.setString(1, numeroProcesso);
			rs = st.executeQuery();

			if (rs.next()) {
				if (rs.getString("CpfReu") == null) {
					PessoaJuridica pes = pessoaJuridicaDao.buscar(rs.getString("CnpjReu"));
					processo.setPessoaReu(pes);
				}
				else {
					PessoaFisica pes = pessoaFisicaDao.buscar(rs.getString("CpfReu"));
					processo.setPessoaReu(pes);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Processo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void listaDeCustos(Processo obj) {
		int i =0;
		PreparedStatement st = null;
		ResultSet rs = null;
		CustoDao custoDao = DaoFactory.criarCusto();
		try {
			st = conn.prepareStatement("SELECT custo.*,processo.nroProcesso as Numero " + "FROM custo INNER JOIN processo "
					+ "ON custo.idProcesso = processo.idProcesso " + "WHERE custo.idProcesso = ? ");
			st.setInt(1, obj.getIdProcesso());
			rs =st.executeQuery();
			while(rs.next()) {
				Custo custo = custoDao.buscar(rs.getString("nroCusto"));
				List<Custo> lista = obj.getCustos();
				for(Custo custos : lista) {
					if(rs.getInt("idCusto") != custo.getIdCusto()) {
						i++;
					}
				}
				System.out.println(i);
				if(i== lista.size()) {
				}
				obj.addCusto(custo);
			
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public void listaDeAudiencias(Processo obj) {
		int i =0;
		PreparedStatement st = null;
		ResultSet rs = null;
		AudienciaDao audienciaDao = DaoFactory.criarAudiencia();
		try {
			st = conn.prepareStatement("SELECT audiencia.*,processo.nroProcesso as Numero " + "FROM audiencia INNER JOIN processo "
					+ "ON audiencia.idProcesso = processo.idProcesso " + "WHERE audiencia.idProcesso = ? ");
			st.setInt(1, obj.getIdProcesso());
			rs =st.executeQuery();
			while(rs.next()) {
				Audiencia audi = audienciaDao.buscar(rs.getString("nroAudiencia"));
				List<Audiencia> lista = obj.getAudiencias();
				for(Audiencia audiencias : lista) {
					if(rs.getInt("idAudi") != audi.getIdAudi()) {
						i++;
					}
				}
				System.out.println(i);
				if(i== lista.size()) {
				}
				obj.addAudiencia(audi);
			
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}
	
	

}
