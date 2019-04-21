package spring.jsample.util;

public class AppConstants {

	public static final class URI {

		public static final String GET_APPS = "/getApps";

		public static final String GET_APPS_PAGE_WISE = "/getAppsPageWise";

		public static final String GET_RUNNING_APPS_PAGE_WISE = "/getRunningAppsPageWise";

		public static final String ADD_APP = "/addApp";

		public static final String UPDATE_APP = "/updateApp/{id}";

		public static final String DELETE_APP = "/deleteApp/{id}";

	}

	public static final class REQ_PARAM {

		public static final String pageNumber = "pageNumber";

		public static final String pageSize = "pageSize";

	}
}
