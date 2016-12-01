package org.academiadecodigo.loginmvcmaven.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import org.academiadecodigo.loginmvcmaven.Navigation;
import org.academiadecodigo.loginmvcmaven.service.ServiceRegistry;
import org.academiadecodigo.loginmvcmaven.service.user.UserService;


import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by codecadet on 16/11/16.
 */
public class SecondScreenController implements Initializable{

    private UserService userService;

    @FXML
    private BorderPane mainPane;

    @FXML
    private ImageView pic;

    @FXML
    private MenuBar menu;

    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem backButton;

    @FXML
    void backScreen(ActionEvent event) {
        Navigation.getInstance().back();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = (UserService) ServiceRegistry.getServiceRegistry().getService(UserService.class.getName());
    }
}
