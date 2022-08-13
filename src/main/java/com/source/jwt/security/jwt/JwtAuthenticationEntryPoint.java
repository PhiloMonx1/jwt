package com.source.jwt.security.jwt;

import com.source.jwt.model.Code;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//**
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		String exception = request.getAttribute("exception").toString();

		if(exception.equals("null") ) {
			setResponse(response, Code.UNKNOWN_ERROR);
		}
		else if(exception.equals(Code.UNKNOWN_ERROR.getCode())){
			setResponse(response, Code.UNKNOWN_ERROR);
		}
		else if(exception.equals(Code.WRONG_TYPE_TOKEN.getCode())) {
			setResponse(response, Code.WRONG_TYPE_TOKEN);
		}
		else if(exception.equals(Code.EXPIRED_TOKEN.getCode())) {
			setResponse(response, Code.EXPIRED_TOKEN);
		}
		else if(exception.equals(Code.UNSUPPORTED_TOKEN.getCode())) {
			setResponse(response, Code.UNSUPPORTED_TOKEN);
		}
		else {
			setResponse(response, Code.ACCESS_DENIED);
		}
	}
	private void setResponse(HttpServletResponse response, Code code) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);


		JSONObject responseJson = new JSONObject();
		responseJson.put("message", code.getMessage());
		responseJson.put("code", code.getCode());
		switch (code.getCode()) {
			case "1004":
				response.setStatus(HttpServletResponse.SC_OK);
				break;
			case "1003":
				response.setStatus(HttpServletResponse.SC_OK);
				break;
			case "1005":
				response.setStatus(HttpServletResponse.SC_OK);
				break;
			case "1006":
				response.setStatus(HttpServletResponse.SC_OK);
				break;
			case "1007":
				response.setStatus(HttpServletResponse.SC_OK);
				break;
		}

		response.getWriter().print(responseJson);
	}
}
