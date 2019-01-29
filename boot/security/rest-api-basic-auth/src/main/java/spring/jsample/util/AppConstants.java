package spring.jsample.util;

public class AppConstants {

	public static final class URI {

		public static final String GET_APPS = "/getApps";

		public static final String ADD_APP = "/admin/addApp";

		public static final String UPDATE_APP = "/admin/updateApp/{id}";

		public static final String DELETE_APP = "/admin/deleteApp/{id}";
	}

	public static final class Role {

		public static final String USER = "user";

		public static final String ADMIN = "admin";

	}

	public static final class UserDetails {

		public static final String USER_ID = "user";
		public static final String USER_PASSWORD = "password";
		
		public static final String ADMIN_ID = "admin";
		public static final String ADMIN_PASSWORD = "password";
	}
}
