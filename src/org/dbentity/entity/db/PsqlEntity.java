package org.dbentity.entity.db;

public class PsqlEntity extends DBEntity {

    public String getCatalog() {
        return "table_catalog";
    }

    public String getTableName() {
        return "table_name";
    }

    public String getColumnName() {
        return "column_name";
    }

    public String getTypeName() {
        return "udt_name";
    }

    public PsqlEntity() {
        super();
        datatype_corresp.put("int4", "int");
        datatype_corresp.put("decimal", "double");
        datatype_corresp.put("numeric", "double");
        datatype_corresp.put("double", "double");
        datatype_corresp.put("varchar", "varchar");
        datatype_corresp.put("date", "date");
        datatype_corresp.put("timestamp", "timestamp");
    }

}
