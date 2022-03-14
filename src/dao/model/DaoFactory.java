package dao.model;

import dao.imp.AudienciaDaoJDBC;
import dao.imp.CustoDaoJDBC;
import dao.imp.PessoaDaoJDBC;
import dao.imp.PessoaFisicaDaoJDBC;
import dao.imp.PessoaJuridicaDaoJDBC;
import dao.imp.ProcessoDaoJDBC;
import dao.imp.TribunalDaoJDBC;
import dao.imp.VaraDaoJDBC;
import db.DB;

public class DaoFactory {

	public static PessoaFisicaDao criarPessoaFisica ( ) {
		return new PessoaFisicaDaoJDBC(DB.getConnection());
	}
	
	public static PessoaJuridicaDao criarPessoaJuridica() {
		return new PessoaJuridicaDaoJDBC();
		
	}
	public static TribunalDao criarTribunal ( ) {
		return new TribunalDaoJDBC (DB.getConnection());
	}
	
	public static VaraDao criarVara ( ) {
		return new VaraDaoJDBC (DB.getConnection());
	}
	public static ProcessoDao criarProcesso ( ) {
		return new ProcessoDaoJDBC (DB.getConnection());
	}
	public static CustoDao criarCusto ( ) {
		return new CustoDaoJDBC(DB.getConnection());
	}
	public static AudienciaDao criarAudiencia () {
		return new AudienciaDaoJDBC(DB.getConnection());
	}
	
	public static PessoaDao criarPessoa () {
		return new PessoaDaoJDBC(DB.getConnection());
	}
}
