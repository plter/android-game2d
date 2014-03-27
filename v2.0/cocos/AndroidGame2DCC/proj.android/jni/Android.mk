LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := cocos2dcpp_shared

LOCAL_MODULE_FILENAME := libcocos2dcpp

LOCAL_SRC_FILES := hellocpp/main.cpp \
                   ../../Classes/AppDelegate.cpp \
                   ../../Classes/Game2DStage.cpp \
                   ../../Classes/com_plter_game2d_display_Stage.cpp \
                   ../../Classes/com_plter_game2d_display_Image.cpp \
                   ../../Classes/com_plter_game2d_display_Sprite.cpp \
                   ../../Classes/com_plter_game2d_display_Label.cpp \
                   ../../Classes/com_plter_game2d_display_DisplayObjectContainer.cpp \
                   ../../Classes/com_plter_game2d_display_DisplayObject.cpp 

LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../Classes

LOCAL_WHOLE_STATIC_LIBRARIES := cocos2dx_static
LOCAL_WHOLE_STATIC_LIBRARIES += cocosdenshion_static
LOCAL_WHOLE_STATIC_LIBRARIES += box2d_static


include $(BUILD_SHARED_LIBRARY)

$(call import-module,2d)
$(call import-module,audio/android)
$(call import-module,Box2D)
