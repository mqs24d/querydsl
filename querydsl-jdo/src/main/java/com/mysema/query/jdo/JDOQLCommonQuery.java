/*
 * Copyright 2011, Mysema Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.query.jdo;

import com.mysema.query.Query;
import com.mysema.query.types.EntityPath;

/**
 * JDOQLCommonQuery is a parent interface for JDOQLQuery and JDOQLSubQuery
 * 
 * @author tiwe
 *
 * @param <Q>
 */
public interface JDOQLCommonQuery<Q extends JDOQLCommonQuery<Q>> extends Query<Q> {
    
    /**
     * Add query sources
     *
     * @param sources
     * @return
     */
    Q from(EntityPath<?>... sources);

}
