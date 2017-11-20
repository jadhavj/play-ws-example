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

        return request.get().thenApply((WSResponse r) -> {
            return ok("Feed title: " + r.getBody());
        });
    }
}
