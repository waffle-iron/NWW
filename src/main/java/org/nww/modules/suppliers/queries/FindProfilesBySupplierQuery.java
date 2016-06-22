/**
 * 
 */
package org.nww.modules.suppliers.queries;

import org.nww.modules.suppliers.orm.Supplier;
import org.nww.modules.suppliers.orm.SupplierManagerImpl;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author mga
 *
 */
public class FindProfilesBySupplierQuery extends Query {
	public FindProfilesBySupplierQuery(Supplier s) {
		super(Criteria.where("extensions.name")
				.is(SupplierManagerImpl.EXTENSION_OBJECT_KEY_SUPPLIER_IDS)
				.and("extensions.stringValues")
				.in(s.getUUID()));
	}
}
