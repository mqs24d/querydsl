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

/**
 * @author tiwe
 *
 */
public class ExpressionException extends RuntimeException{

    private static final long serialVersionUID = 6031724386976562965L;

    public ExpressionException(String msg) {
        super(msg);
    }

    public ExpressionException(String msg, Throwable t) {
        super(msg, t);
    }

    public ExpressionException(Throwable t) {
        super(t);
    }

}
