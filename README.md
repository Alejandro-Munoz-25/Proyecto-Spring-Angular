# Proyecto-Spring-Angular
Aplicación web desarrollada con Spring Framework y Angular, por parte del backend se desplegó en la plataforma Heroku y por parte del frontend se desplegó en Firebase .
Esta aplicación consiste en simular un listado de clientes cada uno con algunos datos personales y foto de perfil si es que tienen asignada una foto, estos datos se pueden consultar por medio de un modal desplegado al seleccionar la foto de perfil, además estos clientes pueden tener facturas la cual se puede acceder para ver el contenido de esta.
La aplicación web maneja autenticación y autorización por medio de JWT y cifrado por medio de RSA , si no se está autenticado solo se mostrará la tabla de clientes y para realizar las demás acciones se necesitara autenticación, en el ejemplo se crearon dos usuarios un usuario normal y un usuario administrador.Por parte del usuario normal solo tendrá permisos para observar los datos de los clientes y sus facturas si es que tienen,por parte del usuario administrador tendrá permiso para poder realizar las acciones de CRUD básicas para los clientes(eliminar,crear,editar ,leer), además podrá cambiar la foto de perfil que tengan los clientes ,la cual se almacenará en el Storage de Firebase ,ya que heroku no permite el almacenamiento de archivos. Adicionalmente el administrador sólo podrá crear,eliminar y leer las facturas que tengan los clientes.
Todos los clientes y usuarios se almacenan en la BD PostgreSQL creada en heroku, en el caso de la foto solo se almacena un nombre único ,el cual se genera al momento de subir o editar una imagen. Y la imagen subida se almacena en Firebase.

<a href="https://proyectoangular-587e7.web.app/clientes">Link del Proyecto</a>

User :admin
password:12345

User:user
password:12345
