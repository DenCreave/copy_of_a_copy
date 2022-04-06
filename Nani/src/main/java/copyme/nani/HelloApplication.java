package copyme.nani;

import copyme.controls.MyEventHandler;
import copyme.globalvars.Global;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Global.root=new Group();
        Global.scene=new Scene(Global.root, 1840,1050);

        MyEventHandler myHandle=new MyEventHandler();


        Global.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent keyEvent) {
                myHandle.handleMe(keyEvent);
            }
        });

        stage.setScene(Global.scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}