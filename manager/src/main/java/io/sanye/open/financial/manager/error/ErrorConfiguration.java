package io.sanye.open.financial.manager.error;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;

/**
 * desc.
 *
 * @author jiawei zhang
 */
@Configuration
public class ErrorConfiguration {

    @Bean
    public MyErrorController basicErrorController(ErrorAttributes errorAttributes,
                                                  ServerProperties serverProperties,
                                                  ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        return new MyErrorController(errorAttributes, serverProperties.getError(),
                errorViewResolversProvider.getIfAvailable());
    }

    @Bean(name = "error")
    public View myError() {
        return new MyView("出错了");
    }

    private static class MyView implements View {

        private final String template;

        MyView(String template) {
            this.template = template;
        }

        @Override
        public String getContentType() {
            return "text/html;charset=utf-8";
        }

        @Override
        public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
            if (response.getContentType() == null) {
                response.setContentType(getContentType());
            }
            //TODO: 可以通过model获取具体数据，或许需要类型转换
            String result = "<html><body><h1>" + template + "</h1></body></html>";
            response.getWriter().append(result);
        }
    }

}
