// package bim.service.board.board.dao.repository.tsvector;

// import java.util.List;

// import org.hibernate.QueryException;
// import org.hibernate.dialect.function.SQLFunction;
// import org.hibernate.engine.spi.Mapping;
// import org.hibernate.engine.spi.SessionFactoryImplementor;
// import org.hibernate.type.StandardBasicTypes;
// import org.hibernate.type.Type;
// import org.springframework.stereotype.Component;

// public class TsVectorMatchExpression implements SQLFunction{

//     @Override
//     public boolean hasArguments() {
//         return true;
//     }

//     @Override
//     public boolean hasParenthesesIfNoArguments() {
//         return false;
//     }

//     @Override
//     public Type getReturnType(Type firstArgumentType, Mapping mapping) throws QueryException {
//         return StandardBasicTypes.BOOLEAN;
//     }

//     @Override
//     public String render(Type firstArgumentType, List arguments, SessionFactoryImplementor factory) throws QueryException {
//         if (arguments.size() != 2) {
//             throw new IllegalArgumentException("tsvector_match requires 2 arguments");
//         }

//         // 'param:* <-> param:*'
//         return arguments.get(0).toString() +
//                 " @@ to_tsquery("+ arguments.get(1).toString() +")";
//     }
    
// }
