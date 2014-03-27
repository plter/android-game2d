//
//  com_plter_game2d_display_Image.cpp
//  AndroidGame2DCC
//
//  Created by plter on 14-3-26.
//
//

#include "com_plter_game2d_display_Image.h"
#include <cocos2d.h>
#include <platform/android/jni/JniHelper.h>

/*
 * Class:     com_plter_game2d_display_Image
 * Method:    createNativeObjectWithFileNameAndRect
 * Signature: (Ljava/lang/String;FFFF)J
 */
JNIEXPORT jlong JNICALL Java_com_plter_game2d_display_Image_createNativeObjectWithFileNameAndRect
(JNIEnv *, jobject, jstring fileName, jfloat x, jfloat y, jfloat width, jfloat height){
    return (jlong)cocos2d::Sprite::create(cocos2d::JniHelper::jstring2string(fileName), cocos2d::Rect(x,y,width,height));
}

/*
 * Class:     com_plter_game2d_display_Image
 * Method:    createNativeObjectWithArgs
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_plter_game2d_display_Image_createNativeObjectWithFileName
(JNIEnv *, jobject, jstring fileName){
    return (jlong)cocos2d::Sprite::create(cocos2d::JniHelper::jstring2string(fileName));
}
