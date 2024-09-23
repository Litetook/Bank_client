package com.pragmatic.sql;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import static com.pragmatic.sql.SqlQuery.PlaceholderUtils.replacePlaceholders;
import static io.micrometer.common.util.StringUtils.isNotBlank;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.util.Assert.isTrue;


/**
 * A class that encapsulates common cases of working with SQL queries. Has a {@link Builder} for constructing the
 * corresponding query. Also, carries the parameters necessary for query invocation, as well as an additional
 * {@link #countQuery} for calculating total count for paged requests. Also it is possible to add parameterized parts to
 * the query in case certain inputs to it are optional (please note the syntax in the sample). Here is an example usage
 * of this class:
 * <pre>
 *     {@code
 *     SqlQuery.Builder builder = SqlQuery.builder()
 *      .query("SELECT * FROM data_source WHERE envId = :envId {{optionalParam: AND optionalField = :optionalParam}}");
 *     builder.param("envId", envId);
 *     builder.includePart("optionalParam", "someValue");
 *     builder.limit(20);
 *     builder.offset(1);
 *     builder.paging(true);
 *     SqlQuery query = builder.build();
 *     }
 * </pre>
 *
 * @author roman.vakulenko
 * @version 2.1 Created: 16.06.22
 */
@Log4j2
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "Builder")
public class SqlQuery {

    private static final String COUNT_START = "SELECT COUNT(*) FROM (\n";
    private static final String COUNT_FINISH = "\n) AS inner_query";
    private static final String LIMIT_PARAM = "limit";
    private static final String OFFSET_PARAM = "offset";
    private static final String PAGING = "\nLIMIT :" + LIMIT_PARAM + " OFFSET :" + OFFSET_PARAM;
    private final String query;
    private final String countQuery;
    private final MapSqlParameterSource params;

    public static class Builder {
        private static final String NOT_NULL_MESSAGE = "name argument must be not null";
        private String query;
        private boolean paging = false;
        private Set<String> enabledPlaceholders = new HashSet<>();
        private Properties customPlaceholdersValues = new Properties();
        private MapSqlParameterSource params = new MapSqlParameterSource();

        Builder() {
        }

        /**
         * Sets the actual SQL query (possibly with templates). Note that the query must not be blank. The syntax of the
         * query itself is not verified by the builder.
         *
         * @param query the SQL query with parameters and optional parts as indicated by documentation
         * @return the builder object
         */
        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder param(String paramName, Object paramValue) {
            isTrue(isNotBlank(paramName), NOT_NULL_MESSAGE);
            enabledPlaceholders.add(paramName);
            this.params.addValue(paramName, paramValue);
            return this;
        }

        public Builder optionalParam(String paramName, String paramValue) {
            isTrue(isNotBlank(paramName), NOT_NULL_MESSAGE);
            if (nonNull(paramValue)) {
                this.params.addValue(paramName, paramValue);
            }
            return this;
        }

        public Builder optionalParam(String paramName, Collection<?> paramValue) {
            isTrue(isNotBlank(paramName), NOT_NULL_MESSAGE);
            if (!CollectionUtils.isEmpty(paramValue)) {
                this.params.addValue(paramName, paramValue);
            }
            return this;
        }

        public Builder optionalParam(String paramName, Number paramValue) {
            isTrue(isNotBlank(paramName), NOT_NULL_MESSAGE);
            if (nonNull(paramValue)) {
                this.params.addValue(paramName, paramValue);
            }
            return this;
        }

        public Builder optionalPart(String paramName, boolean include) {
            isTrue(isNotBlank(paramName), NOT_NULL_MESSAGE);
            if (include) {
                enabledPlaceholders.add(paramName);
            }
            return this;
        }

        public Builder includePart(String paramName) {
            return optionalPart(paramName, true);
        }

        public Builder includePartOrIsNull(String name, Object obj) {
            isTrue(isNotBlank(name), NOT_NULL_MESSAGE);
            enabledPlaceholders.add(name);
            if (isNull(obj)) {
                customPlaceholdersValues.put(name, "is null");
            }
            return this;
        }

