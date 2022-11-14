package br.com.fintech.dao;

import java.util.List;

import br.com.fintech.model.Despesa;

public class SalveiInsight {

	public static void main(String[] args) {
		String mes = null;

		for (int i = 1; i <= 12; i++) {
			
			if (i < 10) {
				mes = String.valueOf("0" + i);
			} else {
				mes = String.valueOf(i);
			}

			

			//switch case ou if para verificar se é receita ou despesa
			
			//case despesa
				//gera o header do mes
				//mapMes.get(mes);
				//despesaDao.sumDespesasByUserByMonth(2, mes);
				
				//Gera as divs de um mês
				//List<Despesa> despesas = despesaDao.selectAllByUserByMonthByYear(2, mes, "2022");
				//for (Despesa item : despesas) {
				//	System.out.println( item ); //aqui a div seria construida
				//}
			
			//case receita
				
		}

	}

}
