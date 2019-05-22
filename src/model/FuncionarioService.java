package model;

import java.util.List;
import java.util.function.Predicate;

import entidades.Funcionarios;

public class FuncionarioService {
	
	public double somaFiltrada(List<Funcionarios> list, Predicate<Funcionarios> criterio) {
		
		double soma = 0;
		for (Funcionarios f: list) {
			if(criterio.test(f)) {
				soma += f.getSalario();
			}
		}
		return soma;
	}

}
