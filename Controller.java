package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void ChangeToItems(MouseEvent mouseEvent) throws IOException {
        Parent changetoItem = FXMLLoader.load(getClass().getResource("ModifyItem.fxml"));
        Scene change1 = new Scene(changetoItem, 800,600);

        Stage window1 = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window1.setScene(change1);
        window1.show();

    }

    @FXML
    private void UserInfoPage(MouseEvent mouseEvent) throws IOException {
        Parent UserInfoPage = FXMLLoader.load(getClass().getResource("UserDB.fxml"));
        Scene change2 = new Scene(UserInfoPage, 800,600);

        Stage window2 = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();

        window2.setScene(change2);
        window2.show();

    }
}
