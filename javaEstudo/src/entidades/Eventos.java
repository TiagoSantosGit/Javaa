package entidades;

public class Eventos {

	private Long idEvento;
	private String descricaoEvento;
	private String dataEvento;

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public String getDescricaoEvento() {
		return descricaoEvento;
	}

	public void setDescricaoEvento(String descricaoEvento) {
		this.descricaoEvento = descricaoEvento;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	@Override
	public String toString() {
		return "Eventos [idEvento=" + idEvento + ", descricaoEvento=" + descricaoEvento + ", dataEvento=" + dataEvento
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEvento == null) ? 0 : dataEvento.hashCode());
		result = prime * result + ((descricaoEvento == null) ? 0 : descricaoEvento.hashCode());
		result = prime * result + ((idEvento == null) ? 0 : idEvento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Eventos other = (Eventos) obj;
		if (dataEvento == null) {
			if (other.dataEvento != null)
				return false;
		} else if (!dataEvento.equals(other.dataEvento))
			return false;
		if (descricaoEvento == null) {
			if (other.descricaoEvento != null)
				return false;
		} else if (!descricaoEvento.equals(other.descricaoEvento))
			return false;
		if (idEvento == null) {
			if (other.idEvento != null)
				return false;
		} else if (!idEvento.equals(other.idEvento))
			return false;
		return true;
	}
}
