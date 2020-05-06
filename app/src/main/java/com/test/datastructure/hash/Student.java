package com.test.datastructure.hash;

import android.support.annotation.Nullable;

public class Student {
    private int grade;
    private int cls;
    private String firstName;
    private String lastName;

    public Student(int grade, int cls, String firstName, String lastName){
        this.grade = grade;
        this.cls = cls;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 1. 如果不覆盖 hashCode()，默认会使用 Object 的 hashCode()，通过对象的地址计算 hashCode 值
     *    不覆盖 hashCode() 存在的问题是，new 两个内容相同的对象，我们希望他们代表同一个对象，
     *    但是由于对象的地址不同，他们的 hashCode 值就不同，他们就不能代表同一个对象
     * 2. 如果覆盖 hashCode()，还需要同时覆盖 Object 的 equals()，用来解决 hashCode 值冲突问题
     *    当 hashCode 值冲突的时候，会使用 equals() 来区分两个对象
     */
    @Override
    public int hashCode() {
        int B = 31;
        int hash = 0;
        hash = hash * B + grade;
        hash = hash * B + cls;
        hash = hash * B + firstName.toLowerCase().hashCode();
        hash = hash * B + lastName.toLowerCase().hashCode();
        return hash;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Student another = (Student) obj;
        return this.grade == another.grade &&
                this.cls == another.cls &&
                this.firstName.toLowerCase().equals(another.firstName.toLowerCase()) &&
                this.lastName.toLowerCase().equals(another.lastName.toLowerCase());
    }

}
