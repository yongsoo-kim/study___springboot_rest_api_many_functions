package com.study.restfulapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    //application.yml에 아이디 패쓰워드를 설정해도 되지만, 이렇게되면 비밀번호를 바꿀수 없고, 바꿔도 그때마다 서버를 재기동해야한다.
    //따라서 DB나 LDAP에 있는 비밀번호를 동적으로 설정할려면, 다음과 같이 설정해주면 된다.
    @Autowired
    public void ConfigureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("kim")
                //여기서는 인코딩 없이 비밀번호설정하기로 한다. 그럴때는 "{noop}"를 사용한다.
                .password("{noop}test1234")
                .roles("USER");
    }


}
