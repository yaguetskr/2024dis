package org.vaadin.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

@Route
public class FilasView extends VerticalLayout {

    public FilasView() throws Exception {
        API api=new API();
        Grid<Row> tabla=new Grid<>(Row.class);
        tabla.setItems(api.getFilas().filas);
        add(tabla);
        var ref = new Object() {
            String _id;
        };

        TextField tfmscode = new TextField("mscode");
        TextField tfyear = new TextField("year");
        TextField tfestCode = new TextField("estCode");
        NumberField tfestimate = new NumberField("estimate");
        NumberField tfse = new NumberField("se");
        NumberField tflowerCIB = new NumberField("lowerCIB");
        NumberField tfupperCIB = new NumberField("upperCIB");
        TextField tflag = new TextField("flag");

        Button btAceptarAniadir = new Button("Aceptar");
        Button btAceptarEliminar = new Button("Aceptar");
        Button btAniadir = new Button("Añadir");
        Button btEditar = new Button("Modificar");
        Button btEliminar = new Button("Eliminar");

        Dialog layoutDatosVer = new Dialog();
        VerticalLayout layoutCamposDatos = new VerticalLayout();

        layoutCamposDatos.add(tfmscode,tfyear,tfestCode,tfestimate,tfse,tflowerCIB,tfupperCIB,tflag); //añadir todos los TextField
        layoutDatosVer.add(layoutCamposDatos);
        add(btAniadir);


        tabla.addItemClickListener(e->{ //Cuando añadimos un elemento al grid usuario

            layoutDatosVer.open();

            //Limpiar todos los TextFields
            tfmscode.clear();
            tfyear.clear();
            tfestCode.clear();
            tfestimate.clear();
            tfse.clear();
            tflowerCIB.clear();
            tfupperCIB.clear();
            tflag.clear();

            //...

            //Mostrar las cajas de texto
            //Colocar valores en las cajas de texto en todos los TextFields
            tfmscode.setValue(e.getItem().getMscode());
            tfyear.setValue(e.getItem().getYear());
            tfestCode.setValue(e.getItem().getEstCode());
            tfestimate.setValue(e.getItem().getEstimate().doubleValue());
            tfse.setValue(e.getItem().getEstimate().doubleValue());
            tflowerCIB.setValue(e.getItem().getLowerCIB().doubleValue());
            tfupperCIB.setValue(e.getItem().getUpperCIB().doubleValue());
            tflag.setValue(e.getItem().getFlag());
            //...
            ref._id = e.getItem().get_id();

            layoutDatosVer.remove(btAceptarAniadir);
            layoutDatosVer.remove(btAceptarEliminar);
            layoutDatosVer.add(btEditar);
            layoutDatosVer.add(btEliminar);

        });

        btAniadir.addClickListener(e->{
            layoutDatosVer.open();
            //Limpiar todos los TextFields
            tfmscode.clear();
            tfyear.clear();
            tfestCode.clear();
            tfestimate.clear();
            tfse.clear();
            tflowerCIB.clear();
            tfupperCIB.clear();
            tflag.clear();
            //...

            layoutDatosVer.remove(btEditar);
            layoutDatosVer.remove(btAceptarEliminar);
            layoutDatosVer.remove(btEliminar);
            layoutDatosVer.add(btAceptarAniadir);
        });

        btEditar.addClickListener(e->{
            layoutDatosVer.close();

            //Creamos un objeto con los datos de los TextFields
            Row datoModificado = new Row(ref._id,tfmscode.getValue(),tfyear.getValue(),tfestCode.getValue(),tfestimate.getValue().floatValue(),tfse.getValue().floatValue(),tflowerCIB.getValue().floatValue(),tfupperCIB.getValue().floatValue(),tflag.getValue()); //para todos los atributos
            //Si algún dato fuera numérico pondríamos:
            //	x Integer.parseInt(tfAtributoX.getValue())
            //	x Float.parseFloat(tfAtributoX.getValue())

            try {
                api.edit(datoModificado); //Llamo a la api
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            UI.getCurrent().getPage().reload(); //recargamos la interfaz para que muestre los datos actualizados
        });


        btEliminar.addClickListener(e->{
            layoutDatosVer.close();

            try {
                api.delete(ref._id);  //Llamo a la api
                System.out.println("prueba");
                System.out.println(ref._id);
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            UI.getCurrent().getPage().reload(); //recargamos la interfaz para que muestre los datos actualizados
        });

        btAceptarAniadir.addClickListener(e->{
            layoutDatosVer.close();

            Row datoNuevo = new Row(UUID.randomUUID().toString(),tfmscode.getValue(),tfyear.getValue(),tfestCode.getValue(),tfestimate.getValue().floatValue(),tfse.getValue().floatValue(),tflowerCIB.getValue().floatValue(),tfupperCIB.getValue().floatValue(),tflag.getValue()); //para todos los atributos
            //Si algún dato fuera numérico pondríamos:
            //	x Integer.parseInt(tfAtributoX.getValue())
            //	x Float.parseFloat(tfAtributoX.getValue())

            try {
                api.add(datoNuevo); //Llamo a la api
            } catch (URISyntaxException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            UI.getCurrent().getPage().reload(); //recargamos la interfaz para que muestre los datos actualizados

        });

        btAceptarEliminar.addClickListener(e->{
            layoutDatosVer.close();
        });

    }
}
