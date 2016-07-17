package com.gebvlad.algorythmgenerator;

import org.apache.commons.math3.distribution.PoissonDistribution;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;


public class AlgTree {
    private Node tree; //Дерево генерируемого алгоритма
    private int x;     // число осведомительных сигналов
    private int y;     // число управляющих сигналов

    //Конструктор класса
    public AlgTree(int x, int y, int m, int n) {
        this.x = x; // число осведомительных сигналов
        this.y = y; // число управляющих сигналов
        tree = new Node(Node.BEGIN); //В дерево алгоритма добавляется узел "BEGIN"
        int i = 0;//рабочая переменная
        int j = 0;//рабочая переменная
        //Рэндом для получения более линейного алгоритма или сильноветвящегося
        int vetvistost = (int) (Math.random() * 2);

        if ((int) (Math.random() * 10) == 9) // Священный рэндом :D
        {
            addNodeOperation(vetvistost);
            j++;
        }

        for (; i < m; i++) addNodeControl(vetvistost);   //Генерация условных вершин
        for (; j < n; j++) addNodeOperation(vetvistost); //Генерация операционных вершин
    }

    //Получение листинга программы (алгоритма) в мнемокоде
    //Для его получения необходимо дерево алгоритма
    public static void printProgram(AlgTree alg) {

        System.out.println((ProgramChecker.check(printProgramToString(alg)) ? " в коде есть ошибки " : " код не содержит ошибок "));
        System.out.println(printProgramToString(alg));
    }

    //Получение листинга программы (алгоритма) в мнемокоде
    //Для его получения необходимо дерево алгоритма
    public static String printProgramToString(AlgTree alg) {
        String st = "";
        //Создаем рабочий списокдля получения списка всех узлов дерева алгоритма
        LinkedList<Node> nnn = alg.traversa();
        int count = 1;//Рабочая переменная для формирования форматирования (отступов)
        int t; //Рабочая переменная
        //Вывод корневого(первого) узла из списка
        st += Node.TYPES[nnn.get(0).getType()] + "\n";
        //Для остальных элементов списка узлов
        for (int i = 1; i < nnn.size(); i++) {
            t = nnn.get(i).getType();//Получить тип узла
            //Если эта вершина соотвествует вспомогательным блокам
            //"END", "ENDALT", "ENDPAR", "ENDLOOP", "P2" то уменьшаем счетчик отступа на 1
            if (t == 6 | t == 7 | t == 8 | t == 9 | t == 10) count--;
            //Вывод отступов
            for (int z = 0; z < count; z++) st += "  ";
            //Вывод типа узла
            st += Node.TYPES[nnn.get(i).getType()] + " ";
            //Вывод списка имен управляющих и осведомительных сигналов
            if (nnn.get(i).getValue() != null) {
                for (int z = 0; z < nnn.get(i).getValue().length; z++) {
                    //Если вершина соотвествует условному блоку - вывод имен начиная с "х"
                    if (t == 2 | t == 3 | t == 4)
                        st += "x" + nnn.get(i).getValue()[z] + " ";
                        //Если вершина соотвествует операционному блоку - вывод имен начиная с "y"
                    else if (t == 1)
                        st += "y" + nnn.get(i).getValue()[z] + " ";
                }//for
            }
            //Если эта вершина соотвествует блокам
            //"ALT", "PAR", "LOOP", "P2" то увеличиваем счетчик отступа на 1
            if (t == 2 | t == 3 | t == 5 | t == 10) count++;
            //Переход на следующую строку
            st += "\n";
        }

        return st;
    }

    //Вывод сгенерированного алгоритма в файл XML для внешнего приложения, для визуализации алгоритма
    public static void printProgramToFile(AlgTree alg, String pathToSave) {
        File f = null;
        f = new File(pathToSave + "programm_" + getDateTime2() + ".asp");
        try {
            PrintWriter pr = new PrintWriter(f);
            pr.write(printProgramToString(alg));
            pr.close();
        } catch (Exception e) {
        }
    }

    //Вывод сгенерированного алгоритма в вормате XML для внешнего приложения, для визуализации алгоритма
    public static void printProgramXML(AlgTree alg) {
        System.out.println(getXMLString(alg));//Вывод в XML формате
    }

    //Вывод сгенерированного алгоритма в файл XML для внешнего приложения, для визуализации алгоритма
    public static void printProgramXMLToFile(AlgTree alg, String pathToSave) {
        File f = null;
        f = new File(pathToSave + "algorythm_" + getDateTime2() + ".afc");
        try {
            PrintWriter pr = new PrintWriter(f);
            pr.write(getXMLString(alg));
            pr.close();
        } catch (Exception e) {
        }
    }

