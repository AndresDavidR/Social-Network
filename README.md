# Red Social Backend - Spring Boot

Backend para una aplicación de red social desarrollada con Java Spring Boot, que proporciona una API REST completa para gestionar usuarios, publicaciones y sistema de likes.

## 🚀 Características

- **Autenticación JWT**: Sistema de login y registro seguro
- **CRUD de Publicaciones**: Crear, leer, actualizar y eliminar posts
- **Sistema de Likes**: Los usuarios pueden dar like a las publicaciones
- **Perfiles de Usuario**: Gestión completa de perfiles
- **Seeder de Datos**: Datos de prueba automáticos al iniciar la aplicación
- **CORS Configurado**: Listo para conectar con frontend Angular
- **WebSocket**: Actualizaciones en tiempo real para likes
- **Base de Datos H2**: En memoria, sin configuración externa requerida
- **Documentación Swagger**: API completamente documentada
- **Frontend Angular**: Interfaz de usuario completa e interactiva
- **Ejecución Integrada**: Un solo comando para ejecutar todo el proyecto

## 🛠️ Tecnologías

- **Java 17**
- **Spring Boot 3.2.10**
- **Spring Security** con JWT
- **Spring Data JPA**
- **H2 Database** (en memoria para desarrollo)
- **Maven**
- **WebSocket** con STOMP para actualizaciones en tiempo real
- **Swagger/OpenAPI 3** para documentación de API
- **Angular 17+** para el frontend
- **Concurrently** para ejecutar backend y frontend simultáneamente

## 📋 Requisitos Previos

- **Node.js 18+** y **npm**
- **Java 17** o superior
- **Maven 3.6+**

**Nota**: No necesitas instalar ninguna base de datos externa. El proyecto usa H2 en memoria que se configura automáticamente.

## ⚙️ Configuración

### 1. Base de Datos

**¡No requiere configuración!** El proyecto usa H2 Database en memoria que se configura automáticamente al iniciar la aplicación.

- **URL de conexión**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: *(vacía)*
- **Consola H2**: http://localhost:8080/h2-console (opcional para debugging)

### 2. Instalación y Ejecución

```bash
# Clonar el repositorio
git clone https://github.com/AndresDavidR/Social-Network

# Navegar al directorio principal
cd Social-Network

# Instalar todas las dependencias (backend y frontend)
npm run install-all

# Ejecutar todo el proyecto (backend + frontend)
npm run dev
```

La aplicación estará disponible en:
- **Frontend Angular**: `http://localhost:4200`
- **API Backend**: `http://localhost:8080`
- **Documentación Swagger**: `http://localhost:8080/swagger-ui/index.html`
- **Consola H2** (opcional): `http://localhost:8080/h2-console`

### Scripts Disponibles

```bash
npm run dev          # Ejecutar backend y frontend juntos (recomendado)
npm run install-all  # Instalar dependencias de backend y frontend
npm run start        # Alias para dev
npm run build        # Compilar backend y frontend para producción
```

## 📚 API Endpoints

### 📖 Documentación Interactiva
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

### Autenticación
- `POST /api/auth/signin` - Iniciar sesión
- `POST /api/auth/signup` - Registrar usuario

### Publicaciones
- `GET /api/posts` - Obtener todas las publicaciones
- `GET /api/posts/paginated` - Obtener publicaciones paginadas
- `GET /api/posts/{id}` - Obtener publicación por ID
- `GET /api/posts/user/{username}` - Obtener publicaciones de un usuario
- `POST /api/posts` - Crear nueva publicación
- `POST /api/posts/{id}/like` - Dar/quitar like a una publicación
- `DELETE /api/posts/{id}` - Eliminar publicación

### Usuarios
- `GET /api/users/{username}` - Obtener perfil de usuario
- `GET /api/users/me` - Obtener perfil actual
- `GET /api/users` - Obtener todos los usuarios
### WebSocket (Tiempo Real)
- **Endpoint**: `/ws` - Conexión WebSocket para actualizaciones en tiempo real
- **Tópico**: `/topic/post-likes/{postId}` - Recibe actualizaciones de likes

**Nota**: Puedes probar todos estos endpoints interactivamente en Swagger UI sin necesidad de configurar herramientas externas.

