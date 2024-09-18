package com.cursomvc.springprojeto.models.dto;

import java.math.BigDecimal;

import com.cursomvc.springprojeto.models.enums.StatusProfessor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostProfessorDTO {
	
	@NotBlank	//NotBlank.postProfessorDTO.nome
	@NotNull
	private String nome;
	
	@NotNull
	@DecimalMin(value = "0.1")	// valor minimo do bigdecimal
	private BigDecimal salario;
	
	private StatusProfessor statusProfessor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public StatusProfessor getStatusProfessor() {
		return statusProfessor;
	}
	public void setStatusProfessor(StatusProfessor statusProfessor) {
		this.statusProfessor = statusProfessor;
	}
	
	@Override
	public String toString() {
		return "PostProfessorDTO [nome=" + nome + ", salario=" + salario + ", statusProfessor=" + statusProfessor + "]";
	}
	
	
}
