package br.com.getnet.agenda.scg.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import br.com.getnet.agenda.scg.data.Registro;

public class AgendaScgCIP {
	private class Arquivo {

		XMLOutputFactory factory;
		XMLStreamWriter writer;
		String nomeArquivo;
		String pathArquivoSaida;
		File arquivoSaida;
		int sequencia;
		long banco;
		final AgendaScgCIP this$0;

		private void escreveHeader() throws Exception {
			writer.writeStartDocument("UTF-16BE", "1.0");
			writer.writeStartElement("ASCGDOC");
			writer.writeDefaultNamespace("http://www.cip-bancos.org.br/ARQ/ASCG026.xsd");
			writer.writeNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
			writer.writeAttribute("xsi:schemaLocation", "http://www.cip-bancos.org.br/ARQ/ASCG026.xsd ASCG026.xsd ");
			writer.writeStartElement("BCARQ");
			writer.writeStartElement("NomArq");
			writer.writeCharacters(nomeArquivo);
			writer.writeEndElement();
			writer.writeStartElement("NumCtrlEmis");
			writer.writeCharacters(String.format("%1$04d", new Object[] { Integer.valueOf(sequencia) }));
			writer.writeEndElement();
			writer.writeStartElement("ISPBEmissor");
			writer.writeCharacters(properties.getProperty("cnpj.credenciador.base"));
			writer.writeEndElement();
			writer.writeStartElement("ISPBDestinatario");
			String ispbDestinatario = properties.getProperty("ispb.destinatario");
			writer.writeCharacters(ispbDestinatario);
			writer.writeEndElement();
			writer.writeStartElement("DtHrArq");
			Date dataHora = new Date();
			writer.writeCharacters((new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")).format(dataHora));
			writer.writeEndElement();
			writer.writeStartElement("SitCons");
			writer.writeCharacters("0");
			writer.writeEndElement();
			writer.writeStartElement("DtRef");
			writer.writeCharacters((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeStartElement("SISARQ");
			writer.writeStartElement("ASCG026");
			writer.writeStartElement("Grupo_ASCG026_Agend");
			writer.writeStartElement("CNPJBaseCreddr");
			writer.writeCharacters(properties.getProperty("cnpj.credenciador.base"));
			writer.writeEndElement();
			writer.writeStartElement("CNPJCreddr");
			writer.writeCharacters(properties.getProperty("cnpj.credenciador"));
			writer.writeEndElement();
			writer.writeStartElement("ISPBIFCredr");
			long ispb = ((Long) bancosSCGMap.get(Long.valueOf(banco))).longValue();
			writer.writeCharacters(String.format("%1$08d", new Object[] { Long.valueOf(ispb) }));
			writer.writeEndElement();
			writer.writeStartElement("AgCreddr");
			writer.writeCharacters(agenciaCredenciador);
			writer.writeEndElement();
			writer.writeStartElement("CtCreddr");
			writer.writeCharacters(contaCredenciador);
			writer.writeEndElement();
			writer.writeStartElement("NomCreddr");
			writer.writeCharacters(nomeCredenciador);
			writer.writeEndElement();
		}

		private void escreveCentralizador(Registro registro) throws Exception {
			writer.writeStartElement("Grupo_ASCG026_Centrlz");
			writer.writeStartElement("NumCtrlCreddrCentrlz");
			String sequencia = String.format("%1$011d", new Object[] { Long.valueOf(registro.getSequencia()) });
			writer.writeCharacters(
					(new StringBuilder(String.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date()))))
							.append("9").append(sequencia).toString());
			writer.writeEndElement();
			writer.writeStartElement("TpPessoaCentrlz");
			writer.writeCharacters(registro.getTipoPessoaCent());
			writer.writeEndElement();
			writer.writeStartElement("CNPJ_CPFCentrlz");
			writer.writeCharacters(registro.getCnpj_centralizador());
			writer.writeEndElement();
			writer.writeStartElement("CodCentrlz");
			writer.writeCharacters(registro.getCentralizador());
			writer.writeEndElement();
			writer.writeStartElement("AgCentrlz");
			writer.writeCharacters(registro.getAgencia());
			writer.writeEndElement();
			writer.writeStartElement("CtCentrlz");
			writer.writeCharacters(registro.getConta());
			writer.writeEndElement();
		}

		private void escreveFimCentralizador() throws Exception {
			writer.writeEndElement();
		}

		private void escrevePV(Registro registro) throws Exception {
			writer.writeStartElement("Grupo_ASCG026_PontoVenda");
			writer.writeStartElement("NumCtrlCreddrPontoVenda");
			String sequencia = String.format("%1$012d", new Object[] { Long.valueOf(registro.getSequencia()) });
			writer.writeCharacters(
					(new StringBuilder(String.valueOf((new SimpleDateFormat("yyyyMMdd")).format(new Date()))))
							.append(sequencia).toString());
			writer.writeEndElement();
			writer.writeStartElement("CodInstitdrArrajPgto");
			writer.writeCharacters(registro.getProduto());
			writer.writeEndElement();
			writer.writeStartElement("CodMoeda");
			writer.writeCharacters(registro.getMoeda());
			writer.writeEndElement();
			writer.writeStartElement("CodPontoVenda");
			writer.writeCharacters(registro.getCodigo_ec());
			writer.writeEndElement();
			writer.writeStartElement("TpPessoaPontoVenda");
			writer.writeCharacters(registro.getTipoPessoaEc());
			writer.writeEndElement();
			writer.writeStartElement("CNPJ_CPFPontoVenda");
			writer.writeCharacters(registro.getCnpj_ec());
			writer.writeEndElement();
			writer.writeStartElement("DtPrevtPgto");
			Date data = (new SimpleDateFormat("ddMMyyyy")).parse(registro.getData_pagamento());
			writer.writeCharacters((new SimpleDateFormat("yyyy-MM-dd")).format(data));
			writer.writeEndElement();
			writer.writeStartElement("VlrLiqdPgtoPrevt");
//			double valor = (new Double(registro.getValor())).doubleValue() / 100D;
			BigDecimal valor = new BigDecimal(registro.getValor()).divide(new BigDecimal(100));
			writer.writeCharacters(String.valueOf(valor));
			writer.writeEndElement();
			writer.writeEndElement();
		}

		private void escreveFimArquivo(int numArqBanco) throws Exception {
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeStartElement("ESTARQ");
			writer.writeCData(
					(new StringBuilder("<Grupo_SeqArq><NumSeqArq>")).append(sequencia).append("</NumSeqArq><QtdTotArq>")
							.append(numArqBanco).append("</QtdTotArq></Grupo_SeqArq>").toString());
			writer.writeEndElement();
			writer.writeEndDocument();
			writer.flush();
			writer.close();
			System.out.println(
					(new StringBuilder("Arquivo [")).append(arquivoSaida.getName()).append("] gerado...").toString());
		}

		private Arquivo(int sequencia, long banco) throws Exception, XMLStreamException {
			this$0 = AgendaScgCIP.this;
			arquivoSaida = null;
			factory = XMLOutputFactory.newInstance();
			pathArquivoSaida = properties.getProperty("path.saida");
			nomeArquivo = (new StringBuilder("ASCG026_")).append(properties.getProperty("cnpj.credenciador.base"))
					.append("_").append((new SimpleDateFormat("yyyyMMdd")).format(new Date())).append("_")
					.append((new SimpleDateFormat("SSS")).format(new Date())).toString();
			arquivoSaida = new File(
					(new StringBuilder(String.valueOf(pathArquivoSaida))).append(nomeArquivo).toString());
			System.out.println(
					(new StringBuilder("Criando arquivo [")).append(arquivoSaida.getName()).append("]...").toString());
			this.sequencia = sequencia;
			this.banco = banco;
			writer = factory.createXMLStreamWriter(new FileOutputStream(arquivoSaida), "UTF-16BE");
		}

		private Arquivo(int sequencia, long banco, int sequenciaArquivo) throws Exception, XMLStreamException {
			this$0 = AgendaScgCIP.this;
			arquivoSaida = null;
			factory = XMLOutputFactory.newInstance();
			pathArquivoSaida = properties.getProperty("path.saida");
			nomeArquivo = (new StringBuilder("ASCG026_")).append(properties.getProperty("cnpj.credenciador.base"))
					.append("_").append((new SimpleDateFormat("yyyyMMdd")).format(new Date())).append("_")
					.append(String.format("%1$03d", sequenciaArquivo)).toString();
			arquivoSaida = new File(
					(new StringBuilder(String.valueOf(pathArquivoSaida))).append(nomeArquivo).toString());
			System.out.println(
					(new StringBuilder("Criando arquivo [")).append(arquivoSaida.getName()).append("]...").toString());
			this.sequencia = sequencia;
			this.banco = banco;
			writer = factory.createXMLStreamWriter(new FileOutputStream(arquivoSaida), "UTF-16BE");
		}

//		Arquivo(int i, long l, Arquivo arquivo) throws Exception, XMLStreamException {
//			this(i, l);
//		}
	}

	private Map bancosSCGMap;
	private Map domicilioCentralizadores;
	private Connection conPayware;
	private Connection conWeblogic;
	private String arquivoProperties;
	private Properties properties;
	private Map produtos;
	private String nomeCredenciador;
	private String agenciaCredenciador;
	private String contaCredenciador;
	private Map centralizadoresPorBanco;
	private Map arquivos;
	private PreparedStatement psInsereRegistros;
	private PreparedStatement psRegistrosStatement;
	private int quantidadeBatch;

	public AgendaScgCIP() {
		bancosSCGMap = new HashMap();
		domicilioCentralizadores = new HashMap();
		produtos = new HashMap();
		centralizadoresPorBanco = new HashMap();
		arquivos = new HashMap();
		psInsereRegistros = null;
		psRegistrosStatement = null;
	}

	public AgendaScgCIP(String arquivoProperties) {
		bancosSCGMap = new HashMap();
		domicilioCentralizadores = new HashMap();
		produtos = new HashMap();
		centralizadoresPorBanco = new HashMap();
		arquivos = new HashMap();
		psInsereRegistros = null;
		psRegistrosStatement = null;
		this.arquivoProperties = arquivoProperties;
		carregaPropriedades();
		produtos.put("01", "003");
		produtos.put("03", "004");
	}

	private void carregaPropriedades() {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(arquivoProperties)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void buscaBancosSCG() throws Exception {
		System.out.println("Buscando bancos contratantes SCG...");
		String bancosSemServico = properties.getProperty("bancos.sem.servico") != null
				? properties.getProperty("bancos.sem.servico") : "-1";
		String sql = (new StringBuilder(
				"select codigo_banco, ISPB from bancos where cambio_agenda <> 'N' and bloqueo_anticipo <> 'N' and codigo_banco not in ("))
						.append(bancosSemServico).append(") ").toString();
		PreparedStatement ps = conPayware.prepareStatement(sql);
		for (ResultSet rs = ps.executeQuery(); rs.next(); bancosSCGMap.put(Long.valueOf(rs.getLong("codigo_banco")),
				Long.valueOf(rs.getLong("ISPB"))));
//		bancosSCGMap.put(237L, 199999L);
//		bancosSCGMap.put(33L, 299999L);
//		bancosSCGMap.put(341L, 399999L);
		System.out.println((new StringBuilder("Encontrados ")).append(bancosSCGMap.size())
				.append(" bancos contratantes...").toString());
		ps.close();
	}

	private void fechaConexaoPayware() {
		System.out.println("Fechando conexao com banco de dados PAYware...");
		if (conPayware != null)
			try {
				conPayware.close();
				System.out.println("Conexao com banco de dados PAYware fechada com sucesso...");
			} catch (SQLException e) {
				System.out.println("Erro fechando conexao com banco de dados!!!");
				e.printStackTrace();
			}
	}

	private void fechaConexaoWeblogic() {
		if (conWeblogic != null)
			try {
				conWeblogic.close();
			} catch (SQLException e) {
				System.out.println("Erro fechando conexao com banco de dados!!!");
				e.printStackTrace();
			}
	}

	private void criaConexaoPayware() {
		try {
			String driverClass = "oracle.jdbc.driver.OracleDriver";
			String url = properties.getProperty("url");
			// String url =
			// "jdbc:oracle:thin:@hmlgbdb.getnet.local/PAYWAREQASRV";
			String userVariable = properties.getProperty("user.db.variable");
			String pwdVariable = properties.getProperty("password.db.variable");
			String user = System.getenv(userVariable);
			String password = System.getenv(pwdVariable);
			if(user == null) {
				user = "hgacqr";
			}
			if(password == null) {
				password = "pci2014";
			}
			System.out.println("Conectando banco de dados PAYware URL[" + url + "]");
			Class.forName(driverClass);
			conPayware = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado com sucesso a base de dados PAYware!!!");
		} catch (Exception e) {
			System.out.println("Erro conectando ao banco de dados PAYware!!!");
			e.printStackTrace();
		}
	}

	private void criaConexaoWeblogic() {
		try {
			String driverClass = "oracle.jdbc.driver.OracleDriver";
			String url = properties.getProperty("url.weblogic");
			// String url =
			// "jdbc:oracle:thin:@db-hk-sp.transacional.getnet.local/santanderhksrv";
			// String url = "jdbc:oracle:thin:@192.168.56.101:1521/ORCL";
			String userVariable = properties.getProperty("user.db.weblogic.variable");
			String pwdVariable = properties.getProperty("password.db.weblogic.variable");
			String user = System.getenv(userVariable);
			String password = System.getenv(pwdVariable);
			if(user == null) {
				user = "STDR_LAC_CADASTRO";
			}
			if(password==null) {
				password = "getnet123";
			}
			// String user = "devtest";
			// String password ="password";
			System.out.println("Conectando banco de dados Weblogic URL[" + url + "]");
			Class.forName(driverClass);
			conWeblogic = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado com sucesso a base de dados Weblogic!!!");
		} catch (Exception e) {
			System.out.println("Erro conectando ao banco de dados Weblogic!!!");
			e.printStackTrace();
		}
	}

	private void createInsertStatement() throws SQLException {
		String sql = "Insert into ARQUIVO_AGENDA_DETALHE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (psInsereRegistros == null)
			psInsereRegistros = conWeblogic.prepareStatement(sql);
	}

	private void closeInsertStatement() throws SQLException {
		if (psInsereRegistros != null)
			psInsereRegistros.close();
	}

	private void adicionaBatch(String banco, String ec, String centralizador, String cnpjCentralizador, String agencia,
			String conta, String valor, String dataPagamento, String moeda, String cnpjEc, String produto,
			String tipoPessoaEc, String tipoPessoaCent, long sequenciaRegistro, int count) throws Exception {
		psInsereRegistros.setInt(1, Integer.parseInt(banco));
		psInsereRegistros.setInt(3, Integer.parseInt(centralizador));
		psInsereRegistros.setInt(2, Integer.parseInt(ec));
		psInsereRegistros.setString(4, cnpjCentralizador);
		psInsereRegistros.setString(5, agencia);
		psInsereRegistros.setString(6, conta);
		psInsereRegistros.setString(7, valor);
		psInsereRegistros.setString(8, dataPagamento);
		psInsereRegistros.setString(9, moeda);
		psInsereRegistros.setString(10, cnpjEc);
		psInsereRegistros.setString(11, produto);
		psInsereRegistros.setString(12, tipoPessoaEc);
		psInsereRegistros.setString(13, tipoPessoaCent);
		psInsereRegistros.setLong(14, sequenciaRegistro);
		psInsereRegistros.addBatch();
	}

	private void adicionaCentralizadorBanco(long banco, long centralizador) {
		Set centralizadores = (Set) centralizadoresPorBanco.get(Long.valueOf(banco));
		if (centralizadores != null) {
			centralizadores.add(Long.valueOf(centralizador));
			centralizadoresPorBanco.put(Long.valueOf(banco), centralizadores);
		} else {
			centralizadores = new HashSet();
			centralizadores.add(Long.valueOf(centralizador));
			centralizadoresPorBanco.put(Long.valueOf(banco), centralizadores);
		}
	}

	private void carregaDomicilioCentralizadores(String arquivo) throws Exception {
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(arquivo));
		String linha = "";
		System.out.println(
				(new StringBuilder("Carregando centralizadores com SCG para o arquivo: ")).append(arquivo).toString());
		domicilioCentralizadores = new HashMap();
		while ((linha = reader.readLine()) != null) {
			int registro = -1;
			try {
				registro = selecionaRegistro(linha);
			} catch (Exception e) {
				System.out.println((new StringBuilder("Erro selecionando registro linha [")).append(linha)
						.append("] do arquivo [").append(arquivo).append("]").toString());
				throw new Exception(e.getCause());
			}
			long ec = 0L;
			long centralizador = 0L;
			long domicilio = 0L;
			if (registro == 3) {
				ec = Long.parseLong(linha.substring(80, 89));
				centralizador = Long.parseLong(linha.substring(195, 205));
				if (centralizador == 0L)
					centralizador = ec;
				if (ec == centralizador || centralizador == 0L) {
					domicilio = Long.parseLong(linha.substring(20, 23));
					if (bancosSCGMap.containsKey(Long.valueOf(domicilio))) {
						String agencia = linha.substring(24, 28);
						String dvAgencia = linha.substring(28, 29);
						String conta = linha.substring(29, 41);
						String dvConta = linha.substring(41, 42);
						String cnpj = linha.substring(179, 193);
						domicilioCentralizadores.put(Long.valueOf(centralizador),
								new DomicilioBancario(domicilio, agencia, dvAgencia, conta, dvConta, cnpj));
						adicionaCentralizadorBanco(domicilio, centralizador);
					}
				}
			}
		}
		System.out.println((new StringBuilder("Centralizadores com SCG para o arquivo ")).append(arquivo).append(" = ")
				.append(domicilioCentralizadores.size()).toString());
		reader.close();
		return;
	}

	private int selecionaRegistro(String linha) {
		String registro = linha.substring(7, 8);
		if (Character.isDigit(registro.charAt(0)))
			return Integer.parseInt(registro);
		else
			return 99;
	}

	private long buscaSequenciaArquivos() throws Exception {
		System.out.println("Buscando ultima sequencia arquivos...");
		String sql = "select * from  ARQUIVOS_SEQ where arquivo = 'AGENDA'";
		PreparedStatement ps = conWeblogic.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		long sequencia = 0;
		if (rs.next()) {
			sequencia = rs.getLong("sequencia_arquivo");
		}
		ps.close();
		rs.close();
		System.out.println("Ultima sequencia arquivos...[" + sequencia + "]");
		return sequencia;
	}

	
//	private void ajustaSequenciaSeNecessario() throws Exception {
//		long dataSequencia;
//		PreparedStatement ps;
//		long hoje;
//		System.out.println("Verficando ajuste ultima sequencia...");
//		String sql = "select * from  ARQUIVOS_SEQ where arquivo = 'AGENDA'";
//		dataSequencia = 0L;
//		ps = conWeblogic.prepareStatement(sql);
//		hoje = Long.parseLong((new SimpleDateFormat("yyyyMMdd")).format(new Date()));
//		ResultSet rs = ps.executeQuery();
//		if (rs.next())
//			dataSequencia = rs.getLong("data");
//		ps.close();
//		if (hoje <= dataSequencia)
//			sql = "update arquivos_seq set data = ?, sequencia = 1, sequencia_arquivo = 1 where arquivo = 'AGENDA' ";
//		ps = conWeblogic.prepareStatement(sql);
//		ps.setLong(1, hoje);
//		ps.executeUpdate();
//		conWeblogic.commit();
//		System.out.println(
//				(new StringBuilder("Sequencia ajustada... Data[")).append(hoje).append("] Valor[1]").toString());
//		ps.close();
//		System.out.println((new StringBuilder("Nao foi necessario ajustar Sequencia... Data Sequencia["))
//				.append(dataSequencia).append("] Data Atual[").append(hoje).append("]").toString());
//	}

	private void ajustaSequenciaSeNecessario() throws Exception {
		System.out.println("Verficando ajuste ultima sequencia...");
		String sql = "select * from  ARQUIVOS_SEQ where arquivo = 'AGENDA'";
		long dataSequencia = 0;
		PreparedStatement ps = conWeblogic.prepareStatement(sql);
		long hoje = Long.parseLong(new SimpleDateFormat("yyyyMMdd")
				.format(new Date()));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			dataSequencia = rs.getLong("data");
		}
		ps.close();
		if (hoje > dataSequencia) {
			try {
				sql = "update arquivos_seq set data = ?, sequencia = 1, sequencia_arquivo = 1 where arquivo = 'AGENDA' ";
				ps = conWeblogic.prepareStatement(sql);
				ps.setLong(1, hoje);
				ps.executeUpdate();
				conWeblogic.commit();
				// ps.close();
				System.out.println("Sequencia ajustada... Data[" + hoje
						+ "] Valor[1]");
			} finally {
				ps.close();
			}
		} else {
			System.out
					.println("Nao foi necessario ajustar Sequencia... Data Sequencia["
							+ dataSequencia + "] Data Atual[" + hoje + "]");
		}

	}

