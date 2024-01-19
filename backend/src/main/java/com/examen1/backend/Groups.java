package com.examen1.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
public class Groups {
    List<Row> MEASA;
    List<Row> MEASB;
    List<Row> MEASC;
    List<Row> MEASE;
    List<Row> MEASF;
    List<Row> MEASG;
    List<Row> MEASH;
    List<Row> MEASI;
    List<Row> MEASJ;

    public void leerjson() {
        Gson gson = new Gson();

        // Nombre del archivo JSON en resources
        String jsonFileName = "MsCode_json.json";

        // Carga el archivo JSON como un InputStream
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFileName)) {
            if (inputStream != null) {
                // Convierte el InputStream a un objeto Groups utilizando Gson
                Groups groups = gson.fromJson(new InputStreamReader(inputStream), Groups.class);

                // Copia los campos del objeto Groups actual con los campos del objeto cargado
                MEASA = groups.MEASA;
                MEASB = groups.MEASB;
                MEASC = groups.MEASC;
                MEASE = groups.MEASE;
                MEASF = groups.MEASF;
                MEASG = groups.MEASG;
                MEASH = groups.MEASH;
                MEASI = groups.MEASI;
                MEASJ = groups.MEASJ;

                // Puedes imprimir cada lista para verificar que se hayan cargado correctamente
                System.out.println("Contenido del JSON cargado correctamente:");
                System.out.println("MEASA: " + MEASA);
                System.out.println("MEASB: " + MEASB);
                // ... (repites para cada lista)

            } else {
                System.err.println("No se pudo encontrar el archivo JSON: " + jsonFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarjson() {
        // Nombre del archivo JSON en resources
        String jsonFileName = "MsCode_json.json";

        // Serializa el objeto Groups a formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        // Guarda el JSON en el archivo
        try (OutputStream outputStream = new FileOutputStream(jsonFileName);
             Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            writer.write(json);
            System.out.println("Contenido del JSON guardado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Row> getMEASA() {
        return MEASA;
    }

    public List<Row> getMEASB() {
        return MEASB;
    }

    public List<Row> getMEASC() {
        return MEASC;
    }

    public List<Row> getMEASE() {
        return MEASE;
    }

    public List<Row> getMEASF() {
        return MEASF;
    }

    public List<Row> getMEASG() {
        return MEASG;
    }

    public List<Row> getMEASH() {
        return MEASH;
    }

    public List<Row> getMEASI() {
        return MEASI;
    }

    public List<Row> getMEASJ() {
        return MEASJ;
    }
}
