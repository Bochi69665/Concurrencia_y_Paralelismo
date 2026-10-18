import java.io.*;

public class EscrituraBuffered {

    public static void main(String[] args) {
        int n = 1_000_000;

        if (args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        String rutaTexto   = "archivo_texto.txt";
        String rutaBinario = "archivo_binario.txt";

        escribirFileWriter(rutaTexto, n);
        escribirBufferedWriter(rutaTexto, n);
        leerFileReader(rutaTexto);
        leerBufferedReader(rutaTexto);
        escribirFileOutputStream(rutaBinario, n);
        escribirBufferedOutputStream(rutaBinario, n);
        leerFileInputStream(rutaBinario);
        leerBufferedInputStream(rutaBinario);
    }

    // ---------- Texto: escritura ----------

    private static void escribirFileWriter(String ruta, int n){
        try (Writer w = new FileWriter(ruta);
             BufferedWriter bw = new BufferedWriter(w)) 
            {

            long inicio = System.nanoTime();
            
            for (int i = 0; i < n; i++) {
                w.write("Hello World " + i + "\n");
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirBufferedWriter(String ruta, int n){
        try (Writer w = new FileWriter(ruta);
             BufferedWriter bw = new BufferedWriter(w)) 
            {

            long inicio = System.nanoTime();
            
            for (int i = 0; i < n; i++) {
                bw.write("Hello World " + i + "\n");
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- Texto: lectura ----------

    private static void leerFileReader(String ruta){
        try (Reader r = new FileReader(ruta);
             BufferedReader br = new BufferedReader(r))
        {
            long inicio = System.nanoTime();

            int c;
            while ((c = r.read()) != -1) {
                // solo va a leer
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerBufferedReader(String ruta){
        try (Reader r = new FileReader(ruta);
             BufferedReader br = new BufferedReader(r))
        {
            long inicio = System.nanoTime();

            int c;
            while ((c = br.read()) != -1) {
                // solo va a leer
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- Binario: escritura ----------

    private static void escribirFileOutputStream(String ruta, int n){
        try (OutputStream fos = new FileOutputStream(ruta);
             BufferedOutputStream bos = new BufferedOutputStream(fos))
        {
            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                fos.write(i);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirBufferedOutputStream(String ruta, int n){
        try (OutputStream fos = new FileOutputStream(ruta);
             BufferedOutputStream bos = new BufferedOutputStream(fos))
        {
            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                bos.write(i);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // ---------- Binario: lectura ----------

    private static void leerFileInputStream(String ruta){
        try (FileInputStream fis = new FileInputStream(ruta);
             BufferedInputStream bis = new BufferedInputStream(fis)
            ) {

            long inicio = System.nanoTime();
                
            int b;
            while ((b = fis.read()) != -1) {
                // solo va a leer
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerBufferedInputStream(String ruta){
        try (FileInputStream fis = new FileInputStream(ruta);
             BufferedInputStream bis = new BufferedInputStream(fis)
            ) {

            long inicio = System.nanoTime();
                
            int b;
            while ((b = bis.read()) != -1) {
                // solo va a leer
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: "  + duracionMs);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}