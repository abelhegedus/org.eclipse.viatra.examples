package org.eclipse.viatra.examples.cps.tests

import org.eclipse.viatra.query.testing.core.api.ViatraQueryTest
import org.eclipse.viatra.examples.cps.tests.queries.util.StateToState1QuerySpecification
import org.junit.Test
import org.eclipse.viatra.examples.cps.tests.queries.util.StateToState2QuerySpecification
import org.eclipse.viatra.examples.cps.tests.queries.util.StateToState3QuerySpecification
import org.eclipse.viatra.examples.cps.tests.queries.util.TransitionToTransition1QuerySpecification
import org.eclipse.viatra.examples.cps.tests.queries.util.TransitionToTransition2QuerySpecification
import org.eclipse.viatra.query.runtime.rete.matcher.ReteBackendFactory
import org.eclipse.viatra.query.testing.core.XmiModelUtil.XmiModelUtilRunningOptionEnum
import org.eclipse.viatra.query.testing.core.XmiModelUtil
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.viatra.query.runtime.base.api.BaseIndexOptions
import org.eclipse.viatra.query.testing.snapshot.QuerySnapshot

class DanglingTest {
    val snapshot = "org.eclipse.viatra.examples.cps.tests/models/snapshots/test_dangling.snapshot"
    public String modelPath = "org.eclipse.viatra.examples.cps.tests/models/instances/dangling.cyberphysicalsystem"
   
     
    extension AllBackendTypes = new AllBackendTypes
 
    def makeScope() {
    	val options = new BaseIndexOptions().withDanglingFreeAssumption(false) 
    	val uri = XmiModelUtil::resolvePlatformURI(XmiModelUtilRunningOptionEnum.BOTH, snapshot)
    	val ResourceSet rSet = new ResourceSetImpl()
    	rSet.getResource(uri , true)
    	return new EMFScope(rSet, options)
    } 
    def extractSnapshot(EMFScope scope) {
    	(scope.scopeRoots.get(0) as ResourceSet).resources.get(0).contents.get(0) as QuerySnapshot
    }
    
    
    
// In a better world, this would work
//    @Test
//    def void stateToState1Ideal() {
//        ViatraQueryTest.test(StateToState1QuerySpecification.instance)
//        				.with(new BaseIndexOptions().withDanglingFreeAssumption(false))
//                        .with(snapshot)
//                        .withAll
//                        .assertEquals
//    }
    
    @Test
    def void stateToState1() {
    	val scope = makeScope
        ViatraQueryTest.test(StateToState1QuerySpecification.instance)
        				.on(scope)
                        .with(scope.extractSnapshot)
                        .withAll
                        .assertEquals
    }
    
    @Test
    def void stateToState2() {
    	val scope = makeScope
        ViatraQueryTest.test(StateToState2QuerySpecification.instance)
        				.on(scope)
                        .with(scope.extractSnapshot)
                        .withAll
                        .assertEquals
    }
    
    @Test
    def void stateToState3() {
    	val scope = makeScope
        ViatraQueryTest.test(StateToState3QuerySpecification.instance)
        				.on(scope)
                        .with(scope.extractSnapshot)
                        .withAll
                        .assertEquals
    }
            
    @Test
    def void transitionToTransition1() {
    	val scope = makeScope
        ViatraQueryTest.test(TransitionToTransition1QuerySpecification.instance)
        				.on(scope)
                        .with(scope.extractSnapshot)
                        .withAll
                        .assertEquals
    }
        
    @Test
    def void transitionToTransition2() {
    	val scope = makeScope
        ViatraQueryTest.test(TransitionToTransition2QuerySpecification.instance)
        				.on(scope)
                        .with(scope.extractSnapshot)
                        .withAll
                        .assertEquals
    }
    
}