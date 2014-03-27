#ifndef __HELLOWORLD_SCENE_H__
#define __HELLOWORLD_SCENE_H__

#include <cocos2d.h>
#include <vector>
#include <platform/android/jni/JniHelper.h>

class Game2DStage : public cocos2d::Layer
{
private:
    static Game2DStage *__ins;
    
public:
    // Here's a difference. Method 'init' in cocos2d-x returns bool, instead of returning 'id' in cocos2d-iphone
    virtual bool init();
    void touchHandler(cocos2d::JniMethodInfo nativeTouchCallbackMInfo,std::string type,std::vector<cocos2d::Touch*> & touches);
    
    static Game2DStage *getInstance();
};

#endif // __HELLOWORLD_SCENE_H__
