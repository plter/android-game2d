//
//  com_plter_game2d_display_Sprite.cpp
//  AndroidGame2DCC
//
//  Created by plter on 14-3-27.
//
//

#include "com_plter_game2d_display_Sprite.h"
#include <cocos2d.h>


/*
 * Class:     com_plter_game2d_display_Sprite
 * Method:    createNativeObject
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_com_plter_game2d_display_Sprite_createNativeObject
(JNIEnv *, jobject){
    return (jlong)cocos2d::Node::create();
}

/*
 * Class:     com_plter_game2d_display_Sprite
 * Method:    nativeSetContentSize
 * Signature: (JFF)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_Sprite_nativeSetContentSize
(JNIEnv *, jobject, jlong thiz, jfloat width, jfloat height){
    ((cocos2d::Node*)thiz)->setContentSize(cocos2d::Size(width,height));
}