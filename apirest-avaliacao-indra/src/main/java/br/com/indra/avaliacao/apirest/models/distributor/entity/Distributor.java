package br.com.indra.avaliacao.apirest.models.distributor.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.banner.entity.Banner;
import br.com.indra.avaliacao.apirest.models.city.entity.City;
import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "distributor")
@EntityListeners(AuditingEntityListener.class)
public class Distributor  extends AbstractModel implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2625624473845971943L;

	@NotNull
	@Size(max = 120)
	@Column(name = "name", nullable = false, length = 120)
	private String name;
	
	@NotNull
	@Size(max = 14)
	@Column(name = "cnpj", nullable = false, length = 14, unique = true)
	private String cnpj;
	
    @JoinColumn(name="cityId", nullable = false, referencedColumnName = "id", 
			foreignKey = @ForeignKey(name = "FK_DISTRIBUTOR_city"))
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private City city;  
	
    @JoinColumn(name="bannerId", nullable = false, referencedColumnName = "id", 
			foreignKey = @ForeignKey(name = "FK_DISTRIBUTOR_banner"))
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Banner banner;  
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getCnpj() {
	  return cnpj;
    }
   
    public void setCnpj(String cnpj) {
	 this.cnpj = cnpj;
    }
    
    public Banner getBanner() {
		return banner;
	}
    
    public void setBanner(Banner banner) {
		this.banner = banner;
	}
    

    public City getCity() {
		return city;
	}
    
    public void setCity(City city) {
		this.city = city;
	}
    
	public void setDistributor(Distributor domainObject) {
		this.name    = domainObject.getName();
		this.cnpj    = domainObject.getCnpj();
		this.banner  = domainObject.getBanner();
//		this.products= domainObject.getProducts();
	}
	
	public Distributor() {
		// TODO Auto-generated constructor stub
	}
	
	public Distributor(String cnpj) {
		super();
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Distributor [name=" + name + ", cnpj=" + cnpj + ", banner="+ banner + "]"; //+ ", products="+ products + "]";
	}
	

}
