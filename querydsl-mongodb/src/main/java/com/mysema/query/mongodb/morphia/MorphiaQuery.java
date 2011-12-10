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
package com.mysema.query.mongodb.morphia;

import org.apache.commons.collections15.Transformer;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.google.code.morphia.mapping.cache.DefaultEntityCache;
import com.google.code.morphia.mapping.cache.EntityCache;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mysema.query.mongodb.MongodbQuery;
import com.mysema.query.types.EntityPath;

/**
 * MorphiaQuery extends MongodbQuery with Morphia specific transformations
 *
 * @author laimw
 * @author tiwe
 *
 */
public class MorphiaQuery<K> extends MongodbQuery<K> {

    private final EntityCache cache;

    public MorphiaQuery(Morphia morphia, Datastore datastore, EntityPath<K> entityPath) {
        this(morphia, datastore, new DefaultEntityCache(), entityPath);
    }

    public MorphiaQuery(final Morphia morphia, final Datastore datastore,
            final EntityCache cache, final EntityPath<K> entityPath) {
        super(datastore.getCollection(entityPath.getType()), new Transformer<DBObject, K>(){
            @Override
            public K transform(DBObject dbObject) {
                return morphia.fromDBObject(entityPath.getType(), dbObject, cache);
            }
        }, MorphiaSerializer.DEFAULT);
        this.cache = cache;
    }

    @Override
    protected DBCursor createCursor() {
        cache.flush();
        return super.createCursor();
    }

}