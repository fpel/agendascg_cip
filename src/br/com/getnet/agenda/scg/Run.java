package br.com.getnet.agenda.scg;

import br.com.getnet.agenda.scg.io.AgendaScgCIP;

public class Run {
	public static void main(String[] args) {
		System.out.println("Iniciando Geração de arquivos de Agenda SCG...");
		System.out.println("Parametros["+args[0]+"], ["+args[1]+"], ["+args[2]+"], ["+args[3]+"]");
		AgendaScgCIP agenda = new AgendaScgCIP(args[0]);
		long inicio = System.currentTimeMillis();
		String [] arquivos = new String[args.length-1];
		int count = 0;
		try {
			for (int i = 1; i <= (args.length-1); i++) {
				arquivos [count] = args[i];
				count++;
			}
			agenda.processaArquivosAgenda(arquivos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Tempo processamento: "+(System.currentTimeMillis()-inicio));
	}
}
