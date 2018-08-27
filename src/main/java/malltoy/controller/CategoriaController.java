package malltoy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import malltoy.model.dao.CategoriaDao;
import malltoy.model.entity.Categoria;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaDao dao;
	
	@GetMapping("/categoriaLista")
	public String listar(ModelMap model) {
		List<Categoria> categoria = dao.buscarTodos(); //obtendo lista de cursos
		
		//passando lista com o atributo para a view
		model.addAttribute("categoria",categoria);
		
		return "/public/admin/categorias/categoriaLista";
	}
	
	@GetMapping("/categoriaCad")
	public String preCadastrar(Categoria categoria) {		
		return "/public/admin/categorias/categoriaCad"; 
	}
	
	@PostMapping("/salvar")
	public String salvarCadastro(@Valid Categoria categoria, BindingResult brs, RedirectAttributes attr) {
		
		if(brs.hasErrors()) {	 
			return "public/admin/categorias/categoriaCad";	 
		}
		
		dao.salvar(categoria);	
		
		attr.addFlashAttribute("message","Categoria salva com sucesso");
		return "redirect:/categorias/categoriaCad"; //usando redirect, o metodo chamará uma nova view, se não usar, ela virá preenchida
	}
	
	@GetMapping("/editar/{ctCodigo}")//esse {id} pega o id do objeto clicado na lista pelo botão 'editar'
	public String preEditar(@PathVariable("ctCodigo") Long ctCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
		//busca o curso pelo id
		
		Categoria categoria = dao.buscarPorId(ctCodigo);		
		 
		model.addAttribute("categoria", categoria);
		
		return "/public/admin/categorias/categoriaCad";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(@Valid Categoria categoria, BindingResult brs, RedirectAttributes attr) {
		if(brs.hasErrors()) {	 
			return "public/admin/categorias/categoriaCad";	 
		}
		
		dao.atualizar(categoria);
		
		return "redirect:/categorias/categoriaLista";
	}
	
	@GetMapping("/excluir/{ctCodigo}")
	public String excluir(@PathVariable("ctCodigo") Long ctCodigo) {
		
		dao.delete(ctCodigo);
		
		return "redirect:/categorias/categoriaLista";
	}
	
}
