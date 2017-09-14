package view;

import controller.Aluno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.AlunoDAO;

public class FormAluno implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    TextField ctNomeAluno;
    @FXML
    TextField ctIdadeAluno;
    @FXML
    ComboBox cbSexoAluno;

    public static Aluno a = new Aluno();
    public AlunoDAO aDAO = new AlunoDAO();
    
    public static String op = "inserir";
    
    static Stage stage;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLFormAluno.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Sobre");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        this.stage = stage;
    }

    @FXML
    public void salvarAluno() {
        try {
            a.setNomeAluno(ctNomeAluno.getText());
            a.setIdadeAluno(Integer.parseInt(ctIdadeAluno.getText()));
            a.setSexoAluno(cbSexoAluno.getSelectionModel().getSelectedItem().toString());
            aDAO.salvar(a);
            sucess();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro " + e.getMessage());
        }
    }

    

    public void sucess() {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Aplicação");
        dialogoInfo.setHeaderText("Cadastro de aluno");
        dialogoInfo.setContentText("Cadastrado com sucesso!");
        dialogoInfo.showAndWait();
    }
    
    @FXML
    public void setCampos(){
        if(op.equals("alterar")){
            ctNomeAluno.setText(a.getNomeAluno());
            ctIdadeAluno.setText(String.valueOf(a.getIdadeAluno()));
            cbSexoAluno.setValue(a.getSexoAluno());
        }else if(op.equals("inserir")){
            a.setIdAluno(null);
            ctNomeAluno.setText(null);
            cbSexoAluno.setValue(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCampos();
    }
}
