package telefone;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class Agenda {

	private Set<Contato> contatos;

	public Agenda() {
		this.contatos = new HashSet<>();
	}

	public boolean adicionarContato(Contato contato) {
		Stream<Telefone> telefones = this.contatos.stream()
				.flatMap(c -> c.getTelefones().stream());
		boolean telefoneRepetido = telefones
				.anyMatch(t -> contato.getTelefones().contains(t));

		if (telefoneRepetido) {
			return false;
		}

		this.contatos.add(contato);
		return true;
	}

	public boolean removerContato(Contato contato) {
		return this.contatos.remove(contato);
	}

	public boolean removerContato(Long id) {
		Iterator<Contato> cada = this.contatos.iterator();
		while (cada.hasNext()) {
			if (cada.next().getId() == id) {
				cada.remove();
				return true;
			}
		}
		return false;
	}

	public Optional<Contato> buscarContato(Long id) {
		for (Iterator<Contato> iterator = this.contatos.iterator(); iterator
				.hasNext();) {
			Contato contato = iterator.next();
			if (contato.getId() == id) {
				return Optional.of(contato);
			}
		}
		return Optional.empty();
	}

	public int size() {
		return this.contatos.size();
	}

	@Override
	public String toString() {

		if (this.contatos.isEmpty()) {
			return "A lista de contatos está vazia.";
		}

		StringBuilder sb = new StringBuilder(">>>> Contatos <<<<\n");
		sb.append(" Id | Nome | Números\n");
		this.contatos
				.forEach(c -> sb.append("%3d | %s | %s%n".formatted(c.getId(),
						c.getNomeCompleto(), c.getTelefones().toString())));

		return sb.toString();
	}

}
