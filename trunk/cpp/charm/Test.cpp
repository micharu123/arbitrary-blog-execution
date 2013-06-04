#include "Charm.h"
#include <iostream>
#include <sstream>
#include <string>
#include <list>
#include <android/log.h>
using namespace std;


int main() {
    __android_log_write(ANDROID_LOG_INFO, "RELIC", "Starting Stuff");

   PairingGroup group;

   group.setCurve(0);

   ZR a = group.random(ZR_t);

   cout << "Hello World!" << endl;
   cout << "a := " << a << endl;
   return 0;
}
