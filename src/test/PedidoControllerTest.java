package test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import br.com.softplan.controller.PedidoController;
import br.com.softplan.model.dao.PedidoDAO;
import br.com.softplan.model.domain.Categoria;
import br.com.softplan.model.domain.Item;
import br.com.softplan.model.domain.Pedido;

public class PedidoControllerTest {

	@Test
	public void deveSalvarPedidoDeLimpeza() {
		PedidoController pedidoController = new PedidoController();
		Pedido pedido = new PedidoDAO().buscarPedido(1L);
		pedidoController.salvarPedidoDeLimpeza(pedido);

		assertTrue(new PedidoDAO().buscarPedido(1L).getItens().stream().allMatch(Item::isProdutoDeLimpeza));
	}

	@Test
	public void todosItensSaoDeLimpeza() {
		PedidoController pedidoController = new PedidoController();

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

		boolean todosItensSaoDeLimpeza = pedidoController.todosItensSaoDe(Arrays.asList(i33, i44), Categoria.LIMPEZA);

		assertTrue(todosItensSaoDeLimpeza);
	}

	@Test
	public void nenhumItemEstaSemValor() {
		PedidoController pedidoController = new PedidoController();

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

		boolean nenhumItemEstaSemValor = pedidoController.nenhumItemEstaSemValor((Arrays.asList(i33, i44)));

		assertTrue(nenhumItemEstaSemValor);
	}

	@Test
	public void existeItemSemValor() {
		PedidoController pedidoController = new PedidoController();

		Item i33 = new Item();
		i33.setDescricao("Sabão em pó");
		i33.setVlUnitario(null);
		i33.setQuantidade(10L);
		i33.setCategoria(Categoria.LIMPEZA);

		Item i44 = new Item();
		i44.setDescricao("Detergente");
		i44.setVlUnitario(2.99);
		i44.setQuantidade(32L);
		i44.setCategoria(Categoria.LIMPEZA);

		boolean nenhumItemEstaSemValor = pedidoController.nenhumItemEstaSemValor((Arrays.asList(i33, i44)));

		assertFalse(nenhumItemEstaSemValor);
	}

	@Test
	public void existeItemSuperiorAoValorDeReferencia() {
		PedidoController pedidoController = new PedidoController();

		Item i33 = new Item();
		i33.setDescricao("Sabão em pó");
		i33.setVlUnitario(10.98);
		i33.setQuantidade(10L);
		i33.setCategoria(Categoria.LIMPEZA);

		Item i44 = new Item();
		i44.setDescricao("Detergente");
		i44.setVlUnitario(2.99);
		i44.setQuantidade(32L);
		i44.setCategoria(Categoria.LIMPEZA);

		boolean nenhumItemEstaSemValor = pedidoController.existeItemSuperiorAoValorDeReferencia(Arrays.asList(i33, i44),
				10.00);

		assertTrue(nenhumItemEstaSemValor);
	}

	@Test
	public void naoExisteItemSuperiorAoValorDeReferencia() {
		PedidoController pedidoController = new PedidoController();

		Item i33 = new Item();
		i33.setDescricao("Sabão em pó");
		i33.setVlUnitario(9.98);
		i33.setQuantidade(10L);
		i33.setCategoria(Categoria.LIMPEZA);

		Item i44 = new Item();
		i44.setDescricao("Detergente");
		i44.setVlUnitario(2.99);
		i44.setQuantidade(32L);
		i44.setCategoria(Categoria.LIMPEZA);

		boolean nenhumItemEstaSemValor = pedidoController.existeItemSuperiorAoValorDeReferencia(Arrays.asList(i33, i44),
				10.00);

		assertFalse(nenhumItemEstaSemValor);
	}

	@Test
	public void deveAplicarUmRealDeDescontoEmUmProdutoDeLimpeza() {
		PedidoController pedidoController = new PedidoController();

		Item i33 = new Item();
		i33.setDescricao("Sabão em pó");
		i33.setVlUnitario(5.00);
		i33.setQuantidade(10L);
		i33.setCategoria(Categoria.LIMPEZA);

		Item i44 = new Item();
		i44.setDescricao("Video Game");
		i44.setVlUnitario(1708.00);
		i44.setQuantidade(1L);
		i44.setCategoria(Categoria.TECNOLOGIA);

		pedidoController.aplicarDescontoEmAlgumItemDeLimpeza(Arrays.asList(i33, i44), 1.00);

		assertTrue(i33.getVlUnitario().equals(4.00));
		assertTrue(i44.getVlUnitario().equals(1708.00));
	}

	@Test
	public void buscarItemMaisBarato() {
		PedidoController pedidoController = new PedidoController();

		Item i33 = new Item();
		i33.setDescricao("Sabão em pó");
		i33.setVlUnitario(5.00);
		i33.setQuantidade(10L);
		i33.setCategoria(Categoria.LIMPEZA);

		Item i44 = new Item();
		i44.setDescricao("Video Game");
		i44.setVlUnitario(1708.00);
		i44.setQuantidade(1L);
		i44.setCategoria(Categoria.TECNOLOGIA);

		Item itemMaisBarato = pedidoController.buscarItemMaisBarato(Arrays.asList(i33, i44));

		assertEquals(i33, itemMaisBarato);
	}
}
