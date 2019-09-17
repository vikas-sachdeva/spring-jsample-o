package spring.jsample;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ibm.icu.util.Calendar;

import io.restassured.module.webtestclient.RestAssuredWebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.jsample.model.Application;
import spring.jsample.service.AppService;

@SpringBootTest
@AutoConfigureWebTestClient
public class AppBaseClass {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private AppService service;

	@BeforeEach
	public void setup() {
		RestAssuredWebTestClient.webTestClient(webTestClient);

		Calendar cal = Calendar.getInstance();
		cal.set(2019, 10, 11, 12, 12, 11);
		Date date = cal.getTime();

		Application app1 = new Application("1", "Application-1", true, date, date);
		Application app2 = new Application("2", "Application-2", false, date, date);
		Application app3 = new Application("3", "Application-3", true, date, date);

		Mockito.when(service.getApps()).thenReturn(Flux.just(app1, app2, app3));

		Mockito.when(service.addApp(Mockito.any(Application.class))).thenAnswer(i -> {

			Application app = i.getArgument(0);
			app.setLastModifiedDate(Calendar.getInstance().getTime());
			app.setId("4");
			app.setCreatedAt(Calendar.getInstance().getTime());
			return Mono.just(app);
		});

		Mockito.when(service.updateApp(Mockito.any(Application.class), Mockito.anyString())).thenAnswer(i -> {

			Application app = i.getArgument(0);
			String id = i.getArgument(1);
			app.setLastModifiedDate(Calendar.getInstance().getTime());
			app.setId(id);
			if (app.getCreatedAt() == null) {
				app.setCreatedAt(date);
			}
			return Mono.just(app);
		});

		Mockito.when(service.deleteApp(Mockito.anyString())).thenReturn(Mono.empty());

	}
}
