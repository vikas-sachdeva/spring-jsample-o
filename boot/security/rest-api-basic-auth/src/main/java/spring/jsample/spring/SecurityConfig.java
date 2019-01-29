package spring.jsample.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import spring.jsample.util.AppConstants;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().
		antMatchers("/admin/**").hasRole(AppConstants.Role.ADMIN).
		antMatchers("/*").hasAnyRole(AppConstants.Role.USER, AppConstants.Role.ADMIN).
		antMatchers("/*/*").hasAnyRole(AppConstants.Role.USER, AppConstants.Role.ADMIN).
		anyRequest().denyAll()
		.and().httpBasic().realmName("jsample").and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * {noop} is password encoder that does nothing. Useful for testing where
		 * working with plain text passwords may be preferred.
		 */
		UserDetails user1 = User.withUsername(AppConstants.UserDetails.USER_ID).password("{noop}"+AppConstants.UserDetails.USER_PASSWORD).roles(AppConstants.Role.USER).build();
		auth.inMemoryAuthentication().withUser(user1);

		UserDetails user2 = User.withUsername(AppConstants.UserDetails.ADMIN_ID).password("{noop}"+AppConstants.UserDetails.ADMIN_PASSWORD).roles(AppConstants.Role.ADMIN)
				.build();
		auth.inMemoryAuthentication().withUser(user2).withUser(user1);
	}
}