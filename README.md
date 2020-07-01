# OrganizadorFiestas
Mi aplicacion esta orientada a un organizador de fiestas donde puedo añadir invitados y despues mirar mi lista, actualizar la lista de invitados o eliminar .
## Explicacion
Tenemos tres clases de java : splash screen , mainactivity y lista.
## Splash screen
en esta almaceno mi pantalla de carga inicial y mediante un metodo establezco el tiempo que quedara en pantalla para despues dirigirme a mi "mainActivity" 
## Main activity
aqui creo las variables y metodos necesarios para el botón agregar el cual añade datos a mi base de datos en firebase como tambien los metodos necesarios para cambiar e iterar entre activitys y metodos de validacion para saber si mis campos estan vacios
## Lista
En esta clase empleo los metodos de eliminar y actualizar para los respectivos botones y mediante las instancias de getters y setters llamo a mis variables para ir seteando, eligo de mi ListView para rellenar campos y actualizar valores, tambien se emplea un metodo para regresar al activity principal
