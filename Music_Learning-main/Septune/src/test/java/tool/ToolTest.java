package test.java.tool;

import main.java.tool.Tool;

public class ToolTest {
    public static void testAll() {
        testPlaySound();
    }

    public static void testPlaySound() {
        try {
            Tool.playSound("son_de_la_victoire.wav");
        } catch(Exception e) {
            System.err.println("CLASS : ToolTest.java\nMETHOD : playSound()\n ERROR\n");
        }
    }
}
