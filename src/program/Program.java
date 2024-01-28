package program;

import java.util.InputMismatchException;
import java.util.Scanner;

import telefone.Agenda;
import telefone.Contato;
import telefone.ContatoGerador;
import telefone.TelefoneGerador;

public class Program {

	static Scanner sc;
	static Agenda agenda;
	static ContatoGerador contatoGerador;
	static TelefoneGerador telefoneGerador;

	public static void main(String[] args) {
		Program.sc = new Scanner(System.in);
		iniciar();

		byte opcao = 4;

		System.out.println("""
				################
				#### AGENDA ####
				################""");

		loop : while (true) {
			System.out.println("\n" + agenda + "\n");

			System.out.print("""
					>>>> Menu <<<<
					1 - Adicionar Contato
					2 - Remover Contato
					3 - Editar Contato
					4 - Sair
					>""" + " ");

			try {
				opcao = sc.nextByte();
				sc.nextLine();
			} catch (InputMismatchException e) {
				continue;
			}

			switch (opcao) {
				case 1 :
					adicionarContato();
					break;
				case 2 :
					removerContato();
					break;
				case 3 :
					EditorContato.editar();
					break;
				case 4 :
					break loop;
				default :
					continue;
			}

		}

		sc.close();

	}

	private static void adicionarContato() {
		Contato contato = contatoGerador.gerar();
		String nome, sobrenome;
		boolean sucesso;

		System.out.print("\nPrimeiro nome: ");
		nome = sc.nextLine();
		System.out.print("Sobrenome: ");
		sobrenome = sc.nextLine();

		EditorContato.adicionarTelefone(contato);

		contato.setNome(nome);
		contato.setSobrenome(sobrenome);
		sucesso = Program.agenda.adicionarContato(contato);

		if (sucesso) {
			System.out.println("O contato foi adicionado com sucesso!");
		} else {
			System.out.println(
					"Telefone já cadastrado, não foi possível adicionar novo contato.");
		}
	}

	private static void removerContato() {
		if (Program.agenda.size() == 0) {
			System.out.println("A agenda está vazia.");
		}

		System.out.print("Digite o id do contato que deseja remover: ");
		Long id = sc.nextLong();
		sc.nextLine();

		if (Program.agenda.removerContato(id)) {
			System.out.println("Contato removido com sucesso.");
		} else {
			System.out.println("Contato inexistente.");
		}
	}

	private static void iniciar() {
		Program.agenda = new Agenda();
		Program.contatoGerador = ContatoGerador.getInstance();
		Program.telefoneGerador = TelefoneGerador.getInstance();
	}

}
