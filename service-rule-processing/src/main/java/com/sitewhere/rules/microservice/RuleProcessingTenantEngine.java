/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.rules.microservice;

import com.sitewhere.microservice.lifecycle.CompositeLifecycleStep;
import com.sitewhere.microservice.multitenant.MicroserviceTenantEngine;
import com.sitewhere.rules.configuration.RuleProcessingTenantConfiguration;
import com.sitewhere.rules.configuration.RuleProcessingTenantEngineModule;
import com.sitewhere.rules.spi.IRuleProcessorsManager;
import com.sitewhere.rules.spi.microservice.IRuleProcessingTenantEngine;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.microservice.lifecycle.ICompositeLifecycleStep;
import com.sitewhere.spi.microservice.lifecycle.ILifecycleProgressMonitor;
import com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine;
import com.sitewhere.spi.microservice.multitenant.ITenantEngineModule;

import io.sitewhere.k8s.crd.tenant.engine.SiteWhereTenantEngine;

/**
 * Implementation of {@link IMicroserviceTenantEngine} that implements rule
 * processing functionality.
 */
public class RuleProcessingTenantEngine extends MicroserviceTenantEngine<RuleProcessingTenantConfiguration>
	implements IRuleProcessingTenantEngine {

    /** Rule processors manager */
    private IRuleProcessorsManager ruleProcessorsManager;

    public RuleProcessingTenantEngine(SiteWhereTenantEngine engine) {
	super(engine);
    }

    /*
     * @see com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine#
     * getConfigurationClass()
     */
    @Override
    public Class<RuleProcessingTenantConfiguration> getConfigurationClass() {
	return RuleProcessingTenantConfiguration.class;
    }

    /*
     * @see com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine#
     * createConfigurationModule()
     */
    @Override
    public ITenantEngineModule<RuleProcessingTenantConfiguration> createConfigurationModule() {
	return new RuleProcessingTenantEngineModule(getActiveConfiguration());
    }

    /*
     * @see com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine#
     * tenantInitialize(com.sitewhere.spi.microservice.lifecycle.
     * ILifecycleProgressMonitor)
     */
    @Override
    public void tenantInitialize(ILifecycleProgressMonitor monitor) throws SiteWhereException {
	// Create outbound connectors manager.
	// this.ruleProcessorsManager = (IRuleProcessorsManager) getModuleContext()
	// .getBean(RuleProcessingBeans.BEAN_RULE_PROCESSORS_MANAGER);

	// Create step that will initialize components.
	ICompositeLifecycleStep init = new CompositeLifecycleStep("Initialize " + getComponentName());

	// Initialize rule processors manager.
	init.addInitializeStep(this, getRuleProcessorsManager(), true);

	// Execute initialization steps.
	init.execute(monitor);
    }

    /*
     * @see com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine#
     * tenantStart(com.sitewhere.spi.microservice.lifecycle.
     * ILifecycleProgressMonitor)
     */
    @Override
    public void tenantStart(ILifecycleProgressMonitor monitor) throws SiteWhereException {
	// Create step that will start components.
	ICompositeLifecycleStep start = new CompositeLifecycleStep("Start " + getComponentName());

	// Start rule processors manager.
	start.addStartStep(this, getRuleProcessorsManager(), true);

	// Execute startup steps.
	start.execute(monitor);
    }

    /*
     * @see com.sitewhere.spi.microservice.multitenant.IMicroserviceTenantEngine#
     * tenantStop(com.sitewhere.spi.microservice.lifecycle.
     * ILifecycleProgressMonitor)
     */
    @Override
    public void tenantStop(ILifecycleProgressMonitor monitor) throws SiteWhereException {
	// Create step that will stop components.
	ICompositeLifecycleStep start = new CompositeLifecycleStep("Stop " + getComponentName());

	// Stop rule processors manager.
	start.addStopStep(this, getRuleProcessorsManager());

	// Execute shutdown steps.
	start.execute(monitor);
    }

    /*
     * @see com.sitewhere.rules.spi.microservice.IRuleProcessingTenantEngine#
     * getRuleProcessorsManager()
     */
    @Override
    public IRuleProcessorsManager getRuleProcessorsManager() {
	return ruleProcessorsManager;
    }

    public void setRuleProcessorsManager(IRuleProcessorsManager ruleProcessorsManager) {
	this.ruleProcessorsManager = ruleProcessorsManager;
    }
}