package spring.jsample.controller;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.jsample.docs.AppDocs;
import spring.jsample.model.Application;
import spring.jsample.service.AppService;
import spring.jsample.util.AppConstants;

@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith({ RestDocumentationExtension.class })
public class AppControllerTest {

	@MockBean
	private AppService service;

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private ApplicationContext context;

	/*
	 * Configure WebTestClient with Rest Docs
	 * 
	 */
	@BeforeEach
	void setUp(RestDocumentationContextProvider restDocumentation) {

		webTestClient = WebTestClient.bindToApplicationContext(context).configureClient()
				.filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation)).build();

	}

	@Test
	public void getAppsTest1() throws Exception {

		Application app1 = new Application("1", "Application-1", true);
		Application app2 = new Application("2", "Application-2", false);
		Application app3 = new Application("3", "Application-3", true);

		Mockito.when(service.getApps()).thenReturn(Flux.just(app1, app2, app3));

		webTestClient.get().uri(AppConstants.URI.GET_APPS).exchange().expectStatus().isOk()
				.expectBodyList(Application.class).isEqualTo(Arrays.asList(app1, app2, app3))
				.consumeWith(WebTestClientRestDocumentation.document("getApps", AppDocs.getAllResponseFields()));
	}

	@Test
	public void addAppTest1() throws Exception {
		Application app4 = new Application("4", "Application-4", true);

		Mockito.when(service.addApp(Mockito.any(Application.class))).thenReturn(Mono.just(app4));

		webTestClient.post().uri(AppConstants.URI.ADD_APP).syncBody(app4).exchange().expectStatus().isOk()
				.expectBody(Application.class).isEqualTo(app4).consumeWith(WebTestClientRestDocumentation
						.document("addApp", AppDocs.addRequestFields(), AppDocs.getResponseFields()));

	}

	@Test
	public void updateAppTest1() throws Exception {
		Application app5 = new Application("5", "Application-5", true);

		Mockito.when(service.updateApp(Mockito.any(Application.class), Mockito.anyString()))
				.thenReturn(Mono.just(app5));

		webTestClient.put().uri(AppConstants.URI.UPDATE_APP, 5).syncBody(app5).exchange().expectStatus().isOk()
				.expectBody(Application.class).isEqualTo(app5)
				.consumeWith(WebTestClientRestDocumentation.document("updateApp", AppDocs.pathParameter(),
						AppDocs.updateRequestFields(), AppDocs.getResponseFields()));
	}

	@Test
	public void deleteAppTest1() throws Exception {

		Mockito.when(service.deleteApp(Mockito.anyString())).thenReturn(Mono.empty());

		webTestClient.delete().uri(AppConstants.URI.DELETE_APP, 5).exchange().expectStatus().isNoContent().expectBody()
				.consumeWith(WebTestClientRestDocumentation.document("deleteApp", AppDocs.pathParameter()));

	}

}
