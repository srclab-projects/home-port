package xyz.srclab.home.port.server.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.savedrequest.HttpSessionRequestCache

@EnableWebSecurity
@Configuration
open class HomePortSecurityConfigurer : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/welcome")
            .permitAll()
            .antMatchers("/hello")
            .hasAuthority("HELLO")
            .and()
            .formLogin()
            //.loginPage("/login")
            .successHandler { request, response, _ ->
                val cache = HttpSessionRequestCache()
                val savedRequest = cache.getRequest(request, response)
                val url = savedRequest.redirectUrl
                if (url !== null) {
                    response.sendRedirect(url)
                } else {
                    response.sendRedirect("welcome")
                }
            }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        val passwordEncoder = BCryptPasswordEncoder()
        auth.inMemoryAuthentication()
            .passwordEncoder(passwordEncoder)
            .withUser("fred")
            .password(passwordEncoder.encode("123456"))
            .authorities("HELLO")
            .and()
            .withUser("fred2")
            .password(passwordEncoder.encode("123456"))
            .authorities("WELCOME")
    }

    companion object {

        private val logger: Logger = LoggerFactory.getLogger(HomePortSecurityConfigurer::class.java)
    }
}