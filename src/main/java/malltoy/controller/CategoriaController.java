package malltoy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import malltoy.model.dao.CategoriaDao;
import malltoy.model.entity.Categoria;


@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaDao dao;
	
	@GetMapping("/categoriaLista")
	public String listar(ModelMap model) {
		List<Categoria> categoria = dao.buscarTodos(); //obtendo lista de cursos
		
		//passando lista com o atributo para a view
		model.addAttribute("categoria",categoria);
		
		return "/categoria/categoriaLista";
	}
	
	@GetMapping("/categoriaCad")
	public String preCadastrar(Categoria categoria) {		
		return "categoria/categoriaCad"; 
	}
	
	@PostMapping("/salvar")
	public String salvarCadastro(Categoria categoria) {
		dao.salvar(categoria);		
		return "redirect:/categoria/categoriaCad"; //usando redirect, o metodo chamará uma nova view, se não usar, ela virá preenchida
	}
	
	@GetMapping("/editar/{ctCodigo}")//esse {id} pega o id do objeto clicado na lista pelo botão 'editar'
	public String preEditar(@PathVariable("ctCodigo") Long ctCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
		//busca o curso pelo id
		Categoria categoria = dao.buscarPorId(ctCodigo);
		
		//cria o atributo com objeto preenchido do curso, pra passar pra iew
		model.addAttribute("categoria", categoria);
		
		return "/categoria/categoriaCad";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(Categoria categoria) {
		
		dao.atualizar(categoria);
		
		return "redirect:/categoria/categoriaLista";
	}
	
	@GetMapping("/excluir/{ctCodigo}")
	public String excluir(@PathVariable("ctCodigo") Long ctCodigo) {
		
		dao.delete(ctCodigo);
		
		return "redirect:/categoria/categoriaLista";
	}
	
}
