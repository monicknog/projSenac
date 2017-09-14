package controller;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

public class Aluno implements Serializable {

    @Id
    private Long idAluno;
    private String nomeAluno;
    private int idadeAluno;
    private String sexoAluno;

    public Aluno() {

    }

    public Long getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Long idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getIdadeAluno() {
        return idadeAluno;
    }

    public void setIdadeAluno(int idadeAluno) {
        this.idadeAluno = idadeAluno;
    }

    public String getSexoAluno() {
        return sexoAluno;
    }

    public void setSexoAluno(String sexoAluno) {
        this.sexoAluno = sexoAluno;
    }

}
