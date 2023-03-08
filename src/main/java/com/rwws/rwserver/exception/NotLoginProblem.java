package com.rwws.rwserver.exception;

import org.jetbrains.annotations.Nullable;
import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.UNAUTHORIZED;

public class NotLoginProblem extends AbstractThrowableProblem {
    public NotLoginProblem() {
        super(null, null, UNAUTHORIZED, "User is not logged in");
    }

    public NotLoginProblem(@Nullable String detail) {
        super(null, UNAUTHORIZED.getReasonPhrase(), UNAUTHORIZED, detail);
    }
}
