package br.com.trier.core.dao;

import br.com.trier.core.dao.postgres.DaoPostgresContato;

public class FactoryDao {

    private static FactoryDao instance;

    private FactoryDao() {
    }

    public DaoContato getDaoContato() {
        return new DaoPostgresContato();
    }

    public static FactoryDao getInstance() {
        if (instance == null) {
            instance = new FactoryDao();
        }
        return instance;
    }

}
