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
package com.mysema.query.jpa.impl;

import javax.persistence.Query;

/**
 * NoSessionHolder is a session holder for detached JPAQuery usage
 *
 * @author tiwe
 *
 */
public class NoSessionHolder implements JPASessionHolder{

    @Override
    public Query createQuery(String queryString) {
        throw new UnsupportedOperationException("No entityManager in detached Query available");
    }

    @Override
    public Query createSQLQuery(String queryString) {
        throw new UnsupportedOperationException("No entityManager in detached Query available");
    }

    @Override
    public Query createSQLQuery(String queryString, Class<?> resultClass) {
        throw new UnsupportedOperationException("No entityManager in detached Query available");
    }

}
