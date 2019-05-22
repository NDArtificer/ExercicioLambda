package aplicacao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entidades.Funcionarios;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Funcionarios> funcionario = new ArrayList<>();
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

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

			System.out.print("Informe o salario: ");
			
			double sal = sc.nextDouble();
			List<String> emails = funcionario.stream()
					.filter(x -> x.getSalario() > sal)
					.map(x -> x.getEmail()).sorted()
					.collect(Collectors.toList());
			
			System.out.println("Email das pessoas que o salrio é maior que R$" + String.format("%.2f", sal) + ": ");
			emails.forEach(System.out::println);

			double soma = funcionario.stream()
					.filter(x -> x.getNome().charAt(0) == 'M')
					.map(x -> x.getSalario())
					.reduce(0.0, (x, y) -> x + y);

			System.out.println("Soma dos salarios das pessoas que começam com a letra 'M': R$" + String.format("%.2f", soma));

		} catch (Exception e) {
			System.out.println("Erro:" + e.getMessage());
		}
		finally {
			sc.close();
		}
	}

}
