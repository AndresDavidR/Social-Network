<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

- [x] Verificar que el archivo copilot-instructions.md esté creado en el directorio .github
- [x] Clarificar Requisitos del Proyecto - Red Social Backend con Spring Boot, JWT, H2
- [x] Estructurar el Proyecto
- [x] Personalizar el Proyecto
- [x] Instalar Extensiones Requeridas
- [x] Compilar el Proyecto
- [x] Crear y Ejecutar Tareas
- [x] Lanzar el Proyecto
- [x] Asegurar que la Documentación esté Completa
- [x] Agregar Actualizaciones WebSocket en Tiempo Real
- [x] Agregar Documentación Swagger/OpenAPI
- [x] Internacionalizar Documentación (Inglés)

## Proyecto Red Social Backend
- **Framework**: Spring Boot con Java
- **Base de Datos**: H2 Database (en memoria)
- **Autenticación**: JWT
- **Funcionalidades**: CRUD publicaciones, likes, perfiles, seeder de datos
- **Tiempo Real**: WebSocket con STOMP
- **Documentación**: Swagger/OpenAPI 3

## ✅ Proyecto Completado

El proyecto completo de red social (Frontend + Backend) ha sido creado exitosamente con todas las funcionalidades:

### 🎯 Características Implementadas:
- ✅ **Frontend Angular**: Interfaz completa de usuario con componentes
- ✅ **Backend Spring Boot**: API REST con autenticación JWT
- ✅ **CRUD de Publicaciones**: Crear, leer y dar likes
- ✅ **Sistema de Likes**: Con actualizaciones en tiempo real
- ✅ **Perfiles de Usuario**: Gestión completa de usuarios
- ✅ **Seeder de Datos**: 5 usuarios y 8 publicaciones de prueba
- ✅ **WebSocket**: Actualizaciones automáticas entre frontend y backend
- ✅ **Swagger UI**: Documentación completa de la API en inglés
- ✅ **Base de Datos H2**: En memoria, sin configuración externa
- ✅ **Concurrently**: Ejecuta frontend y backend simultáneamente

### 🚀 Endpoints y URLs:
- **Frontend Angular**: http://localhost:4200
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **H2 Console**: http://localhost:8080/h2-console
- **WebSocket**: ws://localhost:8080/ws

### 🔧 Scripts de Ejecución:
- `npm run dev` - Ejecutar frontend + backend
- `npm run install-all` - Instalar todas las dependencias
- `npm run build` - Compilar para producción

### 📚 Documentación:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs
- **Desarrollador**: Andrés David Rodríguez (https://github.com/AndresDavidR)
- **Repositorio**: https://github.com/AndresDavidR/Social-Network

### 📦 Próximos Pasos:
1. Instalar Node.js 18+, Java 17+ y Maven (ver SETUP.md)
2. **No requiere configuración de base de datos** (H2 en memoria)
3. Ejecutar: `npm run install-all` luego `npm run dev`
4. Acceder a Frontend en http://localhost:4200
5. Probar API en Swagger UI: http://localhost:8080/swagger-ui/index.html
6. Disfrutar de actualizaciones en tiempo real via WebSocket