package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProdutoFx extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ProdutoFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Meu primeiro testinho CRUD");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
