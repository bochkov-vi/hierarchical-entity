package com.bochkov.hierarchical;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StreamSupport {

    static <T> Stream<T> stream(Iterable<T> iterable) {
        return Optional.ofNullable(iterable).map(items -> java.util.stream.StreamSupport.stream(items.spliterator(), true)).orElseGet(Stream::empty);
    }

    static <T> Stream<T> stream(Stream<T> stream) {
        return Optional.ofNullable(stream).orElseGet(Stream::empty);
    }

    static <T> Stream<T> stream(T... args) {
        return Optional.ofNullable(args).map(Stream::of).orElseGet(Stream::empty);
    }


}
