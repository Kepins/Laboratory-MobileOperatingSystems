#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_labapp5_MainActivity_exampleString(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "STRING from C++";
    return env->NewStringUTF(hello.c_str());
}



extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_labapp5_MainActivity_removeDuplicates(JNIEnv *env, jobject thiz,
                                                       jintArray arrayInput) {
    jint arrayLen = env->GetArrayLength(arrayInput);

    jint* input = env->GetIntArrayElements(arrayInput, 0);
    jint* output = new jint[arrayLen];

    jint outidx = 0;
    for(jint i=0; i < arrayLen;i++){
        jint current_element = input[i];
        jboolean should_insert = true;
        for(jint j=0; j<outidx;j++){
            if (current_element == output[j]){
                should_insert = false;
                break;
            }
        }
        if(should_insert){
            output[outidx] = current_element;
            outidx++;
        }
    }

    jintArray  outputArray = env->NewIntArray(outidx);
    env->SetIntArrayRegion(outputArray, 0, outidx, output);

    delete[] output;
    env->ReleaseIntArrayElements(arrayInput, input, 0);

    return outputArray;
}