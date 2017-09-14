/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Curso;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.CursoDAO;

public class FormCurso implements Initializable {

   @FXML
   private AnchorPane pane;
   
   @FXML
   TextField ctNomeCurso;

   public static Curso c = new Curso();
   public CursoDAO cDAO = new CursoDAO();
   
   public static String op = "inserir";
   
   static Stage stage;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFormCurso.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sobre");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        this.stage = stage;
    }
    
    
    @FXML
    public void salvarCurso() {
        try {
            c.setNomeCurso(ctNomeCurso.getText());
            cDAO.salvar(c);
            sucess();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
        }
    }
   
    
    public void sucess() {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Aplicação");
        dialogoInfo.setHeaderText("Cadastro de Cursos");
        dialogoInfo.setContentText("Cadastrado com sucesso!");
        dialogoInfo.showAndWait();
    }
   
    @FXML
    public void setCampos(){
        if(op.equals("alterar")){
            ctNomeCurso.setText(c.getNomeCurso());
        }else if(!ctNomeCurso.getText().isEmpty()){
            c.setIdCurso(null);
        }
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCampos();
    }    
    
}
