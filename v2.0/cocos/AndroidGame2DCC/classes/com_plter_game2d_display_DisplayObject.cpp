//
//  com_plter_game2d_display_DisplayObject.cpp
//  AndroidGame2DCC
//
//  Created by plter on 14-3-26.
//
//

#include "com_plter_game2d_display_DisplayObject.h"
#include <cocos2d.h>

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeSetVisible
 * Signature: (JZ)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObject_nativeSetVisible
(JNIEnv *, jobject, jlong p, jboolean b){
    ((cocos2d::Node*)p)->setVisible(b);
}

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeSetRotation
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObject_nativeSetRotation
(JNIEnv *, jobject, jlong p, jfloat r){
    ((cocos2d::Node*)p)->setRotation(r);
}

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeGetBoundingBox
 * Signature: (J)[F
 */
JNIEXPORT jfloatArray JNICALL Java_com_plter_game2d_display_DisplayObject_nativeGetBoundingBox
(JNIEnv *env, jobject, jlong p){
    cocos2d::Rect r = ((cocos2d::Node*)p)->getBoundingBox();
    
    jfloat buf[4];
    buf[0] = r.origin.x;
    buf[1] = r.origin.y;
    buf[2] = r.size.width;
    buf[3] = r.size.height;
    
    jfloatArray arr = env->NewFloatArray(4);
    env->SetFloatArrayRegion(arr, 0, 4, buf);

    return arr;
}

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeSetPosition
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObject_nativeSetPosition
(JNIEnv *, jobject, jlong thiz, jfloat x, jfloat y){
    ((cocos2d::Node*)thiz)->setPosition(x, y);
}

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeSetPositionX
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObject_nativeSetPositionX
(JNIEnv *, jobject, jlong thiz, jfloat x){
    ((cocos2d::Node*)thiz)->setPositionX(x);
}

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeSetPositionY
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObject_nativeSetPositionY
(JNIEnv *, jobject, jlong thiz, jfloat y){
    ((cocos2d::Node*)thiz)->setPositionY(y);
}

/*
 * Class:     com_plter_game2d_display_DisplayObject
 * Method:    nativeSetAnchorPoint
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObject_nativeSetAnchorPoint
(JNIEnv *, jobject, jlong thiz, jfloat x, jfloat y){
    ((cocos2d::Node*)thiz)->setAnchorPoint(cocos2d::Point(x,y));
}

