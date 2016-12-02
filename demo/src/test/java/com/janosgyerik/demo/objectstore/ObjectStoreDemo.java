package com.janosgyerik.demo.objectstore;

import com.example.tutorial.AddressBookProtos;
import com.janosgyerik.utils.objectstore.*;
import com.janosgyerik.utils.objectstore.api.ObjectStore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ObjectStoreDemo {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test_1() throws IOException {
        CountingPathGenerator pathGenerator = new CountingPathGenerator(2, 2, folder.getRoot().toPath());
        Reader<AddressBookProtos.Person> reader = AddressBookProtos.Person::parseFrom;
        Writer<AddressBookProtos.Person> writer = (out, value) -> value.writeTo(out);
        ObjectStore<String, AddressBookProtos.Person> store = new SimpleObjectStore<>(pathGenerator, reader, writer);

        AddressBookProtos.Person.Builder builder = AddressBookProtos.Person.newBuilder();
        AddressBookProtos.Person jack = builder.setName("Jack").setId(1).build();
        AddressBookProtos.Person mike = builder.setName("Mike").setId(2).build();

        store.write(jack.getName(), jack);
        store.write(mike.getName(), mike);

        assertThat(store.read(jack.getName())).isEqualTo(Optional.of(jack));
        assertThat(store.read(mike.getName())).isEqualTo(Optional.of(mike));
    }
}
