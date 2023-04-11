package com.example.restservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.moesif.servlet.MoesifConfiguration;
import com.moesif.servlet.MoesifConfigurationAdapter;
import com.moesif.servlet.MoesifFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class MyConfig implements WebMvcConfigurer {

	public String applicationId = "eyJhcHAiOiIxOTg6MTY1MSIsInZlciI6IjIuMCIsIm9yZyI6IjE2Mzo0NTUiLCJpYXQiOjE2ODAzMDcyMDB9.tjFBuRS7fkadtidRVKLsW3dUDTrHnDCZtgwOvhxifnQ";

	@Bean
	public MoesifFilter moesifFilter() {

		MoesifConfiguration config = new MoesifConfigurationAdapter() {

			@Override
			public String identifyUser(HttpServletRequest request, HttpServletResponse response) {
				return "rogers";
			}

			public String identifyCompany(HttpServletRequest request, HttpServletResponse response) {
				return "Jubby Inc.";
			}

			public Object getMetadata(HttpServletRequest request, HttpServletResponse response) {

				Map<String, String> metaData = new HashMap<String, String>();
				metaData.put("some_string", "I am a string!!");

				return metaData;
			}

			@Override
			public String getSessionToken(HttpServletRequest request, HttpServletResponse response) {
				return request.getHeader("Authorization");
			}

			@Override
			public String getApiVersion(HttpServletRequest request, HttpServletResponse response) {
				return request.getHeader("X-Api-Version");
			}
		};

		MoesifFilter moesifFilter = new MoesifFilter(applicationId, config, true);

		// Set flag to log request and response body
		moesifFilter.setLogBody(true);

		System.out.println("Inside MyConfig");
		return moesifFilter;
	}
}
