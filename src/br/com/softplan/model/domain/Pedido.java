package br.com.softplan.model.domain;

import java.util.List;

public class Pedido {

	private String descricao;
	private Long id;
	private List<Item> itens;
	
	public Double getVlTotal() {
		return itens.stream().mapToDouble(item -> item.getVlUnitario() * item.getQuantidade()).sum();
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
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}
	@Override
	public String toString() {
		return "Pedido [descricao=" + descricao + ", itens=" + itens + "]";
	}
	
	
}
