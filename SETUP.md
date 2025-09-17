# Guía de Instalación - Requisitos del Sistema

Para ejecutar este proyecto completo (Frontend Angular + Backend Spring Boot), necesitas instalar las siguientes herramientas:

## 📋 Requisitos

### 0. Node.js 18+ y npm

#### Windows:
1. Descarga Node.js desde: https://nodejs.org/
2. Ejecuta el instalador y sigue las instrucciones
3. Verifica la instalación:
   ```bash
   node -v
   npm -v
   ```

### 1. Java Development Kit (JDK) 17 o superior

#### Windows:
1. Descarga OpenJDK 17 desde: https://adoptium.net/
2. Ejecuta el instalador y sigue las instrucciones
3. Verifica la instalación:
   ```bash
   java -version
   javac -version
   ```

#### Configurar JAVA_HOME:
1. Panel de Control → Sistema → Configuración avanzada del sistema
2. Variables de entorno → Nueva variable del sistema
3. Nombre: `JAVA_HOME`
4. Valor: `C:\Program Files\Eclipse Adoptium\jdk-17.0.x.x-hotspot` (ajustar según tu instalación)

### 2. Apache Maven 3.6+

#### Windows:
1. Descarga Maven desde: https://maven.apache.org/download.cgi
2. Extrae el archivo en `C:\Program Files\Apache\maven`
3. Agrega Maven al PATH:
   - Variables de entorno → Variable del sistema PATH
   - Agregar: `C:\Program Files\Apache\maven\bin`
4. Verifica la instalación:
   ```bash
   mvn -version
   ```

### 3. Base de Datos

**¡No se requiere instalación de base de datos externa!** 

Este proyecto usa **H2 Database en memoria** que se configura automáticamente al iniciar la aplicación:

- **Tipo**: Base de datos en memoria
- **URL**: `jdbc:h2:mem:testdb`
- **Usuario**: `sa`
- **Contraseña**: *(vacía)*
- **Consola H2**: http://localhost:8080/h2-console (para debugging opcional)

**Ventajas**:
- ✅ Sin instalación adicional requerida
- ✅ Datos de prueba precargados automáticamente
- ✅ Ideal para desarrollo y testing
- ✅ Reinicio limpio en cada ejecución

## 🚀 Ejecutar el Proyecto

Una vez instalados Node.js, Java y Maven:

```bash
# Navegar al directorio principal del proyecto
cd "ruta/al/proyecto"

# Instalar todas las dependencias (backend + frontend)
npm run install-all

# Ejecutar todo el proyecto (backend + frontend simultáneamente)
npm run dev
```

**🎉 ¡Eso es todo!** La aplicación iniciará con:
- Frontend Angular en: http://localhost:4200
- Backend API en: http://localhost:8080
- Documentación Swagger: http://localhost:8080/swagger-ui/index.html
- Consola H2: http://localhost:8080/h2-console
- 5 usuarios de prueba con publicaciones precargadas
- WebSocket funcionando para actualizaciones en tiempo real

### Scripts Disponibles

```bash
npm run dev              # Ejecutar backend y frontend juntos (RECOMENDADO)
npm run install-all      # Instalar dependencias de ambos proyectos
npm run start            # Alias para dev
npm run build            # Compilar para producción
npm run start-backend    # Solo backend
npm run start-frontend   # Solo frontend
```

## 🔧 Configuración VS Code

### Extensiones Recomendadas:
- **Extension Pack for Java** (para Spring Boot)
- **Angular Language Service** (para Angular)
- **REST Client** (para probar APIs)
- **Thunder Client** (alternativa a Postman)
- **GitLens** (para Git)
- **Auto Rename Tag** (para HTML)
- **Bracket Pair Colorizer** (para código más legible)

### Configuración de Java en VS Code:
1. Abrir VS Code
2. Ctrl+Shift+P → "Java: Configure Runtime"
3. Seleccionar el JDK instalado

### Configuración de Angular en VS Code:
1. Instalar Angular Language Service extension
2. Configurar TypeScript version en VS Code settings
3. Habilitar auto-save para ver cambios en tiempo real

## 🐳 Información sobre Docker

**Nota**: Aunque incluimos una configuración Docker opcional, la naturaleza de H2 en memoria hace que la aplicación sea extremadamente fácil de ejecutar sin Docker:

```dockerfile
# Dockerfile (opcional)
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

**Recomendación**: Para desarrollo, usar directamente `mvn spring-boot:run` es más rápido y simple.

## ⚡ Quick Start

**Pasos mínimos para ejecutar el proyecto completo:**

1. ✅ Instala Node.js 18+, Java 17+ y Maven
2. ✅ Clona el repositorio: `git clone https://github.com/AndresDavidR/Social-Network`
3. ✅ Ve al directorio principal: `cd Social-Network`
4. ✅ Instala dependencias: `npm run install-all`
5. ✅ Ejecuta todo: `npm run dev`
6. ✅ Abre: 
   - Frontend: http://localhost:4200
   - API Docs: http://localhost:8080/swagger-ui/index.html

**¡No necesitas configurar bases de datos ni variables de entorno!** 🎉

### 🧪 Datos de Prueba Incluidos

El sistema carga automáticamente:
- 5 usuarios de prueba (todos con contraseña: `password123`)
- 8 publicaciones de ejemplo
- Sistema de likes funcional con actualizaciones en tiempo real
- WebSocket configurado para sincronización frontend-backend

### 📚 Recursos Útiles

- **Frontend App**: http://localhost:4200
- **API Documentation**: http://localhost:8080/swagger-ui/index.html
- **Database Console**: http://localhost:8080/h2-console
- **REST Endpoints**: http://localhost:8080/api/*
- **WebSocket Endpoint**: ws://localhost:8080/ws