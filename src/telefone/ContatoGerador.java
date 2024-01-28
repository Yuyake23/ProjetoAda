package telefone;

import java.io.Serial;
import java.io.Serializable;

public class ContatoGerador implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private static ContatoGerador contatoGerador;

	private Long nextId;

	private ContatoGerador() {
		this.nextId = 0L;
	}

	public static ContatoGerador getInstance() {
		if (contatoGerador == null) {
			contatoGerador = new ContatoGerador();
		}
		return contatoGerador;
	}

	public Contato gerar(String nome, String sobrenome,
			Telefone... telefones) {
		return new Contato(nextId++, nome, sobrenome, telefones);
	}

	public Contato gerar() {
		return new Contato(nextId++);
	}

}
