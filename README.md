# PruebaYape

### Demostraci贸n 馃摬

https://user-images.githubusercontent.com/5515818/222192547-0c6ee7ad-7434-48d1-8a1f-c9cd56a4ff44.mp4

### Resumen t茅cnico 馃洜

- Offline-First: Las aplicaciones sin conexi贸n, si bien a煤n requieren una conexi贸n a los servidores, no necesitan una conexi贸n a internet constante. Los datos de los servidores se descargan en el dispositivo del usuario y a煤n se puede acceder a ellos sin conexi贸n.
- Single Source of Truth (SSOT): Es la pr谩ctica de estructurar modelos de informaci贸n y esquemas asociados de modo que cada elemento de datos se almacene exactamente una vez. Puede tener una aplicaci贸n offline y asegurarse de que sus datos siempre usen una fuente y esa es su base de datos.
- Model-View-ViewModel (MVVM): Es un patr贸n de arquitectura de software que facilita la separaci贸n del desarrollo de la interfaz gr谩fica de usuario (sin utilizar DataBinding). Adem谩s, hay estados de pantalla para manejar los diferentes estados en la interfaz de usuario.
- Coroutines: Una corutina es un patr贸n de dise帽o de simultaneidad que puede usar en Android para simplificar el c贸digo que se ejecuta de forma as铆ncrona.
- Android Architecture Components: Colecci贸n de bibliotecas que ayudan a dise帽ar aplicaciones s贸lidas, comprobables y mantenibles.
- Navigation: Este componente ayuda a implementar la navegaci贸n.
- ViewModel: Almacena datos relacionados con la interfaz de usuario que no se destruyen con los cambios de la interfaz de usuario.
- Room: La biblioteca proporciona una capa de abstracci贸n sobre SQLite para permitir un acceso m谩s s贸lido a la base de datos mientras aprovecha todo el poder de SQLite.
- DatabaseView: Esta anotaci贸n le permite encapsular una consulta en una clase. Room se refiere a estas clases respaldadas por consultas como vistas, y se comportan igual que los objetos de datos simples cuando se usan en una DAO.
- Unit Tests con JUnit4: JUnit es el marco de trabajo o framework de testing para Java m谩s popular. Aunque su nombre sugiere que se centra en las pruebas unitarias, en realidad permite implementar cualquier tipo de prueba automatizada.

### Grafico de arquitectura utilizada 馃獪

![architecture](https://user-images.githubusercontent.com/5515818/222506562-3255c4c4-6cce-43fb-9993-d10183924865.png)

### Librerias utilizadas 馃洜

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
