package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

public class Avaliador {

	private Double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private Double menorDeTodos = Double.POSITIVE_INFINITY;
	private Double media;

	public void avalia(Leilao leilao) {
		
		Double total = 0.0;
		for (Lance lances : leilao.getLances()) {
			if (lances.getValor() > maiorDeTodos) {
				this.maiorDeTodos = lances.getValor();
			}
			if (lances.getValor() < menorDeTodos) {
				this.menorDeTodos = lances.getValor();
			}
		   total+= lances.getValor();			
		}
		
		if(total == 0.0) {
			this.media = 0.0;
			return;
		}
		media = total / leilao.getLances().size(); // Pegar o total de elementos do array.
	}

	public Double getMaiorLance() {
		return maiorDeTodos;
	}

	public Double getMenorLance() {
		return menorDeTodos;
	}

	public Double getMediaLance() {
		return media;
	}
}
