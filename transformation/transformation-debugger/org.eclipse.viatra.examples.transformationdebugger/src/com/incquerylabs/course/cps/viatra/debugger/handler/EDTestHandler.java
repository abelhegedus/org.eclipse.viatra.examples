/**
 * Copyright (c) 2010-2016, Peter Lunk, IncQuery Labs Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   Peter Lunk - initial API and implementation
 */
package com.incquerylabs.course.cps.viatra.debugger.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.viatra.examples.cps.traceability.CPSToDeployment;

import com.incquerylabs.course.cps.viatra.debugger.example.CPSEventDrivenTransformation;
import com.incquerylabs.course.cps.viatra.debugger.example.CPSModelInitializer;
import com.incquerylabs.course.cps.viatra.debugger.example.CPSTransformation;

public class EDTestHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {

        final Job job = new Job("CPS batch trafo job") {
            protected IStatus run(IProgressMonitor monitor) {
                // Load the CPS model
                CPSModelInitializer init = new CPSModelInitializer();
                CPSToDeployment cps2dep = init.loadModel("platform:/plugin/com.incquerylabs.course.cps.viatra.debugger/output/example.cyberphysicalsystem");

                // Initialize CPS to Deployment Transformation
                CPSTransformation transformation = new CPSEventDrivenTransformation(cps2dep);
                // Execute the transformation and observe the effects of the selected adapter
                transformation.execute();
                
                transformation.dispose();
                return Status.OK_STATUS;
            }
        };
        job.schedule();
        return null;
    }

}
