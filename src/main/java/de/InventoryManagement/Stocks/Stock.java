package de.InventoryManagement.Stocks;

import java.io.IOException;
import java.util.List;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class Stock {

    /*
    200
    {"total":1,"stocks":[{"created":"2025-05-27T17:15:30.303Z","facilityRef":"8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c","id":"a98a450c-39f6-4bd7-9b29-c963f968a634","lastModified":"2025-05-27T17:15:30.303Z","tenantArticleId":"Article_3","value":1,"locationRef":null,"scannableCodes":[],"scores":[{"type":"RATING","name":"RECEIPT_DATE","value":2775}],"tenantStockId":null,"reserved":0,"facilityWideReserved":0,"available":1,"traits":["PICKABLE","ACCESSIBLE"],"traitConfig":null,"conditions":null,"properties":{},"serializedProperties":"{}","receiptDate":"2025-05-27T17:15:30.282Z","version":1,"customAttributes":null,"availableUntil":null}]}
    */
    public static void checkIfStockWasCreatedForItem(String articleId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/stocks?tenantArticleId=" + articleId;
        sendHttpRequest(apiEndpoint, "", "GET");
    }

    public static void deleteStock(String stockId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/stocks/" + stockId;
        sendHttpRequest(apiEndpoint, "", "DELETE");
    }
}
