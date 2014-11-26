
include_directories("$ENV{JAVA_HOME}include")
if(${CMAKE_SYSTEM_NAME} MATCHES "Darwin")
    include_directories("$ENV{JAVA_HOME}include/darwin")
elseif(${CMAKE_SYSTEM_NAME} MATCHES "linux")
    include_directories("$ENV{JAVA_HOME}include/linux")
elseif(${CMAKE_SYSTEM_NAME} MATCHES "Windows")
    include_directories("$ENV{JAVA_HOME}include/win32")
endif()

set(LIB_SOURCE_FILES bench_functions.cpp bench_functions_jni.cpp)
add_library(kotlinffibench SHARED ${LIB_SOURCE_FILES})
# target_link_libraries(kotlinffibench armadillo)
