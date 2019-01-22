package spring.jsample.spring;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

	@Bean
	public FilterRegistrationBean<UniqueIdFilter> servletRegistrationBean() {
		UniqueIdFilter uniqueIdFilter = new UniqueIdFilter();
		FilterRegistrationBean<UniqueIdFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(uniqueIdFilter);
		registrationBean.setOrder(2);
		return registrationBean;
	}
}
