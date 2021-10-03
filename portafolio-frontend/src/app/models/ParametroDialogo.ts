import { ETipoAccionCRUD } from '../enums/tipo-accion';

/***
 * 
 */
export class ParametroDialogo<T, U> {

    accion: ETipoAccionCRUD;
    objeto: T;
    objetoReferencia?: U;
    resultado?: string;
    datoAdicional?: any;
}
