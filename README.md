# Sistema de Gestión en Base a Microservicios — Book Point

Este repositorio contiene el desarrollo del sistema distribuido de gestión de inventario, biblioteca y ventas para la empresa **Book Point**.La plataforma está diseñada bajo una arquitectura de microservicios, aislando las responsabilidades del negocio en componentes independientes y desacoplados para garantizar una alta disponibilidad, seguridad y escalabilidad.

##  Arquitectura y Tecnologías
* **Backend:** Java con Spring Boot.
* **Persistencia:** JPA / Hibernate para el mapeo de entidades.
* **Base de Datos:** MySQL (un motor independiente por cada microservicio).
* **Herramientas de Productividad:** Lombok (reducción de código repetitivo) y Maven (gestor de dependencias). 
* **Validaciones:** Spring Validation (`@NotNull`, `@NotBlank`, etc.) para asegurar la integridad de las peticiones.

---

##  Mapa de Ecosistema de Microservicios 

* **Catálogo:** Puerto `8082` 
* **Carrito:** Puerto `8083` 
* **Pedidos:** Puerto `8081` 
* **Reseñas:** Puerto `8084` 
* **Favoritos:** Puerto `8085` 
* **Cupones:** Puerto `8087` 
* **Wishlist:** Puerto `8086` 
* **Usuario:** Puerto `8090` 
* **Boleta:** Puerto `8091` 
* **Anuncios:** Puerto `8092` 

---

## ⚙️ Instrucciones de Despliegue Local

Para levantar el ecosistema completo en tu entorno de desarrollo, sigue estos pasos:

1. **Base de Datos:** Abre **XAMPP** e inicia el servicio de **MySQL**Asegúrate de tener creadas de antemano las bases de datos correspondientes a cada módulo.
2. **Configuración:** Verifica las credenciales de acceso locales dentro de la configuración de cada microservicio.
3. **Orden de Arranque Recomendado (vía Postman/IDE):**
   * Levantar primero el servicio núcleo: `codigoms_catalogo` (Puerto 8082).
   * Levantar el resto de módulos de forma paralela conforme al orden de puertos y pruebas estipulado.
   
   
(EN CASO DE QUE VISUAL STUDIO CODE NO DETECTE EL MAIN DE ALGÚN MICRO SERVICIO IR A LA CARPETA RAIZ DEL MICRO SERVICIO Y EJECUTAR EN LA TERMINA mvn spring-boot:run)
