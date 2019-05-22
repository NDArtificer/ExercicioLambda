package aplicacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Funcionarios;
import model.FuncionarioService;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Funcionarios> funcionario= new ArrayList<>(); 
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		FuncionarioService fs = new FuncionarioService();
		
		System.out.println("Digite o caminho do arquivo: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String campos[] = line.split(",");
				String nome = campos[0];
				String email = campos[1];
				double salario = Double.parseDouble(campos[2]);
				funcionario.add(new Funcionarios(nome, email, salario));
				line = br.readLine();
				}
			double soma = fs.somaFiltrada(funcionario, p-> p.getNome().charAt(0) == 'M');
			
			System.out.print("Informe o salario: ");	
			double sal = sc.nextDouble();
			funcionario.removeIf(p-> p.getSalario() <= sal);
			System.out.println("Email das pessoas que recebem mais do que R$"+ String.format("%.2f", sal)+": ");
			for(Funcionarios f : funcionario) {
				
				System.out.println(f.getEmail());
			}
			
			System.out.println("Soma dos salarios das pessoas que começam com a letra 'M': R$" + String.format("%.2f", soma ));
			}
		catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		}
	}

}