	private void atualizaSequencia(long sequenciaRegistros) throws Exception {
		String sql;
		PreparedStatement ps;
		System.out.println((new StringBuilder("Atualizando sequencia... Novo valor [")).append(sequenciaRegistros)
				.append("]").toString());
		sql = "update arquivos_seq set sequencia = ? where arquivo = 'AGENDA' ";
		ps = conWeblogic.prepareStatement(sql);
		ps = conWeblogic.prepareStatement(sql);
		ps.setLong(1, sequenciaRegistros);
		ps.executeUpdate();
		conWeblogic.commit();
		ps.close();
		return;
	}

	private long buscaSequenciaRegistros() throws Exception {
		System.out.println("Buscando ultima sequencia...");
		String sql = "select * from  ARQUIVOS_SEQ where arquivo = 'AGENDA'";
		PreparedStatement ps = conWeblogic.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		long sequencia = 0L;
		if (rs.next())
			sequencia = rs.getLong("sequencia");
		ps.close();
		rs.close();
		System.out.println((new StringBuilder("Ultima sequencia...[")).append(sequencia).append("]").toString());
		return sequencia;
	}

	private void executeInsertBatch() throws SQLException {
		psInsereRegistros.executeBatch();
	}

	private void parseArquivoEntrada(String arquivo) throws Exception {
		System.out.println((new StringBuilder("Iniciando parse do arquivo: ")).append(arquivo).toString());
		File arquivoAgenda = new File(arquivo);
		BufferedReader reader = new BufferedReader(new FileReader(arquivoAgenda));
		String linha = "";
		String produto = arquivoAgenda.getName().substring(16, 18);
		String qtdBatch = properties.getProperty("quantidade.batch") == null ? "100"
				: properties.getProperty("quantidade.batch");
		quantidadeBatch = Integer.parseInt(qtdBatch);
		long sequenciaRegistros = buscaSequenciaRegistros();
		int count = 1;
		while ((linha = reader.readLine()) != null) {
			int registro = selecionaRegistro(linha);
			long ec = 0L;
			long centralizador = 0L;
			DomicilioBancario domicilioBancario = null;
			if (registro == 0) {
				nomeCredenciador = linha.substring(72, 83);
				agenciaCredenciador = linha.substring(53, 57);
				contaCredenciador = linha.substring(57, 70);
			}
			if (registro == 3) {
				ec = Long.parseLong(linha.substring(80, 89));
				centralizador = Long.parseLong(linha.substring(195, 205));
				if (centralizador == 0L)
					centralizador = ec;
				if (domicilioCentralizadores.containsKey(Long.valueOf(centralizador))) {
					domicilioBancario = (DomicilioBancario) domicilioCentralizadores.get(Long.valueOf(centralizador));
					String dataPagamento = linha.substring(93, 101);
					String cnpj = linha.substring(179, 193);
					String tipoPessoa = linha.substring(193, 194);
					String moeda = properties.getProperty(linha.substring(101, 104));
					String produtoLinha = linha.substring(219, 222);
					adicionaBatch(String.valueOf(domicilioBancario.getCodigoBacen()),
							(new StringBuilder()).append(ec).toString(),
							(new StringBuilder()).append(centralizador).toString(), domicilioBancario.getCnpj(),
							domicilioBancario.getAgencia(),
							(new StringBuilder(String.valueOf(domicilioBancario.getConta())))
									.append(domicilioBancario.getDvConta()).toString(),
							(new StringBuilder()).append(getValor(linha)).toString(), dataPagamento, moeda, cnpj,
							(String) produtoLinha, tipoPessoa, tipoPessoa, sequenciaRegistros, count);
					if (count > quantidadeBatch) {
						executeInsertBatch();
						count = 0;
					}
					count++;
					sequenciaRegistros++;
				}
			}
		}
		executeInsertBatch();
		atualizaSequencia(sequenciaRegistros);
		System.out
				.println((new StringBuilder("Parse do arquivo: ")).append(arquivo).append(" realizado...").toString());
		reader.close();
	}

