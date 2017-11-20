package controllers;

import play.mvc.*;

import views.html.*;
import play.mvc.*;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private WSClient ws;

    @Inject
    public HomeController(WSClient ws) {
        this.ws = ws;
    }

    public CompletionStage<Result> index() {
        WSRequest request = ws.url("https://jsonplaceholder.typicode.com/posts/1");

        /* More API @ https://www.playframework.com/documentation/2.6.x/JavaWS
        request.addQueryParameter("paramKey", "paramValue");
        request.addHeader("headerKey", "headerValue");
        request.setContentType("application/json");
        request.addCookies(new WSCookieBuilder().setName("headerKey").setValue("headerValue").build());
        request.setRequestTimeout(Duration.of(1000, ChronoUnit.MILLIS));
        request.post("key1=value1&key2=value2"); // Submitting form data

        JsonNode json = Json.newObject()
                .put("key1", "value1")
                .put("key2", "value2");
        request.post(json);

        */


        return request.get().thenApply((WSResponse r) -> {
            return ok("Feed title: " + r.getBody());
        });
    }
}
