package com.venkat.design.structural.decorator;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class UnmodifiableCollectionDecorator<E> implements Collection<E> {
	
	private final Collection<? extends E> c;

	public UnmodifiableCollectionDecorator(Collection<? extends E> collection) {
		Objects.requireNonNull(collection, "Collection required, can't be null!");
		this.c = collection;
	}

    public int size()                              {return c.size();}
    public boolean isEmpty()                       {return c.isEmpty();}
    public boolean contains(Object o)              {return c.contains(o);}
    public boolean containsAll(Collection<?> coll) {return c.containsAll(coll);}
    public Object[] toArray()                      {return c.toArray();}
    public <T> T[] toArray(T[] a)                  {return c.toArray(a);}
    public String toString()                       {return c.toString();}

    // Override default methods in Collection
    @Override
    public void forEach(Consumer<? super E> action) {c.forEach(action);}
    @SuppressWarnings("unchecked")
    @Override
    public Spliterator<E> spliterator() {return (Spliterator<E>)c.spliterator();}
    @SuppressWarnings("unchecked")
    @Override
    public Stream<E> stream() {return (Stream<E>)c.stream();}
    @SuppressWarnings("unchecked")
    @Override
    public Stream<E> parallelStream() {return (Stream<E>)c.parallelStream();}

	@Override
	public boolean add(E e) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
        throw new UnsupportedOperationException();
	}

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }

	@Override
	public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final Iterator<? extends E> i = c.iterator();

            public boolean hasNext() {return i.hasNext();}
            public E next()          {return i.next();}
            @Override
            public void forEachRemaining(Consumer<? super E> action) {
                // Use backing collection version
                i.forEachRemaining(action);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
	}

}
