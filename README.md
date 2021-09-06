# Objetivo mayoritario
Trabajo realizado en la asignatura Programación y Estructuras de Datos Avanzadas (PREDA) en el Grado de Ingeniería Informática de la UNED. Consiste en calcular el elemento mayoritario de un vector mediante un algoritmo que sigue el esquema "**divide y vencerás**".

El programa se ha desarrollado en Java siguiendo un diseño orientado a objetos.

**Enunciado:**
> _Dado un vector v[1..n] de número naturales, se quiere averiguar si existe un elemento mayoritario, es decir que aparezca al menos n/2+1 veces en el vector (n/2 es división entera)._

> _Por ejemplo, el vector [4,2,1,3,3,3,2,3] no tendría un elemento mayoritario, ya que tiene 8 elementos y ninguno aparece 5 veces o más. En el vector [3,1,4,5,3,3,3,2,3] el 3 sería el elemento mayoritario, ya que el vector tiene 9 elementos y el 3 aparece 5 veces._

> _Se pide diseñar un algoritmo, siguiendo el esquema de divide y vencerás, que resuelva este problema._

# Algoritmo
Tal y como se describe en el enunciado, el algoritmo utilizado para resolver el problema ha sido Divide y Vencerás.

## Descripción del algoritmo Divide y Vencerás
La estrategia de **Divide y Vencerás** es una técnica algorítmica que se basa en la descomposición de un problema en subproblemas de su mismo tipo, lo que permite disminuir la complejidad y en algunos casos, paralelizar la resolución de los mismos.

La **estrategia** del algoritmo es la siguiente:
1. Descomposición del problema en subproblemas de su mismo tipo o naturaleza.
2. Resolución recursiva de los subproblemas.
3. Combinación, si procede, de las soluciones de los subproblemas.

Un **esquema general** de la técnica divide y vencerás es:
```
fun DyV(problema)
  si trivial(problema) entonces
    dev solución-trivial
  sino hacer
    {p1, p2, ..., pk} <- descomponer(problema)
    para i ∈ (1, ..., k) hacer
      si <- DyV(pi)
    fpara
  fsi
  dev combinar(s1, s2, ..., sk)
ffun
```
Cuenta con los siguientes **elementos**:
- **trivial** y **solución-trivial**: un problema es trivial si la solución puede darse sin necesidad de descomponer el problema. En tal caso, una función se encarga de dar la solución a un problema de tamaño suficientemente pequeño.
- **descomponer**: realiza la división del problema en subproblemas. La descomposición exige que los subproblemas sean de menor tamaño que el problema inicial. El tipo de sucesión formada por los sucesivos tamaños del problema y el número de subproblemas determinan la complejidad algorítmica.
- **combinar**: una vez resueltos los subproblemas, lo que queda por abordar desde el punto de vista algorítmico es cómo realizar (si es el caso) la combinación de las soluciones de los subproblemas para obtener la solución del problema final. Si no hay que realizar combinación de soluciones el tipo de problema se conoce como reducción.

## Aplicación del algoritmo Divide y Vencerás al problema
Siguiendo los pasos indicados anteriormente en la exposición del esquema, para calcular el elemento mayoritario en un vector se aplicarán los elementos anteriores de la siguiente manera:
- **Caso trivial**: vector de un único elemento, cuyo elemento mayoritario es él mismo.
- **Descomponer**: la descomposición más eficiente que se plantea de cara a calcular el elemento mayoritario en un vector es descomponer el vector inicial en 2 subproblemas o subvectores del tamaño n/2.
- **Combinar**: En cuanto a la combinación de los subproblemas resueltos, tras la combinación el algoritmo nos debe proporcionar bien el valor mayoritario que hay tras la unión de dos subvectores cuyo valor mayoritario se conoce, o bien indicarnos que éste no existe.

Una vez finalizado, el algoritmo nos proporcionará bien el valor mayoritario (número natural), o bien nos indicará que éste no existe, mediante la devolución de un número que no sea natural, como, por ejemplo -1 (resultado acordado como indicador de inexistencia de elemento mayoritario).

El esquema particular para calcular el elemento mayoritario en un vector en base al algoritmo divide y vencerás es:
```
fun Mayoritario (i, j: natural;, v:vector[1..n] de natural):entero
  var
    m: natural
    s1, s2: entero
  fvar
  si i = j entonces
    dev v[i]
  sino
    m <- (i+j)%2
    s1 <- Mayoritario(i, m, v)
    s2 <- Mayoritario(m+1, j, v)
  dev Combinar(s1, s2, i, j, v)
ffun
```

Donde:
- `v` corresponde al vector donde hay que calcular mayoritario.
- `i`, `j` y `m` corresponden a posiciones del vector.
  -  `i` : posición inicial
  -  `j` : posición final
  -  `m` : posición de la mitad del vector
