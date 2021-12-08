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
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * @author Clinton Begin
 * 这个类是对需要代理的对象做增强逻辑，在 intercept 方法里做逻辑增强，
 * 这样就不用把增强逻辑的代码直接写入调用处理器的 invoke 方法里
 */
public interface Interceptor {

  /**
   * 执行拦截逻辑的方法
   * Invocation 式封装了目标对象的一个对象，
   * 包含了
   *   目标对象本身，
   *   目标对象调用的方法，
   *   目标对想调用方法所需参数
   */
  Object intercept(Invocation invocation) throws Throwable;

  /**
   * target 是被拦截的对象，作用是给被拦截的对象生成一个代理对象
   */
  default Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  /**
   * 读取 plugin 中设置的参数
   */
  default void setProperties(Properties properties) {
    // NOP
  }

}
