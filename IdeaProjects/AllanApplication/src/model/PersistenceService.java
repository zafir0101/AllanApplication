package model;

import java.io.*;

/**
 * A generic utility class responsible for persisting data in the application using Java Serialization.
 * <p>
 * This class handles reading and writing objects of type {@code <T extend Serializable>} to binary files.
 *
 * @author zafir
 * @param <T> The type of the object to be serialized (must implement {@link java.io.Serializable}).
 */
public class PersistenceService<T extends Serializable> {

    public void save(T data, String fileName) throws IOException {
        try (ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputFile.writeObject(data);
        }
    }

    public T load(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) inputFile.readObject();
        }
    }
}