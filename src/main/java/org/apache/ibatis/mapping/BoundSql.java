/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;

/**
 * An actual SQL String got from an {@link SqlSource} after having processed any dynamic content.
 * The SQL may have SQL placeholders "?" and an list (ordered) of an parameter mappings
 * with the additional information for each parameter (at least the property name of the input object to read
 * the value from).
 * <p>
 * Can also have additional parameters that are created by the dynamic language (for loops, bind...).
 *
 * @author Clinton Begin
 */

/**
 * BoundSql (结果对象)
 * 	是 SqlSource 通过对 sql和参数 联合解析得到的 sql和参数
 * 	有3个常用属性   sql / parameterObject / parameterMappings
 *
 * 	parameterObject 为参数本身，可以传递简单对象，pojo，或者map、@Param注解的参数
 * 	      1.传递简单对象。如 int String float double 等
 * 						当传递 int 时 mybatis会把参数变为 Integer 对象传递 ， 类似 long String float double也是如此
 *
 * 				2.传递 POJO 或 Map ， 直接传入
 *
 * 				3.传递多个参数：
 * 						如果没有@Param注解，
 * 								那么 mybatis 会把 parameterObject 变为一个Map <String,Object> 对象，
 * 								键值关系按顺序来划分，类似 {"1":p1,"2":p2}  {"param1":p1,"param2":p2}
 * 								所以 sql 才能用 #{1} #{2} 或 #{param1} #{param2} 获取
 *
 * 						如果有@Param
 * 							  那么 mybatis 也会把 parameterObject 变为一个 Map<String,Object> 对象，
 * 								键值关系按传入的key来，类似 {"key1":p1,"key2":p2}
 * 								同时也包含 {"param1":p1,"param2":p2}
 * 								所以 #{param1} #{param2}依旧有效，但# {1} #{2} 这种写法无效
 *
 * 	ParameterMappings
 * 			一个List，每个元素都是 parameterMapping 对象，对象会描述参数，
 * 			参数属性包括 属性名称， 表达式 javaType jdbcType typeHandler等重要信息
 */
public class BoundSql {
  /** sql 语句 **/
  private final String sql;
  private final List<ParameterMapping> parameterMappings;
  private final Object parameterObject;
  private final Map<String, Object> additionalParameters;
  private final MetaObject metaParameters;

  public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
    this.sql = sql;
    this.parameterMappings = parameterMappings;
    this.parameterObject = parameterObject;
    this.additionalParameters = new HashMap<>();
    this.metaParameters = configuration.newMetaObject(additionalParameters);
  }

  public String getSql() {
    return sql;
  }

  public List<ParameterMapping> getParameterMappings() {
    return parameterMappings;
  }

  public Object getParameterObject() {
    return parameterObject;
  }

  public boolean hasAdditionalParameter(String name) {
    String paramName = new PropertyTokenizer(name).getName();
    return additionalParameters.containsKey(paramName);
  }

  public void setAdditionalParameter(String name, Object value) {
    metaParameters.setValue(name, value);
  }

  public Object getAdditionalParameter(String name) {
    return metaParameters.getValue(name);
  }
}
