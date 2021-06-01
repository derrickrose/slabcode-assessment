package com.slabcode.assessment.constant;

public enum ProgressStatus {
    CREATED, ON_PROGRESS, DONE;

    public static final ProgressStatus fromRaw(String status) {
        switch (status) {
            case "CREATED":
                return ProgressStatus.CREATED;
            case "ON_PROGRESS":
                return ProgressStatus.ON_PROGRESS;
            case "DONE":
                return ProgressStatus.DONE;
        }
        return null;
    }

    public static final boolean isValid(String status) {
        return status == null || "CREATED ON_PROGRESS DONE".contains(status);
    }
}