    /**
     * @return DateFormat
     */
    static private String getDateTime2() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    //Вывод сгенерированного алгоритма в вормате XML для внешнего приложения, для визуализации алгоритма
    private static String getXMLString(AlgTree alg) {
        String xml = "";
        //Получить список всех узлов дерева
        LinkedList<Node> nnn = alg.traversa();
        int t; // рабочая переменная
        xml += "<!DOCTYPE AFC><algorithm><branch>\n";//Вывод в XML формате
        //Для всех узлов списка
        for (int i = 1; i < nnn.size() - 1; i++) {
            t = nnn.get(i).getType();//Получаем тип вершины
            if (t == Node.LIN) {
                xml += "<process text=\"";//Вывод в XML формате

                if (nnn.get(i).getValue() != null) {
                    for (int z = 0; z < nnn.get(i).getValue().length - 1; z++) {
                        xml += "y" + nnn.get(i).getValue()[z] + ", ";//Вывод в XML формате
                    }
                    xml += "y" + nnn.get(i).getValue()[nnn.get(i).getValue().length - 1] + "\" />\n";//Вывод в XML формате
                }
            }
            if (t == Node.LOOP)
                xml += "<post cond=\"x" + nnn.get(i).getValue()[0] + " = 0\" ><branch>\n";//Вывод в XML формате
            if (t == Node.ALT || t == Node.PAR)
                xml += "<if cond=\"x" + nnn.get(i).getValue()[0] + " = 0\" ><branch>\n";//Вывод в XML формате
            if (t == Node.P2)
                xml += "</branch><branch>\n";//Вывод в XML формате
            if (t == Node.ENDLOOP)
                xml += "</branch></post>\n";//Вывод в XML формате
            if (t == Node.ENDALT)
                xml += "</branch><branch></branch></if>\n";//Вывод в XML формате
            if (t == Node.ENDPAR)
                xml += "</branch></if>\n";//Вывод в XML формате
        }
        xml += "</branch></algorithm>";//Вывод в XML формате

        return xml;
    }

    //Добавление узла, соответствующего операционному блоку
    private void addNodeOperation(int vetvistost) {
        int t;
        if (vetvistost == 0)
            t = (int) (Math.random() * 10);
        else
            t = 0;
        //Если у корневого узла ("BEGIN") есть ссылка на другой узел
        if (tree.getNode()[t] != null)
            //Пробуем добавить узел, соответствующий операционному блоку в тот, на который есть ссылка
            addNodeOperation(tree.getNode()[t], vetvistost);
        else
            //Если ссылки нет, добавляем новый блок "LIN"
            tree.getNode()[t] = new Node(Node.LIN, massive());
    }

    //Добавление узла, соответствующего операционному блоку, к указанному узлу дерева
    private void addNodeOperation(Node node, int vetvistost)//(int type)
    {
        int le = node.getNode().length;//Плучаем длину списка ссылок на другие узлы (длина может быть равна 1 или 2)
        int ra = (int) (Math.random() * le); //Выбираем произвольную ссылку
        //Если выбранная ссылка ссылается на другой узел
        if (node.getNode()[ra] != null)
            //Пробуем добавить узел, соответствующий операционному блоку в тот, на который указывает ссылка
            addNodeOperation(node.getNode()[ra], vetvistost);
        else
            //Если ссылки нет, добавляем новый блок "LIN"
            node.getNode()[ra] = new Node(Node.LIN, massive());
    }

    //Добавление узла, соответствующего управляющему блоку
    private void addNodeControl(int vetvistost) {
        int t;
        if (vetvistost == 0)
            t = (int) (Math.random() * 10);
        else
            t = 0;
        //Если у корневого узла ("BEGIN") есть ссылка на другой узел
        if (tree.getNode()[t] != null)
            //Пробуем добавить узел, соответствующий управляющему блоку в тот, на который есть ссылка
            addNodeControl(tree.getNode()[t], vetvistost);
        else
            //Если ссылки нет, добавляем блок соотвествующий ветвлению (ALT, PAR, LOOP)
            //и генерируем имя осведомительного сигнала из списка допустимых имен
            tree.getNode()[t] = new Node(randomAlt(), new int[]{(int) (Math.random() * x) + 1});
    }

