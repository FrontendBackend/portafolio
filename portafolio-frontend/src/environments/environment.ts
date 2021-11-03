// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,

  HOST: 'http://localhost:9999',

  HOST_IMG: 'http://localhost:9999/images/no-usuario.png',

  // HOST_IMG_UPLOAD: 'http://localhost:9999/api/uploads/img',

  REINTENTOS: 2

  //Comando para ejecutar desde mi celular
  // ng serve --host 0.0.0.0
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
