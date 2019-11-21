/**
 *    Copyright 2006-2019 the original author or authors.
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
package study.daydayup.wolf.common.generator.mybatis.codegen;

import study.daydayup.wolf.common.generator.mybatis.api.dom.java.*;
import study.daydayup.wolf.common.generator.mybatis.config.PropertyRegistry;

import java.util.List;
import java.util.Properties;

import static study.daydayup.wolf.common.generator.mybatis.internal.util.JavaBeansUtil.getGetterMethodName;

public abstract class AbstractJavaGenerator extends AbstractGenerator {
    public abstract List<CompilationUnit> getCompilationUnits();
    
    private String project;
    
    public AbstractJavaGenerator(String project) {
        this.project = project;
    }
    
    public String getProject() {
        return project;
    }

    public static Method getGetter(Field field) {
        Method method = new Method(getGetterMethodName(field.getName(), field
                .getType()));
        method.setReturnType(field.getType());
        method.setVisibility(JavaVisibility.PUBLIC);
        StringBuilder sb = new StringBuilder();
        sb.append("return "); //$NON-NLS-1$
        sb.append(field.getName());
        sb.append(';');
        method.addBodyLine(sb.toString());
        return method;
    }

    public String getRootClass() {
        String rootClass = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_CLASS);
        if (rootClass == null) {
            Properties properties = context
                    .getJavaModelGeneratorConfiguration().getProperties();
            rootClass = properties.getProperty(PropertyRegistry.ANY_ROOT_CLASS);
        }

        return rootClass;
    }

    protected void addDefaultConstructor(TopLevelClass topLevelClass) {
        topLevelClass.addMethod(getDefaultConstructor(topLevelClass));
    }

    protected void addDefaultConstructorWithGeneratedAnnotatoin(TopLevelClass topLevelClass) {
        topLevelClass.addMethod(getDefaultConstructorWithGeneratedAnnotation(topLevelClass));
    }

    private Method getDefaultConstructor(TopLevelClass topLevelClass) {
        Method method = getBasicConstructor(topLevelClass);
        addGeneratedJavaDoc(method);
        return method;
    }

    private Method getDefaultConstructorWithGeneratedAnnotation(TopLevelClass topLevelClass) {
        Method method = getBasicConstructor(topLevelClass);
        addGeneratedAnnotation(method, topLevelClass);
        return method;
    }

    private Method getBasicConstructor(TopLevelClass topLevelClass) {
        Method method = new Method(topLevelClass.getType().getShortName());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setConstructor(true);
        method.addBodyLine("super();"); //$NON-NLS-1$
        return method;
    }
    
    private void addGeneratedJavaDoc(Method method) {
        context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
    }

    private void addGeneratedAnnotation(Method method, TopLevelClass topLevelClass) {
        context.getCommentGenerator().addGeneralMethodAnnotation(method, introspectedTable,
                topLevelClass.getImportedTypes());
    }
}
