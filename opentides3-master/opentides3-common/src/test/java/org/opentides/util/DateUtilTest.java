/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * DateUtilTest.java
 * Created on Feb 10, 2008, 1:27:19 PM
 */
package org.opentides.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author allanctan
 *
 */
public class DateUtilTest {
	private Date jan_01_1901;
	private Date mar_16_1989;
	private Date jun_30_1932;
	private Date mar_01_1999;
	private Date dec_31_2009;
	private Date dec_01_2009;	
	private Date dec_01_2045;
	private Date jan_02_2001;
    
    @Before
	public void setUp() throws Exception {
    	SimpleDateFormat dtFormatter = new SimpleDateFormat("yyyyMMdd");
    	jan_01_1901 = dtFormatter.parse("19010101");
    	mar_16_1989 = dtFormatter.parse("19890316");
    	jun_30_1932 = dtFormatter.parse("19320630");
    	mar_01_1999 = dtFormatter.parse("19990301");
    	dec_31_2009 = dtFormatter.parse("20091231");
    	dec_01_2009 = dtFormatter.parse("20091201");
    	dec_01_2045 = dtFormatter.parse("20451201");
    	jan_02_2001 = dtFormatter.parse("20010102");
	}
    
    @Test
    public void testStringToDate() throws ParseException{
    	Assert.assertEquals(jan_01_1901, DateUtil.stringToDate("01/01/1901", "MM/dd/yyyy"));
    }
    
    @Test
    public void testStringToDateException() {
    	try {
    		Assert.assertEquals(jan_01_1901, DateUtil.stringToDate("la/2345/11", "MM/dd/yyyy"));
	    	Assert.fail("Exception not thrown on invalid date.[   ]"); 
    	} catch (ParseException pex) {
    	}
    }

    @Test
    public void testDateToString() throws ParseException{
    	Assert.assertEquals("12/31/2009", DateUtil.dateToString(dec_31_2009, "MM/dd/yyyy"));
    	Assert.assertEquals("", DateUtil.dateToString(null, "MM/dd/yyyy"));
    }
    
    @Test
    public void testConvertShortDate() throws ParseException{
    	Assert.assertEquals(mar_16_1989,DateUtil.convertShortDate("19890316"));
    	Assert.assertEquals(jun_30_1932,DateUtil.convertShortDate("19320630"));
    	Assert.assertEquals("19320630", DateUtil.convertShortDate(jun_30_1932));
    	Assert.assertEquals("19890316", DateUtil.convertShortDate(mar_16_1989));
    	Assert.assertEquals(jun_30_1932,DateUtil.convertShortDate("19320630",mar_16_1989));
    }

    @Test
    public void testConvertShortDateException () throws ParseException {
    	String nul = null;
    	Assert.assertNull(DateUtil.convertShortDate(""));
    	Assert.assertNull(DateUtil.convertShortDate(nul));
    	try {
    		DateUtil.convertShortDate("unknown");
    		Assert.fail("Exception not thrown on invalid date.[]"); 
    	} catch (ParseException pex) {
    	}
    }
    
    @Test
    public void testConvertFlexibleDate() throws ParseException {
    	Assert.assertEquals(mar_16_1989,DateUtil.convertFlexibleDate("03-16-1989"));
    	Assert.assertEquals(mar_16_1989,DateUtil.convertFlexibleDate("03/16/1989"));
    	Assert.assertEquals(jan_01_1901,DateUtil.convertFlexibleDate("1901"));
    	Assert.assertEquals(dec_01_2009,DateUtil.convertFlexibleDate("December 2009"));
    	Assert.assertEquals(jun_30_1932,DateUtil.convertFlexibleDate("June 30, 1932"));
    	Assert.assertEquals(jun_30_1932,DateUtil.convertFlexibleDate("June 30 1932"));
    	Assert.assertEquals(dec_01_2045,DateUtil.convertFlexibleDate("12/2045"));
    	Assert.assertEquals(mar_01_1999,DateUtil.convertFlexibleDate("03-1999"));
    	Assert.assertEquals(mar_01_1999,DateUtil.convertFlexibleDate("1999-03-01"));
    	Assert.assertEquals(jan_02_2001,DateUtil.convertFlexibleDate("2001-01-02"));
    }
    
    @Test
    public void testConvertFlexibleDateException() throws ParseException {
    	Assert.assertNull(DateUtil.convertFlexibleDate(""));
    	Assert.assertNull(DateUtil.convertFlexibleDate(null));
    	try {
    		DateUtil.convertFlexibleDate("unknown");
    		Assert.fail("Exception not thrown on invalid date.[unknown]"); 
    	} catch (ParseException pex) {
    	}
    }
    
    @Test
    public void testNullableDates() {
    	Date d1 = new Date();
    	Date d2 = new Date(d1.getTime()+14675467);
    	Assert.assertTrue(DateUtil.compareNullableDates(d1, d1));
    	Assert.assertTrue(DateUtil.compareNullableDates(d2, d2));
    	Assert.assertTrue(DateUtil.compareNullableDates(null, null));
    	Assert.assertFalse(DateUtil.compareNullableDates(d1, d2));
    	Assert.assertFalse(DateUtil.compareNullableDates(null, d2));
    	Assert.assertFalse(DateUtil.compareNullableDates(d1, null));    	
    }
    
    @Test
    public void testHasTime() throws ParseException {
    	Assert.assertTrue(DateUtil.hasTime(new Date()));
		Assert.assertFalse(DateUtil.hasTime(DateUtil.convertFlexibleDate("2001-01-02")));
		Assert.assertFalse(DateUtil.hasTime(null));
    } 
}
