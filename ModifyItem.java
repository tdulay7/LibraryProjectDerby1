package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaException;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;



public class ModifyItem implements Initializable {
    private static final String DB_URL ="jdbc:derby://localhost:1527/LibraryDatabase;create=true";
    @FXML
    private RadioButton Book;
    @FXML
    private ToggleGroup SelectItem;
    @FXML
    private RadioButton Media;
    @FXML
    private TextField Title;
    @FXML
    private TextField ISBN;
    @FXML
    private TextField Author;
    @FXML
    private TextField Location;
    @FXML
    private TextField Quantity;
    @FXML
    private ListView ItemList;
    private ObservableList<Items> list1;
    @FXML
    private Button ViewList;
    @FXML
    private Button create;
    @FXML
    private Button AddBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list1 = FXCollections.observableArrayList();
        ItemList.setItems(list1);
        }
    @FXML
    private void AddItem(MouseEvent mouseEvent) {


        try {
            Connection conn = DriverManager.getConnection(DB_URL);;
            PreparedStatement stmt = null;
            SelectItem.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
            {
                @Override
                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1)
                {

                }
            });
            String title = Title.getText();
            String isbn = ISBN.getText();
            String author = Author.getText();
            String location = Location.getText();
            Integer quantity = Integer.valueOf(Quantity.getText());
            RadioButton type1 = (RadioButton)Media.getToggleGroup().getSelectedToggle();
            String gettype = type1.getText();
            stmt = conn.prepareStatement("INSERT INTO ITEMSLIST ( Title, ISBN, Author, Location, Quantity, Type) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, title);
            stmt.setString(2,isbn); ;
            stmt.setString(3,author) ;
            stmt.setString(4,location); ;
            stmt.setInt(5,quantity);
            stmt.setString(6, gettype);

            stmt.executeUpdate();
            System.out.println("record inserted into table");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        Title.clear();
        ISBN.clear();
        Author.clear();
        Location.clear();
        Quantity.clear();
        SelectItem.getSelectedToggle().setSelected(false);

    }


    @FXML
    private void ViewItems(MouseEvent mouseEvent) {
        ResultSet resultSet = null;
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement statement = conn.prepareStatement("SELECT * from ITEMSLIST");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer number = (resultSet.getInt("Number"));
                String title = (resultSet.getString("Title"));
                String isbn = (resultSet.getString("ISBN"));
                String Author= (resultSet.getString("Author"));
                String location = (resultSet.getString("Location"));
                Integer quantity = (resultSet.getInt("Quantity"));
                String type = (resultSet.getString("Type"));
                    Items s = new Items(number, title, isbn, Author, location, quantity, type);
                    list1.add(s);
                }

            resultSet.close();
            conn.close();
        } catch (Exception ex) {
            var msg = ex.getMessage();
            System.out.println(msg);
        }


    }
}



