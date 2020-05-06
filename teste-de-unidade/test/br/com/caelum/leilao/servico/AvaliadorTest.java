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
 * - Por convenção separamos as classes de test das classes de produção criando uma nova source folder chamada test e mantemos os mesmos nomes de pacotes.
 * - Delta: o double tem limites de precisão, a versão nova do JUnit pede para passar o tamanho da precisão aceitável
 */

public class AvaliadorTest {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

		// 1) cenário: 3 lances em ordem crescente.
		Usuario joao  = new Usuario("João");
		Usuario jose  = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));

		// 2) executando a ação.
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// 3) comparando a saída com o esperado.
		double maiorEsperado = 400.0;
		double menorEsperado = 250.0;

		Assert.assertEquals(maiorEsperado, avaliador.getMaiorLance(), 0.00001); // A ordem é sempre (esperado, calculado, [delta -> precisão]).
		Assert.assertEquals(menorEsperado, avaliador.getMenorLance(), 0.00001); 
		
	}
	
	
	@Test
	public void deveCalcularAMedia() {

		// 1) cenário: 3 lances em ordem crescente.
		Usuario joao  = new Usuario("João");
		Usuario jose  = new Usuario("Jose");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 4 Novo");

		leilao.propoe(new Lance(maria, 100.0));
		leilao.propoe(new Lance(joao,  300.0));
		leilao.propoe(new Lance(jose,  400.0));

		// 2) executando a ação.
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		// 3) comparando a saída com o esperado.
	
		double mediaEsperado = 266.666666666666;
		Assert.assertEquals(mediaEsperado, avaliador.getMediaLance(), 0.00001);
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testeMediaZeroLance() {

		// cenário
		Usuario edson = new Usuario("Edson");
		
		// ação
		Leilao leilao = new Leilao("Bicicleta Caloi");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		
		// validação
		Assert.assertEquals(0, avaliador.getMediaLance(), 0.000001);
	}
	
	
}
