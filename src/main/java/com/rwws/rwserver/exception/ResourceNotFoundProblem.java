package com.rwws.rwserver.exception;

import org.jetbrains.annotations.Nullable;
import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.NOT_FOUND;

public class ResourceNotFoundProblem extends AbstractThrowableProblem {

    public ResourceNotFoundProblem() {
        super(null, NOT_FOUND.getReasonPhrase(), NOT_FOUND, null);
    }

    public ResourceNotFoundProblem(@Nullable String detail) {
        super(null, NOT_FOUND.getReasonPhrase(), NOT_FOUND, detail);
    }
}
