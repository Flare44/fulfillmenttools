package de.Core.Facilities;

import java.io.IOException;

import static de.Main.API_BASE_URL;
import static de.Utils.sendHttpRequest;

public class Facility {
    /*
    201
    {"type":"MANAGED_FACILITY","name":"Bills Candy Shop","status":"ONLINE","address":{"city":"München","companyName":"Bills Candy Shop Ltd.","country":"DE","houseNumber":"58","postalCode":"81669","street":"Lilienstr.","resolvedCoordinates":{"lat":48.1226,"lon":11.6035,"accuracy":4,"name":"M�nchen","adminName":"Bayern, Upper Bavaria, Kreisfreie Stadt M�nchen","searchableGeoLocationString":"48.1226, 11.6035"},"resolvedTimeZone":{"offsetInSeconds":3600,"timeZoneId":"Europe/Berlin","timeZoneName":"W. Europe Standard Time"}},"fulfillmentProcessBuffer":240,"locationType":"STORE","services":[{"type":"SHIP_FROM_STORE"}],"capacityEnabled":false,"lastModified":"2025-05-27T11:52:58.565Z","version":1,"id":"8a76c4de-1d3c-4ef3-b7fe-bbcb82b14a1c","created":"2025-05-27T11:52:58.565Z"}
    */
    public static void createFacility() throws IOException {
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

    public static void deleteFacility(String facilityId) throws IOException {
        String apiEndpoint = API_BASE_URL + "/api/facilities/" + facilityId + "?forceDeletion=true";
        sendHttpRequest(apiEndpoint, "", "DELETE");
    }
}
