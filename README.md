## Product Inventory Lab

<img src="https://drive.google.com/uc?export=view&id=1m-v0yfqzNgoTTr5KVAk2ttXu_ZLnoxh1" width="1000"/>

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
    - [ ] Buscar Producto por id
    - [ ] Buscar Producto por nombre
    - [ ] Buscar Todos los Productos
    - [ ] Buscar Productos por filtros personalizados
    - [ ] Deshabilitar Producto
    - [ ] Verificar Stock, que devuelva `enum` en caso de estar debajo del mínimo o ser igual al máximo
  - [ ] Contrato de Repositorio
    - [ ] Guardar Producto
    - [ ] Buscar todos
    - [ ] Buscar por id
    - [ ] Buscar por nombre
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

---

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