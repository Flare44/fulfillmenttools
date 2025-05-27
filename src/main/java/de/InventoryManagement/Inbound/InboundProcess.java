package de.InventoryManagement.Inbound;

import java.io.IOException;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class InboundProcess {
    /*
    201
    {"created":"2025-05-26T22:01:49.639Z","facilityRef":"15b96e33-d631-418d-9411-9fadbbdd3ea7","id":"81139f31-9f8e-422c-bc86-55cc39c08b41","lastModified":"2025-05-26T22:01:49.639Z","receipts":[],"status":"OPEN","version":1,"purchaseOrder":{"cancelled":false,"customAttributes":{},"orderDate":"2025-05-26T00:00:00.000Z","requestedDate":{"type":"ASAP","value":"2025-05-26T00:00:00.000Z"},"requestedItems":[{"tenantArticleId":"Article_1","quantity":{"unit":"pieces","value":50},"customAttributes":{},"stockProperties":{"expiry":"2026-05-26T00:00:00.000Z","property1":"Food","property2":"Organic"},"article":{"tenantArticleId":"Article_1"}},{"tenantArticleId":"Article_2","quantity":{"unit":"pieces","value":10},"customAttributes":{},"stockProperties":{"expiry":"2026-06-26T00:00:00.000Z","property1":"Electronics","property2":"Fragile"},"article":{"tenantArticleId":"Article_2"}}],"status":"OPEN","supplier":{"name":"Supplier AT"},"id":"50700465-8597-4ba3-926e-d5afb6da2bd8","created":"2025-05-26T22:01:49.624Z","lastModified":"2025-05-26T22:01:49.624Z"},"tenantInboundProcessId":"Inbound_1","onHold":false,"inboundDate":["1970-01-01T00:00:00.000Z"],"origin":[{"name":"Supplier AT"}],"scannableCodes":["Code_123"],"anonymized":false,"customAttributes":{}}
     */
    public static void createInboundProcessForFacility(String facilityId) throws IOException {
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

    public static void deleteInboundProcess(String inboundProcessId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/inboundprocesses/" + inboundProcessId;
        sendHttpRequest(apiEndpoint, "", "DELETE");
    }
}
