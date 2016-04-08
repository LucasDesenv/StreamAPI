package br.com.softplan.model.domain;


public class Item {

	private Double vlUnitario;
	private Long quantidade;
	private String descricao;
	private Categoria categoria;
	private Long id;
	
	public Double getVlUnitario() {
		return vlUnitario;
	}
	public void setVlUnitario(Double vlUnitario) {
		this.vlUnitario = vlUnitario;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public boolean isProdutoDeLimpeza(){
		return this.categoria.equals(Categoria.LIMPEZA);
	}

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return "\nItem [vlUnitario=" + vlUnitario + ", quantidade=" + quantidade + ", descricao=" + descricao + "]";
	}
	public void aplicarDesconto(Double desconto) {
		this.vlUnitario = this.vlUnitario - desconto;		
	}
}
