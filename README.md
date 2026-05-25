# Sistema de Gestión en Base a Microservicios — Book Point 📚 [cite: 30]

Este repositorio contiene el desarrollo del sistema distribuido de gestión de inventario, biblioteca y ventas para la empresa **Book Point**.La plataforma está diseñada bajo una arquitectura de microservicios, aislando las responsabilidades del negocio en componentes independientes y desacoplados para garantizar una alta disponibilidad, seguridad y escalabilidad[cite: 43].

##  Arquitectura y Tecnologías
* **Backend:** Java con Spring Boot.
* **Persistencia:** JPA / Hibernate para el mapeo de entidades.
* **Base de Datos:** MySQL (un motor independiente por cada microservicio).
* **Herramientas de Productividad:** Lombok (reducción de código repetitivo) y Maven (gestor de dependencias). 
* **Validaciones:** Spring Validation (`@NotNull`, `@NotBlank`, etc.) para asegurar la integridad de las peticiones.

---

##  Mapa de Ecosistema de Microservicios 

El sistema se compone de módulos satélites independientes que se comunican de forma síncrona en tiempo real utilizando el catálogo central como núcleo de datos:
   
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

## ⚙️ Instrucciones de Despliegue Local

Para levantar el ecosistema completo en tu entorno de desarrollo, sigue estos pasos:

1. **Base de Datos:** Abre **XAMPP** e inicia el servicio de **MySQL**Asegúrate de tener creadas de antemano las bases de datos correspondientes a cada módulo.
2. **Configuración:** Verifica las credenciales de acceso locales dentro de la configuración de cada microservicio.
3. **Orden de Arranque Recomendado (vía Postman/IDE):**
   * Levantar primero el servicio núcleo: `codigoms_catalogo` (Puerto 8082).
   * Levantar el resto de módulos de forma paralela conforme al orden de puertos y pruebas estipulado.

