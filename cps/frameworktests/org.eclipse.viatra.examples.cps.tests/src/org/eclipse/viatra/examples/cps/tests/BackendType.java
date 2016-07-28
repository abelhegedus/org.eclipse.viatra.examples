/*******************************************************************************
 * Copyright (c) 2014-2016 IncQuery Labs Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Akos Horvath, Abel Hegedus, Akos Menyhert, Tamas Borbas, Marton Bur, Zoltan Ujhelyi, Daniel Segesdi - initial API and implementation
 *******************************************************************************/
package org.eclipse.viatra.examples.cps.tests;

import java.util.Collections;

import org.eclipse.viatra.query.runtime.localsearch.matcher.integration.LocalSearchBackendFactory;
import org.eclipse.viatra.query.runtime.localsearch.matcher.integration.LocalSearchHints;
import org.eclipse.viatra.query.runtime.matchers.backend.IQueryBackendFactory;
import org.eclipse.viatra.query.runtime.matchers.backend.QueryEvaluationHint;
import org.eclipse.viatra.query.runtime.rete.matcher.ReteBackendFactory;

public enum BackendType {
    Rete, LocalSearch,
    
    LocalSearch_Flat,
    LocalSearch_NoBase;
    
    public IQueryBackendFactory getNewBackendInstance() {
        switch(this) {
            case Rete: return new ReteBackendFactory();
            case LocalSearch_Flat:
            case LocalSearch_NoBase:
            case LocalSearch: 
                return LocalSearchBackendFactory.INSTANCE;
            default: return null;
        }
    }
    
    public QueryEvaluationHint getHints(){
        switch(this){
        case LocalSearch:
            return LocalSearchHints.getDefault().build();
        case LocalSearch_Flat:
            return LocalSearchHints.getDefault_flatten().build();
        case LocalSearch_NoBase:
            return LocalSearchHints.getDefault_nobase().build();
        default:
            return new QueryEvaluationHint(getNewBackendInstance(), Collections.<String, Object>emptyMap());
        }
    }
}
