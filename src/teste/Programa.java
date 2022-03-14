package teste;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.model.AudienciaDao;
import dao.model.CustoDao;
import dao.model.DaoFactory;
import dao.model.PessoaDao;
import dao.model.PessoaFisicaDao;
import dao.model.PessoaJuridicaDao;
import dao.model.ProcessoDao;
import dao.model.TribunalDao;
import dao.model.VaraDao;
import entities.Pessoa;
import entities.Processo;
import entities.Tribunal;

public class Programa {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse("28/02/2022");

		PessoaFisicaDao pessoaFisicaDao = DaoFactory.criarPessoaFisica();
		PessoaJuridicaDao pessoaJuridicaDao = DaoFactory.criarPessoaJuridica();
		VaraDao varaDao = DaoFactory.criarVara();
		TribunalDao tribunalDao = DaoFactory.criarTribunal();
		ProcessoDao processoDao = DaoFactory.criarProcesso();
		CustoDao custoDao = DaoFactory.criarCusto();
		AudienciaDao audienciaDao = DaoFactory.criarAudiencia();
		PessoaDao pessoaDao = DaoFactory.criarPessoa();
		
		Processo pro = processoDao.buscar("0001");
		System.out.println(pro.getPessoaAutor().getNomePes());

	}

}
