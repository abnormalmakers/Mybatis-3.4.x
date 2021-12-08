package com.qmy.myplugin.myinterveptorchain;

import com.qmy.myplugin.interceptor.MyInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyInterceptorChain {
  private List<MyInterceptor> interceptors = new ArrayList<>();

  /**
   * 插入所有拦截器
   */
  public Object pluginAll(Object target) {
    for (MyInterceptor myInterceptor : interceptors) {
      target = myInterceptor.plugin(target);
    }
    return target;
  }

  public void addMyInterceptor(MyInterceptor MyInterceptor) {
    interceptors.add(MyInterceptor);
  }
  /**
   * 返回一个不可修改集合，只能通过addMyInterceptor方法添加
   * 这样控制权就在自己手里
   */
  public List<MyInterceptor> getMyInterceptorList() {
    return Collections.unmodifiableList(interceptors);
  }
}
