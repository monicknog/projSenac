/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Curso;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.CursoDAO;

/**
 * FXML Controller class
 *
 * @author aluno
 */
public class FormListaCursos implements Initializable {

    Curso c = new Curso();
    CursoDAO cDAO = new CursoDAO();
    List<Curso> cursos;
    
    public TableView<Curso> tbListaCursos;
    

    @FXML
    public TextField ctBusca;

    static Stage stage;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLListaCursos.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Relatório dos Cursos");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        this.stage = stage;
    }
    
    
    @FXML
    public void montarLista() {
    

        TableColumn idCurso = new TableColumn<>("Código");
        TableColumn nomeCurso = new TableColumn<>("Nome do Curso");
        

        idCurso.setCellValueFactory(new PropertyValueFactory("idCurso"));
        nomeCurso.setCellValueFactory(new PropertyValueFactory("nomeCurso"));
        
        tbListaCursos.setItems(FXCollections.observableArrayList(cursos));
        tbListaCursos.getColumns().addAll(idCurso, nomeCurso);
        
    }
    
    @FXML
    public void listarCursos() {

        cursos = cDAO.listaCursos();
        montarLista();
        
    }
    
    
    @FXML
    public void atualizar(){
        cursos = cDAO.listaCursos();
        tbListaCursos.setItems(FXCollections.observableArrayList(cursos));
    }
    
    @FXML
    public void excluirRegistro(){
        if(JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?")==0){
            c = (Curso) tbListaCursos.getSelectionModel().getSelectedItem();
            cDAO.excluir(c.getIdCurso());
            atualizar();
        }
    }
    
    @FXML
    public void pesquisar(){
        cursos = cDAO.buscaCurso(ctBusca.getText());
        tbListaCursos.setItems(FXCollections.observableArrayList(cursos));
    }

    @FXML
    private void abrirCadastroCurso(ActionEvent e) throws Exception {
        new FormCurso().start(new Stage());
    }
    
    @FXML
    private void alterarCadastrCurso() throws Exception {
        if(tbListaCursos.getSelectionModel().getSelectedItem() != null) {
            FormCurso fc = new FormCurso();
            FormCurso.c = (Curso)tbListaCursos.getSelectionModel().getSelectedItem();
            FormCurso.op = "alterar";
            fc.start(new Stage());
        }else{
        JOptionPane.showMessageDialog(null, "Selecione um registro"); //
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarCursos();
    }    
    
    @FXML
    private void fechar() {
        FormListaCursos.stage.close();
    }
    
}
