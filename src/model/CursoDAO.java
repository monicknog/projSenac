package model;

import controller.Curso;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class CursoDAO {
    
    public EntityManager getEM(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SenacPU");
        return factory.createEntityManager();
    }
    
    public Curso salvar(Curso curso) throws Exception{
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if(curso.getIdCurso() == null){
                em.persist(curso); //prepara um insert
            }else{
                if(!em.contains(curso)){
                    if(em.find(Curso.class, curso.getIdCurso()) == null){
                        throw new Exception("Erro ao alterar regitro");
                    }
                }
                em.merge(curso); //prepara update
            }
            em.getTransaction().commit(); //executa comando
        } catch (Exception e) {
            em.getTransaction().rollback();
        
        }finally{
            em.close();
        }
        return curso;
    }
    
    
    public List<Curso> listaCursos(){
        EntityManager em = getEM();
        try{
            List cursos = em.createQuery("SELECT c FROM Curso c").getResultList();
            return cursos;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os alunos");
        }finally{
            em.close();
        }
        return null;
    }
    
    public List<Curso> buscaCurso(String nome){
        EntityManager em = getEM();
        try{
           // List alunos = em.createQuery("SELECT a FROM Aluno a WHERE a.nomeAluno LIKE :n").setParameter("n", "%"+nome+"%").getResultList();
           List cursos = em.createQuery("SELECT c FROM Curso c WHERE c.nomeCurso LIKE '%"+nome+"%'").getResultList();
            return cursos;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os alunos");
        }finally{
            em.close();
        }
        return null;
    }
    
    public void excluir(Long id){
        EntityManager em = getEM();
        Curso curso = em.find(Curso.class, id);
        try{
            em.getTransaction().begin();
            em.remove(curso);
            em.getTransaction().commit();
            JOptionPane.showConfirmDialog(null, "Registro excluido");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir curso. " + e.getMessage());
        }finally{
            em.close();
        }
    } 
}
