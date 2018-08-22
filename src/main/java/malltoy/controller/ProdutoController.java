package malltoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import malltoy.model.dao.ProdutoDao;
import malltoy.model.entity.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoDao dao;
	
	@GetMapping("/lista")
	public String listar(ModelMap model) {
		List<Produto> produto = dao.buscarTodos();
		model.addAttribute("produtos",produto);
		
		return "/produtos/lista";
	}
	
	@GetMapping("/cadastro")
	public String preCadastrar(Produto produto) {
		return "produtos/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvarCadastro(Produto produto) {
		
		dao.salvar(produto);
		return "redirect:/produtos/cadastro";
	}
	
	@GetMapping("/editar/{prCodigo}")
	public String preEditar(@PathVariable("prCodigo") Long prCodigo, ModelMap model) {
		Produto produto = dao.buscarPorId(prCodigo);
		model.addAttribute("produto", produto);
		
		return "/produtos/cadastro";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(Produto produto) {
		dao.atualizar(produto);
		
		return "redirect:/produtos/lista";
	}
	
	@GetMapping("/excluir/{prCodigo}")
	public String excluir(@PathVariable("prCodigo") Long prCodigo) {
		
		dao.delete(prCodigo);
		return "redirect:/produtos/produtoLista";
	}
	
}
