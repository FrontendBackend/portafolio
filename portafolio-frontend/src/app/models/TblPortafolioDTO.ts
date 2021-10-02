export class TblPortafolioDTO {

  // CAMPO IDENTIFICADOR DEL PORTAFOLIO
  idPortafolio: number;

  // CAMPO NOMBRE DE PORTAFOLIO
  noPortafolio: string;

  // CAMPO DESCRIPCIÓN DEL PORTAFOLIO
  dePortafolio: string;

  // CAMPO IMAGEN DEL PORTAFOLIO
  imgPortafolio: string;

  // CAMPO ESTADO DEL REGISTRO. LOS POSIBLES VALORES SON: "1" = ACTIVO Y "0" = INACTIVO
  esRegistro: string;

  // CAMPO USUARIO CREADOR
  usCreacion: string;

  // CAMPO TERMINAL DE CREACIÓN
  ipCreacion: string;

  // CAMPO FECHA Y HORA DE CREACIÓN
  feCreacion: Date;

  // CAMPO USUARIO MODIFICADOR
  usActualizacion: string;

  // CAMPO TERMINAL DE MODIFICACIÓN
  ipActualizacion: string;

  // CAMPO FECHA Y HORA DE MODIFICACIÓN
  feActualizacion: Date;
}
