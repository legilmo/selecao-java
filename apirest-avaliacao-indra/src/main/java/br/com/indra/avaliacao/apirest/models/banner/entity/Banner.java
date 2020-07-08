/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.banner.entity;

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
@Table(name = "BANNER")
@EntityListeners(AuditingEntityListener.class)
public class Banner extends AbstractModel  implements IModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1785543557994189143L;
	
	
	@NotNull
	@Size(max = 100)
	@Column(name = "name", nullable = false, length = 100, unique = true)
	private String name;
	
		
    public Banner() {
		super();
	}

	public Banner( String name) {
		super();
		
		this.name = name;
	}


	public Integer getId() {
		return super.getId();
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setBanner(Banner banner) {
		this.name = banner.getName();
	}

	
	@Override
	public String toString() {
		return "Banner [name=" + name + "]";
	}

	
}
