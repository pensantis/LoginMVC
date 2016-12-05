package org.academiadecodigo.loginmvcmaven.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.academiadecodigo.loginmvcmaven.Navigation;
import org.academiadecodigo.loginmvcmaven.model.Role;
import org.academiadecodigo.loginmvcmaven.model.User;
import org.academiadecodigo.loginmvcmaven.service.ServiceRegistry;
import org.academiadecodigo.loginmvcmaven.service.user.UserService;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


/**
 * Created by codecadet on 15/11/16.
 */
public class LoginController  implements Initializable {
    private UserService userService;
    private boolean isOnLogginMode = true;

    @FXML
    private BorderPane mainPane;

    @FXML
    private ImageView pic;

    @FXML
    private VBox vBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane griPane;

    @FXML
    private Label userLabel;

    @FXML
    private Label passLabel;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passText;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField emailText;

    @FXML
    private Hyperlink changeViewButton;

    @FXML
    private ToolBar toolBar;

    @FXML
    private Button submit;

    @FXML
    private Label info;

    @FXML
    void changeView(ActionEvent event) {
        if (isOnLogginMode) {
            onRegister();
        } else {
            onLogin();
        }
    }

    @FXML
    void submit(ActionEvent event) {
        if (isOnLogginMode){
            doLogin();
        }else {
            doRegister();
        }

    }

    /*@FXML
    void initialize(){

    }*/

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private void onLogin() {
        submit.setText("Login");
        emailLabel.setVisible(false);
        emailText.setVisible(false);
        changeViewButton.setText("Register");
        info.setVisible(false);
        userText.setText("");
        passText.setText("");
        emailText.setText("");

        isOnLogginMode = true;

    }

    private void onRegister() {
        submit.setText("Register");
        emailLabel.setVisible(true);
        emailText.setVisible(true);
        changeViewButton.setText("Login");
        info.setVisible(false);
        userText.setText("");
        passText.setText("");
        emailText.setText("");

        isOnLogginMode = false;
    }

    private void doLogin(){
        if(userService.authenticate(userText.getText(),passText.getText())){
            //info.setText("Logged Successfully");
            //info.setTextFill(Color.GREEN);
            userText.setText("");
            passText.setText("");

            Navigation.getInstance().loadScreen("secondscreen");
            //info.setTextFill();
        }
        else {
            info.setText("Login failed!");
            info.setTextFill(Color.RED);
        }
        info.setVisible(true);
    }

    private void doRegister(){
        String passRegex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        String emailRegex = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";

        if(userService.userExist(userText.getText())){
            info.setText("User already registered");
            info.setTextFill(Color.RED);
            info.setVisible(true);
            return;
        }
        if(userText.getText().isEmpty() || passText.getText().isEmpty() || emailText.getText().isEmpty() ){
            info.setText("Please fill all Fields");
            info.setTextFill(Color.RED);
            info.setVisible(true);
            return;
        }
        if(!passText.getText().matches(passRegex) || !emailText.getText().matches(emailRegex)){
            if (!passText.getText().matches(passRegex) && !emailText.getText().matches(emailRegex)){
                info.setText("Invalid password and email");
                info.setTextFill(Color.RED);
                info.setVisible(true);
                return;
            }

            if (!passText.getText().matches(passRegex)){
                info.setText("Invalid password");
                info.setTextFill(Color.RED);
                info.setVisible(true);
                return;
            }
            if(!emailText.getText().matches(emailRegex)){
                info.setText("Invalid email");
                info.setTextFill(Color.RED);
                info.setVisible(true);
                return;
            }
        }

        User user = new User(userText.getText(), passText.getText(), emailText.getText());
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("user"));
        user.setRoles(roles);

        userService.addUser(user);
        info.setText("Registered with Success");
        info.setTextFill(Color.GREEN);
        info.setVisible(true);
        userText.setText("");
        passText.setText("");
        emailText.setText("");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userService = (UserService) ServiceRegistry.getServiceRegistry().getService(UserService.class.getName());
    }
}
