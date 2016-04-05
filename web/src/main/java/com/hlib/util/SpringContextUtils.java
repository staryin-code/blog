package com.hlib.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by hlib on 2015/11/2 0002.
 */
public class SpringContextUtils implements ApplicationContextAware{
    private   static  ApplicationContext applicationContext =  null ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /***
     * 根据一个bean的id获取配置文件中相应的bean
     * @param name
     * @return
     * @throws org.springframework.beans.BeansException
     */
    public   static  Object getBean(String name)  throws  BeansException {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        return wac.getBean(name);
    }
    /***
     * 类似于getBean(String name)只是在参数中提供了需要返回到的类型。
     * @param name
     * @param requiredType
     * @return
     * @throws org.springframework.beans.BeansException
     */
    public   static  Object getBean(String name, Class requiredType)  throws  BeansException {
        return  applicationContext.getBean(name, requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     * @param name
     * @return boolean
     */
    public   static   boolean  containsBean(String name) {
        WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
        return  wac.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     * @param name
     * @return boolean
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public   static   boolean  isSingleton(String name)  throws NoSuchBeanDefinitionException {
        return  applicationContext.isSingleton(name);
    }

    /**
     * @param name
     * @return Class 注册对象的类型
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public   static  Class getType(String name)  throws  NoSuchBeanDefinitionException {
        return  applicationContext.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     * @param name
     * @return
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
     */
    public   static  String[] getAliases(String name)  throws  NoSuchBeanDefinitionException {
        return  applicationContext.getAliases(name);
    }
}