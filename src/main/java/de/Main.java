package de;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String API_BASE_URL = "https://ocff-alphatest-git.api.fulfillmenttools.com";

    public static void main(String[] args) throws IOException {
//        printAPIToken();
//        createFacility();
//        createInboundProcessForFacility("8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c");
//        deleteInboundProcess("81139f31-9f8e-422c-bc86-55cc39c08b41");
//        deleteFacility("15b96e33-d631-418d-9411-9fadbbdd3ea7");
        createPurchaseOrderForExistingInboundProcessAndFacility("d9c4be9d-b3c5-4f8d-92e2-83a4eba2117a", "8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c");

    }


    private static void printAPIToken() throws IOException {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + System.getenv("AUTH_KEY");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String body = """
                {
                    "email": "%s",
                    "password": "%s",
                    "returnSecureToken": true
                }
                """.formatted(System.getenv("LOGIN"), System.getenv("PASSWORD"));

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        }

        int httpResponseCode = connection.getResponseCode();
        System.out.println(httpResponseCode);

        try (InputStream inputStream = (httpResponseCode == 200 || httpResponseCode == 201) ? connection.getInputStream() : connection.getErrorStream()) {
            String response = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
        }
    }


    /*
    201
    {"type":"MANAGED_FACILITY","name":"Bills Candy Shop","status":"ONLINE","address":{"city":"München","companyName":"Bills Candy Shop Ltd.","country":"DE","houseNumber":"58","postalCode":"81669","street":"Lilienstr.","resolvedCoordinates":{"lat":48.1226,"lon":11.6035,"accuracy":4,"name":"M�nchen","adminName":"Bayern, Upper Bavaria, Kreisfreie Stadt M�nchen","searchableGeoLocationString":"48.1226, 11.6035"},"resolvedTimeZone":{"offsetInSeconds":3600,"timeZoneId":"Europe/Berlin","timeZoneName":"W. Europe Standard Time"}},"fulfillmentProcessBuffer":240,"locationType":"STORE","services":[{"type":"SHIP_FROM_STORE"}],"capacityEnabled":false,"lastModified":"2025-05-27T11:52:58.565Z","version":1,"id":"8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c","created":"2025-05-27T11:52:58.565Z"}
    */
    private static void createFacility() throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/facilities";
        String body = """
                {
                    "name": "Bills Candy Shop",
                    "address": {
                        "companyName": "Bills Candy Shop Ltd.",
                        "country": "DE",
                        "postalCode": "81669",
                        "city": "München",
                        "street": "Lilienstr.",
                        "houseNumber": "58"
                    },
                    "services": [
                        {
                            "type": "SHIP_FROM_STORE"
                        }
                    ],
                    "status": "ONLINE",
                    "locationType": "STORE"
                }
                """;

        sendHttpRequest(apiEndpoint, body, "POST");
    }

    private static void deleteFacility(String facilityId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/facilities/" + facilityId + "?forceDeletion=true";
        sendHttpRequest(apiEndpoint, "", "DELETE");
    }


    /*
    201
    {"created":"2025-05-26T22:01:49.639Z","facilityRef":"15b96e33-d631-418d-9411-9fadbbdd3ea7","id":"81139f31-9f8e-422c-bc86-55cc39c08b41","lastModified":"2025-05-26T22:01:49.639Z","receipts":[],"status":"OPEN","version":1,"purchaseOrder":{"cancelled":false,"customAttributes":{},"orderDate":"2025-05-26T00:00:00.000Z","requestedDate":{"type":"ASAP","value":"2025-05-26T00:00:00.000Z"},"requestedItems":[{"tenantArticleId":"Article_1","quantity":{"unit":"pieces","value":50},"customAttributes":{},"stockProperties":{"expiry":"2026-05-26T00:00:00.000Z","property1":"Food","property2":"Organic"},"article":{"tenantArticleId":"Article_1"}},{"tenantArticleId":"Article_2","quantity":{"unit":"pieces","value":10},"customAttributes":{},"stockProperties":{"expiry":"2026-06-26T00:00:00.000Z","property1":"Electronics","property2":"Fragile"},"article":{"tenantArticleId":"Article_2"}}],"status":"OPEN","supplier":{"name":"Supplier AT"},"id":"50700465-8597-4ba3-926e-d5afb6da2bd8","created":"2025-05-26T22:01:49.624Z","lastModified":"2025-05-26T22:01:49.624Z"},"tenantInboundProcessId":"Inbound_1","onHold":false,"inboundDate":["1970-01-01T00:00:00.000Z"],"origin":[{"name":"Supplier AT"}],"scannableCodes":["Code_123"],"anonymized":false,"customAttributes":{}}
     */
    private static void createInboundProcessForFacility(String facilityId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/inboundprocesses";
        String body = """
                {
                  "customAttributes": {},
                  "facilityRef": "%s",
                  "onHold": false,
                  "purchaseOrder": {
                    "customAttributes": {},
                    "orderDate": "2025-05-26T00:00:00.000Z",
                    "requestedDate": {
                      "type": "ASAP",
                      "value": "2025-05-27T00:00:00.000Z"
                    },
                    "requestedItems": [
                      {
                        "customAttributes": {},
                        "quantity": {
                          "unit": "pieces",
                          "value": 50
                        },
                        "stockProperties": {
                          "expiry": "2026-05-26T00:00:00.000Z",
                          "property1": "Food",
                          "property2": "Organic"
                        },
                        "tenantArticleId": "Article_1"
                      },
                      {
                        "customAttributes": {},
                        "quantity": {
                          "unit": "pieces",
                          "value": 10
                        },
                        "stockProperties": {
                          "expiry": "2026-06-26T00:00:00.000Z",
                          "property1": "Electronics",
                          "property2": "Fragile"
                        },
                        "tenantArticleId": "Article_2"
                      }
                    ],
                    "status": "OPEN",
                    "supplier": {
                      "name": "Supplier AT"
                    }
                  },
                  "scannableCodes": [
                    "Code_123"
                  ],
                  "tenantInboundProcessId": "Inbound_1"
                }
                """.formatted(facilityId);

        sendHttpRequest(apiEndpoint, body, "POST");
    }

    private static void deleteInboundProcess(String inboundProcessId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/inboundprocesses/" + inboundProcessId;
        sendHttpRequest(apiEndpoint, "", "DELETE");
    }

    private static void createPurchaseOrderForExistingInboundProcessAndFacility(String inboundProcessId, String facilityId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/inboundprocesses/" + inboundProcessId + "/purchaseorder";
        String body = """
                {
                  "facilityRef": "%s",
                  "orderDate": "2025-03-12T14:15:39.683Z",
                  "requestedDate": {
                    "type": "TIME_POINT",
                    "value": "2025-06-16T14:15:39.683Z"
                  },
                  "requestedItems": [
                    {
                      "quantity": {
                        "unit": "pieces",
                        "value": 100
                      },
                      "stockProperties": {
                          "expiry": "2026-05-26T00:00:00.000Z",
                          "property1": "Fast-Food",
                          "property2": "Non-Organic"
                        },
                      "tenantArticleId": "Article_3"
                    }
                  ],
                  "status": "OPEN",
                  "supplier": {
                    "name": "Supplier AT"
                  },
                  "version": 1
                }
                """.formatted(facilityId);

        sendHttpRequest(apiEndpoint, body, "PUT");

        // Liste mit items und dann JSON generieren
    }

    // TODO: Vielleicht später
    private static void setPurchaseOrderToCancelled(String purchaseOrderId) {
        String apiEndpoint = API_BASE_URL + "/api/purchaseorders";
    }



    private static void sendHttpRequest(String url, String body, String httpVerb) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(httpVerb);
        connection.setRequestProperty("Authorization", "Bearer " + System.getenv("API_KEY"));
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            outputStream.write(body.getBytes(StandardCharsets.UTF_8));
        }

        int httpResponseCode = connection.getResponseCode();
        System.out.println(httpResponseCode);

        try (InputStream inputStream = (httpResponseCode == 200 || httpResponseCode == 201) ? connection.getInputStream() : connection.getErrorStream()) {
            String response = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(response);
        }
    }
}