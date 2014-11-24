
#include "bench_functions.h"
#include <cstring>

int kffis_func_int_int(int param)
{
    return param % 42;
}

int kffis_func_string_int(char* param)
{
    return strlen(param);
}

char string_back[] = "greetings from native";

char* kffis_func_int_string(int param)
{
    return string_back;
}

int kffis_func_struct1_int(kffis_struct1* param)
{
    return param->int_field % 42 + strlen(param->string_field);
}

kffis_struct1 struct_back = { 42, string_back };

kffis_struct1* kffis_func_int_struct1(int param)
{
    return &struct_back;
}

int kffis_func_callback_int(int (*cb)(int))
{
    return cb ? cb(42) : -1;
}
