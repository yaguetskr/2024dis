package org.vaadin.example;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    private Tabs tabs; // Componente de Vaadin para manejar pestañas.

    // Constructor de MainView con inyección de dependencia de un servicio (APIService).
    public MainView() throws Exception {
        // Creación de pestañas.
        Tab pestana1 = new Tab("Pestaña1");
        Tab pestana2 = new Tab("Pestaña2");

        // Creación del componente Tabs con las pestañas definidas.
        tabs = new Tabs(pestana1,pestana2);

        // Añade un listener para cambiar el contenido cuando se selecciona una pestaña diferente.
        tabs.addSelectedChangeListener(event -> {
            if (event.getSelectedTab() == pestana1) {
                try {
                    // Establece el contenido de la primera pestaña.
                    this.setTabContent(new FilasView());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (event.getSelectedTab() == pestana2) {
                try {
                    // Establece el contenido de la segunda pestaña.
                    this.setTabContent(new GroupsView());
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // Añade las pestañas y el contenido inicial al layout.
        this.add(tabs,(new FilasView()));


    }
    // Método para establecer el contenido de la pestaña actual.
    private void setTabContent(VerticalLayout content) {
        // Limpia el contenido actual y añade el nuevo contenido
        removeAll();
        add(tabs, content);
    }
}