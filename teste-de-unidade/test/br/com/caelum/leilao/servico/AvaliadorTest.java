package br.com.caelum.leilao.servico;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

/**
 * @author edson.h.cavalcanti
 * 
 * - assertEquals: quando o teste falha o JUnit usa esses valores para exibir a mensagem de erro ex: expected 10, but was 20 importante manter a ordem do esperado e depois o calculado.
 * - Por conven��o separamos as classes de test das classes de produ��o criando uma nova source folder chamada test e mantemos os mesmos nomes de pacotes.
 * - Delta: o double tem limites de precis�o, a vers�o nova do JUnit pede para passar o tamanho da precis�o aceit�vel
 */

public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// 1) cen�rio: 3 lances em ordem crescente.
		Usuario joao  = new Usuario("Jo�o");
		Usuario jose  = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// 2) executando a a��o.
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// 3) comparando a sa�da com o esperado.
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;

		Assert.assertEquals(maiorEsperado, avaliador.getMaiorLance(), 0.00001); // A ordem � sempre (esperado, calculado, [delta -> precis�o]).
		Assert.assertEquals(menorEsperado, avaliador.getMenorLance(), 0.00001); 
		
	}
	
	
	@Test
	public void deveCalcularAMedia() {

		// 1) cen�rio: 3 lances em ordem crescente.
		Usuario joao  = new Usuario("Jo�o");
		Usuario jose  = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(maria, 100.0));
		leilao.propoe(new Lance(joao,  300.0));
		leilao.propoe(new Lance(jose,  400.0));

		// 2) executando a a��o.
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// 3) comparando a sa�da com o esperado.
	
		double mediaEsperado = 266.666666666666;
		Assert.assertEquals(mediaEsperado, avaliador.getMediaLance(), 0.00001);
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testeMediaZeroLance() {

		// cen�rio
		Usuario edson = new Usuario("Edson");
		
		// a��o
		Leilao leilao = new Leilao("Bicicleta Caloi");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		
		// valida��o
		Assert.assertEquals(0, avaliador.getMediaLance(), 0.000001);
	}
	
	
}
