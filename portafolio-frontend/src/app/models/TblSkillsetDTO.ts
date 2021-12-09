import { Byte } from "@angular/compiler/src/util";

export class TblSkillsetDTO {

  // ID DE SKILLSET
  idSkillset: number;

  // NOMBRE DE SKILLSET
  noSkillset: string;

  // FOTO O IMAGEN DE SKILLSET
  fotoSkillset: string;

  // NOMBRE ORIGINAL DE LA IMAGEN
  filename: String;

  // TIPO DE IMAGEN
  filetype: String;

  // NOMBRE DE LA IMAGEN
  value: Byte[];

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
