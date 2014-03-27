//
//  com_plter_game2d_display_Stage.cpp
//  AndroidGame2DCC
//
//  Created by plter on 14-3-26.
//
//

#include "com_plter_game2d_display_Stage.h"
#include <cocos2d.h>
#include <Game2DStage.h>

/*
 * Class:     com_plter_game2d_display_Stage
 * Method:    createNativeObject
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_plter_game2d_display_Stage_createNativeObject
(JNIEnv *, jobject){
    return (jlong)Game2DStage::getInstance();
}

/*
 * Class:     com_plter_game2d_display_Stage
 * Method:    setDisplayStats
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Stage_setDisplayStats
(JNIEnv *, jobject, jboolean b){
    cocos2d::Director::getInstance()->setDisplayStats(b);
}

/*
 * Class:     com_plter_game2d_display_Stage
 * Method:    setAnimationInterval
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Stage_setAnimationInterval
(JNIEnv *, jobject, jfloat f){
    cocos2d::Director::getInstance()->setAnimationInterval(f);
}

/*
 * Class:     com_plter_game2d_display_Stage
 * Method:    nativeGetVisibleSize
 * Signature: ()[F
 */
JNIEXPORT jfloatArray JNICALL Java_com_plter_game2d_display_Stage_nativeGetVisibleSize
(JNIEnv *env, jobject){
    auto size = cocos2d::Director::getInstance()->getVisibleSize();
    jfloat buf[2];
    buf[0] = size.width;
    buf[1] = size.height;
    
    jfloatArray arr = env->NewFloatArray(2);
    env->SetFloatArrayRegion(arr, 0, 2, buf);
    return arr;
}


/*
 * Class:     com_plter_game2d_display_Stage
 * Method:    nativeSetDesignResolutionSize
 * Signature: (FFI)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Stage_nativeSetDesignResolutionSize
(JNIEnv *, jobject, jfloat width, jfloat height, jint policy){
    
    ResolutionPolicy resolutionPolicy = ResolutionPolicy::UNKNOWN;
    
    switch (policy) {
        case 1:
			resolutionPolicy = ResolutionPolicy::EXACT_FIT;
			break;
		case 2:
			resolutionPolicy = ResolutionPolicy::FIXED_HEIGHT;
			break;
		case 3:
			resolutionPolicy = ResolutionPolicy::FIXED_WIDTH;
			break;
		case 4:
			resolutionPolicy = ResolutionPolicy::NO_BORDER;
			break;
		case 5:
			resolutionPolicy = ResolutionPolicy::SHOW_ALL;
			break;
		case 0:
			resolutionPolicy = ResolutionPolicy::UNKNOWN;
			break;
    }
    
    cocos2d::Director::getInstance()->getOpenGLView()->setDesignResolutionSize(width, height, resolutionPolicy);
}

