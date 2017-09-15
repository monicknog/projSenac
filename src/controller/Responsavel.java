package controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity

public class Responsavel implements Serializable{
    
    @Id
    private Long idResponsavel;
    private String nomeResponsavel;
    private String parentescoResponsavel;
    private String telResponsavel;

    @ManyToOne(cascade = CascadeType.ALL)
    private Aluno aluno;
    
    public Responsavel() {
    }

    public Responsavel(String nomeResponsavel, String parentescoResponsavel, String telResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
        this.parentescoResponsavel = parentescoResponsavel;
        this.telResponsavel = telResponsavel;
    }
    
    
     
    public Long getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Long idResponsavel) {
        this.idResponsavel = idResponsavel;
    }

    
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getParentescoResponsavel() {
        return parentescoResponsavel;
    }

    public void setParentescoResponsavel(String parentescoResponsavel) {
        this.parentescoResponsavel = parentescoResponsavel;
    }

    public String getTelResponsavel() {
        return telResponsavel;
    }

    public void setTelResponsavel(String telResponsavel) {
        this.telResponsavel = telResponsavel;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    
    
}
