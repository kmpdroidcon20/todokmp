package com.kmpdroidcon.util.annotation

/**
 * Using this annotation to ignore some javascript tests related to DB since sqldelight is not
 * supporting js because of a publishing limitation from their side
 *
 * https://github.com/cashapp/sqldelight/issues/1667
 *
 * TODO monitor this issue and once resolved, remove the annotation to allow tests to run
 * against Javascript target
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
expect annotation class IgnoreJs()