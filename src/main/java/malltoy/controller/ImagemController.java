package malltoy.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import malltoy.model.dao.ImagemDao;
import malltoy.model.entity.Imagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@Controller
@RequestMapping("/imagem")
public class ImagemController {
	
	private static String folder = "c://temp//"; //pasta de destino das imagens salvas
	
	@RequestMapping("/imagemupload") //pagina de upload de imagens
	public String index() {
		return "ImagemUpload";
	}
	
	@GetMapping("/ImagemUpload/{imPrCodigo}") 
	public String imagemUploadPeloPrCodigo(@PathVariable("imPrCodigo") Long imPrCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
		//busca o curso pelo id
		Imagem imagem = dao.buscarPorPrCodigo(imPrCodigo);
		
		//cria o atributo com objeto preenchido do curso, pra passar pra iew
		model.addAttribute("imagem", imagem);
		
		return "/imagem/imagemCad";
	}
	
	@RequestMapping("/fileUploadView")
	public String fileUploadView() {
		return "fileUploadView";
	}
		
	@PostMapping("/salvarJpg")
	public String submit(@RequestParam("file") MultipartFile file,ModelMap model) {
	    //model.addAttribute("file", file);
		
		if(file.isEmpty()) {
			
		}		
		try {
			byte[] bt = file.getBytes();
			Path path = Paths.get(folder+file.getOriginalFilename());
			Files.write(path,bt);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return "fileUploadView";
	}
	
	
	
	
	
	@Autowired
	private ImagemDao dao;
	
	@GetMapping("/imagemLista")
	public String listar(ModelMap model) {
		List<Imagem> imagens = dao.buscarTodasImagemPeloPrCodigo(); //obtendo lista de cursos
		
		//passando lista com o atributo para a view
		model.addAttribute("imagens",imagens);
		
		return "/imagem/imagemLista";
	}
	
	@GetMapping("/imagemCad")
	public String preCadastrar(Imagem imagem) {
		return "imagem/imagemCad";
	}
	
	@PostMapping("/salvar")
	public String salvarCadastro(Imagem imagem) {
		
		dao.salvar(imagem);
		return "redirect:/imagem/imagemCad"; //usando redirect, o metodo chamará uma nova view, se não usar, ela virá preenchida
	}
	
	@GetMapping("/editar/{imCodigo}")//esse {id} pega o id do objeto clicado na lista pelo botão 'editar'
	public String preEditar(@PathVariable("imCodigo") Long imCodigo, ModelMap model) { //@PathVariable("id") esta indicando que o valor chamado 'id' vindo da view vai ser atribuido no Long id do parametro
		//busca o curso pelo id
		Imagem imagem = dao.buscarPorId(imCodigo);
		
		//cria o atributo com objeto preenchido do curso, pra passar pra iew
		model.addAttribute("imagem", imagem);
		
		return "/imagem/imagemCad";
	}
	
	@PostMapping("editar")
	public String salvarEdicao(Imagem imagem) {
		
		dao.atualizar(imagem);
		
		return "redirect:/imagem/imagemLista";
	}
	
	@GetMapping("/excluir/{imCodigo}")
	public String excluir(@PathVariable("imCodigo") Long imCodigo) {
		
		dao.delete(imCodigo);
		
		return "redirect:/imagem/imagemLista";
	}
}
