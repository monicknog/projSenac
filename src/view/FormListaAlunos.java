package view;

import controller.Aluno;
import java.net.URL;
import java.util.List;
import java.util.Properties;
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
import model.AlunoDAO;

public class FormListaAlunos implements Initializable {

    AlunoDAO aDAO = new AlunoDAO();
    List<Aluno> alunos;
    Aluno a = new Aluno();
    public TableView<Aluno> tbListaAlunos;

    @FXML
    public TextField ctBusca;

    static Stage stage;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLListaAlunos.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Relatório dos Alunos");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        this.stage = stage;
    }

    @FXML
    public void montarLista() {

        TableColumn idAluno = new TableColumn<>("Código");
        TableColumn nomeAluno = new TableColumn<>("Nome do Aluno");
        TableColumn idadeAluno = new TableColumn<>("Idade");
        TableColumn sexoAluno = new TableColumn<>("Sexo");

        idAluno.setCellValueFactory(new PropertyValueFactory("idAluno"));
        nomeAluno.setCellValueFactory(new PropertyValueFactory("nomeAluno"));
        idadeAluno.setCellValueFactory(new PropertyValueFactory("idadeAluno"));
        sexoAluno.setCellValueFactory(new PropertyValueFactory("sexoAluno"));

        tbListaAlunos.setItems(FXCollections.observableArrayList(alunos));
        tbListaAlunos.getColumns().addAll(idAluno, nomeAluno, idadeAluno, sexoAluno);

    }

    @FXML
    public void listarAlunos() {

        alunos = aDAO.listaAlunos();
        montarLista();

    }

    @FXML
    public void atualizar() {
        alunos = aDAO.listaAlunos();
        tbListaAlunos.setItems(FXCollections.observableArrayList(alunos));
    }

    @FXML
    public void excluirRegistro() {
        if (JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?") == 0) {
            a = (Aluno) tbListaAlunos.getSelectionModel().getSelectedItem();
            aDAO.excluir(a.getIdAluno());
            atualizar();
        }
    }

    @FXML
    public void pesquisar() {
        alunos = aDAO.buscaAlunos(ctBusca.getText());
        tbListaAlunos.setItems(FXCollections.observableArrayList(alunos));
    }

    @FXML
    private void abrirCadastroAluno(ActionEvent e) throws Exception {
        new FormAluno().start(new Stage());
    }

    @FXML
    private void alterarCadastroAluno() throws Exception {
        if(tbListaAlunos.getSelectionModel().getSelectedItem() != null) {
            FormAluno fa = new FormAluno();
            FormAluno.a = (Aluno)tbListaAlunos.getSelectionModel().getSelectedItem();
            FormAluno.op = "alterar";
            fa.start(new Stage());
        }else{
        JOptionPane.showMessageDialog(null, "Selecione um registro"); //
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listarAlunos();
    }

    @FXML
    private void fechar() {
        FormListaAlunos.stage.close();
    }

}
