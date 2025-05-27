package de.InventoryManagement.Inbound;

import java.io.IOException;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class Receipt {
    /*
    201
    {"comments":[],"receivedDate":"2025-03-16T14:15:39.683Z","receivedItems":[{"acceptedQuantity":{"value":100},"comments":[],"rejectedQuantity":{"value":0},"tenantArticleId":"Article_3","id":"2af20f12-3adf-4062-9887-bbf907b5a423","stockProperties":{}}],"status":"OPEN","id":"932bc19e-8925-440c-99e8-97aabd9bc72c","inboundProcessRef":"d9c4be9d-b3c5-4f8d-92e2-83a4eba2117a","created":"2025-05-27T11:57:14.618Z","lastModified":"2025-05-27T16:29:43.262Z","version":5}
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
                        "value": 0
                      },
                      "tenantArticleId": "Article_3"
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
