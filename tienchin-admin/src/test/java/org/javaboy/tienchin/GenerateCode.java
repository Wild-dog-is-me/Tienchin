package org.javaboy.tienchin;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @Author: Odin
 * @Date: 2023/7/22 15:06
 * @Description:
 */

@SpringBootTest
public class GenerateCode {

    @Test
    void generateChannelCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-channel/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("channel") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_channel") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateActivityCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-activity/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("activity") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_activity") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateCourseCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-course/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("course") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_course") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateClueCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-clue/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("clue") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_clue") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateFollowCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-follow/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("follow") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_follow_record") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateAssignmentCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-assignment/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("assignment") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_assignment") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateBusinessCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-business/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("business") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_business") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

    @Test
    void generateContractCode() {

        String path = "/Users/odin/Documents/ruo-yi-study/my-study/code/tienchin/tienchin-contract/src/main";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/ruoyi?serverTimezone=Asia/Shanghai&useSSL=false", "root", "root")
                .globalConfig(builder -> {
                    builder.author("Odin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(path + "/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.tienchin") // 设置父包名
                            .moduleName("contract") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("tienchin_contract") // 设置需要生成的表名
                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