- `s1` y `s2`:
  - `s1` : valor mayoritario de la primera mitad del vector.
  - `s2` : valor mayoritario de la segunda mitad del vector.

Como se puede apreciar en el esquema anterior, durante la función Mayoritario se realizan llamadas recursivas a la propia función, hasta llegar al caso trivial.

## Funciones auxiliares
Aparte de la función Mayoritario, son necesarias otras dos funciones: función Combinar y función ComprobarMayoritario. 

### Función Combinar
La función Combinar debe recibir los valores `s1` y `s2`, y decidir cuál es el valor mayoritario tras la combinación de dos subvectores. Existen las siguientes entradas posibles a la función Combinar:
- **s1 y s2 valen ambos -1**. En tal caso, incluso si s1 = s2, no hay elemento mayoritario, ya que al menos una de las dos mitades debe tener uno.
- **Uno de los dos valores es -1 y el otro no**. En este caso, un subvector tiene un elemento mayoritario. Para comprobar si es mayoritario en el resto del vector, lo que hacemos es comprobar si ocurre n/2 + 1 más veces.
- **Ambos vectores son iguales y no negativos**. Lo que indica que dicho valor es el elemento mayoritario.
- **Ambos valores son no negativos**. Luego ambos son candidatos a ser mayoritario, y habrá que realizar dos comprobaciones. En caso de que ninguno de los dos sea, devolverá -1.

La función Combinar queda por tanto como sigue:
```
fun Combinar (a, b: entero; i,j: natural; v: vector[1..n] de natural): entero
  si a = -1 ^ b = -1 entonces dev -1 fsi
  si a = -1 ^ b ≠ -1 entonces dev ComprobarMayoritario(i,j,b,v) fsi
  si a ≠ -1 ^ b = -1 entonces dev ComprobarMayoritario(i,j,a,v) fsi
  si a ≠ -1 ^ b ≠ -1 entonces
    si ComprobarMayoritario(i,j,a,v) = a entonces dev a
    sino si ComprobarMayoritario(i,j,b,v) = b entonces dev b
    sino dev -1
    fsi
  fsi
fun
```

### Función ComprobarMayoritario
Por último, la función ComprobarMayoritario recorre el vector y cuenta las apariciones del argumento para ver si son más de n/2; en tal caso, devuelve el valor pasado como argumento para su comprobación, y en caso contrario, devuelve -1.
```
fun ComprobarMayoritario (i,j: natural; x:entero, v:vector [1..n] de natural):entero
  var
    c: natural
  fvar
  c <- 0
  para k <- i hasta j hacer
    si v[k] = x
      entonces c <- c + 1
    fsi
  fpara
  si c > (j-i+1)/2
    entonces dev x
  sino
    dev -1
  fsi
ffun
```


# Código desarrollado
Se han desarrollado las siguientes clases:
- [Main.java](src/Main.java): Clase principal. Contiene el método main. Comprueba los argumentos de entrada y muestra la ayuda del programa.
- [ValidaDatosCalculaMayoritario.java](src/ValidaDatosCalculaMayoritario.java): Clase que contiene los métodos utilizados para la validación de los datos, y lanzamiento del cálculo mayoritario.
- [AlgoritmoDyV.java](src/AlgoritmoDyV.java): Clase que contiene el algoritmo recursivo para el cálculo mayoritario.
- [ArrayAndStrings.java](src/ArrayAndStrings.java): Clase auxiliar. Esta clase proporciona funcionalidades básicas para trabajar con arrays de strings y strings en general.
- [Escritura.java](src/Escritura.java): Clase auxiliar. Esta clase contiene los métodos utilizados para la escritura en consola y escritura en archivo.
- [Lectura.java](src/Lectura.java): Clase auxiliar. Contiene los métodos utilizados para la lectura por consola y lectura por archivo.


# Ejecución
La práctica se invoca usando la siguiente sintaxis:
```
java mayoritario [-t][-h] [fichero_entrada] [fichero_salida]
```
o
```
java –jar mayoritario.jar [-t][-h] [fichero_entrada] [fichero_salida]
```
Los argumentos son los siguientes:
- `-t`: traza cada paso de manera que se describa la aplicación del algoritmo utilizado.
- `-h`: muestra una ayuda y la sintaxis del comando.
- `fichero_entrada`: es el nombre del fichero del que se leen los datos de entrada. Consta de una primera línea que indica el número de elementos en el vector de enteros y una segunda línea con el propio vector. Si la entrada no es correcta, el programa debe indicarlo. En caso de que no exista el fichero, se utilizará la entrada estándar.
- `fichero_salida`: es el nombre del fichero que se creará para almacenar la salida. Si el fichero ya existe, el comando dará un error. Si falta este argumento, el programa muestra el resultado por pantalla.

# Bibliografía
- PROGRAMACIÓN Y ESTRUCTURAS DE DATOS AVANZADAS, 1ª Reimpresión (julio 2016) - Ed. CEURA
