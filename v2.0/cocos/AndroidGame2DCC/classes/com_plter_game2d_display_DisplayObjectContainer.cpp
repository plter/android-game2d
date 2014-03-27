//
//  com_plter_game2d_display_DisplayObjectContainer.cpp
//  AndroidGame2DCC
//
//  Created by plter on 14-3-26.
//
//

#include "com_plter_game2d_display_DisplayObjectContainer.h"
#include <cocos2d.h>

/*
 * Class:     com_plter_game2d_display_DisplayObjectContainer
 * Method:    nativeAddChild
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObjectContainer_nativeAddChild
(JNIEnv *, jobject, jlong thiz, jlong child){
    ((cocos2d::Node*)thiz)->addChild((cocos2d::Node*)child);
}

/*
 * Class:     com_plter_game2d_display_DisplayObjectContainer
 * Method:    nativeRemoveChild
 * Signature: (JJ)V
 */
JNIEXPORT void JNICALL Java_com_plter_game2d_display_DisplayObjectContainer_nativeRemoveChild
(JNIEnv *, jobject, jlong thiz, jlong child){
    ((cocos2d::Node*)thiz)->removeChild((cocos2d::Node*)child);
}