## 🖥️ Interfaz de Usuario

El frontend Angular incluye las siguientes páginas y componentes:

### Páginas principales:
- **Login/Registro**: Autenticación de usuarios
- **Feed Principal**: Lista de todas las publicaciones
- **Crear Publicación**: Formulario para nuevos posts
- **Perfil de Usuario**: Información y publicaciones del usuario

### Componentes:
- **Navbar**: Barra de navegación con opciones de usuario
- **Post List**: Lista de publicaciones con likes en tiempo real
- **Create Post**: Formulario para crear nuevas publicaciones
- **Profile**: Perfil de usuario con edición de información

## 🧪 Datos de Prueba

Al iniciar la aplicación, se crean automáticamente usuarios de prueba con sus respectivas publicaciones:

| Usuario | Email | Contraseña |
|---------|-------|------------|
| juan_dev | juan@example.com | password123 |
| maria_design | maria@example.com | password123 |
| carlos_data | carlos@example.com | password123 |
| ana_mobile | ana@example.com | password123 |
| pedro_backend | pedro@example.com | password123 |

## 🔑 Ejemplo de Uso

### Login
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{
    "username": "juan_dev",
    "password": "password123"
  }'
```

### Crear Publicación
```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "content": "Mi primera publicación en la red social!",
    "imageUrl": "https://example.com/image.jpg"
  }'
```

### Obtener Publicaciones
```bash
curl -X GET http://localhost:8080/api/posts \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## 🔧 Configuración para Desarrollo

El proyecto está configurado para desarrollo con recarga automática:

- **Backend**: Se recarga automáticamente cuando cambias archivos Java
- **Frontend**: Se recarga automáticamente cuando cambias archivos TypeScript/HTML/CSS
- **CORS**: Configurado para permitir conexiones entre frontend (4200) y backend (8080)

Si necesitas cambiar los puertos, actualiza:

**Backend** - `application.properties`:
```properties
server.port=8080
app.cors.allowed-origins=http://localhost:4200
```

**Frontend** - `src/environments/environment.ts`:
```typescript
export const environment = {
  apiUrl: 'http://localhost:8080/api'
};
```

## 🏗️ Estructura del Proyecto

```
src/main/java/com/redsocial/backend/
├── config/          # Configuraciones (CORS, Seeder, WebSocket, Swagger)
├── controller/      # Controladores REST y WebSocket
├── dto/            # Data Transfer Objects
├── model/          # Entidades JPA
├── repository/     # Repositorios de datos
├── security/       # Configuración de seguridad JWT
└── service/        # Lógica de negocio

src/app/ (Frontend Angular)
├── components/     # Componentes de la aplicación
├── services/       # Servicios para API y WebSocket
├── models/         # Interfaces y modelos TypeScript
├── guards/         # Guards de autenticación
└── interceptors/   # Interceptores HTTP
```

## 🚀 Próximos Pasos

El proyecto está completo y listo para usar. Para continuar el desarrollo:

### Funcionalidades adicionales que puedes implementar:

```typescript
// Comentarios en publicaciones
interface Comment {
  id: number;
  content: string;
  user: User;
  post: Post;
  createdAt: Date;
}

// Sistema de seguimiento
interface Follow {
  follower: User;
  following: User;
  createdAt: Date;
}

// Notificaciones en tiempo real
interface Notification {
  id: number;
  type: 'like' | 'comment' | 'follow';
  user: User;
  message: string;
  read: boolean;
}
```

### Mejoras sugeridas:
1. **Comentarios**: Sistema de comentarios en publicaciones
2. **Seguir usuarios**: Poder seguir/dejar de seguir usuarios
3. **Feed personalizado**: Mostrar solo publicaciones de usuarios seguidos
4. **Notificaciones push**: Notificaciones en tiempo real
5. **Subida de imágenes**: Permitir subir imágenes a las publicaciones
6. **PWA**: Convertir en Progressive Web App
7. **Tests**: Agregar tests unitarios y e2e completos

## 📄 Licencia

Este proyecto está bajo la Licencia MIT.

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

---

¡Listo para usar! Ejecuta `npm run dev` y comienza a explorar tu red social completa 🚀