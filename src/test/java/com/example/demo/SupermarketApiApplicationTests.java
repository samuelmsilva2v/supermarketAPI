package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.application.dtos.CategoriaRequestDto;
import com.example.demo.application.dtos.CategoriaResponseDto;
import com.example.demo.application.dtos.ProdutoRequestDto;
import com.example.demo.application.dtos.ProdutoResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SupermarketApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static UUID idCategoriaTeste;
	private static String nomeCategoriaTeste;
	private static UUID idProdutoTeste;
	private static String nomeProdutoTeste;

	@Test
	@Order(1)
	public void criarCategoriaTest() throws Exception {

		var request = new CategoriaRequestDto();
		var faker = new Faker();

		request.setNome(faker.commerce().department());

		MvcResult result = mockMvc.perform(post("/api/categorias").contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, CategoriaResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(request.getNome(), response.getNome());

		idCategoriaTeste = response.getId();
		nomeCategoriaTeste = request.getNome();
	}

	@Test
	@Order(2)
	public void categoriaComNomeDuplicadoTest() throws Exception {

		var request = new CategoriaRequestDto();
		request.setNome(nomeCategoriaTeste);

		MvcResult result = mockMvc
				.perform(post("/api/categorias").contentType("application/json")
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest()).andReturn();

		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertEquals("Já existe uma categoria cadastrada com o nome: " + request.getNome() + ".", content);
	}

	@Test
	@Order(3)
	public void criarProdutoTest() throws Exception {

		var request = new ProdutoRequestDto();
		var faker = new Faker();

		request.setNome(faker.commerce().productName());
		double precoDouble = faker.number().randomDouble(2, 1, 1000);
		request.setPreco(BigDecimal.valueOf(precoDouble));
		request.setQuantidade(10);
		request.setCategoriaId(idCategoriaTeste);

		MvcResult result = mockMvc.perform(
				post("/api/produtos").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ProdutoResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(request.getNome(), response.getNome());
		assertEquals(request.getPreco(), response.getPreco());
		assertEquals(request.getQuantidade(), response.getQuantidade());
		assertEquals(request.getCategoriaId(), response.getCategoria().getId());

		idProdutoTeste = response.getId();
		nomeProdutoTeste = request.getNome();
	}

	@Test
	@Order(4)
	public void produtoComNomeDuplicadoTest() throws Exception {

		var request = new ProdutoRequestDto();
		request.setNome(nomeProdutoTeste);
		request.setPreco(BigDecimal.valueOf(10));
		request.setQuantidade(10);
		request.setCategoriaId(idCategoriaTeste);

		MvcResult result = mockMvc
				.perform(post("/api/produtos").contentType("application/json")
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest()).andReturn();

		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertEquals("Já existe um produto cadastrado com o nome: " + request.getNome() + ".", content);
	}

	@Test
	@Order(5)
	public void produtoComEstoqueTest() throws Exception {

		MvcResult result = mockMvc.perform(delete("/api/produtos/" + idProdutoTeste).contentType("application/json"))
				.andExpect(status().isBadRequest()).andReturn();

		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertEquals("Não é possível excluir o produto '" + nomeProdutoTeste + "' porque ainda possui " + 10
				+ " unidades em estoque.", content);
	}

	@Test
	@Order(6)
	public void editarProdutoTest() throws Exception {

		var request = new ProdutoRequestDto();
		var faker = new Faker();

		request.setNome(faker.commerce().productName());
		double precoDouble = faker.number().randomDouble(2, 1, 1000);
		request.setPreco(BigDecimal.valueOf(precoDouble));
		request.setQuantidade(0);
		request.setCategoriaId(idCategoriaTeste);

		MvcResult result = mockMvc.perform(put("/api/produtos/" + idProdutoTeste).contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ProdutoResponseDto.class);

		assertNotNull(response.getId());
		assertEquals(request.getNome(), response.getNome());
		assertEquals(request.getPreco(), response.getPreco());
		assertEquals(request.getQuantidade(), response.getQuantidade());
		assertEquals(request.getCategoriaId(), response.getCategoria().getId());
	}

	@Test
	@Order(7)
	public void excluirProdutoTest() throws Exception {

		MvcResult result = mockMvc.perform(delete("/api/produtos/" + idProdutoTeste).contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertEquals("Produto excluído com sucesso!", content);
	}

	@Test
	@Order(8)
	public void excluirCategoriaTest() throws Exception {

		MvcResult result = mockMvc
				.perform(delete("/api/categorias/" + idCategoriaTeste).contentType("application/json"))
				.andExpect(status().isOk()).andReturn();

		String content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		assertEquals("Categoria excluída com sucesso!", content);
	}

}
