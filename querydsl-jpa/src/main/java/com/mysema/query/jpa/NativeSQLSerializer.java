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
package com.mysema.query.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;

import com.mysema.query.sql.SQLSerializer;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.Constant;
import com.mysema.query.types.Path;

/**
 * NativeSQLSerializer extends the SQLSerializer to extract referenced entity paths and change some
 * serialization formats 
 * 
 * @author tiwe
 *
 */
public final class NativeSQLSerializer extends SQLSerializer{

    private final List<Path<?>> entityPaths = new ArrayList<Path<?>>();

    public NativeSQLSerializer(SQLTemplates templates) {
        super(templates);
    }

    @Override
    public Void visit(Constant<?> expr, Void context) {
        if (expr.getConstant() instanceof Collection<?>) {
            append("(");
            boolean first = true;
            for (Object element : ((Collection<?>)expr.getConstant())) {
                if (!first) {
                    append(", ");
                }
                visitConstant(element);
                first = false;
            }            
            append(")");
        } else {
            visitConstant(expr.getConstant());    
        }
        return null;
    }

    private void visitConstant(Object constant) {
        if (!getConstantToLabel().containsKey(constant)) {
            String constLabel = getConstantPrefix() + (getConstantToLabel().size() + 1);
            getConstantToLabel().put(constant, constLabel);
            append(":"+constLabel);
        } else {
            append(":"+getConstantToLabel().get(constant));
        }
    }
    
    @Override
    public Void visit(Path<?> path, Void context) {
        if (path.getMetadata().getParent() == null && path.getType().isAnnotationPresent(Entity.class)) {
            super.visit(path, context);
            if (stage == Stage.SELECT) {
                append(".*");    
            }            
            entityPaths.add(path);
        } else {
            super.visit(path, context);
        }
        return null;
    }

    public List<Path<?>> getEntityPaths() {
        return entityPaths;
    }

}
