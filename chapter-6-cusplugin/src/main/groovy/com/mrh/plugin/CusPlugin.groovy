package com.mrh.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义plugin，有两个点需要特别注意：
 * 1、使用的Extension类，要用 groovy 文件，不要直接用 java 文件，否则使用的时候会报错找不到方法
 * 2、目录结构，必须严格 按照 resources -> META-INF -> gradle-plugins   ->   cusplugin.properties
 * 3、customplugin.properties文件、gradle、以及外部apply的时候，注意id要相同
 */
public class CusPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("***********custom plugin apply**************")
        project.extensions.add("cusExtension", CusExtension);

        project.task('helloPlugin', {
            doLast {
                CusExtension cusExtension = project.cusExtension
                System.out.println("your name : " + cusExtension.name)
                System.out.println("your hobby : " + cusExtension.hobby)
            }
        })
    }
}