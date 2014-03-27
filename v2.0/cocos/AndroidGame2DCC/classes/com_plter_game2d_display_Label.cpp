//
//  com_plter_game2d_display_Label.cpp
//  AndroidGame2DCC
//
//  Created by plter on 14-3-27.
//
//

#include "com_plter_game2d_display_Label.h"
#include <cocos2d.h>
#include <platform/android/jni/JniHelper.h>


/*
 * Class:     com_plter_game2d_display_Label
 * Method:    createNativeObjectWithString
 * Signature: (Ljava/lang/String;Ljava/lang/String;F)J
 */
JNIEXPORT jlong JNICALL Java_com_plter_game2d_display_Label_createNativeObjectWithString
(JNIEnv *, jobject, jstring str, jstring font, jfloat size){
    return (jlong)cocos2d::LabelTTF::create(cocos2d::JniHelper::jstring2string(str), cocos2d::JniHelper::jstring2string(font), size);
}

/*
 * Class:     com_plter_game2d_display_Label
 * Method:    nativeSetString
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Label_nativeSetString
(JNIEnv *, jobject, jlong thiz, jstring str){
    ((cocos2d::LabelTTF*)thiz)->setString(cocos2d::JniHelper::jstring2string(str));
}

/*
 * Class:     com_plter_game2d_display_Label
 * Method:    nativeSetFontName
 * Signature: (JLjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Label_nativeSetFontName
(JNIEnv *, jobject, jlong thiz, jstring font){
    ((cocos2d::LabelTTF*)thiz)->setFontName(cocos2d::JniHelper::jstring2string(font));
}

/*
 * Class:     com_plter_game2d_display_Label
 * Method:    nativeSetFontSize
 * Signature: (JF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Label_nativeSetFontSize
(JNIEnv *, jobject, jlong thiz, jfloat size){
    ((cocos2d::LabelTTF*)thiz)->setFontSize(size);
}