	private long getValor(String linha) {
		String valor = linha.substring(163, 177);
		return Long.parseLong(valor);
	}

	private void createRegistrosStatement() throws SQLException {
//		String sql = "select * from ARQUIVO_AGENDA_DETALHE where banco = ? and centralizador = ? and produto = ? order by data_pagamento ";
		String sql = "select * from ARQUIVO_AGENDA_DETALHE where banco = ? and centralizador = ? order by data_pagamento ";
		if (psRegistrosStatement == null)
			psRegistrosStatement = conWeblogic.prepareStatement(sql);
	}

	private void closeRegistrosStatement() throws SQLException {
		if (psRegistrosStatement != null)
			psRegistrosStatement.close();
	}

	private List buscaRegistrosPorProduto(Long banco, long centralizador, String produto) throws Exception {
		psRegistrosStatement.setLong(1, banco.longValue());
		psRegistrosStatement.setLong(2, centralizador);
		psRegistrosStatement.setString(3, produto);
		List registros = new ArrayList();
		ResultSet rs;
		Registro registro;
		for (rs = psRegistrosStatement.executeQuery(); rs.next(); registros.add(registro)) {
			registro = new Registro();
			registro.setBanco(rs.getString("BANCO"));
			registro.setCodigo_ec(rs.getString("CODIGO_EC"));
			registro.setCentralizador(rs.getString("CENTRALIZADOR"));
			registro.setCnpj_centralizador(rs.getString("CNPJ_CENTRALIZADOR"));
			registro.setAgencia(rs.getString("AGENCIA"));
			registro.setConta(rs.getString("CONTA"));
			registro.setValor(rs.getString("VALOR"));
			registro.setData_pagamento(rs.getString("DATA_PAGAMENTO"));
			registro.setMoeda(rs.getString("MOEDA"));
			registro.setCnpj_ec(rs.getString("CNPJ_EC"));
			registro.setProduto(rs.getString("PRODUTO"));
			registro.setTipoPessoaEc(rs.getString("TIPO_PESSOA_EC"));
			registro.setTipoPessoaCent(rs.getString("TIPO_PESSOA_CENT"));
			registro.setSequencia(rs.getLong("SEQUENCIA"));
		}

		rs.close();
		return registros;
	}

