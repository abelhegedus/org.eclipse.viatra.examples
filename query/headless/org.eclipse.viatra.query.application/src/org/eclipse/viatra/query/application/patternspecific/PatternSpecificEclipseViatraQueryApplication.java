/*******************************************************************************
 * Copyright (c) 2004-2011 Abel Hegedus, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Abel Hegedus - initial API and implementation
 *    Istvan Rath - modifications for pattern-specific API demonstration
 *******************************************************************************/
package org.eclipse.viatra.query.application.patternspecific;

import java.util.Map;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.viatra.query.application.common.ViatraQueryHeadless;

/**
 * @author Abel Hegedus
 * @author Istvan Rath
 * 
 */
public class PatternSpecificEclipseViatraQueryApplication implements IApplication {

	private static String modelParam = "-m";

	@Override
	public Object start(IApplicationContext context) throws Exception {

		Map<String, Object> arguments = context.getArguments();
		String[] args = (String[]) arguments.get("application.args");
		String model = null;
		if (args == null || args.length == 0) {
			displayHelp();
			return IApplication.EXIT_OK;
		}
		int i = 0;
		while (i < args.length) {
			if (args[i].equals(modelParam)) {
				model = args[i + 1];
				i += 2;
				continue;
			}
			else {
				i++;
				continue;
			}
		}

		if (model == null) {
		  System.out.println("Model parameter not set");
			displayHelp();
			return IApplication.EXIT_OK;
		}

		ViatraQueryHeadless hl = new ViatraQueryHeadless();
		System.out.println(hl.executeDemo(model));
		System.out.println(hl.executeDemo_PatternGroups(model));
		System.out.println(hl.executeTrackChangesDemo(model));
		
		
		
		return IApplication.EXIT_OK;
	}

	@Override
	public void stop() {

	}

	private void displayHelp() {
		System.out.println("Usage:\n<call> -m <modelFilePAth> \n  -m    :  Required, the model to match on.");
	}
}