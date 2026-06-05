package bim.service.board.board.dao.repository.tsvector;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.BooleanType;

public class FunctionBuilderContributor implements MetadataBuilderContributor{

    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction("fts",new SQLFunctionTemplate(BooleanType.INSTANCE, "cont_search @@ to_tsquery(?1)"));
    }
    
}
// spring.jpa.properties.hibernate.metadata_builder_contributor=bim.service.board.board.dao.repository.tsvector.FunctionBuilderContributor