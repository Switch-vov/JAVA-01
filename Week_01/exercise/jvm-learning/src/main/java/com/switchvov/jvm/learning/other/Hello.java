package com.switchvov.jvm.learning.other;

/**
 * 自己写一个简单的`Hello.java`，里面需要涉及基本类型，四则运行，`if`和`for`，然后自己分析一下对应的字节码
 *
 * @author switch
 * @since 2021/1/12
 */
public class Hello {
    public static void main(String[] args) {
        int num1 = 1;
        int num2 = 2;
        int num3 = num1 + num2;
        num3 *= 5;
        num3 -= 5;
        num3 /= 2;

        if (num1 > num3) {
            num3 = num1;
        }
        for (int i = 0; i < 10; i++) {
            num3 += i;
        }
    }
}
