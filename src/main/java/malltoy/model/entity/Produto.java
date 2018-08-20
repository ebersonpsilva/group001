package malltoy.model.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Produto")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long prCodigo;
	
	@NotBlank
	@Column(nullable=false)
	private String prNome;	
	
	private String prDescricao;
	
	private Timestamp prDtCadastro;	
	
	private double prValor;
	
	private boolean prSatus;
	
	@ManyToOne
	@JoinColumn(name="prCtCodigo")//é a chave estrangeira na tabela aluno	
	private Categoria categoria;

	//getters e setters
	public Long getPrCodigo() {
		return prCodigo;
	}

	public void setPrCodigo(Long prCodigo) {
		this.prCodigo = prCodigo;
	}

	public String getPrNome() {
		return prNome;
	}

	public void setPrNome(String prNome) {
		this.prNome = prNome;
	}

	public String getPrDescricao() {
		return prDescricao;
	}

	public void setPrDescricao(String prDescricao) {
		this.prDescricao = prDescricao;
	}

	public Timestamp getPrDtCadastro() {
		return prDtCadastro;
	}

	public void setPrDtCadastro(Timestamp prDtCadastro) {
		this.prDtCadastro = prDtCadastro;
	}

	public double getPrValor() {
		return prValor;
	}

	public void setPrValor(double prValor) {
		this.prValor = prValor;
	}

	public boolean isPrSatus() {
		return prSatus;
	}

	public void setPrSatus(boolean prSatus) {
		this.prSatus = prSatus;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	
	
}
