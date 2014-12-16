
find_package(Java REQUIRED COMPONENTS Runtime Development)
find_package(JNI REQUIRED)
find_package(SWIG REQUIRED)
include(UseJava)
include(UseSWIG)

message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")

include_directories(${JNI_INCLUDE_DIRS})
include_directories(${CMAKE_CURRENT_SOURCE_DIR})

set(CMAKE_SWIG_FLAGS -package bench_functions)
# set(CMAKE_SWIG_OUTDIR "${PROJECT_SOURCE_DIR}swig/")
set(CMAKE_SWIG_OUTDIR "swig")

set_source_files_properties(../bench/bench_functions.h PROPERTIES CPLUSPLUS ON)
set_source_files_properties(../bench/bench_functions.h PROPERTIES SWIG_FLAGS "-includeall")

swig_add_module(bench_functions_swig java ../bench/bench_functions.h ../bench/bench_functions.cpp bench_functions_swig.cpp)
swig_link_libraries(bench_functions_swig ${JNI_LIBRARIES})
