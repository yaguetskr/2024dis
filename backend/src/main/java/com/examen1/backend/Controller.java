package com.examen1.backend;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    Rows archivo1=new Rows();
    Groups archivo2=new Groups();

    @GetMapping("/getrows")
    public ResponseEntity<Rows> getrows(){
        archivo1.leerjson();
        return new ResponseEntity<>(archivo1, HttpStatus.OK);

    }

    @GetMapping("/getgroups")
    public ResponseEntity<Groups> getgroups(){
        archivo2.leerjson();
        return new ResponseEntity<>(archivo2, HttpStatus.OK);

    }
    @GetMapping("/edit")
    public void edit(@RequestBody Row datosModificados) {
        archivo1.edit(datosModificados);
   }

    @PostMapping("/add")
    public void add(@RequestBody Row datosModificados) {
        archivo1.add(datosModificados);
    }

    @GetMapping(path="/delete/{id}")
    public void delete(@PathVariable String id) {
        archivo1.delete(id);
    }

}













