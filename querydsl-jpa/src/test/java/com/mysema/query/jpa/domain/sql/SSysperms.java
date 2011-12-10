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
package com.mysema.query.jpa.domain.sql;

import static com.mysema.query.types.PathMetadataFactory.forVariable;

import com.mysema.query.types.Path;
import com.mysema.query.types.PathMetadata;
import com.mysema.query.types.path.StringPath;


/**
 * SSysperms is a Querydsl query type for SSysperms
 */
public class SSysperms extends com.mysema.query.sql.RelationalPathBase<SSysperms> {

    private static final long serialVersionUID = -1700037433;

    public static final SSysperms sysperms = new SSysperms("SYSPERMS");

    public final StringPath grantee = createString("GRANTEE");

    public final StringPath grantor = createString("GRANTOR");

    public final StringPath isgrantable = createString("ISGRANTABLE");

    public final StringPath objectid = createString("OBJECTID");

    public final StringPath objecttype = createString("OBJECTTYPE");

    public final StringPath permission = createString("PERMISSION");

    public final StringPath uuid = createString("UUID");

    public SSysperms(String variable) {
        super(SSysperms.class, forVariable(variable), "SYS", "SYSPERMS");
    }

    public SSysperms(Path<? extends SSysperms> entity) {
        super(entity.getType(), entity.getMetadata(), "SYS", "SYSPERMS");
    }

    public SSysperms(PathMetadata<?> metadata) {
        super(SSysperms.class, metadata, "SYS", "SYSPERMS");
    }

}