	private List buscaRegistros(Long banco, Long centralizador) throws Exception {
		psRegistrosStatement.setLong(1, banco.longValue());
		psRegistrosStatement.setLong(2, centralizador);
		List registros = new ArrayList();
		ResultSet rs;
		Registro registro;
		for (rs = psRegistrosStatement.executeQuery(); rs.next(); registros.add(registro)) {
			registro = new Registro();
			registro.setBanco(rs.getString("BANCO"));
			registro.setCodigo_ec(rs.getString("CODIGO_EC"));
			registro.setCentralizador(rs.getString("CENTRALIZADOR"));
			registro.setCnpj_centralizador(rs.getString("CNPJ_CENTRALIZADOR"));
			registro.setAgencia(rs.getString("AGENCIA"));
			registro.setConta(rs.getString("CONTA"));
			registro.setValor(rs.getString("VALOR"));
			registro.setData_pagamento(rs.getString("DATA_PAGAMENTO"));
			registro.setMoeda(rs.getString("MOEDA"));
			registro.setCnpj_ec(rs.getString("CNPJ_EC"));
			registro.setProduto(rs.getString("PRODUTO"));
			registro.setTipoPessoaEc(rs.getString("TIPO_PESSOA_EC"));
			registro.setTipoPessoaCent(rs.getString("TIPO_PESSOA_CENT"));
			registro.setSequencia(rs.getLong("SEQUENCIA"));
		}

		rs.close();
		return registros;
	}

