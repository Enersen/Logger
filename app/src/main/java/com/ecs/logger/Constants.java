package com.ecs.logger;

public class Constants {
    public static final String LOG_TAG = "EcsLogger";
    public static final String DATABASE_NAME = "ecsLogger.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME_SUBJECTS = "subjects";
    public static final String TABLE_NAME_LOGITEMS = "logitems";

    public static final String FIELD_NAME_SUBJECTS_ID = "id";
    public static final String FIELD_NAME_SUBJECTS_NAME = "name";
    public static final String FIELD_NAME_SUBJECTS_COMMENT = "comment";
    public static final String FIELD_NAME_SUBJECTS_DATE_ADDED = "date_added";
    public static final String FIELD_NAME_SUBJECTS_DATE_ACCESSED = "date_accessed";

    public static final String FIELD_NAME_LOGITEMS_ID = "id";
    public static final String FIELD_NAME_LOGITEMS_SUBJECT = "subject";
    public static final String FIELD_NAME_LOGITEMS_COMMENT = "comment";
    public static final String FIELD_NAME_LOGITEMS_DATE_ADDED = "date_added";
    public static final String FIELD_NAME_LOGITEMS_DATE_HAPPENED = "date_happened";
}

