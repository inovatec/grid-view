
package br.com.inovatec.grid.dao.connection;

import br.com.inovatec.grid.dao.exceptions.ConnectionDatabaseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author zrobe
 */
public class ConnectionFactory {
    
    private static ConnectionFactory instance;
    private EntityManager entityManager;

    private ConnectionFactory() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("br.com.inovatec_GridView_jar_1.0-SNAPSHOTPU");
        this.entityManager = emf.createEntityManager();
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Testar conexao com o banco de dados
     */
    public void testConnection() throws ConnectionDatabaseException {
        try {
            this.entityManager.createNamedQuery("usuario.countUsers", Number.class).getSingleResult();
        } catch(Exception ex) {
            throw new ConnectionDatabaseException(ex);
        }
    }
    
}
