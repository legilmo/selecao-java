/**
 * 
 */
package br.com.indra.avaliacao.apirest.models.state.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.indra.avaliacao.apirest.models.foundation.entity.AbstractModel;
import br.com.indra.avaliacao.apirest.models.foundation.entity.IModel;
import br.com.indra.avaliacao.apirest.models.region.entity.Region;

/**
 * @author Legilmo Oliveira
 *
 */
@Entity
@Table(name = "state")
@EntityListeners(AuditingEntityListener.class)
public class State extends AbstractModel  implements IModel  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3338794845627153473L;

	@Column(name = "INITIALS", unique = true)
	private String initials;
	
	@Column(name = "NAME")
	private String name;
	
    @JoinColumn(name="regionId", nullable = false, referencedColumnName = "id", 
			foreignKey = @ForeignKey(name = "FK_STATE_region"))
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private Region region;  
	
    public State() {
		super();
	}
    
    public State(String initials) {
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

	public void setState(State state) {
		this.initials = state.getInitials();
		this.name     = state.getName();
		this.region   = state.getRegion();
	}

	public Region getRegion() {
		return region;
	}
	
	public void setRegion(Region region) {
		this.region = region;
	}
	
	
	@Override
	public String toString() {
		return "State [initials=" + initials + ", name=" + name + ", region="+ region + "]";
	}

	
}
