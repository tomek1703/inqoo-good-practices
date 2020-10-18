package com.inqoo.quality.clean;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LSP {
    @Test
    public void listIsOrderedNonUnique () {
        // given
        List<Integer> arrayList = new ArrayList<>(0);
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> myOwnList = new MyOwnList();

        arrayList.add(1);
        arrayList.add(2);

        linkedList.add(1);
        linkedList.add(2);

        myOwnList.add(1);
        myOwnList.add(2);

        System.out.println(arrayList);
        System.out.println(linkedList);
        System.out.println(myOwnList);
    }

}

class MyOwnList extends ArrayList {
    @Override
    public boolean add(Object o) {
        add(0, o);
        return true;
    }
}
