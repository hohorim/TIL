package bim.service.board.board.dao.repository.tsvector;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.type.StandardBasicTypes;

import com.vladmihalcea.hibernate.type.search.PostgreSQLTSVectorType;
public class CustomPostgreSQL10Dialect extends PostgreSQL10Dialect{
    public CustomPostgreSQL10Dialect(){
        super();
        registerFunction("tsquery",new TsVectorMatchExpression());
        System.out.println();
    }    
}
// spring.jpa.properties.hibernate.dialect=bim.service.board.board.dao.repository.tsvector.CustomPostgreSQL10Dialect