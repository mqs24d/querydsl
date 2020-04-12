/*
 * Copyright 2015, The Querydsl Team (http://www.querydsl.com/team)
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
package com.querydsl.core.group;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.mysema.commons.lang.CloseableIterator;
import com.querydsl.core.FetchableQuery;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.FactoryExpressionUtils;
import com.querydsl.core.types.Projections;

import java.util.Set;

/**
 * Provides aggregated results as a set
 *
 * @author Jan-Willem Gmelig Meyling
 *
 * @param <K> group by key type
 * @param <V> set value type
 */
public class GroupBySet<K, V> extends AbstractGroupByTransformer<K, Set<V>> {

    GroupBySet(Expression<K> key, Expression<?>... expressions) {
        super(key, expressions);
    }

    @Override
    public Set<V> transform(FetchableQuery<?,?> query) {
        // create groups
        FactoryExpression<Tuple> expr = FactoryExpressionUtils.wrap(Projections.tuple(expressions));
        boolean hasGroups = false;
        for (Expression<?> e : expr.getArgs()) {
            hasGroups |= e instanceof GroupExpression;
        }
        if (hasGroups) {
            expr = withoutGroupExpressions(expr);
        }
        final CloseableIterator<Tuple> iter = query.select(expr).iterate();

        Set<V> set = Sets.newHashSet();
        GroupImpl group = null;
        K groupId = null;
        while (iter.hasNext()) {
            @SuppressWarnings("unchecked") //This type is mandated by the key type
            K[] row = (K[]) iter.next().toArray();
            if (group == null) {
                group = new GroupImpl(groupExpressions, maps);
                groupId = row[0];
            } else if (!Objects.equal(groupId, row[0])) {
                set.add(transform(group));
                group = new GroupImpl(groupExpressions, maps);
                groupId = row[0];
            }
            group.add(row);
        }
        if (group != null) {
            set.add(transform(group));
        }
        iter.close();
        return set;
    }

    @SuppressWarnings("unchecked")
    protected V transform(Group group) {
        return (V) group;
    }
}
