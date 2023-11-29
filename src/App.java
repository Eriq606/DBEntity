import java.sql.Connection;

import org.dbentity.maker.ClassMaker;
import org.dbentity.maker.Credentials;
import org.dbentity.template.Template;
import org.dbentity.utils.StringUtils;
import org.dbentity.utils.db.Connexion;
import org.dbentity.utils.file.FileUtils;

public class App {
    public static void main(String[] args) throws Exception {
        // POSTGRESQL
        Credentials credentials = new Credentials();
        credentials.setUser_name("eriq");
        credentials.setPassword("root");
        credentials.setPort("5432");
        credentials.setDb_name("voiture");

        Connection connex = Connexion.getPsqlConnexion(credentials.getDb_name(), credentials.getHost(), credentials.getPort(), credentials.getUser_name(), credentials.getPassword());
        ClassMaker classMaker = ClassMaker.getClassMaker(1, 2, connex, credentials, "tokens");

        connex.close();
        System.out.println(classMaker);

        // assert  classMaker.getSimpleTypeCorrespField() !=null;
        classMaker.setField_type(classMaker.getSimpleTypeCorrespField());
        classMaker.setField_type(classMaker.getLanguageTypeCorrespField());

        Template template = new Template(classMaker);

        String content = template.getLayout();
        System.out.println("\n"+content);

        String path = StringUtils.majStart(template.getClassMaker().getEntityName())+".java";
        FileUtils.createFile(path, content);

        System.out.println("\nFichier créé !");
    }
}
