package com.portafolio.portafoliobackend.dtos;


import java.util.Date;
import java.util.List;

import com.portafolio.portafoliobackend.utils.FechasUtil;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data	
@NoArgsConstructor
public class ResponseDTO {

	private String status; // success, info, error
	private Object data; //Devuelve el Object
	private String mensaje;//Mensaje
	private List<?> lista;//Retorna la lista siempre y cuando sea Success 	
	private Date timestamp;	//La fecha y hora de consulta o transaccion
	private Long id; //Id del objeto persistido cuando la transaccion es success
	private Number valor; //Devuelve el valor de la consulta

	

	public ResponseDTO(String status, Object data, String mensaje){
		this.status = status;
		this.data = data;
		this.mensaje = mensaje;
	} 

	public ResponseDTO(String status, List<?> lista, String mensaje){
		this.status = status;
		this.lista = lista;
		this.mensaje = mensaje;
	} 

	public ResponseDTO(String status, String mensaje, Long id){
		this.status = status;
		this.mensaje = mensaje;
		this.id = id;
	} 

	public ResponseDTO(String status, String mensaje){
		this.status = status;
		this.mensaje = mensaje;
	} 	
	

	public Date getTimestamp() {
		return timestamp!=null?timestamp:FechasUtil.getToFullDay();
	}
	
	public ResponseDTO(String status, String mensaje, Number valor){
		this.status = status;
		this.mensaje = mensaje;
		this.valor = valor;
	}

}
