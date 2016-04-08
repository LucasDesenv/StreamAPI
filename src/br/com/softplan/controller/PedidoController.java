package br.com.softplan.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.softplan.model.dao.PedidoDAO;
import br.com.softplan.model.domain.Categoria;
import br.com.softplan.model.domain.Item;
import br.com.softplan.model.domain.Pedido;

public class PedidoController {

	public void salvarPedidoDeLimpeza(Pedido pedido) {
		List<Item> itens = pedido.getItens();
		List<Item> itensDeLimpeza = new ArrayList<>();
		for (Item item : itens) {
			if (item.isProdutoDeLimpeza()){
				itensDeLimpeza.add(item);
			}
		}
		
		pedido.setItens(itensDeLimpeza);
		new PedidoDAO().salvar(pedido);
	}
	
	public boolean todosItensSaoDe(List<Item> itens, Categoria categoria){
		for (Item item : itens) {
			if (!item.getCategoria().equals(categoria)){
				return false;
			}
		}
		return true;
	}
	
	public boolean nenhumItemEstaSemValor(List<Item> itens){
		for (Item item : itens) {
			if (item.getVlUnitario() == null || item.getVlUnitario() == 0){
				return false;
			}
		}
		return true;
	}
	
	public boolean existeItemSuperiorAoValorDeReferencia(List<Item> itens , Double valorReferencia){
		for (Item item : itens) {
			if (item.getVlUnitario() > valorReferencia){
				return true;
			}
		}
		
		return false;
	}
	
	public void aplicarDescontoEmAlgumItemDeLimpeza(List<Item> itens, Double desconto){
		for (Item item : itens) {
			if (item.isProdutoDeLimpeza()){
				item.aplicarDesconto(desconto);
				break;
			}
		}
	}
	
	public Item buscarItemMaisBarato(List<Item> itens){
		itens.sort((item1,item2) -> item1.getVlUnitario().compareTo(item2.getVlUnitario()));
		return itens.get(0);
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
