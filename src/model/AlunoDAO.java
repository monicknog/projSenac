package model;

import controller.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

public class AlunoDAO {

    public EntityManager getEM(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("SenacPU");
        return factory.createEntityManager();
    }
    
    public Aluno salvar(Aluno aluno) throws Exception{
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            if(aluno.getIdAluno() == null){
                em.persist(aluno); //prepara um insert
            }else{
                if(!em.contains(aluno)){
                    if(em.find(Aluno.class, aluno.getIdAluno()) == null){
                        throw new Exception("Erro ao alterar regitro");
                    }
                }
                em.merge(aluno); //prepara update
            }
            em.getTransaction().commit(); //executa comando
        } catch (Exception e) {
            em.getTransaction().rollback();
        
        }finally{
            em.close();
        }
        return aluno;
    }
    
    public List<Aluno> listaAlunos(){
        EntityManager em = getEM();
        try{
            List alunos = em.createQuery("SELECT a FROM Aluno a").getResultList();
            return alunos;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os alunos");
        }finally{
            em.close();
        }
        return null;
    }
    
    public List<Aluno> buscaAlunos(String nome){
        EntityManager em = getEM();
        try{
           // List alunos = em.createQuery("SELECT a FROM Aluno a WHERE a.nomeAluno LIKE :n").setParameter("n", "%"+nome+"%").getResultList();
           List alunos = em.createQuery("SELECT a FROM Aluno a WHERE a.nomeAluno LIKE '%"+nome+"%'").getResultList();
            return alunos;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os alunos");
        }finally{
            em.close();
        }
        return null;
    }
    
    public void excluir(Long id){
        EntityManager em = getEM();
        Aluno aluno = em.find(Aluno.class, id);
        try{
            em.getTransaction().begin();
            em.remove(aluno);
            em.getTransaction().commit();
            JOptionPane.showConfirmDialog(null, "Registro excluido");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao excluir aluno. " + e.getMessage());
        }finally{
            em.close();
        }
    } 
}

