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

@Entity
@Table(name="Imagem")
public class Imagem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long imCodigo;
	
	private String imTitle;
	
	private String imHref;
	
	@Column(nullable=false)
	private boolean imStatus;
	
	private Timestamp imDtCadastro;
	
	//atributo usado como foreign key
	@ManyToOne
	@JoinColumn(name="imPrCodigo")
	private Produto produto;	
	
	//atributo usado temporariamente, quando estiver pronto, use a variavel comentada acima
	//private long produto;
	
	//getter e setters
	public Long getImCodigo() {
		return imCodigo;
	}

	public void setImCodigo(Long imCodigo) {
		this.imCodigo = imCodigo;
	}

	public String getImTitle() {
		return imTitle;
	}

	public void setImTitle(String imTitle) {
		this.imTitle = imTitle;
	}

	public String getImHref() {
		return imHref;
	}

	public void setImHref(String imHref) {
		this.imHref = imHref;
	}

	public boolean isImStatus() {
		return imStatus;
	}

	public void setImStatus(boolean imStatus) {
		this.imStatus = imStatus;
	}

	public Timestamp getImDtCadastro() {
		return imDtCadastro;
	}

	public void setImDtCadastro(Timestamp imDtCadastro) {
		this.imDtCadastro = imDtCadastro;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}	
	
}
