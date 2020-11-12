package com.cesario.pontointeligente.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.cesario.pontointeligente.enums.TipoEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "lancamento")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Lancamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "localizacao", nullable = false)
	private String localizacao;
	
	@Column(name = "data_criacao", nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = false)
	private Date dataAtualizacao;
	
	@Enumerated(EnumType.STRING)
	private TipoEnum tipo;
	
	@Column(name = "funcionario", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Funcionario funcionario;
	
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao=new Date();
	}
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}	

}
