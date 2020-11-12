package com.cesario.pontointeligente.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.cesario.pontointeligente.enums.PerfilEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Funcionario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "nome",nullable = false)
	private String nome;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "senha",nullable = false)
	private String senha;
	
	@Column(name = "cpf",nullable = false)
	private String cpf;
	
	@Column(name = "valor_hora",nullable = true)
	private BigDecimal valorHora;
	
	@Column(name = "horas_trabalhadas",nullable = true)
	private Float qtsHorasTrabalhoDia;
	
	@Column(name = "horas_almocando", nullable = true)
	private Float qtdHorasAlmoco;
	
	@Column(name = "perfil", nullable = false)
	@Enumerated(EnumType.STRING)
	private PerfilEnum perfil;
	
	@Column(name = "data_criacao",nullable = false)
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao",nullable = false)
	private Date dataAtualizacao;
	
	@Column(name = "empresa", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@Column(name = "lancamentos", nullable = true)
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lancamento> lancamentos;
	
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
