## Proyecto-Spring-Angular
<p align="justify">
Aplicación web desarrollada con Spring Framework y Angular, desplegada en heroku y firebase.Esta aplicación consiste en simular un listado de clientes, los cuales pueden ser creados o eliminados según el rol que tenga el usuario, además cuenta con un pequeño sistema de creación de facturas, las cuales pueden ser creadas o eliminadas.
</p>
<p align="justify">
La aplicación web maneja autenticación y autorización por medio de JWT y cifrado por medio de RSA , si no se está autenticado solo se mostrará la tabla de clientes y para realizar las demás acciones se necesitara autenticación, en el ejemplo se crearon dos usuarios un usuario normal y un usuario administrador.Por parte del usuario normal solo tendrá permisos para observar los datos de los clientes y sus facturas si es que tienen,por parte del usuario administrador tendrá permiso para poder realizar las acciones de CRUD básicas para los clientes(eliminar,crear,editar ,leer), además podrá cambiar la foto de perfil que tengan los clientes ,la cual se almacenará en el Storage de Firebase ,ya que heroku no permite el almacenamiento de archivos. Adicionalmente el administrador sólo podrá crear,eliminar y leer las facturas que tengan los clientes.
</p>
<p align="justify">
Todos los clientes y usuarios se almacenan en la BD PostgreSQL creada en heroku. La imagen subida se almacena en Firebase.
</p>
<p align="justify">
<a href="https://proyectoangular-587e7.web.app/clientes">Link del Proyecto</a>
</p>
<ul>
  <li>
    User: admin | Password: 12345
  </li>
  
  <li>
    User: user | Password: 12345
  </li>
</ul>
