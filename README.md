## Proyecto integrador del curso Java Intermedio, de Argentina Programa 4.0.
### Contexto general del proyecto
Una importante empresa de soporte operativo solicita el diseño y desarrollo de un sistema
que le permita la generación y seguimiento de los incidentes que se presentan.
La empresa en cuestión se dedica a brindar soporte operativo sobre distintas aplicaciones
(SAP, Tango, etc.) y sistemas operativos (Windows, MacOS, Linux Ubuntu).
El área de RRHH se encarga de realizar las altas, bajas y modificaciones de los técnicos que se
encargan de resolver los incidentes reportados.
Cada técnico tiene una o varias especialidades y solo se le pueden asignar incidentes que
coincidan con las mismas.
El área comercial es responsable de incorporar nuevos clientes a la empresa. Administra las
altas, bajas y modificaciones de los datos de cada uno de ellos.
Finalmente, la mesa de ayuda es responsable de atender las llamadas e ingresar al sistema los
incidentes reportados.
### Ciclo de vida de un incidente
- Cuando un cliente llama, la mesa de ayuda le solicita los datos para identificarlo (razón social,
CUIT) y los ingresa en el sistema para que el mismo le muestre los servicios que el cliente
tiene contratados.
- El operador (de la mesa de ayuda) solicita que le informen por cuál de esos servicios desea
reportar un incidente, junto con una descripción del problema y el tipo del problema.
- Al ingresar el incidente, el sistema devuelve un listado de técnicos disponibles para resolver el
problema. El operador selecciona uno de los técnicos disponibles y el sistema le informa el
tiempo estimado de resolución. Luego, informa al cliente que el incidente ha sido ingresado y
la fecha posible de resolución.
- Al confirmarse el incidente, el sistema debe enviar una notificación al técnico informándole
que tiene un nuevo incidente para resolver.
