create view task_info_view as SELECT
concat(jd.JOB_NAME,':', t.TRIGGER_NAME) AS id,
jd.JOB_NAME AS job_name,
jd.DESCRIPTION AS job_description,
jd.JOB_GROUP AS job_group,
jd.JOB_CLASS_NAME AS job_class,
t.TRIGGER_NAME AS trigger_name,
t.TRIGGER_GROUP AS trigger_group,
t.TRIGGER_STATE AS state,
FROM_UNIXTIME(t.PREV_FIRE_TIME/1000,'%Y-%m-%d %T') AS prev_fire_time,
FROM_UNIXTIME(t.NEXT_FIRE_TIME/1000,'%Y-%m-%d %T') AS next_fire_time,
FROM_UNIXTIME(t.START_TIME/1000,'%Y-%m-%d %T') AS start_time,
ct.CRON_EXPRESSION AS cron_expression
FROM QRTZ_JOB_DETAILS jd
JOIN QRTZ_TRIGGERS t
JOIN QRTZ_CRON_TRIGGERS ct ON jd.JOB_NAME = t.JOB_NAME
AND t.TRIGGER_NAME = ct.TRIGGER_NAME
AND t.TRIGGER_GROUP = ct.TRIGGER_GROUP;