	private void limpaTabelas() throws SQLException {
		String sql = "truncate table ARQUIVO_AGENDA_DETALHE";
		System.out.println("Iniciando processo de limpeza da tabela ARQUIVO_AGENDA_DETALHE...");
		PreparedStatement ps = conWeblogic.prepareStatement(sql);
		ps.executeUpdate();
		System.out.println("Limpeza da tabela ARQUIVO_AGENDA_DETALHE realizada com sucesso...");
	}

	public void processaArquivosAgenda(String arquivos[]) throws Exception {
		System.out.println((new StringBuilder("Inicio Processo...["))
				.append((new SimpleDateFormat("dd/MM/yyyy hh:mm:ss")).format(new Date())).append("]").toString());
		criaConexaoPayware();
		buscaBancosSCG();
		fechaConexaoPayware();
		criaConexaoWeblogic();
		limpaTabelas();
		ajustaSequenciaSeNecessario();
		createInsertStatement();
		String as[];
		int j = (as = arquivos).length;
		for (int i = 0; i < j; i++) {
			String arquivo = as[i];
			System.out.println((new StringBuilder("Inicio carga centralizadores arquivo[")).append(arquivo)
					.append("]...[").append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date()))
					.append("]").toString());
			carregaDomicilioCentralizadores(arquivo);
			System.out.println((new StringBuilder("Fim carga centralizadores arquivo[")).append(arquivo).append("]...[")
					.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())).append("]").toString());
			System.out.println((new StringBuilder("Inicio parse arquivo[")).append(arquivo).append("]...[")
					.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())).append("]").toString());
			parseArquivoEntrada(arquivo);
			System.out.println((new StringBuilder("Fim parse arquivo[")).append(arquivo).append("]...[")
					.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())).append("]").toString());
		}

		closeInsertStatement();
		createRegistrosStatement();
		System.out.println((new StringBuilder("Inicio geracao arquivos saida...["))
				.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())).append("]").toString());
		escreveArquivoSaidaBanco();
		System.out.println((new StringBuilder("Fim geracao arquivos saida...["))
				.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())).append("]").toString());
		closeRegistrosStatement();
		fechaConexaoWeblogic();
		System.out.println((new StringBuilder("Fim Processo...["))
				.append((new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date())).append("]").toString());
	}

	private int calculaNumeroDeArquivos(int numCents) {
		String valor = properties.getProperty("num.max.centralizadores") != null
				? properties.getProperty("num.max.centralizadores") : "50000";
		int numeroMaximoCentsArquivo = Integer.parseInt(valor);
		double retorno = Math.ceil((double) numCents / (double) numeroMaximoCentsArquivo);
		return (int) retorno;
	}

	private void atualizaSequenciaArquivos(long sequenciaArquivos) throws Exception {
		System.out.println("Atualizando sequencia... Novo valor ["
				+ sequenciaArquivos + "]");
		String sql = "update arquivos_seq set sequencia_arquivo = ? where arquivo = 'AGENDA' ";
		PreparedStatement ps = conWeblogic.prepareStatement(sql);
		try {
			ps = conWeblogic.prepareStatement(sql);
			ps.setLong(1, sequenciaArquivos);
			ps.executeUpdate();
			conWeblogic.commit();
		} finally {
			ps.close();
		}
	}


	private void escreveArquivoSaidaBanco()
			throws FactoryConfigurationError, Exception, XMLStreamException, FileNotFoundException {
		Set bancos = centralizadoresPorBanco.keySet();
		int sequencia = 1;
		int countArquivos = 0;
		int maxCentralizadoresPorArquivo = Integer.parseInt(properties.getProperty("num.max.centralizadores"));
		int centralizadoresPorArquivo = 0;
		Arquivo arquivo = null;
		long sequenciaArquivo = buscaSequenciaArquivos();
		
		for (Iterator iterator = bancos.iterator(); iterator.hasNext();) {
			Long banco = (Long) iterator.next();
			long ispb = ((Long) bancosSCGMap.get(banco)).longValue();
			System.out.println((new StringBuilder("Iniciando geracao do arquivo de saida para o banco [")).append(banco)
					.append("] ISPB[").append(String.format("%1$08d", new Object[] { Long.valueOf(ispb) }))
					.append("]...").toString());
			Set centralizadores = (Set) centralizadoresPorBanco.get(banco);
			System.out.println((new StringBuilder("Existem [")).append(centralizadores.size())
					.append("] Centralizadores para o banco [").append(banco).append("]... ").toString());
			System.out.println((new StringBuilder("Numero maximo de centralizadores por Arquivo ["))
					.append(maxCentralizadoresPorArquivo).append("]...").toString());
			int numeroDeArquivosBanco = calculaNumeroDeArquivos(centralizadores.size());
			System.out.println((new StringBuilder("Numero de arquivos a serem gerados [")).append(numeroDeArquivosBanco)
					.append("]...").toString());
			for (Iterator iterator1 = centralizadores.iterator(); iterator1.hasNext();) {
				Long centralizador = (Long) iterator1.next();
				arquivo = (Arquivo) arquivos.get(banco);
				if (arquivo == null) {
					arquivo = new Arquivo(sequencia, banco.longValue(), (int)sequenciaArquivo);
					sequenciaArquivo++;
					arquivo.escreveHeader();
					arquivos.put(banco, arquivo);
					countArquivos++;
				} else if (maxCentralizadoresPorArquivo == centralizadoresPorArquivo) {
					arquivo.escreveFimArquivo(numeroDeArquivosBanco);
					sequencia++;
					arquivo = new Arquivo(sequencia, banco.longValue(), (int)sequenciaArquivo);
					sequenciaArquivo++;
					countArquivos++;
					arquivo.escreveHeader();
					arquivos.put(banco, arquivo);
					centralizadoresPorArquivo = 0;
				}
//				Set produtosKeys = produtos.keySet();
//				for (Iterator iterator2 = produtosKeys.iterator(); iterator2.hasNext();) {
//					String key = (String) iterator2.next();
//					String produto = (String) produtos.get(key);
//				List registros = buscaRegistrosPorProduto(banco, centralizador.longValue(), produto);
				List registros = buscaRegistros(banco, centralizador);
					boolean first = true;
					if (registros.size() != 0) {
						for (Iterator iterator3 = registros.iterator(); iterator3.hasNext();) {
							Registro registro = (Registro) iterator3.next();
							if (first) {
								arquivo.escreveCentralizador(registro);
								first = false;
								centralizadoresPorArquivo++;
							}
							if (!"0".equals(registro.getValor()))
								arquivo.escrevePV(registro);
						}
//						centralizadoresPorArquivo++;
						arquivo.escreveFimCentralizador();
					}
//					centralizadoresPorArquivo++;
//				}
//				centralizadoresPorArquivo++;
			}

			arquivo.escreveFimArquivo(numeroDeArquivosBanco);
			centralizadoresPorArquivo = 0;
			sequencia = 1;
		}
		atualizaSequenciaArquivos(sequenciaArquivo);
		System.out.println((new StringBuilder("Gerados [")).append(countArquivos).append("] arquivos...").toString());
	}

}
