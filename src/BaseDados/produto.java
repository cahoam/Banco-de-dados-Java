package BaseDados;


public class produto extends unidadeMedida{
	private String descricao;
	private float preco;
	private long codigo;
	
	
	public produto(String descricaoMedida, long codigoMedida, String descricao, float preco, long codigo) {
		super(descricaoMedida, codigoMedida);
		this.descricao = descricao;
		this.preco = preco;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
}
