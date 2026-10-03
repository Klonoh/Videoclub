# VideoClub

Sistema de información desarrollado en Java para la gestión de un videoclub. El proyecto permite administrar clientes y películas, registrar arriendos y devoluciones, consultar historiales y generar recomendaciones personalizadas a partir de la información almacenada.

El sistema puede ejecutarse mediante **consola** o mediante una **interfaz gráfica con Swing**, según la opción elegida al iniciar la aplicación.

## Integrantes

- Sebastián Arriagada
- Joaquín Carrillo
- Benjamín Beltrán

## Características principales

- Registro, listado, búsqueda, edición y eliminación de clientes.
- Registro, listado, búsqueda, edición y eliminación de películas.
- Búsqueda de películas por ID, título o género.
- Registro de arriendos y devoluciones.
- Consulta, búsqueda, edición y eliminación de arriendos asociados a un cliente.
- Control de stock disponible de películas.
- Historial de arriendos por cliente.
- Generación de recomendaciones personalizadas.
- Registro de recomendaciones exitosas.
- Persistencia mediante archivos CSV.
- Carga automática al iniciar y guardado al salir.
- Interfaz de consola.
- Interfaz gráfica con Java Swing.
- Excepciones propias del dominio.
- Herencia, polimorfismo, sobrecarga y sobreescritura.
- Documentación Javadoc en `docs/`.

## Tecnologías utilizadas

- Java
- Java Collections Framework
- Java Swing
- CSV
- Javadoc
- PlantUML
- Git y GitHub

El proyecto no utiliza librerías externas ni gestores de dependencias como Maven o Gradle.

## Requisitos

Para compilar y ejecutar el proyecto se necesita:

- **JDK 11**
- Terminal, PowerShell, CMD o un IDE compatible con Java

El proyecto fue probado utilizando **JDK 11**.

Para comprobar la instalación:

```bash
java -version
javac -version
```

## Instalación de Java

### Windows

1. Instalar un JDK, por ejemplo Temurin/OpenJDK u Oracle JDK.
2. Verificar que Java esté agregado al `PATH`.
3. Abrir CMD o PowerShell y ejecutar:

```bash
java -version
javac -version
```

Si `javac` no es reconocido, debe agregarse la carpeta `bin` del JDK a la variable de entorno `PATH`.

### Linux

En distribuciones basadas en Ubuntu/Debian:

