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
package com.mysema.query;

import com.mysema.query.types.Constant;
import com.mysema.query.types.Visitor;
import com.mysema.query.types.expr.BooleanExpression;

/**
 * BooleanConstant provides constants for Boolean.TRUE and Boolean.FALSE
 *
 * @author tiwe
 *
 */
final class BooleanConstant extends BooleanExpression implements Constant<Boolean>{

    public static final BooleanExpression FALSE = new BooleanConstant(Boolean.FALSE);

    private static final long serialVersionUID = -4106376704553234781L;

    public static final BooleanExpression TRUE = new BooleanConstant(Boolean.TRUE);

    public static BooleanExpression create(Boolean b){
        return b.booleanValue() ? TRUE : FALSE;
    }

    private final Boolean constant;

    private BooleanConstant(Boolean b){
        this.constant = b;
    }

    @Override
    public <R,C> R accept(Visitor<R,C> v, C context) {
        return v.visit(this, context);
    }

    @Override
    public BooleanExpression eq(Boolean b){
        return constant.equals(b) ? TRUE : FALSE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }else if (o instanceof Constant){
            Constant c = (Constant)o;
            return c.getConstant().equals(constant);
        }else{
            return false;
        }
    }

    @Override
    public Boolean getConstant() {
        return constant;
    }

    @Override
    public int hashCode() {
        return constant.hashCode();
    }

    @Override
    public BooleanExpression ne(Boolean b){
        return constant.equals(b) ? FALSE : TRUE;
    }

    @Override
    public BooleanExpression not() {
        return constant.booleanValue() ? FALSE : TRUE;
    }

}
