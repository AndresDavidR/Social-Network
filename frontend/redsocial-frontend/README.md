# Red Social Frontend - Angular

Frontend para la aplicación de red social desarrollada con Angular 17, que proporciona una interfaz de usuario completa para interactuar con el backend de Spring Boot.

## 🚀 Características

- **Autenticación JWT**: Login y registro de usuarios
- **Feed de Publicaciones**: Ver y crear publicaciones
- **Sistema de Likes**: Dar like a publicaciones con actualizaciones en tiempo real
- **Perfiles de Usuario**: Ver y editar perfiles
- **WebSocket**: Actualizaciones automáticas sin recargar la página
- **Diseño Responsivo**: Compatible con dispositivos móviles y desktop

## 🛠️ Tecnologías

- **Angular 17+**
- **TypeScript**
- **RxJS** para programación reactiva
- **WebSocket** con STOMP para tiempo real
- **Angular Material** (opcional para UI)
- **SCSS** para estilos

## 📋 Requisitos Previos

- **Node.js 18+** y **npm**
- **Backend Spring Boot** ejecutándose en puerto 8080

## ⚙️ Configuración y Ejecución

### Opción 1: Ejecutar todo el proyecto (Recomendado)

Desde el directorio raíz del proyecto:

```bash
# Instalar dependencias de todo el proyecto
npm run install-all

# Ejecutar frontend y backend simultáneamente
npm run dev
```

### Opción 2: Solo el frontend

Desde el directorio `frontend/redsocial-frontend`:

```bash
# Instalar dependencias
npm install

# Ejecutar servidor de desarrollo
ng serve
```

La aplicación estará disponible en `http://localhost:4200/`

## 🏗️ Estructura del Proyecto

```
src/app/
├── components/          # Componentes de la aplicación
│   ├── login/          # Página de inicio de sesión
│   ├── register/       # Página de registro
│   ├── home/           # Feed principal
│   ├── post-list/      # Lista de publicaciones
│   ├── create-post/    # Crear nueva publicación
│   ├── profile/        # Perfil de usuario
│   └── navbar/         # Barra de navegación
├── services/           # Servicios para API y WebSocket
│   ├── auth.service.ts # Autenticación JWT
│   ├── post.service.ts # Gestión de publicaciones
│   ├── user.service.ts # Gestión de usuarios
│   └── websocket.service.ts # WebSocket para tiempo real
├── models/             # Interfaces y modelos
├── guards/             # Guards de autenticación
└── interceptors/       # Interceptores HTTP
```

## 🔧 Desarrollo

### Generar componentes

Para generar un nuevo componente, ejecuta:

```bash
ng generate component nombre-componente
```

### Compilar proyecto

Para compilar el proyecto ejecuta:

```bash
ng build
```

Los archivos compilados se guardarán en el directorio `dist/`. Por defecto, la compilación de producción optimiza la aplicación para rendimiento y velocidad.

### Ejecutar pruebas unitarias

Para ejecutar las pruebas unitarias con [Karma](https://karma-runner.github.io), usa:

```bash
ng test
```

### Ejecutar pruebas end-to-end

Para pruebas end-to-end (e2e), ejecuta:

```bash
ng e2e
```

## 🔗 Integración con Backend

La aplicación se conecta al backend Spring Boot que debe estar ejecutándose en `http://localhost:8080`:

### Servicios principales:

- **AuthService**: Maneja login, registro y tokens JWT
- **PostService**: CRUD de publicaciones y sistema de likes
- **UserService**: Gestión de perfiles de usuario
- **WebSocketService**: Conexión WebSocket para actualizaciones en tiempo real

### Endpoints utilizados:

- `POST /api/auth/signin` - Iniciar sesión
- `POST /api/auth/signup` - Registrar usuario
- `GET /api/posts` - Obtener publicaciones
- `POST /api/posts` - Crear publicación
- `POST /api/posts/{id}/like` - Dar/quitar like
- `GET /api/users/{username}` - Obtener perfil
- `WebSocket /ws` - Conexión tiempo real

## 🧪 Usuarios de Prueba

El backend incluye usuarios de prueba que puedes usar:

| Usuario | Contraseña |
|---------|------------|
| juan_dev | password123 |
| maria_design | password123 |
| carlos_data | password123 |
| ana_mobile | password123 |
| pedro_backend | password123 |

## 🚀 Próximos Pasos

Para continuar el desarrollo:

1. Personalizar estilos y diseño
2. Agregar más funcionalidades (comentarios, seguir usuarios, etc.)
3. Implementar notificaciones push
4. Optimizar para PWA (Progressive Web App)
5. Agregar tests unitarios y e2e

---

Generado con [Angular CLI](https://github.com/angular/angular-cli) versión 20.3.1.
