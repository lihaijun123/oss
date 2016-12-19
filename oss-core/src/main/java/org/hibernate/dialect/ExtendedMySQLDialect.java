package org.hibernate.dialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.SQLFunctionTemplate;

public class ExtendedMySQLDialect extends MySQLDialect {

	public ExtendedMySQLDialect(){
		super();
		registerFunction("date_add",new SQLFunctionTemplate(Hibernate.DATE,"date_add(?1,INTERVAL ?2 ?3)"));
	}
}
