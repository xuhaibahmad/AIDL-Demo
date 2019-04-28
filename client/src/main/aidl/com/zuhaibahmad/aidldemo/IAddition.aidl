// IAddition.aidl
package com.zuhaibahmad.aidldemo;

import com.zuhaibahmad.aidldemo.Result;
// Declare any non-default types here with import statements

interface IAddition {
    Result performAddition(int numOne, int numTwo);
}
