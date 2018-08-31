package io.sanye.open.financial.swagger;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * desc.
 *
 * @author jiawei zhang
 * 2018/8/30 下午4:08
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = { java.lang.annotation.ElementType.TYPE })
@Documented
@Import({SwaggerConfiguration.class})
@EnableSwagger2
public @interface EnableMySwagger {
}
