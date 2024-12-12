package util;

import javax.servlet.http.HttpServletRequest;

public class ImagePathUtil {
	
	private static String DEFAULT_PROFILE = "profile.png";
	
	public static String getImagePath(HttpServletRequest request, String imageName) {
		return request.getServletContext().getContextPath().concat("/img/%s".formatted(imageName));
	}

	public static String getImagePath(HttpServletRequest request) {
		return request.getServletContext().getContextPath().concat("/img/%s".formatted(DEFAULT_PROFILE));
	}
}
