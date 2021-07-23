package com.bayrktlihn.courserecord;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class DropCourseConditionExtension implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {


        if (List.of("student").containsAll(extensionContext.getTags()) || List.of("student", "dropCourse").containsAll(extensionContext.getTags())) {
            return ConditionEvaluationResult.enabled("Drop course is enabled");
        }


        return ConditionEvaluationResult.disabled("Only drop course allowed to run");
    }
}
