# Red Social - Full Stack Application

Aplicación completa de red social desarrollada con **Angular** (frontend) y **Spring Boot** (backend), que proporciona una experiencia interactiva con actualizaciones en tiempo real.

## 🚀 Características

### Frontend (Angular)
- **Interfaz Moderna**: UI responsiva compatible con móvil y desktop
- **Autenticación JWT**: Login y registro de usuarios
- **Feed Interactivo**: Ver y crear publicaciones en tiempo real
- **Sistema de Likes**: Dar like con actualizaciones instantáneas vía WebSocket
- **Perfiles de Usuario**: Ver y editar perfiles de usuario
- **Navegación Intuitiva**: Barra de navegación con opciones contextuales

### Backend (Spring Boot)
- **API REST Completa**: Endpoints documentados con Swagger
- **Autenticación JWT**: Sistema de login y registro seguro
- **CRUD de Publicaciones**: Crear, leer, actualizar y eliminar posts
- **Sistema de Likes**: Los usuarios pueden dar like a las publicaciones
- **Perfiles de Usuario**: Gestión completa de perfiles
- **Seeder de Datos**: Datos de prueba automáticos al iniciar la aplicación
- **CORS Configurado**: Listo para conectar con frontend Angular
- **WebSocket**: Actualizaciones en tiempo real para likes
- **Base de Datos H2**: En memoria, sin configuración externa requerida
- **Documentación Swagger**: API completamente documentada

### Integración Full Stack
- **Ejecución Unificada**: Un solo comando para ejecutar frontend y backend
- **WebSocket**: Comunicación en tiempo real entre frontend y backend
- **Autenticación**: JWT compartido entre ambas aplicaciones

## 🛠️ Tecnologías

### Frontend
- **Angular 17+** con TypeScript
- **RxJS** para programación reactiva
- **WebSocket** con STOMP para tiempo real
- **SCSS** para estilos responsivos

### Backend
- **Java 17**
- **Spring Boot 3.2.10**
- **Spring Security** con JWT
- **Spring Data JPA**
- **H2 Database** (en memoria para desarrollo)
- **Maven**
- **WebSocket** con STOMP para actualizaciones en tiempo real
- **Swagger/OpenAPI 3** para documentación de API

### Herramientas de Desarrollo
- **Concurrently** para ejecutar backend y frontend simultáneamente
- **Node.js** y **npm** para gestión de dependencias del frontend

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

### 🎯 Páginas Principales
- **Login/Registro**: Autenticación de usuarios con validación
- **Feed Principal**: Lista de todas las publicaciones con scroll infinito
- **Crear Publicación**: Formulario para nuevos posts con soporte para imágenes
- **Perfil de Usuario**: Información y publicaciones del usuario con opción de edición

### 🧩 Componentes
- **Navbar**: Barra de navegación con opciones de usuario autenticado
- **Post List**: Lista de publicaciones con likes en tiempo real vía WebSocket
- **Create Post**: Formulario para crear nuevas publicaciones
- **Profile**: Perfil de usuario con edición de información personal

### ⚡ Funcionalidades Interactivas
- **Likes en Tiempo Real**: Actualizaciones instantáneas sin recargar la página
- **Navegación SPA**: Experiencia de Single Page Application fluida
- **Responsive Design**: Compatible con dispositivos móviles y desktop
- **Autenticación Persistente**: Sesión mantenida con JWT tokens

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
Social-Network/
├── package.json                    # Scripts para ejecutar todo el proyecto
├── README.md                       # Documentación principal
├── SETUP.md                        # Guía de configuración detallada
│
├── backend/                        # Backend Spring Boot
│   ├── pom.xml                    # Dependencias Maven
│   └── src/main/java/com/redsocial/backend/
│       ├── config/                # Configuraciones (CORS, Seeder, WebSocket, Swagger)
│       ├── controller/            # Controladores REST y WebSocket
│       ├── dto/                   # Data Transfer Objects
│       ├── model/                 # Entidades JPA
│       ├── repository/            # Repositorios de datos
│       ├── security/              # Configuración de seguridad JWT
│       └── service/               # Lógica de negocio
│
└── frontend/redsocial-frontend/   # Frontend Angular
    ├── src/app/
    │   ├── components/            # Componentes de la aplicación
    │   │   ├── login/            # Página de inicio de sesión
    │   │   ├── register/         # Página de registro
    │   │   ├── home/             # Feed principal
    │   │   ├── post-list/        # Lista de publicaciones
    │   │   ├── create-post/      # Crear nueva publicación
    │   │   ├── profile/          # Perfil de usuario
    │   │   └── navbar/           # Barra de navegación
    │   ├── services/             # Servicios para API y WebSocket
    │   │   ├── auth.service.ts   # Autenticación JWT
    │   │   ├── post.service.ts   # Gestión de publicaciones
    │   │   ├── user.service.ts   # Gestión de usuarios
    │   │   └── websocket.service.ts # WebSocket para tiempo real
    │   ├── models/               # Interfaces y modelos TypeScript
    │   ├── guards/               # Guards de autenticación
    │   └── interceptors/         # Interceptores HTTP
    └── public/                   # Recursos estáticos
```

## 🚀 Próximos Pasos

El proyecto está completo y listo para usar. Para continuar el desarrollo, considera estas mejoras:

### 🔧 Funcionalidades Sugeridas

#### Sistema de Comentarios
```typescript
interface Comment {
  id: number;
  content: string;
  user: User;
  post: Post;
  createdAt: Date;
}
```

#### Sistema de Seguimiento
```typescript
interface Follow {
  follower: User;
  following: User;
  createdAt: Date;
}
```

#### Notificaciones en Tiempo Real
```typescript
interface Notification {
  id: number;
  type: 'like' | 'comment' | 'follow';
  user: User;
  message: string;
  read: boolean;
}
```

### 🎯 Mejoras Recomendadas
1. **Comentarios en Publicaciones**: Sistema completo de comentarios
2. **Seguir Usuarios**: Poder seguir/dejar de seguir usuarios
3. **Feed Personalizado**: Mostrar solo publicaciones de usuarios seguidos
4. **Notificaciones Push**: Notificaciones en tiempo real con WebSocket
5. **Subida de Archivos**: Permitir subir imágenes a las publicaciones
6. **PWA**: Convertir en Progressive Web App
7. **Tests Completos**: Agregar tests unitarios y e2e para frontend y backend
8. **Docker**: Containerización para despliegue fácil
9. **CI/CD**: Pipeline de integración y despliegue continuo

### 🔄 Desarrollo Frontend
Para trabajar específicamente en el frontend:

```bash
# Navegar al directorio del frontend
cd frontend/redsocial-frontend

# Generar nuevos componentes
ng generate component nombre-componente

# Ejecutar solo el frontend (requiere backend activo)
ng serve

# Compilar para producción
ng build --prod

# Ejecutar tests
ng test

# Tests end-to-end
ng e2e
```

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