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
package com.mysema.query.types;

import java.util.List;

/**
 * Operation represents an operation with operator and arguments
 *
 * @author tiwe
 */
public interface Operation<T> extends Expression<T>{

    /**
     * Get the argument with the given index
     *
     * @param index
     * @return
     */
    Expression<?> getArg(int index);

    /**
     * Get the arguments of this operation
     *
     * @return
     */
    List<Expression<?>> getArgs();

    /**
     * Get the operator symbol for this operation
     *
     * @return
     */
    Operator<? super T> getOperator();

}
