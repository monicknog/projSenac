package controller;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity

public class Curso implements Serializable {
    
    @Id
    private Long idCurso;
    
    private String nomeCurso;
    
    public Curso(){
        
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
    
    
}
