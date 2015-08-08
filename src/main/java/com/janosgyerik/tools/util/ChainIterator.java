package com.janosgyerik.tools.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ChainIterator<E> implements Iterator<E> {
    private final Queue<Iterator<E>> iterators = new LinkedList<>();

    public ChainIterator(Iterator<E>... iterators) {
        for (Iterator<E> iterator : iterators) {
            if (iterator.hasNext()) {
                this.iterators.add(iterator);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();
    }

    @Override
    public E next() {
        Iterator<E> iterator = iterators.peek();
        E result = iterator.next();
        if (!iterator.hasNext()) {
            iterators.poll();
        }
        return result;
    }
}
