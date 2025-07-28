## Product Inventory Lab

<img src="https://drive.google.com/uc?export=view&id=1thzClNb4TVyiJH65o-nbALVfcnIDh1Wd" width="1000"/>

**ProductInventory** es un proyecto (aún en desarrollo) de laboratorio desarrollado con Spring Boot, orientado a practicar y consolidar conceptos técnicos clave como auditoría con JPA, seguridad con SSL, pruebas automatizadas, arquitectura hexagonal, JasperReports y scheduling. El proyecto gestiona un inventario de productos, usuarios y empleados, permitiendo el registro y seguimiento de operaciones con trazabilidad completa.

El proyecto en su culminación debe contar con:

- Arquitectura Hexagonal (Ports/Adapters)
- Auditoría con `@EntityListeners`
- Seguridad SSL con `.jks`
- Integración con JPA y Flyway
- Spring Security + configuración modular
- Testing con JUnit
- JasperReports (opcional)
- Tareas automáticas con `@Scheduled`

### [hexagonal-refactoring] Refactorización a Arquitectura Hexagonal
> Objetivo: Migrar la capa de productos a arquitectura hexagonal

#### Subtareas:
- [x] Crear paquetes `domain/model`, `ports/in`, `ports/out`
- [x] Separar `ProductController` en `input/rest`
- [x] Reintegrar la capabilities `listeners` y `users`
- [x] Integrar capability `common` para configuraciones y esqueleto de los Mappers

---

### [jpa-listeners] Contratos de API + Business Logic
> Objetivo: Realizar lógica de Negocio, con manejo de excepciones

#### Subtareas:
- [x] Crear capa de dominio, para ello, definir los contratos de API:
  - [x] Contrato de Servicio
    - [x] Crear Producto
    - [x] Actualizar Producto
    - [x] Buscar Producto Habilitado por id
    - [x] Buscar Cualquier Producto por id
    - [x] Buscar Producto Habilitado por nombre
    - [x] Buscar Cualquier Producto por nombre
    - [x] Buscar Todos los Productos
    - [x] Buscar Todos los Productos Habilitados
    - [x] Buscar Todos los Productos Deshabilitados
    - [x] Buscar Productos por filtros personalizados
    - [x] Deshabilitar Producto
    - [x] Activar Producto
    - [x] Verificar Stock, que devuelva `enum` en caso de estar debajo del mínimo o ser igual al máximo
  - [x] Contrato de Repositorio
    - [x] Guardar Producto
    - [x] Buscar todos
    - [x] Buscar todos los deshabilitados
    - [x] Buscar todos los habilitados
    - [x] Buscar cualquiera por id
    - [x] Buscar habilitado por id
    - [x] Buscar cualquier por nombre
    - [x] Buscar habilitado por nombre
    - [x] Buscar por Query Personalizado
- [ ] Excepciones Propias
- [ ] Manejar las excepciones mediante `@RestControllerAdvice`
- [ ] Manejar `MessageResponse`
- [ ] Utilizar `CategoryServicePort` en lugar de `CategoryRepositoryPort`

### [jpa-listeners] Implementación de Auditoría Manual con Listeners
> Objetivo: Implementar listener para auditar operaciones sobre productos

#### Subtareas:
- [x] Crear entidad `AuditProductEntity`
- [x] Crear `AuditProductListener` con `@PrePersist`, `@PreUpdate`, `@PreRemove`
- [x] Usar puertos para persistir en lugar de JPA directo
- [ ] Registrar nombre viejo y nuevo del producto mediante Trigger SQL
- [x] Agregar auditoría con usuario InMemoryDetails (provisional)
- [ ] Agregar auditoría de usuario autenticado

### [jpa-listeners] Implementación de Fabricantes
> Objetivo: Implementar modelado, contratos y lógica de negocio para fabricantes

#### Subtareas:
- [ ] Crear scaffolding para `Manufacturers`
- [ ] Crear contratos para `Manufacturers`
- [ ] Implementar la relación `One Manufacturer → Many Products`

### [jpa-listeners] Configuración de Seguridad con SSL
> Objetivo: Configurar HTTPS y políticas básicas de seguridad

#### Subtareas:
- [x] Generar archivo `.jks`
- [x] Configurar `application.properties`
- [x] Crear `SecurityConfig` en `shared/config`
- [x] Forzar HTTPS
- [x] Configurar JWT o BasicAuth

---

### [azure-bs] Implementación de Bucket Blob Storage
> Objetivo: Implementar lógica para Azure Blob Storage

---

### [testing] Testing de capa de aplicación
> Objetivo: Agregar pruebas unitarias e integración para `ProductService`

#### Subtareas:
- [ ] Pruebas unitarias con mocks
  - [ ] Products
    - [ ] Guardar
      - [ ] Con Categoría nula, se le asigne `UNDEFINED`
      - [ ] Con Stock nulo, se le asigne `MIN_STOCK`
      - [ ] Con minStock nulo, se le asigne `MIN_STOCK`
      - [ ] Con maxStock nulo, se le asigne `MAX_STOCK`
      - [ ] Con Fabricante nulo, se le asigne `MANUFACTURER`
      - [ ] Si el Stock introducido es mayor al maxStock o menor que minStock lance sus respectivas excepciones personalizadas
    - [ ] Actualizar
      - [ ] Si el id del `JSON Body` es nulo, se le asigne el de la `URL`
      - [ ] Si el id del `JSON Body` es distinto al de la `URL` se lance una excepción
      - [ ] Si ingresa valores nulos se le asignen los datos ya persistidos a nivel de BD
- [ ] Verificar cobertura del 100%
- [ ] Pruebas de integración con Sonar