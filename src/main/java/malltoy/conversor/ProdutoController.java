package malltoy.conversor;

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
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoDao dao;
	
	@GetMapping("/produtoLista")
	public String listar(ModelMap model) {
		List<Produto> produto = dao.buscarTodos(); //obtendo lista de cursos
		
		//passando lista com o atributo para a view
		model.addAttribute("produto",produto);
		
		return "/produto/produtoLista";
	}
	
	@GetMapping("/produtoCad")
	public String preCadastrar(Produto produto) {
		return "produto/produtoCad";
	}
	
	@PostMapping("/salvar")
	public String salvarCadastro(Produto produto) {
		
		dao.salvar(produto);
		return "redirect:/produto/produtoCad"; //usando redirect, o metodo chamará uma nova view, se não usar, ela virá preenchida
	}
	
	@GetMapping("/editar/{prCodigo}")//esse {id} pega o id do objeto clicado na lista pelo botão 'editar'
	public String preEditar(@PathVariable("prCodigo") Long prCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
		//busca o curso pelo id
		Produto produto = dao.buscarPorId(prCodigo);
		
		//cria o atributo com objeto preenchido do curso, pra passar pra iew
		model.addAttribute("produto", produto);
		
		return "/produto/produtoCad";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(Produto produto) {
		
		dao.atualizar(produto);
		
		return "redirect:/produto/produtoLista";
	}
	
	@GetMapping("/excluir/{prCodigo}")
	public String excluir(@PathVariable("prCodigo") Long prCodigo) {
		
		dao.delete(prCodigo);
		
		return "redirect:/produto/produtoLista";
	}
	
}
