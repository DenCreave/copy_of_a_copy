package copyme.nani;

import copyme.controls.MyEventHandler;
import copyme.controls.MyLoader;
import copyme.globalvars.Global;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

/**
 * the default main, tho another main calls it
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Global.root=new Group();
        Global.scene=new Scene(Global.root, 1840,1050);
        MyLoader myLoader=new MyLoader();
        myLoader.startup();



        MyEventHandler myHandle=new MyEventHandler();


        Global.scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent keyEvent)
            {

                myHandle.handleMe(keyEvent.getCode());

            }
        });

        stage.setScene(Global.scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("file:textures/pix.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}