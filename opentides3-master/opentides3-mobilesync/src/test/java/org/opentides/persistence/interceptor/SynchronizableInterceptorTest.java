package org.opentides.persistence.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.opentides.bean.SystemCodes;
import org.opentides.util.DateUtil;

public class SynchronizableInterceptorTest {

	@Test
	public void buildInsertStatement() {
		SystemCodes sc = new SystemCodes("category1","key1","value1");
		SynchronizableInterceptor s = new SynchronizableInterceptor();
		s.buildInsertStatement(sc);
		Assert.assertEquals(
				"insert into system_codes (KEY_,VALUE_,CATEGORY_) values ('KEY1','value1','CATEGORY1')",
				s.buildInsertStatement(sc));
		
		Date now = new Date();
		sc.setCreateDate(now);
		String nowString = DateUtil.dateToString(now, "yyyy-MM-dd HH:mm:ss");
		Assert.assertEquals(
				"insert into system_codes (CREATEDATE,KEY_,VALUE_,CATEGORY_) values ('"
						+ nowString + "','KEY1','value1','CATEGORY1')",
				s.buildInsertStatement(sc));
		
		SystemCodes p = new SystemCodes("categoryP","keyP","parentP");
		p.setId(2l);
		sc.setParent(p);
		sc.setId(3l);
		Assert.assertEquals(
				"insert into system_codes (ID,CREATEDATE,KEY_,VALUE_,CATEGORY_,PARENT_) values (3,'"
						+ nowString + "','KEY1','value1','CATEGORY1',2)",
				s.buildInsertStatement(sc));
		
	}
	
	@Test
	public void buildUpdateStatement() {
		SystemCodes _new = new SystemCodes("category2","key1","value2");
		_new.setId(1l);
		
		List<String> updatedFields = new ArrayList<String>();
		updatedFields.add("category");
		updatedFields.add("value");

		SynchronizableInterceptor s = new SynchronizableInterceptor();		
		Assert.assertEquals(
				"update system_codes set CATEGORY_='CATEGORY2',VALUE_='value2' where id=1",
				s.buildUpdateStatement(_new, updatedFields));
		
	}
	
	@Test
	public void buildDeleteStatement() {
		SystemCodes sc = new SystemCodes("category1","key1","value1");
		sc.setId(1l);
		
		SynchronizableInterceptor s = new SynchronizableInterceptor();
		Assert.assertEquals("delete from system_codes where id=1",
				s.buildDeleteStatement(sc));
	}

}
