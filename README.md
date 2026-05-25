# [cite_start]Sistema de Gestión en Base a Microservicios — Book Point 📚 [cite: 30]

[cite_start]Este repositorio contiene el desarrollo del sistema distribuido de gestión de inventario, biblioteca y ventas para la empresa **Book Point**[cite: 30]. [cite_start]La plataforma está diseñada bajo una arquitectura de microservicios, aislando las responsabilidades del negocio en componentes independientes y desacoplados para garantizar una alta disponibilidad, seguridad y escalabilidad[cite: 43].

## 🚀 Arquitectura y Tecnologías
* [cite_start]**Backend:** Java con Spring Boot[cite: 3].
* [cite_start]**Persistencia:** JPA / Hibernate para el mapeo de entidades[cite: 30, 518].
* [cite_start]**Base de Datos:** MySQL (un motor independiente por cada microservicio)[cite: 33, 573].
* [cite_start]**Herramientas de Productividad:** Lombok (reducción de código repetitivo) [cite: 515, 517, 568] [cite_start]y Maven (gestor de dependencias)[cite: 196].
* [cite_start]**Validaciones:** Spring Validation (`@NotNull`, `@NotBlank`, etc.) para asegurar la integridad de las peticiones[cite: 508, 509, 510].

---

## [cite_start]🗺️ Mapa de Ecosistema de Microservicios [cite: 72]

[cite_start]El sistema se compone de módulos satélites independientes que se comunican de forma síncrona en tiempo real utilizando el catálogo central como núcleo de datos[cite: 72, 480, 481, 483]:

| [cite_start]Microservicio [cite: 72] | [cite_start]Puerto [cite: 72] | [cite_start]Base de Datos [cite: 72] | [cite_start]Cliente HTTP [cite: 72] | [cite_start]Entidades Principales [cite: 72] |
| :--- | :---: | :--- | :---: | :--- |
| `codigoms_pedido` | 8081 | `pedido_bd` | FeignClient | Pedido |
| `codigoms_catalogo` | 8082 | `catalogo_bd` | Ninguno | Categoría, Libro |
| `codigoms_carrito` | 8083 | `carrito_bd` | FeignClient | ItemCarrito, Carrito |
| `codigoms_resenas` | 8084 | `resenas_bd` | FeignClient | Resena |
| `codigoms_favoritos` | 8085 | `favoritos_bd` | WebClient | Favorito |
| `codigoms_cupones` | 8086 | `cupones_bd` | FeignClient | Cupón |
| `codigoms_wishlist` | 8086 | `wishlist_bd` | WebClient | Estados WishList, WishList |
| `codigoms_usuario` | 8090 | `usuario_bd` | FeignClient | Usuario |
| `codigoms_boleta` | 8090 | `boleta_bd` | FeignClient | EstadoCompra, MetodoPago, BoletaCompra |
| `codigoms_anuncios` | 8092 | `anuncios_bd` | FeignClient | Anuncio |

---

## [cite_start]🛠️ Decisiones Técnicas Relevantes [cite: 614]

* [cite_start]**Migración a OpenFeign:** Inicialmente se utilizó `WebClient`, pero debido a problemas de inestabilidad y fallos que colapsaban los servicios, se optó por migrar a **Spring Cloud OpenFeign**[cite: 482, 617, 618]. [cite_start]Esto simplificó el desarrollo y agilizó las consultas[cite: 618]. [cite_start]Por motivos de gestión de tiempo, los módulos de `WishList` y `Favoritos` mantuvieron la estructura original en `WebClient`[cite: 619].
* [cite_start]**Control Centralizado de Excepciones:** Se incorporó un componente `GlobalExceptionHandler` con la anotación `@RestControllerAdvice` en los servicios, logrando capturar fallas de sintaxis y validaciones de forma global para entregar respuestas limpias con códigos HTTP estándar[cite: 488, 489].
* [cite_start]**Observabilidad:** Uso de la anotación `@Slf4j` a nivel de capa de servicios (`Service`) para estructurar e imprimir logs de información (`log.info`) y errores transaccionales (`log.error`) en tiempo real[cite: 554, 568].

---

## ⚙️ Instrucciones de Despliegue Local

Para levantar el ecosistema completo en tu entorno de desarrollo, sigue estos pasos:

1. [cite_start]**Base de Datos:** Abre tu panel de **XAMPP** e inicia el servicio de **MySQL**[cite: 573]. [cite_start]Asegúrate de tener creadas de antemano las bases de datos correspondientes a cada módulo[cite: 33, 72].
2. **Configuración:** Verifica las credenciales de acceso locales dentro de la configuración de cada microservicio.
3. **Orden de Arranque Recomendado (vía Postman/IDE):**
   * [cite_start]Levantar primero el servicio núcleo: `codigoms_catalogo` (Puerto 8082)[cite: 480, 574].
   * [cite_start]Levantar el resto de módulos de forma paralela conforme al orden de puertos y pruebas estipulado[cite: 573].

---

## [cite_start]👥 Control de Versiones y Colaboración [cite: 581, 592]
[cite_start]El proyecto se gestionó de manera distribuida y asíncrona mediante Git, aplicando la estrategia **Feature Branching**[cite: 582, 594]. [cite_start]Cada desarrollador trabajó sobre una rama independiente (`feature/`) para programar y testear localmente el microservicio asignado antes de unificar e integrar el código en la rama de pre-producción `develop` y la rama definitiva `main`[cite: 585, 586, 587, 595, 596, 597].
