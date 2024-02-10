package io;

import java.util.Scanner;

public interface Input {
    Input CONSOLE = () -> new Scanner(System.in).nextInt();

    int nextMoveColumn();
}
