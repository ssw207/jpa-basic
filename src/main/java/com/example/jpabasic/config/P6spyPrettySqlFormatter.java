package com.example.jpabasic.config;

import java.util.Locale;

import org.hibernate.engine.jdbc.internal.FormatStyle;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6spyPrettySqlFormatter implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared,
		String sql, String url) {
		return "OperationTime : " + elapsed + "ms" + formatSql(category, sql);
	}

	private String formatSql(String category, String sql) {
		if (sql == null || sql.trim().equals(""))
			return sql;

		// Only format Statement, distinguish DDL And DML
		if (Category.STATEMENT.getName().equals(category)) {
			String tmpsql = sql.trim().toLowerCase(Locale.ROOT);
			if (tmpsql.startsWith("create") || tmpsql.startsWith("alter") || tmpsql.startsWith("comment")) {
				sql = FormatStyle.DDL.getFormatter().format(sql);
			} else {
				sql = FormatStyle.BASIC.getFormatter().format(sql);
			}
			sql = "|\nHeFormatSql(P6Spy sql,Hibernate format):" + sql;
		}

		return sql;
	}
}