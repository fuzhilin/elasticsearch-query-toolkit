package org.elasticsearch.dsl.parser;

import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.google.common.collect.Lists;
import org.elasticsearch.dsl.ElasticDslContext;
import org.elasticsearch.sql.ElasticSqlSelectQueryBlock;

import java.util.List;

public class QuerySelectFieldListParser implements ElasticSqlParser {

    public static final String SQL_FIELD_MATCH_ALL = "*";

    @Override
    public void parse(ElasticDslContext dslContext) {
        ElasticSqlSelectQueryBlock queryBlock = (ElasticSqlSelectQueryBlock) dslContext.getQueryExpr().getSubQuery().getQuery();

        final List<String> selectFields = Lists.newLinkedList();
        for (SQLSelectItem selectField : queryBlock.getSelectList()) {
            ElasticSqlIdfParser.parseSqlIdentifier(selectField.getExpr(), dslContext.getQueryAs(), new ElasticSqlIdfParser.ElasticSqlTopIdfFunc() {
                @Override
                public void parse(String idfName) {
                    if (!SQL_FIELD_MATCH_ALL.equals(idfName)) {
                        selectFields.add(idfName);
                    }
                }
            }, new ElasticSqlIdfParser.ElasticSqlNestIdfFunc() {
                @Override
                public void parse(String nestPath, String idfName) {
                    selectFields.add(String.format("%s.%s", nestPath, idfName));
                }
            });
        }
        dslContext.setQueryFieldList(selectFields);
    }
}
