package de.InventoryManagement.Inbound;

import java.io.IOException;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class PurchaseOrder {
    /*
    200
    {"id":"f745f7ed-fe0a-47af-bd2d-6ffd4761668b","cancelled":false,"orderDate":"2025-03-12T14:15:39.683Z","requestedDate":{"type":"TIME_POINT","value":"2025-06-16T14:15:39.683Z"},"requestedItems":[{"tenantArticleId":"Article_3","quantity":{"unit":"pieces","value":100},"stockProperties":{"expiry":"2026-05-26T00:00:00.000Z","property1":"Fast-Food","property2":"Non-Organic"},"article":{"tenantArticleId":"Article_3"}}],"status":"OPEN","supplier":{"name":"Supplier AT"},"version":1,"lastModified":"2025-05-27T11:57:14.607Z","created":"2025-05-27T11:57:14.607Z"}
     */
    public static void createPurchaseOrderForExistingInboundProcessAndFacility(String inboundProcessId, String facilityId) throws IOException {
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
}
