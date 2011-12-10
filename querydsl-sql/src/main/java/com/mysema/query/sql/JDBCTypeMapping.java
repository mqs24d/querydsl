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
package com.mysema.query.sql;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * JDBCTypeMapping defines a mapping from JDBC types to Java classes.
 *
 * @author tiwe
 *
 */
public final class JDBCTypeMapping {

    private static final Map<Integer, Class<?>> defaultTypes = new HashMap<Integer, Class<?>>();
    
    static{
        // BOOLEAN
        registerDefault(Types.BIT, Boolean.class);
        registerDefault(Types.BOOLEAN, Boolean.class);

        // NUMERIC
        registerDefault(Types.BIGINT, Long.class);
        registerDefault(Types.DECIMAL, BigDecimal.class);
        registerDefault(Types.DOUBLE, Double.class);
        registerDefault(Types.FLOAT, Float.class);
        registerDefault(Types.INTEGER, Integer.class);
        registerDefault(Types.NUMERIC, BigDecimal.class);
        registerDefault(Types.REAL, Float.class);
        registerDefault(Types.SMALLINT, Short.class);
        registerDefault(Types.TINYINT, Byte.class);

        // DATE and TIME
        registerDefault(Types.DATE, java.sql.Date.class);
        registerDefault(Types.TIME, java.sql.Time.class);
        registerDefault(Types.TIMESTAMP, java.sql.Timestamp.class); 

        // TEXT
        registerDefault(Types.CHAR, String.class);
        registerDefault(Types.NCHAR, String.class);
        registerDefault(Types.CLOB, String.class);
        registerDefault(Types.NCLOB, String.class);
        registerDefault(Types.LONGVARCHAR, String.class);
        registerDefault(Types.LONGNVARCHAR, String.class);
        registerDefault(Types.SQLXML, String.class);
        registerDefault(Types.VARCHAR, String.class);
        registerDefault(Types.NVARCHAR, String.class);

        // byte[]
        registerDefault(Types.BINARY, byte[].class);
        registerDefault(Types.LONGVARBINARY, byte[].class);
        registerDefault(Types.VARBINARY, byte[].class);
        
        // BLOB
        registerDefault(Types.BLOB, Blob.class);
        
        // OTHER
        registerDefault(Types.ARRAY, Object[].class);
        registerDefault(Types.DISTINCT, Object.class);
        registerDefault(Types.DATALINK, Object.class);
        registerDefault(Types.JAVA_OBJECT, Object.class);
        registerDefault(Types.NULL, Object.class);
        registerDefault(Types.OTHER, Object.class);
        registerDefault(Types.REF, Object.class);
        registerDefault(Types.ROWID, Object.class);
        registerDefault(Types.STRUCT, Object.class);
        
    }
    
    private static void registerDefault(int sqlType, Class<?> javaType) {
        defaultTypes.put(sqlType, javaType);
    }
    
    private final Map<Integer, Class<?>> types = new HashMap<Integer, Class<?>>();
    
    public void register(int sqlType, Class<?> javaType) {
        types.put(sqlType, javaType);
    }

    @Nullable
    public Class<?> get(int sqlType) {
        if (types.containsKey(sqlType)) {
            return types.get(sqlType);
        } else {
            return defaultTypes.get(sqlType);
        }        
    }

}
