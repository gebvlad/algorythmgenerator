package com.gebvlad.algorythmgenerator;

public class Node {
    //Константы
    public static final int BEGIN = 0; //Блок начала алгоритма
    public static final int LIN = 1; //Линейный блок (операционный)
    public static final int LOOP = 2; //Блок цикла
    public static final int ALT = 3; //Блок ветвления
    public static final int PAR = 4; //Блок ветвления программы на две параллельные ветви
    public static final int P = 5; //Вспомогательный блок для блока PAR
    public static final int END = 6; //Вспомогательный блок для блока PAR
    public static final int ENDLOOP = 7; //Конец цикла
    public static final int ENDALT = 8; //Конец блока ветвления
    public static final int ENDPAR = 9; //Конец блока ветвления
    public static final int P2 = 10;//Начало второй ветви блока PAR
    // Названия вершин, нужны для формирования листинга сгенерированного алгоритма
    public static final String[] TYPES =
            {"BEGIN", "LIN", "LOOP", "ALT", "PAR", "P", "END", "ENDLOOP", "ENDALT", "ENDPAR", "P2"};
    // Число ссылок на другие вершины. Нули для тех вершин, которые являются вспомогательными
    private final int[] NUM_NODES = {10, 2/*<<<<<<1*/, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0};
    private final boolean[] TYPE_NODES = {false, true, false, false, false, false, false, false, true, true, false};
    //Поля
    private int type;     // тип вершины
    private Node[] node; // ссылки на другие вершины
    private int[] value; // значения осведомительных и управляющих сигналов

    //Создать узел дерева указанного типа
    public Node(int type) {
        this.type = type; //Записать тип вершины
        node = new Node[NUM_NODES[type]];//Создать пустые ссылки на другие вершины.
        //Их число определяется типом узла
        //Если узел типа "PAR", то необходимо добавить вспомогательный узел "P"
        if (type == PAR) node[0] = new Node(P);//Рекурсивный вызов конструктора класса Node, имен сигналов не хранит
    }

    //Создать узел дерева указанного типа и записать в него имена управляющих или осведомительных сигналов
    public Node(int type, int[] value) {
        this.type = type;  //Записать тип вершины
        this.value = value;//Записать имена сигналов
        node = new Node[NUM_NODES[type]];//Создать пустые ссылки на другие вершины.
        //Их число определяется типом узла
        //Если узел типа "PAR", то необходимо добавить вспомогательный узел "P"
        if (type == PAR) node[0] = new Node(P);//Рекурсивный вызов конструктора класса Node, имен сигналов не хранит
    }

    public boolean isOperate() {
        return TYPE_NODES[type];
    }

    //Получение типа узла
    public int getType() {
        return type;
    }

    //Получение имен хранящихся сигналов
    public int[] getValue() {
        return value;
    }

    //Получение массива ссылок на другие узлы
    public Node[] getNode() {
        return node;
    }
}