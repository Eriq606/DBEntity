package org.dbentity.entity.language;

public class JavaEntity extends Entity {
    public String getPackageDeclare() {
        return "package";
    }
    public String getImportDeclare() {
        return "import";
    }

    public JavaEntity() {
        super();
        datatype_corresp.put("int", "int");
        datatype_corresp.put("decimal", "double");
        datatype_corresp.put("numeric", "double");
        datatype_corresp.put("double", "double");
        datatype_corresp.put("varchar", "String");
        datatype_corresp.put("date", "LocalDate");
        datatype_corresp.put("timestamp", "LocalDateTime");
    }

    @Override
    public String getDatePackage() {
        return "java.time.LocalDate";
    }

    @Override
    public String getDateTimePackage() {
        return "java.time.LocalDateTime";
    }
    @Override
    public String getColumnAnnote() {
        return "Column";
    }
    @Override
    public String getTableAnnote() {
        return "Table";
    }
    @Override
    public String getAnnoteStart() {
        return "@";
    }
    @Override
    public String getAnnoteEnd() {
        return "";
    }
    @Override
    public String getAnnotePackage() {
        return "veda.godao.annotations";
    }
    
}
