/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.device.persistence.rdb.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.sitewhere.rdb.entities.RdbBrandedEntity;
import com.sitewhere.spi.customer.ICustomer;

@Entity
@Table(name = "customer")
@NamedQueries({
	@NamedQuery(name = Queries.QUERY_CUSTOMER_BY_TOKEN, query = "SELECT c FROM RdbCustomer c WHERE c.token = :token"),
	@NamedQuery(name = Queries.QUERY_CUSTOMER_BY_PARENT_ID, query = "SELECT c FROM RdbCustomer c WHERE c.parentId = :parentId") })
public class RdbCustomer extends RdbBrandedEntity implements ICustomer {

    /** Serial version UID */
    private static final long serialVersionUID = 5603208951111630899L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    /** Customer type id */
    @Column(name = "customer_type_id")
    private UUID customerTypeId;

    /** Parent customer id */
    @Column(name = "parent_id")
    private UUID parentId;

    /** Area name */
    @Column(name = "name")
    private String name;

    /** Area description */
    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @CollectionTable(name = "customer_metadata")
    @MapKeyColumn(name = "prop_key")
    @Column(name = "prop_value")
    private Map<String, String> metadata = new HashMap<>();

    /*
     * @see com.sitewhere.spi.common.IPersistentEntity#getId()
     */
    @Override
    public UUID getId() {
	return id;
    }

    public void setId(UUID id) {
	this.id = id;
    }

    /*
     * @see com.sitewhere.spi.customer.ICustomer#getCustomerTypeId()
     */
    @Override
    public UUID getCustomerTypeId() {
	return customerTypeId;
    }

    public void setCustomerTypeId(UUID customerTypeId) {
	this.customerTypeId = customerTypeId;
    }

    /*
     * @see com.sitewhere.spi.common.ITreeEntity#getParentId()
     */
    @Override
    public UUID getParentId() {
	return parentId;
    }

    public void setParentId(UUID parentId) {
	this.parentId = parentId;
    }

    /*
     * @see com.sitewhere.spi.common.IAccessible#getName()
     */
    @Override
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    /*
     * @see com.sitewhere.spi.common.IAccessible#getDescription()
     */
    @Override
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    /*
     * @see com.sitewhere.spi.common.IMetadataProvider#getMetadata()
     */
    @Override
    public Map<String, String> getMetadata() {
	return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
	this.metadata = metadata;
    }

    public static void copy(ICustomer source, RdbCustomer target) {
	if (source.getId() != null) {
	    target.setId(source.getId());
	}
	if (source.getCustomerTypeId() != null) {
	    target.setCustomerTypeId(source.getCustomerTypeId());
	}
	if (source.getParentId() != null) {
	    target.setParentId(source.getParentId());
	}
	if (source.getName() != null) {
	    target.setName(source.getName());
	}
	if (source.getDescription() != null) {
	    target.setDescription(source.getDescription());
	}
	if (source.getMetadata() != null) {
	    target.setMetadata(source.getMetadata());
	}
	RdbBrandedEntity.copy(source, target);
    }
}
