package telefone;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Telefone implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private final Long id;
	private String ddd;
	private String numero;

	protected Telefone(Long id) {
		this.id = id;
	}

	protected Telefone(Long id, String ddd, String numero) {
		this.id = id;
		this.ddd = ddd;
		this.numero = numero;
	}

	public Long getId() {
		return id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ddd, numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(ddd, other.ddd)
				&& Objects.equals(numero, other.numero);
	}

	@Override
	public String toString() {
		return "(%3s) %s".formatted(ddd, numero);
	}

}
