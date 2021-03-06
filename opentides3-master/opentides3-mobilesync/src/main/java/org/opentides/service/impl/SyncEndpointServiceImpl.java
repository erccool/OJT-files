/*
 * SyncEndpointServiceImpl.java
 */
package org.opentides.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opentides.bean.SyncEndpoint;
import org.opentides.service.SyncEndpointService;
import org.springframework.stereotype.Service;

/**
 * This is the service implementation for SyncEndpoint.
 * Scaffold generated by opentides3 on Jan 17, 2015 08:39:59. 
 * @author opentides
 *
 */
 @Service(value = "syncEndpointService")
public class SyncEndpointServiceImpl extends BaseCrudServiceImpl<SyncEndpoint>
		implements SyncEndpointService {

	 	@Override
		public SyncEndpoint findSyncEndpointByClientCode(String clientcode){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("clientcode", clientcode);
			return getDao().findSingleResultByNamedQuery("jpql.syncendpoint.findEndpointByClientCode", map);
		}
}
