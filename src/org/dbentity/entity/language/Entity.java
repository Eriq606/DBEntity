package org.dbentity.entity.language;

import java.util.HashMap;

public class Entity {
    protected String packageDeclare;
    protected String importDeclare;
    protected String datePackage;
    protected String dateTimePackage;
    protected String selfDeclare = "this";
    protected String dotDeclare = ".";
    protected String tableAnnote;
    protected String annoteStart;
    protected String annoteEnd;
    protected String annotePackage;
    public String getAnnotePackage() {
        return annotePackage;
    }

    public String getAnnoteEnd() {
        return annoteEnd;
    }

    public String getAnnoteStart() {
        return annoteStart;
    }

    public void setAnnoteStart(String annoteStart) {
        this.annoteStart = annoteStart;
    }

    public String getTableAnnote() {
        return tableAnnote;
    }

    public void setTableAnnote(String tableAnnote) {
        this.tableAnnote = tableAnnote;
    }
    protected String columnAnnote;

    public String getColumnAnnote() {
        return columnAnnote;
    }

    public void setColumnAnnote(String columnAnnote) {
        this.columnAnnote = columnAnnote;
    }

    public String getSelfDeclare() {
        return selfDeclare;
    }

    public void setSelfDeclare(String selfDeclare) {
        this.selfDeclare = selfDeclare;
    }

    public String getDotDeclare() {
        return dotDeclare;
    }

    public void setDotDeclare(String dotDeclare) {
        this.dotDeclare = dotDeclare;
    }

    protected HashMap<String, String> datatype_corresp = new HashMap<>();

    public String getPackageDeclare() {
        return packageDeclare;
    }

    public String getImportDeclare() {
        return importDeclare;
    }

    public HashMap<String, String> getDatatype_corresp() {
        return datatype_corresp;
    }

    public String getDatePackage() {
        return datePackage;
    }

    public void setDatePackage(String datePackage) {
        this.datePackage = datePackage;
    }

    public String getDateTimePackage() {
        return dateTimePackage;
    }

    public void setDateTimePackage(String dateTimePackage) {
        this.dateTimePackage = dateTimePackage;
    }
}
