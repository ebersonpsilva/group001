package malltoy.model.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Categoria")
public class Categoria {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ctCodigo;
	
	@NotBlank
	private String ctDescricao;

	private Timestamp ctDtCadastro;
		
	private boolean ctSatus;
	
	@OneToMany(mappedBy="categoria") //mapeada para uma variavel chamada 'categoria' que esta na classe Produto
	private List<Produto> produtos;

	//getters e setters
	public Long getCtCodigo() {
		return ctCodigo;
	}

	public void setCtCodigo(Long ctCodigo) {
		this.ctCodigo = ctCodigo;
	}

	public String getCtDescricao() {
		return ctDescricao;
	}

	public void setCtDescricao(String ctDescricao) {
		this.ctDescricao = ctDescricao;
	}

	public Timestamp getCtDtCadastro() {
		return ctDtCadastro;
	}

	public void setCtDtCadastro(Timestamp ctDtCadastro) {
		this.ctDtCadastro = ctDtCadastro;
	}

	public boolean isCtSatus() {
		return ctSatus;
	}

	public void setCtSatus(boolean ctSatus) {
		this.ctSatus = ctSatus;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}	
	
}
