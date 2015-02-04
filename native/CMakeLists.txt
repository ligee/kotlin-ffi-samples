cmake_minimum_required(VERSION 2.8.4)
project(kotlin_ffi_samples_native)

find_package(Java REQUIRED COMPONENTS Runtime Development)
find_package(JNI REQUIRED)

include(UseJava)

message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")

include_directories(".")
include_directories(${JNI_INCLUDE_DIRS})

set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${CMAKE_CURRENT_SOURCE_DIR}/lib")

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

add_subdirectory("bench")
add_subdirectory("bench_swig")

