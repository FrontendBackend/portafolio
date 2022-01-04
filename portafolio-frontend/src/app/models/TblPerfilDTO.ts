import { Byte } from '@angular/compiler/src/util';
export class TblPerfilDTO {

  // CAMPO IDENTIFICADOR DE PERFIL
  idPerfil: number;

  // CAMPO NUMERO DE DNI DEL PERFIL
  nuDniPerfil: number;

  // CAMPO NOMBRE DE PERFIL
  noPerfil: string;

  // CAMPO APELLIDOS DEL PERFIL
  apPerfil: string;

  // CAMPO DIRECCIÓN DEL PERFIL
  dirPerfil: string;

  // CAMPO TELEFONO DEL PERFIL
  telPerfil: number;

  // CAMPO CORREO ELECTRONICO DEL PERFIL
  emailPerfil: string;

  // CAMPO FECHA DE NACIMIENTO DEL PERFIL
  feNacimientoPerfil: Date;

  // NOMBRE ORIGINAL DE LA IMAGEN
  imgPerfil: string;

  // TIPO DE IMAGEN
  tipoImg: string;

  // NOMBRE DE LA IMAGEN
  codImg: Byte[];

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