```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

Luego:

```bash
java -version
javac -version
```

### macOS

Con Homebrew:

```bash
brew install openjdk@11
```

Luego:

```bash
java -version
javac -version
```

## Obtención del proyecto

### Opción 1: archivo ZIP entregado por el Aula Virtual

Si el proyecto fue descargado desde el Aula Virtual:

1. Descargar el archivo `.zip` entregado junto con la presentación y el reporte del proyecto.
2. Extraer el contenido del archivo en una carpeta local.
3. Abrir una terminal, PowerShell o CMD dentro de la carpeta extraída.
4. Continuar con la sección **Compilación** de este README.

### Opción 2: clonar el repositorio

```bash
git clone https://github.com/Klonoh/Videoclub.git
cd Videoclub
```

### Opción 3: descargar como ZIP desde GitHub

1. Abrir el repositorio en GitHub.
2. Seleccionar **Code > Download ZIP**.
3. Extraer el archivo.
4. Abrir una terminal en la carpeta extraída.

## Compilación

Desde la raíz del proyecto:

```bash
javac *.java
```

Esto genera los archivos `.class` correspondientes.

Los archivos compilados están excluidos del repositorio mediante `.gitignore`.

## Ejecución

Una vez compilado:

```bash
java Main
```

Al iniciar se mostrará:

```text
=== Video Club ===
1. Usar Consola
2. Usar Ventanas
Seleccione modo:
```

Ingrese:

- `1` para la interfaz de consola.
- `2` para la interfaz gráfica.

## Ejecución desde un IDE

También puede ejecutarse desde IntelliJ IDEA, Eclipse, NetBeans o Visual Studio Code.

Pasos generales:

1. Abrir la carpeta del proyecto.
2. Configurar un JDK.
3. Abrir `Main.java`.
4. Ejecutar el método `main`.

La clase de entrada es:

```java
Main
```

## Uso mediante consola

La interfaz de consola incluye:

```text
1. Agregar Cliente
2. Agregar Pelicula
3. Listar Clientes
4. Listar Peliculas
5. Buscar Cliente
6. Buscar Pelicula
7. Editar Cliente
8. Editar Pelicula
9. Eliminar Cliente
10. Eliminar Pelicula
11. Realizar Arriendo
12. Realizar Devolucion
13. Generar Recomendaciones
14. Listar Arriendos
15. Buscar Arriendo
16. Editar Arriendo
17. Eliminar Arriendo
18. Estadisticas
0. Salir
```

Para finalizar correctamente debe seleccionarse la opción `0`.

## Uso mediante interfaz gráfica

Al seleccionar la opción `2`, se abre una interfaz gráfica desarrollada con Java Swing.

La ventana ofrece las mismas operaciones principales mediante botones y cuadros de diálogo.

Al cerrarla, el sistema guarda los datos antes de finalizar.

## Persistencia de datos

El sistema utiliza persistencia en modo batch mediante:

```text
clientes.csv
peliculas.csv
arriendos.csv
recomendaciones.csv
```

Los datos se cargan al iniciar la aplicación y se guardan al finalizar.

### Primera ejecución

Si no existe ninguno de los cuatro archivos CSV, `PersistenciaCSV` carga un conjunto de datos iniciales y los guarda automáticamente.

Si los archivos ya existen, se utiliza la información contenida en ellos.

### Importante

Los CSV deben mantenerse en el directorio desde el cual se ejecuta el programa.

Si se desea conservar la información entre ejecuciones, no deben eliminarse.

## Sistema de recomendaciones

El sistema genera recomendaciones utilizando el historial del cliente y la información registrada.

Para clientes con historial se consideran:

- frecuencia de arriendos por género;
- recomendaciones anteriores exitosas;
- stock disponible;
- películas actualmente arrendadas;
- recomendaciones que ya resultaron exitosas.

Las recomendaciones exitosas tienen peso adicional. Una recomendación pendiente puede volver a aparecer sin generar registros duplicados.

Para clientes sin historial, el sistema utiliza información global de popularidad basada en cantidad de arriendos y recomendaciones exitosas.

La lógica principal se encuentra en:

```text
GestorRecomendaciones.java
```

## Gestión de arriendos

La lógica específica de arriendos se encuentra en:

```text
GestorArriendos.java
```

Esta clase se encarga de:

- registrar arriendos;
- procesar devoluciones;
- buscar arriendos;
- editar arriendos;
- eliminar arriendos;
- consultar arriendos activos;
- aportar información al sistema de recomendaciones.

## Estructura del proyecto

```text
Videoclub/
│
├── Main.java
├── VideoClub.java
│
├── Cliente.java
├── Pelicula.java
├── InteraccionClientePelicula.java
├── Arriendo.java
├── Recomendacion.java
│
├── GestorArriendos.java
├── GestorRecomendaciones.java
│
├── VistaConsola.java
├── VistaGrafica.java
├── PanelEstadisticas.java
├── Formateador.java
│
├── PersistenciaCSV.java
│
├── ClienteNoEncontradoException.java
├── PeliculaNoDisponibleException.java
│
├── clientes.csv
├── peliculas.csv
├── arriendos.csv
├── recomendaciones.csv
│
├── UML.png
├── UML.puml
├── docs/
└── .gitignore
```

## Descripción de las clases principales

### `Main`

Punto de entrada. Inicializa el sistema, carga los datos y solicita la interfaz.

### `VideoClub`

Clase central del modelo. Administra las entidades principales y coordina los gestores.

### `Cliente`

Representa a un cliente y mantiene sus datos e historial de arriendos.

### `Pelicula`

Representa una película del catálogo y administra su información y stock.

### `InteraccionClientePelicula`

Clase abstracta con la información común de las interacciones entre cliente y película.

### `Arriendo`

Representa un arriendo y registra fechas y estado de devolución.

### `Recomendacion`

Representa una recomendación y permite registrar si fue exitosa.

### `GestorArriendos`

Concentra la lógica de arriendos, devoluciones y consultas relacionadas.

### `GestorRecomendaciones`

Concentra el cálculo y registro de recomendaciones.

### `VistaConsola`

Implementa la interacción mediante terminal.

### `VistaGrafica`

Implementa la interacción mediante Java Swing.

### `PanelEstadisticas`

Componente gráfico que representa la cantidad de arriendos por género mediante un gráfico de barras.

### `PersistenciaCSV`

Gestiona la lectura y escritura de los archivos CSV.

### `Formateador`

Proporciona representaciones legibles de objetos para mostrarlos al usuario.

## Diseño orientado a objetos

El proyecto utiliza:

- encapsulamiento mediante atributos privados;
- herencia mediante `InteraccionClientePelicula`;
- polimorfismo en `Arriendo` y `Recomendacion`;
- sobreescritura de `estaFinalizada()`;
- sobrecarga de métodos de búsqueda y formateo;
- separación de responsabilidades mediante gestores;
- copias defensivas de colecciones.

## Excepciones

### `ClienteNoEncontradoException`

Se utiliza cuando una operación requiere un cliente inexistente.

### `PeliculaNoDisponibleException`

Se utiliza cuando una película no existe, no está disponible o no existe un arriendo activo válido para completar una operación.

## UML

El repositorio incluye:

```text
UML.png
UML.puml
```

`UML.puml` puede abrirse y editarse con PlantUML.

## Documentación Javadoc

La documentación generada se encuentra en:

```text
docs/index.html
```

Para consultarla localmente, abrir ese archivo en un navegador.

Puede regenerarse con:

```bash
javadoc -d docs *.java
```

## Limpieza de archivos compilados

### Windows PowerShell

```powershell
Remove-Item *.class
```

### Linux/macOS

```bash
rm -f *.class
```

## Problemas frecuentes

### `java` o `javac` no se reconoce como comando

El JDK no está instalado correctamente o la carpeta `bin` no está en el `PATH`.

Verifique:

```bash
java -version
javac -version
```

### La interfaz gráfica no abre

Compruebe que el programa se ejecuta en un entorno con interfaz gráfica disponible. Swing forma parte del JDK y no requiere dependencias externas.

### Los datos no aparecen al reiniciar

Verifique que:

- existan los archivos CSV;
- el programa tenga permisos de escritura;
- se ejecute desde el mismo directorio que contiene los CSV;
- la aplicación haya finalizado correctamente.

### Error de formato en los CSV

La modificación manual de los archivos puede causar errores si se altera la estructura esperada.

## Repositorio

Repositorio oficial:

https://github.com/Klonoh/Videoclub

## Contexto académico

Proyecto desarrollado para la asignatura **Programación Avanzada** de Ingeniería Civil Informática, Pontificia Universidad Católica de Valparaíso.
