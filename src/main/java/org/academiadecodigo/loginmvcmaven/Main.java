package org.academiadecodigo.loginmvcmaven;

import org.academiadecodigo.loginmvcmaven.Persistence.hibernate.HibernateSessionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.academiadecodigo.loginmvcmaven.Persistence.jdbc.ConnectionManager;
import org.academiadecodigo.loginmvcmaven.service.Service;
import org.academiadecodigo.loginmvcmaven.service.ServiceRegistry;
import org.academiadecodigo.loginmvcmaven.service.user.HibernateUserService;
import org.academiadecodigo.loginmvcmaven.service.user.UserService;

/**
 * Created by codecadet on 15/11/16.
 */
public class Main extends Application {
    private FXMLLoader fxmlLoader;
    private Parent root;
    private ConnectionManager connectionManager;
    private UserService userService;

    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void init() {

        /*userService = new UserServiceImplementation(
                new HibernateUserDao(),
                new HibernateRoleDao(),
                new HibernateTransactionManager());
        ServiceRegistry.getServiceRegistry()
                .registerService(UserService.class.getSimpleName()),
        userService);*/


        Service userService = new HibernateUserService();

        ServiceRegistry.getServiceRegistry().addService(userService);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {





        Navigation.getInstance().setStage(primaryStage);

        Navigation.getInstance().loadScreen("register");



    }

    @Override
    public void stop() throws Exception {
        //connectionManager.close();
        HibernateSessionManager.close();
        System.out.println("Connection Close");
    }
}
