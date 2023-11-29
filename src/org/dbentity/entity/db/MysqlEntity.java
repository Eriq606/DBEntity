package org.dbentity.entity.db;

public class MysqlEntity extends DBEntity {
    public String getCatalog() {
        return "table_schema";
    }

    public String getTableName() {
        return "table_name";
    }

    public String getColumnName() {
        return "column_name";
    }

    public String getTypeName() {
        return "data_type";
    }

    public MysqlEntity() {
        super();
        datatype_corresp.put("int", "int");
        datatype_corresp.put("decimal", "double");
        datatype_corresp.put("numeric", "double");
        datatype_corresp.put("double", "double");
        datatype_corresp.put("varchar", "varchar");
        datatype_corresp.put("date", "date");
        datatype_corresp.put("datetime", "timestamp");
    }

}
