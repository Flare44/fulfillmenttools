package de;

import java.io.IOException;
import java.util.List;

import static de.Core.Facilities.Facility.createFacility;
import static de.Core.Facilities.Facility.deleteFacility;
import static de.InventoryManagement.Inbound.InboundProcess.createInboundProcessForFacility;
import static de.InventoryManagement.Inbound.InboundProcess.deleteInboundProcess;
import static de.InventoryManagement.Inbound.PurchaseOrder.createPurchaseOrderForExistingInboundProcessAndFacility;
import static de.InventoryManagement.Inbound.Receipt.addReceiptToExistingInboundProcessAndFacility;
import static de.InventoryManagement.Inbound.Receipt.deleteReceipt;
import static de.InventoryManagement.Stocks.Stock.checkIfStockWasCreatedForItem;
import static de.InventoryManagement.Stocks.Stock.deleteStock;
import static de.Utils.printAPIToken;

public class Main {
    public static final String API_BASE_URL = "https://ocff-alphatest-git.api.fulfillmenttools.com";

    public static void main(String[] args) throws IOException {
//        printAPIToken();
//        createFacility();
//        createInboundProcessForFacility("8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c");
//        deleteInboundProcess("81139f31-9f8e-422c-bc86-55cc39c08b41");
//        deleteFacility("15b96e33-d631-418d-9411-9fadbbdd3ea7");
//        createPurchaseOrderForExistingInboundProcessAndFacility("d9c4be9d-b3c5-4f8d-92e2-83a4eba2117a", "8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c");
//        addReceiptToExistingInboundProcessAndFacility("d9c4be9d-b3c5-4f8d-92e2-83a4eba2117a", "8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c");
//        deleteReceipt("fedafe42-f300-4a29-8c81-966763e2ed51", "9");
//        checkIfStockWasCreatedForItem("Article_3");
//        deleteStock("b7d926bc-a513-46ae-82ec-18ec90f73395");
    }
}