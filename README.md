# PruebaYape

### Demostración 📲

https://user-images.githubusercontent.com/5515818/222192547-0c6ee7ad-7434-48d1-8a1f-c9cd56a4ff44.mp4

### Resumen técnico 🛠

- Offline-First: Las aplicaciones sin conexión, si bien aún requieren una conexión a los servidores, no necesitan una conexión a internet constante. Los datos de los servidores se descargan en el dispositivo del usuario y aún se puede acceder a ellos sin conexión.
- Single Source of Truth (SSOT): Es la práctica de estructurar modelos de información y esquemas asociados de modo que cada elemento de datos se almacene exactamente una vez. Puede tener una aplicación offline y asegurarse de que sus datos siempre usen una fuente y esa es su base de datos.
- Model-View-ViewModel (MVVM): Es un patrón de arquitectura de software que facilita la separación del desarrollo de la interfaz gráfica de usuario (sin utilizar DataBinding). Además, hay estados de pantalla para manejar los diferentes estados en la interfaz de usuario.
- Coroutines: Una corutina es un patrón de diseño de simultaneidad que puede usar en Android para simplificar el código que se ejecuta de forma asíncrona.
- Android Architecture Components: Colección de bibliotecas que ayudan a diseñar aplicaciones sólidas, comprobables y mantenibles.
- Navigation: Este componente ayuda a implementar la navegación.
- ViewModel: Almacena datos relacionados con la interfaz de usuario que no se destruyen con los cambios de la interfaz de usuario.
- Room: La biblioteca proporciona una capa de abstracción sobre SQLite para permitir un acceso más sólido a la base de datos mientras aprovecha todo el poder de SQLite.
- DatabaseView: Esta anotación le permite encapsular una consulta en una clase. Room se refiere a estas clases respaldadas por consultas como vistas, y se comportan igual que los objetos de datos simples cuando se usan en una DAO.
- Unit Tests con JUnit4: JUnit es el marco de trabajo o framework de testing para Java más popular. Aunque su nombre sugiere que se centra en las pruebas unitarias, en realidad permite implementar cualquier tipo de prueba automatizada.

### Grafico de arquitectura utilizada 🪜

![mad-arch-overview-data](https://user-images.githubusercontent.com/5515818/222504687-3131a02d-a55a-46d3-9810-07c2f4adbcad.png)

### Librerias utilizadas 🛠

- [Kotlin](https://kotlinlang.org/) 
- [Clean Architecture](https://developer.android.com/topic/architecture)
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) 
- [Compose](https://developer.android.com/jetpack/compose) 
- [Navigation](https://developer.android.com/guide/navigation)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) 
  - [StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/)
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [Hilt](https://dagger.dev/hilt/)
- [Retrofit](https://square.github.io/retrofit/)
- [Material Components for Android](https://github.com/material-components/material-components-android) 
- [Coil](https://coil-kt.github.io/coil/compose/) 
- [JUnit4](https://junit.org/junit4/) 
- [Mockito](https://site.mockito.org/) 
