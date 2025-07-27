## Product Inventory Lab

<img src="https://drive.google.com/uc?export=view&id=1C452ZKemzSyGFD5j4vHjEGvN8cUjn_Ef" width="1000"/>

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

### [jpa-listeners] Implementación de Auditoría Manual con Listeners
> Objetivo: Implementar listener para auditar operaciones sobre productos

#### Subtareas:
- [x] Crear capa de dominio, para ello, definir los contratos de API:
  - [ ] Contrato de Servicio
    - [ ] Crear Producto
    - [ ] Actualizar Producto
    - [x] Buscar Producto Habilitado por id
    - [x] Buscar Cualquier Producto por id
    - [x] Buscar Producto Habilitado por nombre
    - [x] Buscar Cualquier Producto por nombre
    - [x] Buscar Todos los Productos
    - [x] Buscar Todos los Productos Habilitados
    - [x] Buscar Todos los Productos Deshabilitados
    - [x] Buscar Productos por filtros personalizados
    - [ ] Deshabilitar Producto
    - [ ] Verificar Stock, que devuelva `enum` en caso de estar debajo del mínimo o ser igual al máximo
  - [ ] Contrato de Repositorio
    - [ ] Guardar Producto
    - [ ] Buscar todos
    - [ ] Buscar todos los deshabilitados
    - [ ] Buscar todos los habilitados
    - [ ] Buscar cualquiera por id
    - [ ] Buscar habilitado por id
    - [ ] Buscar cualquier por nombre
    - [ ] Buscar habilitado por nombre
    - [ ] Buscar por Query Personalizado


### [jpa-listeners] Implementación de Auditoría Manual con Listeners
> Objetivo: Implementar listener para auditar operaciones sobre productos

#### Subtareas:
- [x] Crear entidad `AuditProductEntity`
- [x] Crear `AuditProductListener` con `@PrePersist`, `@PreUpdate`, `@PreRemove`
- [ ] Usar puertos para persistir en lugar de JPA directo
- [ ] Registrar nombre viejo y nuevo del producto mediante Trigger SQL
- [x] Agregar auditoría con usuario InMemoryDetails (provisional)
- [ ] Agregar auditoría de usuario autenticado

### [jpa-listeners] Implementación de Auditoría Manual con Listeners
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

### [testing] Testing de capa de aplicación
> Objetivo: Agregar pruebas unitarias e integración para `ProductService`

#### Subtareas:
- [ ] Pruebas unitarias con mocks
- [ ] Verificar cobertura del 100%
- [ ] Pruebas de integración con Sonar