package com.wjf.github.overall.situation.gateway.filter;

import com.wjf.github.commons.util.ResultTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class UserTokenFilter extends GenericFilterBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenFilter.class);

	private static final String TOKEN_NAME = "userToken";

	private static final String USER_NAME_HEADER = "userId";

	private UserDetailsService userDetailsService;

	private RedisService redisService;

	public UserTokenFilter(UserDetailsService userDetailsService, RedisService redisService) {
		this.userDetailsService = userDetailsService;
		this.redisService = redisService;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		final String token = request.getHeader(TOKEN_NAME);
		final String userName = request.getHeader(USER_NAME_HEADER);
		if (StringUtils.hasText(token) && StringUtils.hasText(userName)) {
			final ResultTemplate<String> value = redisService.getValue(token);
			if (StringUtils.hasText(value.getT()) && token.equals(userName)) {
				final UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
				final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				LOGGER.info("用户名{}登录成功",userDetails.getUsername());
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
