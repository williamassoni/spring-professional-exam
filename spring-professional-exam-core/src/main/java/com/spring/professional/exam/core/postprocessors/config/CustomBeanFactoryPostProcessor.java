package com.spring.professional.exam.core.postprocessors.config;

import static java.util.Arrays.stream;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        stream(beanFactory.getBeanDefinitionNames())
                .map(beanFactory::getBeanDefinition)
                .filter(beanDefinition -> beanClassNameContains(beanDefinition, "com.spring.professional.exam.core.postprocessors"))
                .map(BeanDefinition::getBeanClassName)
                .forEach(System.err::println);
    }

    private boolean beanClassNameContains(BeanDefinition beanDefinition, String subString) {
        return beanDefinition.getBeanClassName() != null && beanDefinition.getBeanClassName().contains(subString);
    }

}
