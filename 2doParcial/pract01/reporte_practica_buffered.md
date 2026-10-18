# Reporte: Medición de tiempo de ejecución con flujos con y sin buffer

**Alumno:** Diego Valadez Almeyda
**Materia:** Concurrencia y Paralelismo, 2.º parcial
**Práctica:** 01

---

## 1. Objetivo

Escribir y leer archivos de texto y binarios en Java con flujos directos (`FileWriter`, `FileReader`, `FileOutputStream`, `FileInputStream`) y con buffer (`BufferedWriter`, `BufferedReader`, `BufferedOutputStream`, `BufferedInputStream`), y medir el tiempo de cada operación con `System.nanoTime()`.

## 2. Metodología

- Código fuente: `EscrituraBuffered.java` (adjunto), con los ocho flujos en métodos separados.
- Cantidad de elementos `n` recibida como argumento: `java EscrituraBuffered <n>`.
- Valores probados: **100,000**, **1,000,000** y **10,000,000**.
- Texto: en cada iteración se escribe la cadena `"Hello World " + i + "\n"`. La lectura se hace carácter por carácter con `read()`.
- Binario: en cada iteración se escribe un byte con `write(i)`. La lectura se hace byte por byte con `read()`.
- En las versiones sin buffer se escribe/lee directamente con el flujo base. En las versiones con buffer se usa el flujo `Buffered*` correspondiente.
- El tiempo se mide con `System.nanoTime()` alrededor del ciclo y se convierte a milisegundos (`ns / 1_000_000.0`).
- Orden de las 8 líneas de salida del programa: `FileWriter`, `BufferedWriter`, `FileReader`, `BufferedReader`, `FileOutputStream`, `BufferedOutputStream`, `FileInputStream`, `BufferedInputStream`.

## 3. Resultados

### 3.1 n = 100,000

| Flujo utilizado | Tamaño (elementos) | Ejec. 1 (ms) | Ejec. 2 (ms) | Ejec. 3 (ms) | Promedio (ms) |
|---|---|---|---|---|---|
| FileWriter | 100,000 | 53.0307 | 44.5891 | 45.043 | 47.5543 |
| BufferedWriter | 100,000 | 14.1895 | 10.5531 | 10.8107 | 11.8511 |
| FileReader | 100,000 | 71.1596 | 42.7829 | 49.5144 | 54.4856 |
| BufferedReader | 100,000 | 18.9583 | 14.0295 | 14.202 | 15.7299 |
| FileOutputStream | 100,000 | 269.6770 | 241.7988 | 237.4783 | 249.6514 |
| BufferedOutputStream | 100,000 | 3.3781 | 3.44 | 3.3136 | 3.3772 |
| FileInputStream | 100,000 | 190.3656 | 176.2473 | 177.2577 | 181.2902 |
| BufferedInputStream | 100,000 | 3.2507 | 3.0504 | 3.1784 | 3.1598 |

### 3.2 n = 1,000,000

| Flujo utilizado | Tamaño (elementos) | Ejec. 1 (ms) | Ejec. 2 (ms) | Ejec. 3 (ms) | Promedio (ms) |
|---|---|---|---|---|---|
| FileWriter | 1,000,000 | 191.2617 | 227.5909 | 193.5538 | 204.1355 |
| BufferedWriter | 1,000,000 | 52.4663 | 49.8272 | 52.4768 | 51.5901 |
| FileReader | 1,000,000 | 325.1425 | 293.3331 | 298.857 | 305.7775 |
| BufferedReader | 1,000,000 | 115.3476 | 114.4876 | 114.406 | 114.7471 |
| FileOutputStream | 1,000,000 | 2223.5500 | 2454.9907 | 2339.1544 | 2339.2317 |
| BufferedOutputStream | 1,000,000 | 10.2431 | 9.9904 | 10.058 | 10.0972 |
| FileInputStream | 1,000,000 | 1636.3934 | 1781.4502 | 1739.7682 | 1719.2039 |
| BufferedInputStream | 1,000,000 | 8.7791 | 9.2339 | 8.8522 | 8.9551 |

### 3.3 n = 10,000,000

