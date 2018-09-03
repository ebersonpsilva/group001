package malltoy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import malltoy.model.dao.CategoriaDao;
import malltoy.model.dao.ProdutoDao;
import malltoy.model.entity.Categoria;
import malltoy.model.entity.Produto;

@Controller
@RequestMapping("/produtos")
public class ProdutoController{

	@Autowired
	private ProdutoDao dao;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@GetMapping("/lista")
	public String listar(ModelMap model) {
		List<Produto> produto = dao.buscarTodos();
		model.addAttribute("produtos",produto);
		
		return "/public/admin/produtos/lista";
	}
	
	@GetMapping("/cadastro")
	public String preCadastrar(Produto produto) {
		return "/public/admin/produtos/cadastro";
	}
	
	@PostMapping("/salvar")
	public String salvarCadastro(@Valid Produto produto, BindingResult bs, RedirectAttributes attr) {
		
		if(bs.hasErrors()) {	 
			return "/public/admin/produtos/cadastro";	 
		}
		
		dao.salvar(produto);
		attr.addFlashAttribute("message", "Produto cadastrado com sucesso!");
		return "redirect:/produtos/cadastro";
	}
	
	@GetMapping("/editar/{prCodigo}")
	public String preEditar(@PathVariable("prCodigo") Long prCodigo, ModelMap model) {
		Produto produto = dao.buscarPorId(prCodigo);
		model.addAttribute("produto", produto);
		
		return "/public/admin/produtos/cadastro";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(@Valid Produto produto, BindingResult bs, RedirectAttributes attr) {
		if(bs.hasErrors()) {	 
			return "/public/admin/produtos/cadastro";	 
		}
		
		dao.atualizar(produto);
		attr.addFlashAttribute("message", "Produto atualizado com sucesso!");
		return "redirect:/produtos/lista";
	}
	
	@GetMapping("/excluir/{prCodigo}")
	public String excluir(@PathVariable("prCodigo") Long prCodigo) {
		
		dao.delete(prCodigo);
		return "redirect:/produtos/lista";
	}
	
	@ModelAttribute("listaCategoria")
	public List<Categoria> getListaCategorias(){
		return categoriaDao.buscarTodos();
	}
}
