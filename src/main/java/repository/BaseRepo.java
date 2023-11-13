
package repository;

import utils.DatabaseHandler;

import java.util.List;

public abstract class BaseRepo<Model> {
    protected DatabaseHandler db;
    protected String tableName;

    protected BaseRepo(DatabaseHandler db, String tableName) {
        this.db = db;
        this.tableName = tableName;
    }

    public List<Model> findAll() throws Exception {
        throw new Exception("Method not implemented");
    }
    public Model create(Model model) throws Exception {
        throw new Exception("Method not implemented");
    }
    public Model update(Model model) throws Exception {
        throw new Exception("Method not implemented");
    }
    public Model delete(Model model) throws Exception {
        throw new Exception("Method not implemented");
    }
}
