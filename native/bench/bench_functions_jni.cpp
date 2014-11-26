
#include "bench_functions_jni.h"
#include "bench_functions.h"

jint Java_ffibench_jni_LibFfiBench_funcIntInt(JNIEnv * env, jobject obj, jint param)
{
    return (jint)kffis_func_int_int((int)param);
}

jint Java_ffibench_jni_LibFfiBench_funcStringInt(JNIEnv * env, jobject obj, jstring param)
{
    const char *str = env->GetStringUTFChars(param, NULL);
    return (jint)kffis_func_string_int(str ? str : "");
}

jstring Java_ffibench_jni_LibFfiBench_funcIntString(JNIEnv * env, jobject obj, jint param)
{
    return env->NewStringUTF(kffis_func_int_string(param));
}
/*
jint Java_com_kffis_FfiLib_func_struct1_int(JNIEnv * env, jobject obj, jobject param)
{
    return (jint)kffis_func_int_int((int)param);
}

jobject Java_com_kffis_FfiLib_func_int_struct1(JNIEnv * env, jobject obj, jint param)
{
    return (jint)kffis_func_int_int((int)param);
}
*/