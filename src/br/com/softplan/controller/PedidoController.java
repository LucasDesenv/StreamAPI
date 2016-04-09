package br.com.softplan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.softplan.model.dao.PedidoDAO;
import br.com.softplan.model.domain.Categoria;
import br.com.softplan.model.domain.Item;
import br.com.softplan.model.domain.Pedido;

public class PedidoController {

	public void salvarPedidoDeLimpeza(Pedido pedido) {
		List<Item> itens = pedido.getItens();
		List<Item> itensDeLimpeza = itens.stream().filter(item -> item.isProdutoDeLimpeza()).collect(Collectors.toList());
		pedido.setItens(itensDeLimpeza);
		new PedidoDAO().salvar(pedido);
	}
	
	public boolean todosItensSaoDe(List<Item> itens, Categoria categoria){
		return itens.stream().allMatch(item -> item.getCategoria().equals(categoria));
	}
	
	public boolean nenhumItemEstaSemValor(List<Item> itens){
		return itens.stream().noneMatch(item -> item.getVlUnitario() == null || item.getVlUnitario() == 0);
	}
	
	public boolean existeItemSuperiorAoValorDeReferencia(List<Item> itens , Double valorReferencia){
		return itens.stream().anyMatch(item -> item.getVlUnitario() > valorReferencia);
	}
	
	
	public Item buscarItemMaisBarato(List<Item> itens){
		itens.sort((item1,item2) -> item1.getVlUnitario().compareTo(item2.getVlUnitario()));
		return itens.stream().findFirst().get();
	}
	
	public void aplicarDescontoEmAlgumItemDeLimpeza(List<Item> itens, Double desconto){
		Item item = itens.stream().filter(item2 -> item2.isProdutoDeLimpeza()).findAny().get();
		item.aplicarDesconto(desconto);
	}
	
	public static void main(String[] args) {
		PedidoController pedidoController = new PedidoController();
		Pedido pedido = new PedidoDAO().buscarPedido(1L);
		pedidoController.salvarPedidoDeLimpeza(pedido);
		List<Item> itens = pedido.getItens();
		System.out.println(itens);
		System.out.println(pedidoController.buscarItemMaisBarato(itens));
		pedidoController.aplicarDescontoEmAlgumItemDeLimpeza(itens, 1.99);
		System.out.println(itens);
	}
}
