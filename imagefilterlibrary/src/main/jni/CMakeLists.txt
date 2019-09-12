# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# 设置CMAKE的版本号
cmake_minimum_required(VERSION 3.4.1)

#支持-std=gnu++11
set(CMAKE_VERBOSE_MAKEFILE on)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=gnu++11 -Wall -DGLM_FORCE_SIZE_T_LENGTH")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -DGLM_FORCE_RADIANS")

#设置生成的so动态库最后输出的路径
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/../jniLibs/${ANDROID_ABI})




set(JNILIBS_DIR ${CMAKE_SOURCE_DIR}/src/main/jniLibs/${ANDROID_ABI})


add_definitions("-ljnigraphics")

add_library( # Sets the name of the library.
        MagicSDK #动态库名字

        # Sets the library as a shared library.
        SHARED
        MagicSDK.cpp

        bitmap/BitmapOperation.cpp
        bitmap/Conversion.cpp
        skinsmooth/MagicBeauty.cpp
        # Provides a relative path to your source file(s). 注意，CMakeLists.txt 在 cpp 目录下，此处不需要加路径前缀

        )

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

target_link_libraries( # Specifies the target library.
        MagicSDK
        -ljnigraphics
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )
