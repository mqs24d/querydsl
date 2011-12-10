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
package com.mysema.query.lucene;

import com.mysema.query.types.Constant;
import com.mysema.query.types.ConstantImpl;
import com.mysema.query.types.Visitor;
import com.mysema.query.types.expr.StringExpression;

/**
 * PhraseElement represents the embedded String as a phrase
 *
 * @author tiwe
 *
 */
public class PhraseElement extends StringExpression{

    private static final long serialVersionUID = 2350215644019186076L;

    private final Constant<String> string;

    public PhraseElement(String str) {
        this.string = ConstantImpl.create(str);
    }

    @Override
    public <R,C> R accept(Visitor<R,C> v, C context) {
        return string.accept(v, context);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        } else if (o instanceof PhraseElement){
            return ((PhraseElement)o).string.equals(string);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(){
        return string.hashCode();
    }
    
}