| Flujo utilizado | Tamaño (elementos) | Ejec. 1 (ms) | Ejec. 2 (ms) | Ejec. 3 (ms) | Promedio (ms) |
|---|---|---|---|---|---|
| FileWriter | 10,000,000 | 1638.0413 | 1678.4128 | 1680.151 | 1665.5350 |
| BufferedWriter | 10,000,000 | 417.1635 | 431.8869 | 473.2517 | 440.7674 |
| FileReader | 10,000,000 | 3016.8111 | 2883.3246 | 3040.2362 | 2980.1240 |
| BufferedReader | 10,000,000 | 1193.4013 | 1157.9905 | 1170.0917 | 1173.8278 |
| FileOutputStream | 10,000,000 | 24092.8592 | 28458.3588 | 26138.0237 | 26229.7472 |
| BufferedOutputStream | 10,000,000 | 79.3808 | 64.9902 | 71.2713 | 71.8808 |
| FileInputStream | 10,000,000 | 21320.9117 | 17269.9695 | 19575.7814 | 19388.8875 |
| BufferedInputStream | 10,000,000 | 71.0496 | 61.2735 | 75.1253 | 69.1495 |

### 3.4 Factor de mejora al usar buffer (tiempo sin buffer ÷ tiempo con buffer)

| Operación | n = 100,000 | n = 1,000,000 | n = 10,000,000 |
|---|---|---|---|
| Texto, escritura (`FileWriter` / `BufferedWriter`) | 4.0× | 4.0× | 3.8× |
| Texto, lectura (`FileReader` / `BufferedReader`) | 3.5× | 2.7× | 2.5× |
| Binario, escritura (`FileOutputStream` / `BufferedOutputStream`) | 73.9× | 231.7× | 365.0× |
| Binario, lectura (`FileInputStream` / `BufferedInputStream`) | 57.4× | 192.0× | 280.4× |

## 4. Análisis y respuestas

### 4.1 ¿Qué diferencia se observó entre flujos con buffer y sin buffer? ¿Por qué ocurre?

En las 24 mediciones, el flujo con buffer fue más rápido que su equivalente directo. La magnitud de la mejora depende del tipo de flujo:

- **Texto:** la mejora fue de unas **2.5× a 3.9×**.
- **Binario:** la mejora fue enorme: de **≈ 59× a ≈ 304×**. Con n = 10,000,000, `FileOutputStream` tardó cerca de 24 s y `BufferedOutputStream` unos 79 ms.

**Por qué ocurre:** cada llamada a `write(int)` o `read()` en `FileOutputStream` / `FileInputStream` se traduce en una llamada nativa al sistema operativo, y esa llamada es cara comparada con copiar un byte en memoria. Los flujos con buffer acumulan los datos en un arreglo interno (8 KB por defecto) y solo acceden al sistema operativo cuando el buffer se llena (escritura) o se vacía (lectura). Así, un millón de operaciones de un byte se reducen a unas ~120 llamadas al sistema (1,000,000 / 8192) en lugar de un millón.

### 4.2 ¿Cómo varía el tiempo al aumentar `n`? ¿Es lineal? ¿Se comporta igual en lectura y escritura?

- **Tendencia general:** el tiempo crece al aumentar `n`, de forma **aproximadamente lineal para valores grandes**. En la mayoría de los flujos, pasar de 1 millón a 10 millones multiplica el tiempo por 8 a 13, cerca del 10× esperado.
- **Primer salto (100 mil → 1 millón):** en los flujos con buffer y en los de texto, el tiempo crece **menos** de 10× (entre 2.7× y 6.1×). Con pocos datos pesan los costos fijos (arranque de la JVM, compilación JIT, creación de objetos), así que el tiempo no es proporcional a `n`.
- **Flujos directos binarios** (`FileOutputStream`, `FileInputStream`): son los más cercanos a lineales (8.2× a 13.0×), porque el costo por byte es casi constante (una llamada al sistema por byte). El 13.0× de `FileInputStream` en el último salto es mayor que lineal; puede deberse a efectos del sistema como el caché de disco o la sincronización de OneDrive.
- **Lectura vs. escritura:** el comportamiento es similar, pero en texto la lectura fue más lenta que la escritura (por ejemplo, con n = 10,000,000: 3016.8 ms leyendo contra 1638.0 ms escribiendo con flujos directos). En binario con flujos directos, escritura y lectura tardan lo mismo en orden de magnitud (≈ 24 s contra ≈ 21 s). Con buffer, ambas son muy rápidas y comparables (79.4 ms contra 71.0 ms).

### 4.3 ¿Hay diferencia de rendimiento entre caracteres (texto) y bytes (binario)? ¿A qué se debe?

