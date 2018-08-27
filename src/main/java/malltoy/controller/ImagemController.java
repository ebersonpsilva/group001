package malltoy.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import malltoy.model.dao.ImagemDao;
import malltoy.model.dao.ProdutoDao;
import malltoy.model.entity.Imagem;
import malltoy.model.entity.Produto;


@Controller
@RequestMapping("/images")
public class ImagemController {
	
	public static final String TOKEN_SEPARATOR		= "\t";
	public static final String SEP					= System.getProperty("file.separator");
	
	private static final String DIRBASE				= System.getProperty("user.dir");
	private static final String DIRRES				= SEP+"src"+SEP+"main"+SEP+"resources"+SEP+"static"; 
	private static final String IMGALUNOS			= SEP+"images"+SEP+"alunos";

	@Autowired
	private ImagemDao dao;
	
	@Autowired
	private ProdutoDao produtoDao;

	@GetMapping("/lista")
	public String listar(ModelMap model) {
		List<Imagem> imagem = dao.buscarTodasImagemPeloPrCodigo();
		
		model.addAttribute("imagens",imagem);
		return "/public/admin/images/lista";
	}
	
	@GetMapping("/cadastro")
	public String preCadastrar(Imagem imagem) {		
		return "/public/admin/alunos/cadastro"; 
	}
	
	@PostMapping("/salvar")
	public String salvarImagem(@Valid Imagem imagem, BindingResult bs, @RequestParam("imagem") MultipartFile file, RedirectAttributes attr) {	
		if(bs.hasErrors()) {
			return "/public/admin/images/cadastro";
		}
		
		if(salveImg(file)) {
			imagem.setImHref(DIRBASE+DIRRES+IMGALUNOS+SEP+file.getOriginalFilename());
		}
		
		dao.salvar(imagem);
		attr.addFlashAttribute("message", "Aluno cadastrado com sucesso!");
		
		return "redirect:/images/cadastro";
	}
	
	@GetMapping("/editar/{codigo}")
	public String preEditar(@PathVariable("codigo") Long codigo, ModelMap model) {
		Imagem imagem = dao.buscarPorId(codigo);
		model.addAttribute("imagem", imagem);
		
		return "/public/admin/images/cadastro";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(@Valid Imagem imagem,
			BindingResult bs,
			@RequestParam("imHref") String imHref,
			@RequestParam("imagem") MultipartFile file,
			RedirectAttributes attr) {
		
		if(bs.hasErrors()) {
			return "/public/admin/images/cadastro";
		}

		if(!file.isEmpty()) {
			removeImg(imagem.getImHref());
			if(salveImg(file)) {
				imagem.setImHref(DIRBASE+DIRRES+IMGALUNOS+SEP+file.getOriginalFilename());
			}
		}
		
		dao.atualizar(imagem);
		attr.addFlashAttribute("message","Aluno atualizado com sucesso!");
		
		return "redirect:/images/lista";
	}
	
	@GetMapping("/excluir/{codigo}")
	public String excluir(@PathVariable("codigo") Long codigo) {
		Imagem imagem = dao.buscarPorId(codigo);
		removeImg(imagem.getImHref());
		dao.delete(codigo);			
		return "redirect:/images/lista";
	}

	@ModelAttribute("listaProduto")
	public List<Produto> getListaProdutos(){
		return produtoDao.buscarTodos();
	}

	private boolean salveImg(MultipartFile file) {
		if(file.isEmpty()) {
			return false;
		}		
		try {
			byte[] bt = file.getBytes();
			Path path = Paths.get(DIRBASE+DIRRES+IMGALUNOS+SEP+file.getOriginalFilename());
			Files.write(path,bt);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}
	
	private boolean removeImg(String file) {
		File arqFoto = new File(file);
		if (arqFoto != null && arqFoto.exists()) {
			arqFoto.delete();
			return true;
		}
		return false;
	}
}



//@GetMapping("/ImagemUpload/{imPrCodigo}") 
//public String imagemUploadPeloPrCodigo(@PathVariable("imPrCodigo") Long imPrCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
//	//busca o curso pelo id
//	Imagem imagem = dao.buscarPorPrCodigo(imPrCodigo);
//	
//	//cria o atributo com objeto preenchido do curso, pra passar pra iew
//	model.addAttribute("imagem", imagem);
//	
//	return "/public/admin/images/cadastro";
//}

//@RequestMapping("/fileUploadView")
//public String fileUploadView() {
//	return "/public/admin/images/fileUploadView";
//}
	
//@PostMapping("/salvarJpg")
//public String submit(@RequestParam("file") MultipartFile file,ModelMap model) {
//    //model.addAttribute("file", file);
//	
//	if(file.isEmpty()) {
//		
//	}		
//	try {
//		byte[] bt = file.getBytes();
//		Path path = Paths.get(folder+file.getOriginalFilename());
//		Files.write(path,bt);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//    return "/public/admin/images/fileUploadView";
//}

//@PostMapping("/salvar")
//public String salvarCadastro(Imagem imagem) {
//	
//	dao.salvar(imagem);
//	return "redirect:/images/cadastro"; //usando redirect, o metodo chamará uma nova view, se não usar, ela virá preenchida
//}
//
//@GetMapping("/editar/{imCodigo}")//esse {id} pega o id do objeto clicado na lista pelo botão 'editar'
//public String preEditar(@PathVariable("imCodigo") Long imCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
//	//busca o curso pelo id
//	Imagem imagem = dao.buscarPorId(imCodigo);
//	
//	//cria o atributo com objeto preenchido do curso, pra passar pra iew
//	model.addAttribute("imagem", imagem);
//	
//	return "/public/admin/images/cadastro";
//}
//
//@PostMapping("editar")
//public String salvarEdicao(Imagem imagem) {
//	
//	dao.atualizar(imagem);
//	
//	return "redirect:/images/imagemLista";
//}
//
//@GetMapping("/excluir/{imCodigo}")
//public String excluir(@PathVariable("imCodigo") Long imCodigo) {
//	
//	dao.delete(imCodigo);
//	
//	return "redirect:/images/imagemLista";
//}
//