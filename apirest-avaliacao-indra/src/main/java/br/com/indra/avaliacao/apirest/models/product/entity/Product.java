/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product extends AbstractModel implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6451589348000007336L;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "name", nullable = false, length = 100, unique = true)
	private String name;
	
	@NotNull
	@Size(max = 100)
	@Column(name = "UnitOfMeasurement", nullable = false, length = 100)
	private String unitOfMeasurement;
		
	public Product() {
		super();
	}

	public Product(String name) {
		super();
		this.name = name;
	}
	
	public void setProduct(Product domainObject) {
		this.name = domainObject.getName();
		this.unitOfMeasurement = domainObject.getUnitOfMeasurement();
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	
}
