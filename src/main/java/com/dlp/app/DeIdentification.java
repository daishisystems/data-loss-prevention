package com.dlp.app;

import java.util.List;

import com.google.cloud.dlp.v2.DlpServiceClient;
import com.google.privacy.dlp.v2.CharacterMaskConfig;
import com.google.privacy.dlp.v2.ContentItem;
import com.google.privacy.dlp.v2.DeidentifyConfig;
import com.google.privacy.dlp.v2.DeidentifyContentRequest;
import com.google.privacy.dlp.v2.DeidentifyContentResponse;
import com.google.privacy.dlp.v2.InfoType;
import com.google.privacy.dlp.v2.InfoTypeTransformations;
import com.google.privacy.dlp.v2.InspectConfig;
import com.google.privacy.dlp.v2.PrimitiveTransformation;
import com.google.privacy.dlp.v2.ProjectName;
import com.google.privacy.dlp.v2.InfoTypeTransformations.InfoTypeTransformation;

public class DeIdentification {

    // [START dlp_deidentify_masking]
    /**
     * Deidentify a string by masking sensitive information with a character using
     * the DLP API.
     *
     * @param string           The string to deidentify.
     * @param maskingCharacter (Optional) The character to mask sensitive data with.
     * @param numberToMask     (Optional) The number of characters' worth of
     *                         sensitive data to mask. Omitting this value or
     *                         setting it to 0 masks all sensitive chars.
     * @param projectId        ID of Google Cloud project to run the API under.
     */
    static void deIdentifyWithMask(String string, List<InfoType> infoTypes, Character maskingCharacter,
            int numberToMask, String projectId) {

        // instantiate a client
        try (DlpServiceClient dlpServiceClient = DlpServiceClient.create()) {

            ContentItem contentItem = ContentItem.newBuilder().setValue(string).build();

            CharacterMaskConfig characterMaskConfig = CharacterMaskConfig.newBuilder()
                    .setMaskingCharacter(maskingCharacter.toString()).setNumberToMask(numberToMask).build();

            // Create the deidentification transformation configuration
            PrimitiveTransformation primitiveTransformation = PrimitiveTransformation.newBuilder()
                    .setCharacterMaskConfig(characterMaskConfig).build();

            InfoTypeTransformation infoTypeTransformationObject = InfoTypeTransformation.newBuilder()
                    .setPrimitiveTransformation(primitiveTransformation).build();

            InfoTypeTransformations infoTypeTransformationArray = InfoTypeTransformations.newBuilder()
                    .addTransformations(infoTypeTransformationObject).build();

            DeidentifyConfig deidentifyConfig = DeidentifyConfig.newBuilder()
                    .setInfoTypeTransformations(infoTypeTransformationArray).build();

            // Create the deidentification request object
            DeidentifyContentRequest request = DeidentifyContentRequest.newBuilder()
                    .setParent(ProjectName.of(projectId).toString()).setDeidentifyConfig(deidentifyConfig)
                    .setItem(contentItem).build();

            // Execute the deidentification request
            DeidentifyContentResponse response = dlpServiceClient.deidentifyContent(request);

            // Print the character-masked input value
            // e.g. "My SSN is 123456789" --> "My SSN is *********"
            String result = response.getItem().getValue();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Error in deidentifyWithMask: " + e.getMessage());
        }
    }
}