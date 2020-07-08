/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.region.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "region")
@EntityListeners(AuditingEntityListener.class)
public class Region extends AbstractModel  implements IModel  {


	/**
	 * 
	 */
	private static final long serialVersionUID = 515550527078297012L;

	@Column(name = "INITIALS", unique = true)
	private String initials;
	
	@Column(name = "NAME")
	private String name;
	
		
    public Region() {
		super();
	}
    
    public Region(String initials) {
		super();
		
		this.initials = initials;
	}



	public Integer getId() {
		return super.getId();
	}
	
	public String getInitials() {
		return initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setRegion(Region region) {
		this.initials = region.getInitials();
		this.name = region.getName();
	
	}

   
	
	@Override
	public String toString() {
		return "Region [initials=" + initials + ", name=" + name + "]";
	}

	
}
