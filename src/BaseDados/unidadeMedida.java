package BaseDados;

public class unidadeMedida {
	private String descricaoMedida;
	private long codigoMedida;
	
	public unidadeMedida(String descricaoMedida, long codigoMedida) {
		super();
		this.descricaoMedida = descricaoMedida;
		this.codigoMedida = codigoMedida;
	}

	public String getDescricaoMedida() {
		return descricaoMedida;
	}

	public void setDescricaoMedida(String descricaoMedida) {
		this.descricaoMedida = descricaoMedida;
	}

	public long getCodigoMedida() {
		return codigoMedida;
	}

	public void setCodigoMedida(long codigoMedida) {
		this.codigoMedida = codigoMedida;
	}
	
	
}
