package com.rwws.rwserver.exception;

import org.jetbrains.annotations.Nullable;
import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.CONFLICT;
import static org.zalando.problem.Status.NOT_FOUND;

public class ResourceConflictProblem extends AbstractThrowableProblem {

    public ResourceConflictProblem() {
        super(null, CONFLICT.getReasonPhrase(), CONFLICT, null);
    }

    public ResourceConflictProblem(@Nullable String detail) {
        super(null, CONFLICT.getReasonPhrase(), CONFLICT, detail);
    }
}
