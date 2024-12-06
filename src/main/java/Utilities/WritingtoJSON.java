package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.codehaus.groovy.tools.Utilities;
import org.json.JSONArray;
import org.json.JSONObject;

public class WritingtoJSON {

    /**
     * Dynamically updates a JSON object at a specified JSON path.
     *
     * @param jsonObject The JSON object to modify.
     * @param jsonPath   The JSON path to the field to update (supports arrays like identifications[0]).
     * @param newValue   The new value to assign.
     */
    public static void updateJsonValue(String filename, String jsonPath, Object newValue) {
    	   try {
    	
	
    	String jsonfile = new String(Files.readAllBytes(Paths.get("./Payload/" + filename)));
	
    	JSONObject jsonObject = new JSONObject(jsonfile);
        String[] pathParts = jsonPath.split("\\.");
        JSONObject currentObject = jsonObject;
       

     
            // Iterate through path parts, navigating to the target field
            for (int i = 0; i < pathParts.length - 1; i++) {
                String part = pathParts[i];

                // Handle array navigation (e.g., identifications[0])
                if (part.contains("[")) {
                    String arrayKey = part.substring(0, part.indexOf("["));
                    int index = Integer.parseInt(part.substring(part.indexOf("[") + 1, part.indexOf("]")));
                    JSONArray jsonArray = currentObject.getJSONArray(arrayKey);
                    currentObject = jsonArray.getJSONObject(index);
                } else {
                    // Navigate into nested objectu
                    currentObject = currentObject.getJSONObject(part);
                }
            }

            // Update the final field
            String targetField = pathParts[pathParts.length - 1];
            currentObject.put(targetField, newValue);

            System.out.println("Updated JSON: " + newValue);
            Files.write(Paths.get("./Payload/" + filename), jsonObject.toString(4).getBytes());


            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
