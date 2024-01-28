package program;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import telefone.Contato;
import telefone.Telefone;

public class EditorContato {

	static void editar() {
		System.out.println(Program.agenda.toString());

		if (Program.agenda.size() == 0) {
			return;
		}

		System.out.print("\nDigite o id do contato que deseja editar: ");
		Long id = Program.sc.nextLong();
		Program.sc.nextLine();

		Optional<Contato> contato = Program.agenda.buscarContato(id);

		if (contato.isPresent()) {
			EditorContato.editar(contato.get());
		} else {
			System.out.println("Contato inexistente.");
		}

	}

	static void editar(Contato contato) {
		short opcao = 4;

		System.out.println("\n" + contato.toString());

		while (true) {
			System.out.println();
			System.out.print("""
					>>>> Edição <<<<
					1 - Editar Nome
					2 - Editar Sobrenome
					3 - Editar telefones
					4 - Sair
					>""");

			opcao = Program.sc.nextShort();
			Program.sc.nextLine();

			switch (opcao) {
				case 1 :
					editarNome(contato);
					break;
				case 2 :
					editarSobrenome(contato);
					break;
				case 3 :
					editarTelefones(contato);
					break;
				case 4 :
					return;
				default :
					continue;
			}

		}

	}

	private static void editarNome(Contato contato) {
		System.out.println("\nTecle enter sem digitar nada para sair.");
		System.out.print("Novo nome: ");
		String nome = Program.sc.nextLine();
		if (nome.isBlank()) {
			return;
		}
		contato.setNome(nome);
	}

	private static void editarSobrenome(Contato contato) {
		System.out.println("\nTecle enter sem digitar nada para sair.");
		System.out.print("Novo sobrenome: ");
		String sobrenome = Program.sc.nextLine();
		if (sobrenome.isBlank()) {
			return;
		}
		contato.setSobrenome(sobrenome);
	}

	private static void editarTelefones(Contato contato) {
		System.out.println(
				"\nVocê será redirecionado para escolher os telefones.");
		byte opcao = 4;

		while (true) {
			System.out.print("""
					
					>>>> Menu <<<<
					1 - Editar telefone(s)
					2 - Deletar telefone(s)
					3 - Adicionar telefone(s)
					4 - Sair
					>""");
			try {
				opcao = Program.sc.nextByte();
				Program.sc.nextLine();
			} catch (InputMismatchException e) {
				continue;
			}

			switch (opcao) {
				case 1 :
					editarTelefone(contato);
					break;
				case 2 :
					deletarTelefone(contato);
					break;
				case 3 :
					adicionarTelefone(contato);
					break;
				case 4 :
					return;
				default :
					continue;
			}

		}

	}

	private static void editarTelefone(Contato contato) {
		while (true) {
			System.out.println("\n>>>> Telefones <<<<");
			contato.getTelefones()
					.forEach(t -> System.out.println("%2d - %s".formatted(t.getId(), t)));

			System.out.println("\nTecle enter sem digitar nada para sair.");
			System.out.print("Id do telefone para editar: ");
			String idString = Program.sc.nextLine();
			if (idString.isBlank()) {
				return;
			}
			Optional<Telefone> optionalTelefone = contato
					.getTelefone(Long.valueOf(idString));

			if (optionalTelefone.isEmpty()) {
				System.out.println("Telefone não encontrado, tente novamente.");
				continue;
			}

			System.out.println(
					"Digite o número no seguinte formato: 44 912345678");
			System.out.println("Tecle enter sem digitar nada para sair.");
			Telefone telefone = optionalTelefone.get();
			while (true) {
				try {
					System.out.print("> ");
					String string = Program.sc.nextLine();
					if (string.isBlank()) {
						break;
					}
					String[] strings = string.split(" ");
					String ddd = strings[0];
					String numero = strings[1];

					telefone.setDdd(ddd);
					telefone.setNumero(numero);
					break;
				} catch (ArrayIndexOutOfBoundsException
						| NumberFormatException e) {
					System.out.println("Formato inválido, tente novamente.");
					continue;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void deletarTelefone(Contato contato) {
		while (true) {
			List<Telefone> telefones = contato.getTelefones();
			System.out.println("\n>>>> Telefones <<<<");
			telefones.forEach(t -> System.out.println("%2d - %s".formatted(t.getId(), t)));

			System.out.println("\nTecle enter sem digitar nada para sair.");
			System.out.print("Id do telefone para deletar: ");
			String idString = Program.sc.nextLine();
			if (idString.isBlank()) {
				return;
			}
			boolean removeu = telefones
					.removeIf(t -> t.getId() == Long.valueOf(idString));

			if (!removeu) {
				System.out.println("Telefone não encontrado, tente novamente.");
			}
		}
	}

	static void adicionarTelefone(Contato contato) {
		System.out.println(
				"Digite os números, um por linha, no seguinte formato: 44 912345678");
		System.out
				.println("Dê enter quando não quiser adicionar mais números.");
		while (true) {
			System.out.print("> ");
			String telefone = Program.sc.nextLine();
			if (telefone.isBlank())
				break;
			try {
				String[] strings = telefone.split(" ");
				String ddd = strings[0];
				String numero = strings[1];

				contato.addTelefone(Program.telefoneGerador.gerar(ddd, numero));
			} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
				System.out.println("Formato inválido, tente novamente.");
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