    //Добавление узла, соответствующего управляющему блоку, к указанному узлу дерева
    private void addNodeControl(Node node, int vetvistost) {
        int le = node.getNode().length;//Плучаем длину списка ссылок на другие узлы (длина может быть равна 1 или 2)
        int ra = (int) (Math.random() * le); //Выбираем произвольную ссылку
        //Если выбранная ссылка ссылается на другой узел
        if (node.getNode()[ra] != null)
            //Пробуем добавить узел, соответствующий управляющему блоку в тот, на который указывает ссылка
            addNodeControl(node.getNode()[ra], vetvistost);
        else
            //Если ссылки нет, добавляем блок соотвествующий ветвлению (ALT, PAR, LOOP)
            //и генерируем имя осведомительного сигнала из списка допустимых имен
            node.getNode()[ra] = new Node(randomAlt(), new int[]{(int) (Math.random() * x) + 1});
    }

    private int randomAlt() {
        return (int) (Math.random() * 3 + 2);
    }

    //Генерация списка имен управляющих сигналов
    private int[] massive() {
        int[] m = null; //Рабочий массив списка имен управляющих сигналов
        //Определяем длину списка. Определение длины осуществляется по распределению Пуассона
        PoissonDistribution p = new PoissonDistribution(2);//Создать распределение
        int z = p.sample() + 1; // получить случайное число
        z = (z > y ? y : z); // Если полученное число превышает число управляющих сигналов,
        // то принять его равным числу управляющих сигналов
        m = new int[z]; //Создать массив списка имен управляющих сигналов нужной длины
        //Список нужен для выявления повторов имен управляющих сигналов
        LinkedList<Integer> li = new LinkedList();
        int g; //Рабочая переменная
        //Заполняем массив именами
        for (int i = 0; i < z; i++) {
            g = (int) (Math.random() * y) + 1; //Получаем имя управляющего сигнала из списка допустимых
            //Процесс повторяется до тех пор пока не будет получено уникальное имя в рамках данного списка
            while (true) {
                //Если такого имени управляющего сигнала не было
                if (!li.contains(g)) {
                    m[i] = g; //Записать имя в массив
                    li.add(g);//Запомнить имя в сиске
                    break;    //Прервать поиск для данного элемента массива
                } else {
                    //В ином случае генерируем новое имя и повторяем все заново
                    g = (int) (Math.random() * y) + 1;
                }
            }//while
        }//for
        Arrays.sort(m);
        return m; //Возвращаем полученный массив
    }

    //Обход дерева, для получения списка всех узлов дерева (Инициализация обхода)
    public LinkedList<Node> traversa() {
        //Список для хренения всех узлов дерева алгоритма
        LinkedList<Node> nodes = new LinkedList();
        //В список добавляется корень дерева алгоритма
        nodes.add(tree);
        //Начинаем рекурсивный обход поддерева с указанного узла
        traversa(nodes, tree);
        //После завершения обхода всего дерева, добавляем вспомогательный узел "END"
        nodes.add(new Node(Node.END));
        //Вернуть полученный список узлов
        return nodes;
    }

    //Обход дерева с указанной вершины (корня) с формированием (дополнением) имеющегося списка узлов
    private void traversa(LinkedList<Node> nodes, Node node) {
        //Получить список ссылок на другие узлы от имеющегося корня
        Node[] n = node.getNode();
        //Для всего списка
        for (int i = 0; i < n.length; i++) {
            //Если ссылка не пустая
            if (n[i] != null) {
                //Добавить текущий узел в список узлов дерева
                nodes.add(n[i]);
                //Обойти поддерево дерева алгоритмов для данного узла
                traversa(nodes, n[i]);
                //После обхода поддерева
                //Если текущий узел был "LOOP"
                if (n[i].getType() == Node.LOOP)
                    //Добавить в список вспомогательный узел "ENDLOOP"
                    nodes.add(new Node(Node.ENDLOOP));
                    //Если текущий узел был "ALT"
                else if (n[i].getType() == Node.ALT)
                    //Добавить в список вспомогательный узел "ENDALT"
                    nodes.add(new Node(Node.ENDALT));
                    //Если текущий узел был "PAR"
                else if (n[i].getType() == Node.PAR)
                    //Добавить в список вспомогательный узел "ENDPAR"
                    nodes.add(new Node(Node.ENDPAR));
                    //Если текущий узел был "P"
                else if (n[i].getType() == Node.P)
                    //Добавить в список вспомогательный узел "ENDP"
                    nodes.add(new Node(Node.P2));
            }//if
        }//for
    }

    //Получение листинга программы (алгоритма) в мнемокоде
    public void printProgram() {
        printProgram(this);
    }

    public void printProgramToFile(String pathToSave) {
        printProgramToFile(this, pathToSave);
    }

    public void printProgramXML() {
        printProgramXML(this);
    }

    public void printProgramXMLToFile(String pathToSave) {
        printProgramXMLToFile(this, pathToSave);
    }
}