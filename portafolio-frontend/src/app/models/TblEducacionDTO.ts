export class TblEducacionDTO {

  // CAMPO IDENTIFICADOR DE EDUCACION
  idEducacion: number;

  // IDENTIFICADOR INTERNO DEL PERFIL
  idPerfil: number;

  // DESCRIBIR TU EDUCACIÓN
  deEducacion: string;

  // CAMPO ESTADO DEL REGISTRO. LOS POSIBLES VALORES SON: "1" = ACTIVO Y "0" =
  // INACTIVO
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
