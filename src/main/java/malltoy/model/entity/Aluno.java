package malltoy.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long usCodigo;
	
	@NotBlank(message="O nome é obrigatório!")
	@Column(length=150,nullable=false)
	private String usNome;
	
	@NotBlank(message="O e-mail é obrigatório!")
	@Column(length=100,nullable=false)
	private String usEmail;
	
	@Column(length=1, columnDefinition="int default 1", insertable=false)
	private int usStatus;
	
	@Column(columnDefinition="timestamp default CURRENT_TIMESTAMP()")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usDtCadastro;
	
	@NotBlank(message="O RGM é obrigatório!")
	@Column(length=10,nullable=false,unique=true)
	private String usRgm;
	
	@Column(length=150,nullable=true)
	private String usHref;
	
	
	public Long getUsCodigo() {
		return usCodigo;
	}
	public void setUsCodigo(Long usCodigo) {
		this.usCodigo = usCodigo;
	}
	public String getUsNome() {
		return usNome;
	}
	public void setUsNome(String usNome) {
		this.usNome = usNome;
	}
	public String getUsEmail() {
		return usEmail;
	}
	public void setUsEmail(String usEmail) {
		this.usEmail = usEmail;
	}
	public int getUsStatus() {
		return usStatus;
	}
	public void setUsStatus(int usStatus) {
		this.usStatus = usStatus;
	}
	public Date getUsDtCadastro() {
		return usDtCadastro;
	}
	public void setUsDtCadastro(Date usDtCadastro) {
		this.usDtCadastro = usDtCadastro;
	}
	public String getUsRgm() {
		return usRgm;
	}
	public void setUsRgm(String usRgm) {
		this.usRgm = usRgm;
	}
	public String getUsHref() {
		return usHref;
	}
	public void setUsHref(String usHref) {
		this.usHref = usHref;
	}
	
}
