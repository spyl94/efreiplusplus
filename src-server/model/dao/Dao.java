package model.dao;

import java.sql.Connection;
import java.util.Set;

public abstract class Dao<T> {

    protected Connection conn = null;

    public Dao(Connection conn) {
        this.conn = conn;
    }

    /**
     * 
     * @param obj
     * @return boolean
     */
    public abstract boolean create(T obj);

    /**
     * Méthode pour effacer
     * 
     * @param obj
     * @return boolean
     */
    public abstract boolean delete(T obj);

    /**
     * 
     * @param obj
     * @return boolean
     */
    public abstract boolean update(T obj);

    /**
     * 
     * @param id
     * @return T
     */
    public abstract T find(int id);

    public abstract Set<T> findAll();

}
