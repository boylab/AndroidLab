package com.boylab.projectstruct.utilre;

public class StringArrayUtils {

    public static int indexOf(String[] array, String str) {
        if ((array == null) || (str == null))
            return -1;
        for (int i = 0; i < array.length; ++i) {
            if (array[i].compareTo(str) == 0)
                return i;
        }
        return -1;
    }

}
