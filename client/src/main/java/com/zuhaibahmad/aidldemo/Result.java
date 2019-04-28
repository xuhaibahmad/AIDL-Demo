package com.zuhaibahmad.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Result implements Parcelable {
    private int numOne;
    private int numTwo;
    private int result;

    public Result() {}

    public Result(int numOne, int numTwo, int result) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.result = result;
    }

    public int getNumOne() {
        return numOne;
    }

    public void setNumOne(int numOne) {
        this.numOne = numOne;
    }

    public int getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(int numTwo) {
        this.numTwo = numTwo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int numResult) {
        this.result = numResult;
    }

    @Override
    public String toString() {
        return numOne + " + " + numTwo + " = " + result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numOne);
        parcel.writeInt(numTwo);
        parcel.writeInt(result);
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>(){

        @Override
        public Result createFromParcel(Parcel parcel) {
            Result res=new Result();
            res.numOne = parcel.readInt();
            res.numTwo = parcel.readInt();
            res.result = parcel.readInt();
            return res;
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}