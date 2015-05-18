/*
   Licensed to the Apache Software Foundation (ASF) under one
   or more contributor license agreements.  See the NOTICE file
   distributed with this work for additional information
   regarding copyright ownership.  The ASF licenses this file
   to you under the Apache License, Version 2.0 (the
   "License"); you may not use this file except in compliance
   with the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing,
   software distributed under the License is distributed on an
   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
   KIND, either express or implied.  See the License for the
   specific language governing permissions and limitations
   under the License.    
 */
package org.opentides.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.opentides.bean.SystemCodes;
import org.opentides.dao.SystemCodesDao;
import org.opentides.util.StringUtil;
import org.springframework.stereotype.Repository;


/**
 * This is the dao implementation for SystemCodes.
 * Scaffold generated by opentides3 on Jan 16, 2013 12:40:25. 
 * @author opentides
 */
@Repository(value="systemCodesDao")
public class SystemCodesDaoJpaImpl extends BaseEntityDaoJpaImpl<SystemCodes, Long>
		implements SystemCodesDao {
	/**
	 * Retrieves SystemCodes for the given category
	 */
	public List<SystemCodes> findSystemCodesByCategory(String category) {		
        SystemCodes example = new SystemCodes();
        example.setCategory(category);
        return findByExample(example, true);        
	}

	/**
	 * Retrieves system code based on key.
	 */
	public SystemCodes loadBySystemCodesByKey(String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyName", key);
		return findSingleResultByNamedQuery("jpql.systemcodes.findByKey", map);
	}
	
	/**
	 * Returns all the available categories.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAllCategories() {
		String queryString = getJpqlQuery("jpql.systemcodes.findAllCategories");
		Query queryObject = getEntityManager().createQuery(queryString);
		
		return queryObject.getResultList();
	}

	/** 
     * Selects all available categories except for the
     * specified ones 
     */
	@SuppressWarnings("unchecked")
	public List<String> getAllCategoriesExcept(String... categories) {
		String queryString = getJpqlQuery("jpql.systemcodes.findAllCategoriesExcept");
		Query queryObject = getEntityManager().createQuery(queryString);
		queryObject.setParameter("categories", categories);
		
		return queryObject.getResultList();
	}

	/* (non-Javadoc)
	 * @see org.opentides.persistence.impl.BaseEntityDAOJpaImpl#appendOrderToExample(org.opentides.bean.BaseEntity)
	 */
	@Override
	protected String appendOrderToExample(SystemCodes example) {
		if (StringUtil.isEmpty(example.getOrderOption()))
			return "order by obj.value asc";
		else
			return super.appendOrderToExample(example);
	}

	@Override
	public long countDuplicate(SystemCodes code) {
		String queryString = getJpqlQuery("jpql.systemcodes.countDuplicate");
		Query queryObject = getEntityManager().createQuery(queryString);
		queryObject.setParameter("keyName", code.getKey());
		// if id is null it means it is a new systemCodes
		if (code.getId() == null) {
			queryObject.setParameter("id", 0l);
		} else {
			queryObject.setParameter("id", code.getId());
		}
		return (Long) queryObject.getSingleResult();	
	}
}