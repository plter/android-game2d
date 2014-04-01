#include "Game2DStage.h"

USING_NS_CC;

Game2DStage* Game2DStage::__ins = NULL;

Game2DStage* Game2DStage::getInstance(){
    if (__ins == NULL) {
        __ins = new Game2DStage();
        __ins->init();
    }
    return __ins;
}

// on "init" you need to initialize your instance
bool Game2DStage::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !Layer::init() )
    {
        return false;
    }
    //creation complete call back
    JniMethodInfo nativeCreationCompleteCallbackMInfo;
    JniHelper::getStaticMethodInfo(nativeCreationCompleteCallbackMInfo, "com.plter.game2d.display.Game2DActivity", "nativeCreationCompleteCallback", "()V");
    nativeCreationCompleteCallbackMInfo.env->CallStaticVoidMethod(nativeCreationCompleteCallbackMInfo.classID, nativeCreationCompleteCallbackMInfo.methodID);
    
    addTouchListener();
    addKeyPressListener();
    
    return true;
}


void Game2DStage::addTouchListener(){
    JniMethodInfo nativeTouchCallbackMInfo;
    JniHelper::getStaticMethodInfo(nativeTouchCallbackMInfo, "com.plter.game2d.display.Game2DActivity", "nativeTouchCallback", "(Ljava/lang/String;[F)V");
    
    auto touchListener = EventListenerTouchAllAtOnce::create();
    touchListener->onTouchesBegan = [this,nativeTouchCallbackMInfo](std::vector<Touch*> touches,Event *e){
        this->touchHandler(nativeTouchCallbackMInfo, "touchBegan", touches);
    };
    touchListener->onTouchesEnded = [this,nativeTouchCallbackMInfo](std::vector<Touch*> touches,Event *e){
        this->touchHandler(nativeTouchCallbackMInfo, "touchEnded", touches);
    };
    touchListener->onTouchesMoved = [this,nativeTouchCallbackMInfo](std::vector<Touch*> touches,Event *e){
        this->touchHandler(nativeTouchCallbackMInfo, "touchMoved", touches);
    };
    Director::getInstance()->getEventDispatcher()->addEventListenerWithSceneGraphPriority(touchListener, this);
}


void Game2DStage::touchHandler(JniMethodInfo nativeTouchCallbackMInfo, std::string type, std::vector<cocos2d::Touch *> &touches){
    
    jstring jType = nativeTouchCallbackMInfo.env->NewStringUTF(type.c_str());
    
    int len = touches.size()*2;
    jfloat buf[len];
    jfloatArray arr = nativeTouchCallbackMInfo.env->NewFloatArray(len);
    
    Touch *t = NULL;
    Point p;
    
    int index = 0;
    for (auto it = touches.begin(); it!=touches.end(); it++) {
        t = (Touch*)(*it);
        p = t->getLocation();
        
        buf[index] = p.x;index++;
        buf[index] = p.y;index++;
    }
    
    nativeTouchCallbackMInfo.env->SetFloatArrayRegion(arr, 0, len, buf);
    
    nativeTouchCallbackMInfo.env->CallStaticVoidMethod(nativeTouchCallbackMInfo.classID, nativeTouchCallbackMInfo.methodID,jType,arr);
    
    nativeTouchCallbackMInfo.env->DeleteLocalRef(jType);
    nativeTouchCallbackMInfo.env->DeleteLocalRef(arr);
}


void Game2DStage::addKeyPressListener(){
    JniMethodInfo nativeKeyEventCallbackMInfo;
    JniHelper::getStaticMethodInfo(nativeKeyEventCallbackMInfo, "com.plter.game2d.display.Game2DActivity", "nativeKeyPressCallback", "(Ljava/lang/String;I)V");
    
    
    auto l = EventListenerKeyboard::create();
    l->onKeyPressed = [this,nativeKeyEventCallbackMInfo](EventKeyboard::KeyCode code,Event *e){
        this->keyEventHandler(nativeKeyEventCallbackMInfo, "keyDown", (int)code);
    };
    l->onKeyReleased = [this,nativeKeyEventCallbackMInfo](EventKeyboard::KeyCode code,Event *e){
        this->keyEventHandler(nativeKeyEventCallbackMInfo, "keyUp", (int)code);
    };
    
    Director::getInstance()->getEventDispatcher()->addEventListenerWithSceneGraphPriority(l, this);
}

void Game2DStage::keyEventHandler(cocos2d::JniMethodInfo minfo, std::string type, int keyCode){
    jstring jtype = minfo.env->NewStringUTF(type.c_str());
    
    minfo.env->CallStaticVoidMethod(minfo.classID, minfo.methodID,jtype,keyCode);
    
    minfo.env->DeleteLocalRef(jtype);
}
