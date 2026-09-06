package com.example.travel;

import java.util.Set;
import java.util.StringJoiner;

public class IncompletePackageException extends IllegalStateException {
    private static final long serialVersionUID = 1L;

    private static final String MESSAGE_PREFIX =
            "Cannot build the travel package, missing step(s): ";
    private static final String SEPARATOR = ", ";

    public IncompletePackageException(Set<BuildStep> missingSteps) {
        super(MESSAGE_PREFIX + join(missingSteps));
    }

    private static String join(Set<BuildStep> missingSteps) {
        StringJoiner joiner = new StringJoiner(SEPARATOR);
        for (BuildStep step : missingSteps) {
            joiner.add(step.getLabel());
        }
        return joiner.toString();
    }
}
