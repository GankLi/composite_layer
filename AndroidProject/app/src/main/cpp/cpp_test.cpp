//
// Created by Administrator on 2020/7/15.
//

#include "includes/cpp_test.h"
#include <iostream>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <sched.h>
#include <sys/syscall.h>
#include <unistd.h>
#include <android/log.h>
#include <jni.h>

#define TAG "VrShell"

typedef enum {
        SP_DEFAULT = -1,
        SP_BACKGROUND = 0,
        SP_FOREGROUND = 1,
        SP_SYSTEM = 2,  // can't be used with set_sched_policy()
        SP_AUDIO_APP = 3,
        SP_AUDIO_SYS = 4,
        SP_TOP_APP = 5,
        SP_RT_APP = 6,
        SP_RESTRICTED = 7,
        SP_CNT,
        SP_MAX = SP_CNT - 1,
        SP_SYSTEM_DEFAULT = SP_FOREGROUND,
    } SchedPolicy;

using namespace std;
const char * cppFunction(){
    const char * str = "String from C++ !";
    return str;
}


#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)

extern int set_cpuset_policy(int tid, SchedPolicy policy);
extern int set_sched_policy(int tid, SchedPolicy policy);

extern "C"
JNIEXPORT jint JNICALL
Java_com_pimax_vrshell_activity_MainUnityActivity_setCpuAffinity(JNIEnv *env, jobject thiz) {


    //set_sched_policy(tid, SP_FOREGROUND);
    //set_cpuset_policy(tid, SP_TOP_APP);

    /*
    LOGD("Affinity set start, tid:%d\n", gettid());
    char* cpus = "01000000"; // 绑定到核心0上，剩下的都为0
    cpu_set_t mask;
    CPU_ZERO(&mask);

    //char *cpus = (char *)cpus;
    int size = 8;
    for(int i=0;i<size;i++)
    {
        char cpu = cpus[i];
        if(cpu == '1')
        {
            CPU_SET(i, &mask);
        }
    }

    pid_t pid = getpid();
    //errno = 0;
    int x = sched_setaffinity(pid, sizeof(cpu_set_t), &mask);
    if(x==-1) {
        LOGD("Affinity set error %d-%d\n", __errno, x);
        return -1;
    }else{
        LOGD("Affinity set success %d-%d\n", __errno, x);
    }
    LOGD("Affinity set done");
    return 0;
    */

    pid_t pid = getpid();
    pid_t tid = gettid();

    LOGD("Affinity set start, pid:%d\n", pid);
    cpu_set_t cpuset;
    CPU_ZERO(&cpuset);
    CPU_SET(0, &cpuset);
    //CPU_SET(5, &cpuset);
    int err, syscallres;
        syscallres = syscall(__NR_sched_setaffinity, pid, sizeof(cpuset), cpuset); //gettid()
    if (syscallres) {
        err = errno;
        LOGD("Affinity set error %d\n", errno);
    } else {
        LOGD("Affinity set Success\n");
    }
    struct sched_param param_rt;
    param_rt.sched_priority = 50;
    pthread_setschedparam(pthread_self(), SCHED_RR, &param_rt);

    LOGD("Affinity Done\n");
    return 0;
}

