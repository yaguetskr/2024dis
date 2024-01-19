package com.examen1.backend;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Rows {

    List<Row> Filas=new ArrayList<>();


    public void add(Row fila){
        this.leerjson();
        Filas.add(fila);
        this.guardarjson();
    }

    public void delete(String id) {
        this.leerjson();
        Iterator<Row> iterator = Filas.iterator();


        while (iterator.hasNext()) {

            Row fila = iterator.next();
            if (fila.get_id().equals(id)) {
                iterator.remove();
                System.out.println("Row con _id " + id + " eliminado correctamente.");
                this.guardarjson();
                return; // Sale del método después de eliminar el primer Row con el _id especificado
            }
        }
        System.out.println("No se encontró un Row con _id " + id + ".");
    }

    public void edit(Row edited) {
        this.leerjson();
        String id = edited.get_id();
        for (int i = 0; i < Filas.size(); i++) {
            Row fila = Filas.get(i);
            if (fila.get_id().equals(id)) {
                Filas.set(i, edited);
                System.out.println("Row con _id " + id + " editado correctamente.");
                this.guardarjson();
                return;
            }
        }
        System.out.println("No se encontró un Row con _id " + id + ".");
    }

    public void guardarjson() {
        // Nombre del archivo JSON en resources
        String jsonFileName = "cp-national-datafile.json";

        // Obtén la ruta al directorio "resources"
        ClassLoader classLoader = getClass().getClassLoader();
        File resourcesDirectory = new File(classLoader.getResource("").getFile());
        String resourcesPath = resourcesDirectory.getAbsolutePath();

        // Construye la ruta completa al archivo JSON dentro del directorio "resources"
        String jsonFilePath = Paths.get(resourcesPath, jsonFileName).toString();

        // Serializa la lista Filas a formato JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(Filas);

        // Guarda el JSON en el archivo
        try (OutputStream outputStream = new FileOutputStream(jsonFilePath);
             Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            writer.write(json);
            System.out.println("Contenido del JSON guardado correctamente en: " + jsonFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void leerjson() {
        Gson gson = new Gson();

        // Nombre del archivo JSON en resources
        String jsonFileName = "cp-national-datafile.json";

        // Carga el archivo JSON como un InputStream
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFileName)) {
            if (inputStream != null) {
                // Convierte el InputStream a una lista de objetos Row utilizando Gson
                Type listType = new TypeToken<List<Row>>() {
                }.getType();
                Filas = gson.fromJson(new InputStreamReader(inputStream), listType);

                // Puedes imprimir las filas para verificar que se hayan cargado correctamente
                System.out.println("Contenido del JSON cargado correctamente:");
                for (Row fila : Filas) {

                }
            } else {
                System.err.println("No se pudo encontrar el archivo JSON: " + jsonFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Row> getFilas() {
        return Filas;
    }
}
