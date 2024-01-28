package telefone;

import java.io.Serial;
import java.io.Serializable;

public class TelefoneGerador implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private static TelefoneGerador telefoneGerador;

	private Long nextId;

	private TelefoneGerador() {
		this.nextId = 0L;
	}

	public static TelefoneGerador getInstance() {
		if (telefoneGerador == null) {
			telefoneGerador = new TelefoneGerador();
		}
		return telefoneGerador;
	}

	public Telefone gerar(String ddd, String numero) {
		return new Telefone(nextId++, ddd, numero);
	}

	public Telefone gerar() {
		return new Telefone(nextId++);
	}

}
