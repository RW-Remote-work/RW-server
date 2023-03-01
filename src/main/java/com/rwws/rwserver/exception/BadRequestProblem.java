package com.rwws.rwserver.exception;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.zalando.problem.AbstractThrowableProblem;

import java.net.URI;

import static org.zalando.problem.Status.BAD_REQUEST;

public class BadRequestProblem extends AbstractThrowableProblem {
    public BadRequestProblem() {
        super(null, null, BAD_REQUEST, BAD_REQUEST.getReasonPhrase());
    }

    public BadRequestProblem(@Nullable URI type, String title) {
        super(type, title, BAD_REQUEST, BAD_REQUEST.getReasonPhrase());
    }
}
