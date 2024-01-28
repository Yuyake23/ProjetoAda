package telefone;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Contato implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private final Long id;
	private String nome;
	private String sobrenome;
	private List<Telefone> telefones;

	protected Contato(Long id) {
		this.id = id;
		this.telefones = new ArrayList<>(1);
	}

	protected Contato(Long id, String nome, String sobrenome,
			Telefone... telefones) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefones = Arrays.asList(telefones);
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		return nome + " " + sobrenome;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public Optional<Telefone> getTelefone(Long id) {
		return telefones.stream().filter(t -> t.getId() == id).limit(1)
				.findFirst();
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void addTelefone(Telefone telefone) {
		telefones.add(telefone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("\n>>>> Contato <<<<");
		sb.append("\nNome: " + nome);
		sb.append("\nSobrenome: " + sobrenome);
		sb.append("\nTelefones: ");
		sb.append("\n    id - (ddd) telefone");
		telefones.forEach(t -> sb.append("\n    %2d - %s%n".formatted(t.getId(), t)));
		return sb.toString();
	}

}
