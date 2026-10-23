package org.ims.Utils;

import java.util.UUID;

public class Validate {
    public static boolean validateUUID(UUID input) {
        if (input == null) {
            return false;
        }

        try {
            UUID.fromString(String.valueOf(input));
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
