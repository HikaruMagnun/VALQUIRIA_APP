# 🏨 VALQUIRIA APP - Sistema de Reservas y Servicios Hoteleros

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![API](https://img.shields.io/badge/Min%20SDK-21-blue.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)
![Version](https://img.shields.io/badge/Version-1.0-red.svg)

## 📱 Descripción

**Valquiria App** es una aplicación móvil Android desarrollada para la gestión integral de reservas hoteleras y servicios adicionales. La aplicación permite a los usuarios realizar reservas de habitaciones, solicitar servicios del hotel y procesar pagos de manera intuitiva y segura.

## ✨ Características Principales

### 🏠 **Gestión de Habitaciones**
- Visualización de habitaciones disponibles
- Selección de fechas de entrada y salida
- Información detallada de cada habitación (tipo, precio, amenidades)
- Personalización con diferentes tipos de muebles
- Cálculo automático del costo total

### 🛎️ **Servicios del Hotel**
- Catálogo completo de servicios disponibles
- Servicios categorizados por tipo
- Selección de cantidad y personalización
- Servicios para huéspedes con y sin reserva

### 💳 **Sistema de Facturación**
- Interfaz de pago profesional y segura
- Soporte para múltiples métodos de pago
- Resumen detallado de costos
- Simulación de procesamiento de pagos

### 🔐 **Autenticación y Seguridad**
- Sistema de login y registro de usuarios
- Validación de datos de usuario
- Protección de información sensible

## 🏗️ Arquitectura Técnica

### **Patrón de Arquitectura**
- **MVVM (Model-View-ViewModel)** con Android Architecture Components
- **Navigation Component** para navegación entre fragmentos
- **LiveData** para observación de datos reactivos
- **ViewBinding** para acceso seguro a las vistas

### **Tecnologías Utilizadas**
- **Lenguaje**: Java
- **SDK Mínimo**: Android API 21 (Android 5.0)
- **Componentes**: 
  - Navigation Component
  - Material Design Components
  - CardView & RecyclerView
  - ConstraintLayout

### **Estructura del Proyecto**
```
app/src/main/
├── java/com/pe/valquiriaapp/
│   ├── ui/
│   │   ├── billing/           # Módulo de facturación
│   │   ├── habitacion/        # Gestión de habitaciones
│   │   ├── reserva_*/         # Módulos de reservas
│   │   ├── servicio_*/        # Módulos de servicios
│   │   └── alojamiento_*/     # Validadores y confirmaciones
│   ├── adapters/              # Adaptadores para RecyclerView
│   └── model/                 # Modelos de datos
└── res/
    ├── layout/                # Layouts XML
    ├── navigation/            # Gráficos de navegación
    └── values/               # Recursos (strings, colors, etc.)
```

## 🚀 Instalación y Configuración

### **Prerrequisitos**
- Android Studio Arctic Fox o superior
- JDK 8 o superior
- Android SDK API 21+
- Gradle 7.0+

### **Clonar el Repositorio**
```bash
git clone https://github.com/tu-usuario/valquiria-app.git
cd valquiria-app
```

### **Configuración del Proyecto**
1. Abrir el proyecto en Android Studio
2. Sincronizar dependencias de Gradle
3. Conectar dispositivo Android o configurar emulador
4. Ejecutar la aplicación

## 📊 Flujo de Usuario

### **1. Reserva de Habitación**
```
Inicio → Selección de Fechas → Lista de Habitaciones → 
Detalles de Habitación → Personalización → Facturación → Confirmación
```

### **2. Solicitud de Servicios**
```
Inicio → Validador de Servicios → Tipos de Servicios → 
Lista por Tipo → Detalles del Servicio → Facturación → Confirmación
```

### **3. Sistema de Pagos**
```
Confirmación de Pedido → Formulario de Pago → 
Ingreso de Datos de Tarjeta → Procesamiento → Confirmación Final
```

## 🎨 Interfaz de Usuario

### **Diseño Visual**
- **Material Design 3** con componentes modernos
- **Paleta de colores** profesional y amigable
- **Tipografía** clara y legible
- **Iconografía** intuitiva y reconocible

### **Experiencia de Usuario**
- **Navegación fluida** entre pantallas
- **Feedback visual** en todas las interacciones
- **Formularios intuitivos** con validación en tiempo real
- **Diseño responsive** para diferentes tamaños de pantalla

## 🔄 Estados de la Aplicación

### **Gestión de Estados**
- **Loading States**: Indicadores de carga durante operaciones
- **Error Handling**: Manejo graceful de errores
- **Empty States**: Pantallas informativas cuando no hay datos
- **Success States**: Confirmaciones visuales de operaciones exitosas

## 🧪 Testing

### **Tipos de Pruebas Implementadas**
- **Unit Tests**: Lógica de negocio y ViewModels
- **Integration Tests**: Navegación y flujos completos
- **UI Tests**: Interacciones de usuario automatizadas

```bash
# Ejecutar tests unitarios
./gradlew test

# Ejecutar tests de UI
./gradlew connectedAndroidTest
```

## 📦 Dependencias Principales

```gradle
dependencies {
    // Android Core
    implementation 'androidx.core:core-ktx:1.9.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    
    // Material Design
    implementation 'com.google.android.material:material:1.8.0'
    
    // Navigation Component
    implementation 'androidx.navigation:navigation-fragment:2.5.3'
    implementation 'androidx.navigation:navigation-ui:2.5.3'
    
    // Architecture Components
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.6.1'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.6.1'
}
```

## 🚀 Deployment

### **Generación de APK**
```bash
./gradlew assembleDebug    # APK de desarrollo
./gradlew assembleRelease  # APK de producción
```

### **Generación de Bundle**
```bash
./gradlew bundleRelease
```

## 🤝 Contribución

### **Proceso de Contribución**
1. Fork del repositorio
2. Crear rama feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit de cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear Pull Request

### **Estándares de Código**
- Seguir convenciones de Java
- Documentar métodos públicos
- Mantener cobertura de tests > 80%
- Usar nombres descriptivos para variables y métodos

## 🔧 Configuración de Desarrollo

### **Variables de Entorno**
```properties
# local.properties
sdk.dir=/path/to/android/sdk
api.base.url=https://api.valquiria.com
debug.mode=true
```

### **Configuración de Base de Datos**
- Revisar `database_script.sql` para estructura de BD
- Configurar conexión en archivos de configuración

## 📋 Roadmap

### **Versión 1.1 (Próximas Funcionalidades)**
- [ ] Notificaciones push
- [ ] Modo offline
- [ ] Integración con mapas
- [ ] Chat de soporte en tiempo real

### **Versión 2.0 (Futuro)**
- [ ] Migración a Kotlin
- [ ] Implementación de Clean Architecture
- [ ] Integración con pagos reales
- [ ] Dashboard de administración

## 🐛 Problemas Conocidos

- **Navegación**: Ocasionalmente puede requerir rebuild después de cambios en navigation graph
- **Rendimiento**: Optimización pendiente para listas grandes de habitaciones

## 📞 Soporte

Para reportar bugs o solicitar funcionalidades:
- **Issues**: [GitHub Issues](https://github.com/tu-usuario/valquiria-app/issues)
- **Email**: soporte@valquiria.com
- **Documentación**: [Wiki del Proyecto](https://github.com/tu-usuario/valquiria-app/wiki)

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

---

**© 2024 Valquiria Hotel App. Todos los derechos reservados.**

---

## 👨‍💻 Desarrolladores

- **Desarrollador Principal**: [Tu Nombre]
- **Arquitectura**: [Nombre del Arquitecto]
- **UI/UX Design**: [Nombre del Diseñador]

---

*¿Te gusta el proyecto? ¡Dale una ⭐ en GitHub!*