        /**
         * Sets the limit of entries to be returned by the query. Note that for having a paged query, the {@link #paging}
         * method has to be invoked.
         *
         * @param limit limit to be set
         * @return the builder object
         */
        public Builder limit(int limit) {
            this.params.addValue(LIMIT_PARAM, limit);
            return this;
        }

        /**
         * Sets the offset for the entries to be returned by the query. Note that for having a paged query, the {@link #paging}
         * method has to be invoked.
         *
         * @param offset offset to be set
         * @return the builder object
         */
        public Builder offset(int offset) {
            this.params.addValue(OFFSET_PARAM, offset);
            return this;
        }

        /**
         * Indicates whether the query is supposed to be used for a paged request. This would result in the generation
         * of a {@link #countQuery}, and the addition of `LIMIT` and `OFFSET` to the input query. Note that the usage of
         * this method requires setting the {@link #limit} and {@link #offset}, otherwise the query generation will throw
         * an exception.
         *
         * @return the builder object
         */

        public Builder paging() {
            this.paging = true;
            return this;
        }

        public SqlQuery build() {
            isTrue(isNotBlank(query), "Query not specified");
            String queryString = replacePlaceholders(query, enabledPlaceholders, customPlaceholdersValues);

            if (this.paging) {
                isTrue(this.params.hasValue(LIMIT_PARAM) && this.params.hasValue(OFFSET_PARAM),
                        "Paging parameters not specified");
                String countQuery = COUNT_START + queryString + COUNT_FINISH;
                queryString += PAGING;
                return new SqlQuery(queryString, countQuery, params);
            } else {
                return new SqlQuery(queryString, null, params);
            }
        }

        /**
         * Build count query from provided query.
         *
         * @return {@link SqlQuery}
         */
        public SqlQuery buildCountQuery() {
            isTrue(isNotBlank(query), "Query not specified");
            String queryString = replacePlaceholders(query, enabledPlaceholders, customPlaceholdersValues);

            String countQuery = COUNT_START + queryString + COUNT_FINISH;
            return new SqlQuery(countQuery, null, params);
        }
    }

    @Log4j2
    @UtilityClass
    class PlaceholderUtils {

        private static final String VALUE_SEPARATOR = ":";
        private static final String PLACEHOLDER_PREFIX = "{{";
        private static final String PLACEHOLDER_SUFFIX = "}}";

        private static final PropertyPlaceholderHelper PLACEHOLDER_HELPER =
                new PropertyPlaceholderHelper(PLACEHOLDER_PREFIX, PLACEHOLDER_SUFFIX, VALUE_SEPARATOR, false);

        public static String replacePlaceholders(String template, Set<String> enabledPlaceholders, Properties customPlaceholdersValues) {
            Assert.notNull(enabledPlaceholders, "'enabledPlaceholders' must not be null");
            Assert.notNull(customPlaceholdersValues, "'customPlaceholdersValues' must not be null");
            PropertyPlaceholderHelper.PlaceholderResolver placeholderResolver = new PropertyPlaceholderHelper.PlaceholderResolver() {
                @Override
                public String resolvePlaceholder(String placeholderName) {
                    if (isPlaceholderEnabled(placeholderName)) {
                        //get value or default if no custom value
                        return customPlaceholdersValues.getProperty(placeholderName);
                    } else {
                        //clear unresolved placeholders
                        return "";
                    }
                }

                private boolean isPlaceholderEnabled(String placeholder) {
                    if (enabledPlaceholders.contains(placeholder)) {
                        return true;
                    } else {
                        int separatorIndex = placeholder.indexOf(VALUE_SEPARATOR);
                        if (separatorIndex != -1) {
                            String placeholderKey = placeholder.trim().substring(0, separatorIndex);
                            return enabledPlaceholders.contains(placeholderKey);
                        }
                    }
                    return false;
                }

            };

            return PLACEHOLDER_HELPER.replacePlaceholders(template, placeholderResolver);
        }

    }
}