Sí, y el resultado puede parecer contraintuitivo: **sin buffer, el binario fue mucho más lento que el texto** (con n = 1,000,000: 2223.6 ms en `FileOutputStream` contra 191.3 ms en `FileWriter`), a pesar de que el archivo de texto es unas 19 veces más grande. **Con buffer ocurre lo contrario:** el binario es más rápido (10.2 ms en `BufferedOutputStream` contra 52.5 ms en `BufferedWriter`).

Las causas probables son:

1. **`FileWriter` y `FileReader` ya llevan un buffer interno.** Heredan de `OutputStreamWriter` / `InputStreamReader`, que usan un codificador/decodificador con un buffer de bytes de 8 KB. Por eso `FileWriter` no hace una llamada al sistema por carácter, mientras que `FileOutputStream.write(int)` sí hace una por byte. Además, cada `write(String)` mueve unos 19 caracteres de una sola vez.
2. **El texto tiene trabajo extra por elemento:** concatenar `"Hello World " + i + "\n"` crea objetos `String`, y después hay que codificar o decodificar según el *charset* por defecto. Esto explica que, con buffer, el texto sea más lento que el binario.
3. **Volumen de datos distinto:** para el mismo `n`, el texto procesa aproximadamente 19 veces más caracteres que el binario bytes.

### 4.4 ¿Qué tamaño tiene el buffer por defecto? ¿Se puede cambiar?

| Clase | Tamaño por defecto | Unidad |
|---|---|---|
| `BufferedOutputStream` | 8192 | bytes (8 KB) |
| `BufferedInputStream` | 8192 | bytes (8 KB) |
| `BufferedWriter` | 8192 | caracteres |
| `BufferedReader` | 8192 | caracteres |

Sí se puede cambiar, mediante el constructor que recibe el tamaño como segundo argumento:

```java
new BufferedOutputStream(fos, 64 * 1024);
new BufferedInputStream(fis, 64 * 1024);
new BufferedWriter(w, 64 * 1024);
new BufferedReader(r, 64 * 1024);
```

Un buffer mayor reduce aún más las llamadas al sistema, pero consume más memoria y tiene rendimientos decrecientes pasado cierto punto.

### 4.5 ¿Por qué `BufferedReader` ofrece `readLine()` y `BufferedInputStream` no? ¿Qué implicación tiene al leer texto?

`BufferedReader` trabaja con **caracteres ya decodificados**, así que puede reconocer los terminadores de línea (`\n`, `\r`, `\r\n`) y devolver una línea completa como `String`. `BufferedInputStream` trabaja con **bytes crudos** sin ninguna noción de codificación ni de "línea": solo entrega bytes, y asumir que un byte equivale a un carácter sería incorrecto en codificaciones como UTF-8, donde un carácter puede ocupar varios bytes.

**Implicación:** para leer texto por líneas se debe usar un flujo de caracteres, o convertir el de bytes con un puente que indique la codificación:

```java
BufferedReader br = new BufferedReader(
    new InputStreamReader(new FileInputStream(ruta), StandardCharsets.UTF_8));
String linea;
while ((linea = br.readLine()) != null) { ... }
```

Si se usa solo `BufferedInputStream`, el programador tendría que decodificar y separar las líneas por su cuenta.

---

## 6. Conclusión

Los resultados muestran que usar buffer es una de las optimizaciones más simples y efectivas al trabajar con archivos en Java. En flujos de bytes, la diferencia fue de dos a casi tres órdenes de magnitud: escribir 10 millones de bytes pasó de unos 24 segundos con `FileOutputStream` a unos 79 milisegundos con `BufferedOutputStream`. En flujos de texto la mejora fue menor (de 2.5× a 3.9×) porque `FileWriter` y `FileReader` ya incorporan un buffer interno, pero siguió siendo consistente.

La causa es que reducir las llamadas al sistema operativo pesa mucho más que copiar datos en memoria. Por eso, al leer o escribir pocos datos a la vez conviene envolver el flujo en uno con buffer, y para texto usar `BufferedReader` / `BufferedWriter`. Esto no excluye otras optimizaciones: leer o escribir bloques completos (arreglos de `byte[]` o `char[]`) en lugar de elemento por elemento también reduce las llamadas. También es importante cerrar siempre los flujos con `try-with-resources` para garantizar el vaciado del buffer.

