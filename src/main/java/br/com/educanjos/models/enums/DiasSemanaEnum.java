package br.com.educanjos.models.enums;

import org.springframework.http.HttpStatus;

import br.com.educanjos.infra.handler.model.ExceptionEducanjosApi;

public enum DiasSemanaEnum {

	DOMINGO("0", "Domingo"),
	SEGUNDA("1", "Segunda"),
	TERCA("2", "Terça"),
	QUARTA("3", "Quarta"),
	QUINTA("4", "Quinta"),
	SEXTA("5", "Sexta"),
	SABADO("6", "Sábado");
	
	private String valor;
	private String descricao;
	
	private DiasSemanaEnum(String valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public static DiasSemanaEnum get(String valor) {
		for(DiasSemanaEnum dia : DiasSemanaEnum.values()) {
			if(dia.valor.equals(valor)) {
				return dia;
			}
		}
		throw new ExceptionEducanjosApi(HttpStatus.BAD_REQUEST, "VALIDATION-6", "valor", DiasSemanaEnum.values());
	}
}
