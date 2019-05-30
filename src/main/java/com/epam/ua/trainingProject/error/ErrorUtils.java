package com.epam.ua.trainingProject.error;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hibernate.validator.internal.metadata.core.ConstraintHelper.MESSAGE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorUtils {

    public static Error newError(String field, String message) {
        return new Error(field, message);
    }

    public static Errors newErrorsList(Error... phone) {
        return new Errors(asList(phone));
    }

    public static Errors newSingletonErrors(String message) {
        return new Errors(singletonList(new Error(MESSAGE, message)));
    }

    public static Errors newSingletonErrors(String message, String exceptionMessage) {
        return new Errors(singletonList(new Error(MESSAGE, message + " " + exceptionMessage)));
    }
}