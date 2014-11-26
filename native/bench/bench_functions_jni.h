
#ifndef _KOTLIN_FFI_SAMPLES_BENCH_FUNCTIONS_JNI_H_
#define _KOTLIN_FFI_SAMPLES_BENCH_FUNCTIONS_JNI_H_

#include <jni.h>

extern "C" {

JNIEXPORT jint JNICALL Java_ffibench_jni_LibFfiBench_funcIntInt(JNIEnv * env, jobject obj, jint param);

JNIEXPORT jint JNICALL Java_ffibench_jni_LibFfiBench_funcStringInt(JNIEnv * env, jobject obj, jstring param);

JNIEXPORT jstring JNICALL Java_ffibench_jni_LibFfiBench_funcIntString(JNIEnv * env, jobject obj, jint param);

JNIEXPORT jint JNICALL Java_ffibench_jni_LibFfiBench_funcStruct1Int(JNIEnv * env, jobject obj, jobject param);

JNIEXPORT jobject JNICALL Java_ffibench_jni_LibFfiBench_funcIntStruct1(JNIEnv * env, jobject obj, jint param);

JNIEXPORT jint JNICALL Java_ffibench_jni_LibFfiBench_funcCallbackInt(JNIEnv * env, jobject obj, jobject param);

}

#endif // _KOTLIN_FFI_SAMPLES_BENCH_FUNCTIONS_JNI_H_
