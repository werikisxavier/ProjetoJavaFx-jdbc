
package gui.util;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;


public class Utils {
    
    //receber o palco do de onde o botao foi acionado
    public static Stage currentStage (ActionEvent event){
        return (Stage) ((Node)event.getSource()).getScene().getWindow();
    }
    
}
