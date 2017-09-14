package classes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.FormAluno;
import view.FormCurso;
import view.FormListaAlunos;
import view.FormListaCursos;


public class FXMLFormSistemaController implements Initializable {
    
    
    @FXML
    private Label label;
    
    @FXML
    private void abrirCadastroAluno(ActionEvent e) throws Exception{
        new FormAluno().start(new Stage());
    }
    
     @FXML
    private void abrirListaAlunos(ActionEvent e) throws Exception {
        new FormListaAlunos().start(new Stage());
    }
    
    @FXML
    private void abrirCadastroCursos(ActionEvent e) throws Exception{
        new FormCurso().start(new Stage());
    }
    
    @FXML
    private void abrirListaCursos(ActionEvent e) throws Exception {
        new FormListaCursos().start(new Stage());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
