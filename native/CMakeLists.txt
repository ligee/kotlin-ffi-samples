cmake_minimum_required(VERSION 2.8.4)
project(kotlin_ffi_samples_native)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")
set(LIBRARY_OUTPUT_PATH "${PROJECT_SOURCE_DIR}/lib")

add_subdirectory("bench")
add_subdirectory("bench_swig")

