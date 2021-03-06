/*******************************************************************************
 * Copyright (c) 2014-2016, IncQuery Labs Ltd. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Akos Horvath, Abel Hegedus, Tamas Borbas, Marton Bur, Zoltan Ujhelyi, Robert Doczi, Daniel Segesdi, Peter Lunk - initial API and implementation
 *******************************************************************************/

package org.eclipse.viatra.examples.cps.xform.m2m.launcher

import org.eclipse.viatra.examples.cps.traceability.CPSToDeployment
import org.eclipse.viatra.examples.cps.xform.m2m.batch.eiq.CPS2DeploymentBatchTransformationEiq
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint

class BatchQueryOnly extends CPSTransformationWrapper {

	CPS2DeploymentBatchTransformationEiq xform
	AdvancedViatraQueryEngine engine
	QueryEvaluationHint hint
	QueryEvaluationHint tracesHint

	new(QueryEvaluationHint hint, QueryEvaluationHint tracesHint) {
		this.hint = hint
		this.tracesHint = tracesHint
	}

	override initializeTransformation(CPSToDeployment cps2dep) {
		engine = AdvancedViatraQueryEngine.createUnmanagedEngine(new EMFScope(cps2dep.eResource.resourceSet));
		xform = new CPS2DeploymentBatchTransformationEiq(cps2dep, engine, hint, tracesHint)
	}

	override executeTransformation() {
		xform.execute
	}

	override cleanupTransformation() {
		if (engine != null) {
			engine.dispose
		}
		engine = null
		xform = null
	}
	
	override isIncremental() {
		false
	}

}
