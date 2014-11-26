
#include "bench_functions_jni.h"
#include "bench_functions.h"

jint Java_ffibench_jni_LibFfiBench_funcIntInt(JNIEnv * env, jobject obj, jint param)
{
    return (jint)kffis_func_int_int((int)param);
}

jint JNICALL Java_ffibench_jni_LibFfiBench_funcStringInt(JNIEnv * env, jobject obj, jstring param)
{
    const char *str = env->GetStringUTFChars(param, NULL);
    auto res = (jint)kffis_func_string_int(str ? str : "");
    env->ReleaseStringUTFChars(param, str);
    return res;
}

jstring Java_ffibench_jni_LibFfiBench_funcIntString(JNIEnv * env, jobject obj, jint param)
{
    return env->NewStringUTF(kffis_func_int_string(param));
}

jint Java_ffibench_jni_LibFfiBench_funcStruct1Int(JNIEnv * env, jobject, jobject param)
{
    jclass cls = env->GetObjectClass(param);
    jfieldID int_fid = env->GetFieldID(cls, "int_field", "I");
    jfieldID str_fid = env->GetFieldID(cls, "string_field", "Ljava/lang/String;");
    kffis_struct1 str1;
    str1.int_field = env->GetIntField(param, int_fid);
    jstring str_fld = (jstring)env->GetObjectField(param, str_fid);
    str1.string_field = env->GetStringUTFChars(str_fld, NULL);
    auto res = (jint)kffis_func_struct1_int(&str1);
    env->ReleaseStringUTFChars(str_fld, str1.string_field);
    return res;
}

jobject Java_ffibench_jni_LibFfiBench_funcIntStruct1(JNIEnv * env, jobject, jint param)
{
    auto str1 = kffis_func_int_struct1((int)param);
    jclass cls = env->FindClass("ffibench/jni/struct1");
    jfieldID int_fid = env->GetFieldID(cls, "int_field", "I");
    jfieldID str_fid = env->GetFieldID(cls, "string_field", "Ljava/lang/String;");
    jobject obj = env->AllocObject(cls);
    env->SetIntField(obj, int_fid, str1->int_field);
    jstring s_val = (jstring) env->NewStringUTF(str1->string_field);
    env->SetObjectField(obj, str_fid, s_val);
    return obj;
}

jint Java_ffibench_jni_LibFfiBench_funcCallbackInt(JNIEnv * env, jobject obj, jobject param)
{
    jclass cls = env->GetObjectClass(param);
    jmethodID cb_mid = env->GetMethodID(cls, "callback", "(I)I");
    return env->CallIntMethod(param, cb_mid, 42);
}
