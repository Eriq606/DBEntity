package org.dbentity.entity.db;

import java.util.HashMap;

public class DBEntity {
    protected String catalog;
    protected String tableName;
    protected String columnName;
    protected String typeName;
    protected HashMap<String, String> datatype_corresp = new HashMap<>();


    public String getCatalog() {
        return catalog;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getTypeName() {
        return typeName;
    }

    public HashMap<String, String> getDatatype_corresp() {
        return datatype_corresp;
    }
}
