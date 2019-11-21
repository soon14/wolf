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
package study.daydayup.wolf.common.generator.mybatis.sql;

import study.daydayup.wolf.common.generator.mybatis.api.dom.java.FullyQualifiedJavaType;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.Interface;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.JavaVisibility;
import study.daydayup.wolf.common.generator.mybatis.api.dom.java.TopLevelClass;
import study.daydayup.wolf.common.generator.mybatis.codegen.AbstractJavaClientGenerator;
import study.daydayup.wolf.common.generator.mybatis.codegen.AbstractXmlGenerator;
import study.daydayup.wolf.common.generator.mybatis.config.PropertyRegistry;
import study.daydayup.wolf.common.generator.mybatis.internal.util.JavaBeansUtil;
import study.daydayup.wolf.common.generator.mybatis.runtime.dynamic.sql.elements.*;

import static study.daydayup.wolf.common.generator.mybatis.internal.util.StringUtility.stringHasValue;

public abstract class AbstractDynamicSqlMapperGenerator extends AbstractJavaClientGenerator {

    // record type for insert, select, update
    protected FullyQualifiedJavaType recordType;
    
    // id to use for the common result map
    protected String resultMapId;
    
    // name of the field containing the table in the support class
    protected String tableFieldName;
    
    protected FragmentGenerator fragmentGenerator;

    public AbstractDynamicSqlMapperGenerator(String project) {
        super(project, false);
    }

    protected void preCalculate() {
        recordType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        resultMapId = recordType.getShortNameWithoutTypeArguments() + "Result"; //$NON-NLS-1$
        tableFieldName =
                JavaBeansUtil.getValidPropertyName(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
        fragmentGenerator = new FragmentGenerator.Builder()
                .withIntrospectedTable(introspectedTable)
                .withResultMapId(resultMapId)
                .withTableFieldName(tableFieldName)
                .build();
    }

    protected Interface createBasicInterface() {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3JavaMapperType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        context.getCommentGenerator().addJavaFileComment(interfaze);
        interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper")); //$NON-NLS-1$
        interfaze.addAnnotation("@Mapper"); //$NON-NLS-1$

        String rootInterface = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }
        return interfaze;
    }

    protected void addBasicCountMethod(Interface interfaze) {
        BasicCountMethodGenerator generator = new BasicCountMethodGenerator.Builder()
                .withContext(context)
                .withIntrospectedTable(introspectedTable)
                .build();
        
        generate(interfaze, generator);
    }
    
    protected void addBasicDeleteMethod(Interface interfaze) {
        BasicDeleteMethodGenerator generator = new BasicDeleteMethodGenerator.Builder()
                .withContext(context)
                .withIntrospectedTable(introspectedTable)
                .withTableFieldName(tableFieldName)
                .build();
        
        generate(interfaze, generator);
    }

    protected void addBasicInsertMethod(Interface interfaze) {
        BasicInsertMethodGenerator generator = new BasicInsertMethodGenerator.Builder()
                .withContext(context)
                .withFragmentGenerator(fragmentGenerator)
                .withIntrospectedTable(introspectedTable)
                .withTableFieldName(tableFieldName)
                .withRecordType(recordType)
                .build();
        
        generate(interfaze, generator);
    }

    protected void addBasicSelectManyMethod(Interface interfaze) {
        BasicSelectManyMethodGenerator generator = new BasicSelectManyMethodGenerator.Builder()
                .withContext(context)
                .withFragmentGenerator(fragmentGenerator)
                .withIntrospectedTable(introspectedTable)
                .withTableFieldName(tableFieldName)
                .withRecordType(recordType)
                .build();
        
        generate(interfaze, generator);
    }
    
    protected void addBasicUpdateMethod(Interface interfaze) {
        BasicUpdateMethodGenerator generator = new BasicUpdateMethodGenerator.Builder()
                .withContext(context)
                .withIntrospectedTable(introspectedTable)
                .withTableFieldName(tableFieldName)
                .build();
        
        generate(interfaze, generator);
    }

    protected TopLevelClass getSupportClass() {
        return DynamicSqlSupportClassGenerator.of(
                introspectedTable, context.getCommentGenerator(), warnings).generate();
    }

    protected void generate(Interface interfaze, AbstractMethodGenerator generator) {
        MethodAndImports mi = generator.generateMethodAndImports();
        if (mi != null && generator.callPlugins(mi.getMethod(), interfaze)) {
            interfaze.addMethod(mi.getMethod());
            interfaze.addImportedTypes(mi.getImports());
            interfaze.addStaticImports(mi.getStaticImports());
        }
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return null;
    }
}
