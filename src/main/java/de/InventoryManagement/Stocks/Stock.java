package de.InventoryManagement.Stocks;

import java.io.IOException;
import java.util.List;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class Stock {

    /*
    201
    {"created":"2025-05-27T17:16:14.178Z","facilityRef":"8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c","id":"b7d926bc-a513-46ae-82ec-18ec90f73395","lastModified":"2025-05-27T17:16:14.178Z","tenantArticleId":"Article_3","value":100,"scannableCodes":[],"scores":[{"type":"RATING","name":"RECEIPT_DATE","value":2775}],"reserved":0,"facilityWideReserved":0,"available":100,"traits":["PICKABLE","ACCESSIBLE"],"properties":{},"serializedProperties":"{}","receiptDate":"2025-05-27T17:16:14.172Z","version":1}
     */
    public static void checkIfStockWasCreatedForItem(String facilityId, String articleId) throws IOException {
        StringBuilder apiEndpointBuilder = new StringBuilder(API_BASE_URL + "/api/stocks"); //?
//        articleIds.forEach(articleId -> {
//            apiEndpointBuilder.append("tenantArticleId=").append(articleId).append("&");
//        });
//        apiEndpointBuilder.deleteCharAt(apiEndpointBuilder.length() - 1); // letztes '&' wieder entfernen

        String body = """
                {
                    "facilityRef": "%s",
                    "tenantArticleId": "%s",
                    "value": 100
                }
                """.formatted(facilityId, articleId);

        sendHttpRequest(apiEndpointBuilder.toString(), body, "GET");
    }
}
