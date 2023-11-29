package org.dbentity.template;

import org.dbentity.maker.ClassMaker;
import org.dbentity.utils.StringUtils;

import java.util.Map;

public class Template {
    private final ClassMaker classMaker;

    public Template(ClassMaker classMaker) {
        this.classMaker = classMaker;
    }
    public String getHeaderLayout() {
        String layout =
                """
                %s %s;
                %s %s;
                %s %s;

                %s %s.%s;
                %s %s.%s;
                
                %s%s("%s")%s
                public class %s {
                """;
        layout = String.format(layout,
                classMaker.getEntity().getPackageDeclare(),
                classMaker.getPackageName(),
                classMaker.getEntity().getImportDeclare(),
                classMaker.getEntity().getDatePackage(),
                classMaker.getEntity().getImportDeclare(),
                classMaker.getEntity().getDateTimePackage(),
                classMaker.getEntity().getImportDeclare(),
                classMaker.getEntity().getAnnotePackage(),
                classMaker.getEntity().getTableAnnote(),
                classMaker.getEntity().getImportDeclare(),
                classMaker.getEntity().getAnnotePackage(),
                classMaker.getEntity().getColumnAnnote(),
                classMaker.getEntity().getAnnoteStart(),
                classMaker.getEntity().getTableAnnote(),
                classMaker.getEntityName(),
                classMaker.getEntity().getAnnoteEnd(),
                StringUtils.majStart(classMaker.getEntityName()));

        return layout;
    }

    public String getBodyLayout() {
        StringBuilder layout = new StringBuilder();

        for (Map.Entry<String, String> e : classMaker.getField_type().entrySet()) {
            String colAnnote=String.format("\t%s%s(\"%s\")%s\n", 
                                            classMaker.getEntity().getAnnoteStart(),
                                            classMaker.getEntity().getColumnAnnote(),
                                            e.getKey(),
                                            classMaker.getEntity().getAnnoteEnd());
            layout.append(colAnnote);
            layout.append(String.format("\tprivate %s %s;\n", e.getValue(), e.getKey()));
        }

        // GETTERS
        for (Map.Entry<String, String> e : classMaker.getField_type().entrySet()) {
            layout.append(String.format("""
                    
                    \tpublic %s get%s() {
                        \treturn %s%s%s;
                    \t}
                    """,
                    e.getValue(),
                    StringUtils.majStart(e.getKey()),
                    classMaker.getEntity().getSelfDeclare(),
                    classMaker.getEntity().getDotDeclare(),
                    e.getKey()));
        }

        // SETTERS
        for (Map.Entry<String, String> e : classMaker.getField_type().entrySet()) {
            layout.append(String.format("""
                    
                    \tpublic void set%s(%s %s) {
                        \t%s%s%s = %s;
                    \t}
                    """,
                    StringUtils.majStart(e.getKey()),
                    e.getValue(),
                    e.getKey(),
                    classMaker.getEntity().getSelfDeclare(),
                    classMaker.getEntity().getDotDeclare(),
                    e.getKey(),
                    e.getKey()));
        }

        return layout.toString();
    }

    public String getFooterLayout() {
        return "}";
    }

    public String getLayout() {
        return getHeaderLayout() + getBodyLayout() + getFooterLayout();
    }

    public ClassMaker getClassMaker() {
        return classMaker;
    }
}
