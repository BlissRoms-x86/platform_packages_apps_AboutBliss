LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_CERTIFICATE       := platform
LOCAL_MODULE_TAGS       := optional
LOCAL_PACKAGE_NAME      := AboutBliss
LOCAL_PRIVILEGED_MODULE := true

LOCAL_STATIC_JAVA_LIBRARIES := \
	android-support-v4 \
	android-support-v7-cardview \
	android-support-v7-palette \
	android-support-design

LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res $(cardview_dir)
LOCAL_SRC_FILES    := $(call all-java-files-under,java)

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

include $(BUILD_MULTI_PREBUILT)
