package com.rwws.rwserver.exception;

import org.jetbrains.annotations.Nullable;
import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.BAD_REQUEST;

public class BadRequestProblem extends AbstractThrowableProblem {
    public BadRequestProblem() {
        super(null, BAD_REQUEST.getReasonPhrase(), BAD_REQUEST, null);
    }

    public BadRequestProblem(@Nullable String detail) {
        super(null, BAD_REQUEST.getReasonPhrase(), BAD_REQUEST, detail);
    }
}
