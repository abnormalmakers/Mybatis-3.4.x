package com.qmy.myplugin;

import com.qmy.myplugin.interceptor.HelloLogicInterceptor;
import com.qmy.myplugin.interceptor.LogInterceptor;
import com.qmy.myplugin.interceptor.MyInterceptor;
import com.qmy.myplugin.myinterveptorchain.MyInterceptorChain;
import com.qmy.myplugin.service.HelloService;
import com.qmy.myplugin.service.HelloServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MyTestJdkProxy {

  public static void main(String[] args) {
    /**
     * jdk 动态代理
     */
/*    HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
    MyInvocationHandler myInvocationHandler = new MyInvocationHandler(helloServiceImpl);
    HelloService helloService = (HelloService) MyInvocationHandler.wrap(helloServiceImpl);
    helloService.sayhello();*/


/*    HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
    List<MyInterceptor> list = new ArrayList<>();
    HelloLogicInterceptor helloLogicInterceptor = new HelloLogicInterceptor();
    LogInterceptor logInterceptor = new LogInterceptor();
    list.add(helloLogicInterceptor);
    list.add(logInterceptor);
    HelloService helloService = (HelloService) MyInvocationHandler.wrap(helloServiceImpl,list);
    helloService.sayhello();*/

/*    HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

    LogInterceptor logInterceptor = new LogInterceptor();
    HelloService helloService = (HelloService) MyInvocationHandler.wrap(helloServiceImpl,logInterceptor);
    helloService.sayhello();*/

    HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

    LogInterceptor logInterceptor = new LogInterceptor();
    HelloLogicInterceptor helloLogicInterceptor = new HelloLogicInterceptor();

    MyInterceptorChain myInterceptorChain = new MyInterceptorChain();
    myInterceptorChain.addMyInterceptor(logInterceptor);
    myInterceptorChain.addMyInterceptor(helloLogicInterceptor);

    HelloService helloService = (HelloService) myInterceptorChain.pluginAll(helloServiceImpl);
    helloService.sayhello();


  }
}
