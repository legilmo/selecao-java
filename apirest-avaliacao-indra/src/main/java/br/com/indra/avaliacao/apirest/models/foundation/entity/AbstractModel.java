package br.com.indra.avaliacao.apirest.models.foundation.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public abstract class AbstractModel implements IModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -394655476483911221L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "The auto generated identity", required = true)
	private Integer id;
	
	@Column(name = "CREATED_AT", columnDefinition="TIMESTAMP DEFAULT current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date createdAt = new Date();
		
	@Column(name = "UPDATED_AT", columnDefinition="TIMESTAMP DEFAULT current_timestamp")
	@Temporal(TemporalType.TIMESTAMP)	
	@JsonIgnore
	private Date updatedAt = new Date();

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public AbstractModel() {
		super();
	}

	public AbstractModel(Integer id, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;

	}

	@Override
	public IModel cloneModel() {
		try {
			IModel clone = (IModel) super.clone();
			return clone;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
