package utils;

public class ValidationUtils {

    public static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }

    public static void validateNotEmpty(String field) throws ValidationException {
        if (field == null || field.trim().isEmpty()) {
            throw new ValidationException("Поля не могут быть пустыми");
        }
    }

    public static void validateLength(String field, int maxLength, String fieldName) throws ValidationException {
        if (field.length() > maxLength) {
            throw new ValidationException(fieldName + " не может превышать " + maxLength + " символов");
        }
    }

    public static void validateWithRegex(String field, String regex, String errorMessage) throws ValidationException {
        if (!field.matches(regex)) {
            throw new ValidationException(errorMessage);
        }
    }

}
