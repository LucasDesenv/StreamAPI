package br.com.softplan.model.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.softplan.model.domain.Categoria;
import br.com.softplan.model.domain.Item;
import br.com.softplan.model.domain.Pedido;

public class PedidoDAO {

	private static List<Pedido> pedidos;

	static {
		pedidos = new ArrayList<>();
		Pedido p1 = new Pedido();
		p1.setDescricao("Pedido para Área da Secreteria do Estado");
		p1.setId(1L);
		
		Item i1 = new Item();
		i1.setDescricao("Impressora Multifuncional");
		i1.setVlUnitario(280.00);
		i1.setQuantidade(10L);
		i1.setCategoria(Categoria.TECNOLOGIA);

		Item i2 = new Item();
		i2.setDescricao("Cartucho preto e branco");
		i2.setVlUnitario(25.00);
		i2.setQuantidade(400L);
		i2.setCategoria(Categoria.TECNOLOGIA);
		
		Item i33 = new Item();
		i33.setDescricao("Sabão em pó");
		i33.setVlUnitario(8.56);
		i33.setQuantidade(10L);
		i33.setCategoria(Categoria.LIMPEZA);
		
		Item i44 = new Item();
		i44.setDescricao("Detergente");
		i44.setVlUnitario(2.99);
		i44.setQuantidade(32L);
		i44.setCategoria(Categoria.LIMPEZA);
		
		p1.setItens(Arrays.asList(i1, i2,i33,i44));
		
		pedidos.add(p1);
		
		Pedido p2 = new Pedido();
		p2.setDescricao("Pedido para Área Serviços Gerais do Estado");
		p2.setId(1L);
		
		Item i3 = new Item();
		i3.setDescricao("Sabão em líquido 1L");
		i3.setVlUnitario(5.00);
		i3.setQuantidade(1000L);
		i3.setCategoria(Categoria.LIMPEZA);

		Item i4 = new Item();
		i4.setDescricao("Líquido tira manchas");
		i4.setVlUnitario(9.50);
		i4.setQuantidade(75L);
		i4.setCategoria(Categoria.LIMPEZA);
		
		p2.setItens(Arrays.asList(i3, i4));
		
		
		pedidos.add(p2);
	}
	
	public Pedido buscarPedido(Long id){
		return pedidos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
	}
	
	public List<Pedido> buscarTodos(){
		return pedidos;
	}

	public void salvar(Pedido pedido) {
		pedidos.removeIf(p -> p.getId().equals(pedido.getId()));
		pedidos.add(pedido);
	}
}
