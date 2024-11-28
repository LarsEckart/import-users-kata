package kata;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ModernRandomUserApiImporter implements Importer {
    private final String url;
    public HttpClient httpClient = HttpClient.newHttpClient();
    public IdGenerator idGenerator = new IdGenerator();

    public ModernRandomUserApiImporter(String url) {
        this.url = url;
    }

    @Override
    public List<User> importUsers() {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<ApiResponse> response = httpClient.send(request, new JsonBodyHandler<>(ApiResponse.class));
            ApiResponse apiResponse = response.body();

            List<User> list = new ArrayList<>();
            for (ApiResponse.ApiUser result : apiResponse.getResults()) {
                list.add(parseUser(result));
            }
            return list;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private User parseUser(ApiResponse.ApiUser result) {
        long id = idGenerator.next();
        String name = result.getName().getFirst() + " " + result.getName().getLast();
        Birthdate dob = Birthdate.of(LocalDate.parse(result.getDob().getDate().substring(0, 10)));
        String country = result.getLocation().getCountry();
        String zip = String.valueOf(result.getLocation().getPostcode());
        String email = result.getEmail();

        return new User(id, name, dob, country, zip, email);
    }
}
