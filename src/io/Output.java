package io;

public interface Output {
    Output CONSOLE = System.out::print;

    void print(String message);
}
