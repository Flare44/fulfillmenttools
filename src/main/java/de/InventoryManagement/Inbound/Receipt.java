package de.InventoryManagement.Inbound;

import java.io.IOException;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class Receipt {
    /*
    201
    {"comments":[],"receivedDate":"2025-03-16T14:15:39.683Z","receivedItems":[{"acceptedQuantity":{"value":100},"comments":[],"rejectedQuantity":{"value":5},"stockProperties":{"conditions":"DEFECTIVE"},"tenantArticleId":"Article_3","customAttributes":{"conditions":"DEFECTIVE"},"id":"fb287735-b2a0-4299-9632-d45902db078e"}],"status":"OPEN","id":"8a1d1988-eb3e-437a-b018-895948394e44","inboundProcessRef":"d9c4be9d-b3c5-4f8d-92e2-83a4eba2117a","created":"2025-05-27T11:57:14.618Z","lastModified":"2025-05-27T20:24:31.683Z","version":12}
    */
    public static void addReceiptToExistingInboundProcessAndFacility(String inboundProcessId, String facilityId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/inboundprocesses/" + inboundProcessId + "/receipts";
        String body = """
                {
                  "facilityRef": "%s",
                  "comments": [],
                  "receivedDate": "2025-03-16T14:15:39.683Z",
                  "receivedItems": [
                    {
                      "acceptedQuantity": {
                        "value": 100
                      },
                      "comments": [],
                      "rejectedQuantity": {
                        "value": 5
                      },
                      "stockProperties": {
                        "conditions": "DEFECTIVE"
                      },
                      "tenantArticleId": "Article_3",
                      "customAttributes": {
                        "conditions": "DEFECTIVE"
                      }
                    }
                  ],
                  "status": "OPEN"
                }
                """.formatted(facilityId);
        sendHttpRequest(apiEndpoint, body, "POST");
    }

    public static void deleteReceipt(String receiptId, String version) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/receipts/" + receiptId + "?version=" + version;
        sendHttpRequest(apiEndpoint, "", "DELETE");
    }
}
