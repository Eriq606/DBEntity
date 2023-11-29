package org.dbentity.maker;

import org.dbentity.entity.db.DBEntity;
import org.dbentity.entity.db.MysqlEntity;
import org.dbentity.entity.db.PsqlEntity;
import org.dbentity.entity.language.Entity;
import org.dbentity.entity.language.JavaEntity;
import org.dbentity.utils.Constante;
import org.dbentity.utils.db.Connexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked"})
public class ClassMaker {

    private String entityName;
    private String packageName;
    private HashMap<String, String> field_type = new HashMap<>();
    private Entity entity;
    private DBEntity dbEntity;

    public static ClassMaker getClassMaker(int language, int sgbd, Connection connection, Credentials credentials, String entityName) throws SQLException, ClassNotFoundException {
        boolean opened = false;
        Connection connex = connection;
        DBEntity dbEntity = null;
        if (connection == null) {
            switch (sgbd) {
                case Constante.MYSQL_ID -> {
                    connex = Connexion.getMysqlConnexion(credentials.getDb_name(), credentials.getHost(), credentials.getPort(), credentials.getUser_name(), credentials.getPassword());
                    opened = true;
                    dbEntity = new MysqlEntity();
                }

                case Constante.PSQL_ID -> {
                    connex = Connexion.getPsqlConnexion(credentials.getDb_name(), credentials.getHost(), credentials.getPort(), credentials.getUser_name(), credentials.getPassword());
                    opened = true;
                    dbEntity = new PsqlEntity();
                }

                default -> throw new SQLException("SGBD not supported");
            }
        }
        else {
            switch (sgbd) {
                case Constante.MYSQL_ID -> {
                    dbEntity = new MysqlEntity();
                }

                case Constante.PSQL_ID -> {
                    dbEntity = new PsqlEntity();
                }

                default -> throw new SQLException("SGBD not supported");
            }
        }

        String query = "select %s ,%s ,%s ,%s from information_schema.columns where table_name = ?";

        query = String.format(query, dbEntity.getCatalog(), dbEntity.getTableName(), dbEntity.getColumnName(), dbEntity.getTypeName());
        assert connection != null;
        PreparedStatement stat = connection.prepareStatement(query);

        try (stat) {
            stat.setString(1, entityName);
            ResultSet resultSet = stat.executeQuery();
            ClassMaker classMaker = new ClassMaker();
            for (int i = 0; resultSet.next(); i++) {
                if (i == 0) {
                    classMaker.setDbEntity(dbEntity);
                    classMaker.setEntityName(resultSet.getString(2));
                    classMaker.setPackageName(resultSet.getString(1));
                    switch (language) {
                        case Constante.JAVA_ID -> classMaker.setEntity(new JavaEntity());
                        default -> throw new SQLException("Language not supported");
                    }
                }
                classMaker.getField_type().put(resultSet.getString(3), resultSet.getString(4));
            }
            return classMaker;
        } finally {
            if (opened)
                connex.close();
        }
    }


    public HashMap<String, String> getSimpleTypeCorrespField() {
        HashMap<String, String> realField = (HashMap<String, String>) this.getField_type().clone();
        for(Map.Entry<String, String> e : realField.entrySet()) {
            realField.replace(e.getKey(), this.getDbEntity().getDatatype_corresp().get(e.getValue()));
        }
        return realField;
    }

    public HashMap<String, String> getLanguageTypeCorrespField() {
        HashMap<String, String> realField = (HashMap<String, String>) this.getField_type().clone();
        for(Map.Entry<String, String> e : realField.entrySet()) {
            realField.replace(e.getKey(), this.getEntity().getDatatype_corresp().get(e.getValue()));
        }
        return realField;
    }


    public DBEntity getDbEntity() {
        return dbEntity;
    }

    public void setDbEntity(DBEntity dbEntity) {
        this.dbEntity = dbEntity;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getPackageName() {
        return packageName;
    }

    public HashMap<String, String> getField_type() {
        return field_type;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setField_type(HashMap<String, String> field_type) {
        this.field_type = field_type;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String toString() {
        return "ClassMaker [entityName=" + entityName + ", packageName=" + packageName + ", field_type=" + field_type + "]";
    }
}
