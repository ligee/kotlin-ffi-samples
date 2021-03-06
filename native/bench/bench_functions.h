
#ifndef _KOTLIN_FFI_SAMPLES_BENCH_FUNCTIONS_H_
#define _KOTLIN_FFI_SAMPLES_BENCH_FUNCTIONS_H_

#ifdef SWIG
%module example
%{
#include "header.h"
%}
#endif

#ifdef __cplusplus
extern "C" {
#endif

int kffis_func_int_int(int param);

int kffis_func_string_int(const char* param);

char* kffis_func_int_string(int param);

struct kffis_struct1 {
    int int_field;
    const char* string_field;
};

int kffis_func_struct1_int(kffis_struct1* param);

kffis_struct1* kffis_func_int_struct1(int param);

int kffis_func_callback_int(int (*cb)(int));

#ifdef __cplusplus
}
#endif

#endif // _KOTLIN_FFI_SAMPLES_BENCH_FUNCTIONS_H_