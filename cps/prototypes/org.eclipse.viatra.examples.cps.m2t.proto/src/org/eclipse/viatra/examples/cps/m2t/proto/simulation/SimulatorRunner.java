/*******************************************************************************
 * Copyright (c) 2014, 2016 IncQuery Labs Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Akos Horvath, Abel Hegedus, Marton Bur, Zoltan Ujhelyi - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.examples.cps.m2t.proto.simulation;

public class SimulatorRunner {

	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		simulator.initDeployment();
		while (true) {
			simulator.stepSimulation();
		}
	}

}
