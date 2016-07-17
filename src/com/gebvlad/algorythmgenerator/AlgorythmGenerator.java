package com.gebvlad.algorythmgenerator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class AlgorythmGenerator {
    private int n; // число операционных вершин
    private int m; // число управляющих вершин
    private int y; // число управляющих сигналов
    private int x; // число осведомительных сигналов

    public AlgorythmGenerator(int x, int y, int n, int m) {
        this.n = n;
        this.m = m;
        this.x = x;
        this.y = y;
    }

    public AlgTree createAlg() {
        return new AlgTree(x, y, m, n);
    }

    /**
     * <br>
     * [Адрес] : [Тип микрокоманды: 0] [Список управляющих сигналов] [--------------]<br>
     * [Адрес] : [Тип микрокоманды: 1] [Осведомительный сигнал     ] [Адрес перехода]
     */
    //Получение микропрограммы для естественного способа адресации микрокоманд дла заданного алгоритма
    public LinkedList<Integer[][]> getNativeAddressingMicroprogram(AlgTree alg) {
        LinkedList<Integer[][]> li = new LinkedList();
        //<Адрес> <Тип микрокоманды: 0 > <Список управляющих сигналов> <-------------->
        //<Адрес> <Тип микрокоманды: 1 > <Осведомительный сигнал     > <Адрес перехода>
        LinkedList<Node> node = alg.traversa();
        //<Тип><Список параметров>
        int t; //рабочая переменная для хранения типа
        Node nd;//рабочая переменная для хранения обрабатываемого узла
        int count = 0;//счетчик адреса
        int[] oldArray;//Для хранения массива имен сигналов
        Integer[] newArray;//Для хранения массива имен сигналов
        Stack<Integer> loopst = new Stack();//Для обработки LOOP/ENDLOOP
        Stack<Integer> altst = new Stack();//Для обработки ALT/ENDALT
        //Для обработки инструкций PAR/P2/ENDPAR
        Stack<Integer> parst = new Stack();
        Stack<Integer> p2st = new Stack();
        //Формирование микропрограммы в общих обозначениях
        //Для всех программы в псевдокоде
        for (int i = 0; i < node.size(); i++) {
            nd = node.get(i);//Получить узел с которым работаем
            //Если тип узла LOOP
            if (nd.getType() == Node.LOOP) {
                loopst.push(count);//Запоминаем адрес инструкции
                loopst.push(nd.getValue()[0]);//Запоминаем проверяемый осведомительный сигнал
            }//if
            //Для остальных инструкций, за исключением BEGIN и P
            else if ((nd.getType() != Node.BEGIN) & (nd.getType() != Node.P))//Убрать BEGIN и P
            {
                oldArray = nd.getValue();//получаем список имен сигналов
                //Если список имен не пуст, то преобразуем список в нужный формат
                if (oldArray != null) {
                    newArray = new Integer[oldArray.length];
                    int ggg = 0;
                    for (int value : oldArray)
                        newArray[ggg++] = Integer.valueOf(value);
                }//if
                else //В ином случае вписываем 0, что соотвествует либо безусловному переходу,
                    //либо отсуствующим осведомительным сигналам
                    newArray = new Integer[]{0};
                //Для операционной инструкции
                if (nd.isOperate()) {
                    //записываем строку микропрограммы
                    //<Адрес микрокоманды><Операционная><Список имен управл. сигналов>
                    li.add(new Integer[][]{{count}, {0}, newArray});
                    //Если инструкция ENDALT
                    if (nd.getType() == Node.ENDALT)
                        //Уcтанавливаем в соотвествующей инструкции ALT адрес перехода на ENDALT
                        li.get(altst.pop())[3][0] = count;
                        //Если инструкция ENDPAR
                    else if (nd.getType() == Node.ENDPAR)
                        //Уcтанавливаем в соотвествующей инструкции P2 адрес перехода на ENDPAR
                        li.get(p2st.pop())[3][0] = count;
                }//if
                //Для управляющей инструкции
                else {
                    //Если инструкция ENDLOOP
                    if (nd.getType() == Node.ENDLOOP)
                        //Записать адрес перехода на соотвествующую инструкцию LOOP и проверяемый сигнал
                        li.add(new Integer[][]{{count}, {1}, {loopst.pop()}, {loopst.pop()}});
                        //Для остальных..
                    else {
                        //записываем строку микропрограммы
                        //<Адрес микрокоманды><Управляющая><Список имен осведомительных сигналов><Адрес перехода>
                        li.add(new Integer[][]{{count}, {1}, newArray, {-1}});
                        //Если инструкция ALT
                        if (nd.getType() == Node.ALT) {
                            altst.push(count);//Запоминаем ее адрес
                        }
                        //Если инструкция P2
                        else if (nd.getType() == Node.P2) {
                            p2st.push(count);//Запоминаем ее адрес
                            //Записываем соотвествующей инструкции PAR аддрес перехода
                            //на инструкуию следующую за P2
                            li.get(parst.pop())[3][0] = count + 1;
                        }
                        //Если инструкция PAR
                        else if (nd.getType() == Node.PAR) {
                            parst.push(count);//Запоминаем ее адрес
                        }
                    }//else
                }//else
                count++;
            }//elseif
        }//for
        //Инструкция END - устанавливаем адрес перехода "0"
        li.get(li.size() - 1)[3][0] = 0;

        return li;//Возвращаем микропрограмму в общих обозначениях
    }

    /**
     * Формат [Адрес текущий x.y] : [М] [Х] [A]
     */
    //Получение микропрограммы для принудительного способа адресации микрокоманд дла заданного алгоритма
    public HashMap<Integer, Integer[][]> getForcedAddressingMicroprogram(AlgTree alg) {
        //Выполнение разметки алгоритма для принудительной адресации микрокоманд
        //Список узлов алгоритма
        LinkedList<Node> node = alg.traversa();
        //<Тип><Список параметров>
        //Список для хранения алгоритма разбитого на блоки - [операция][проверка условия]
        LinkedList<Node> newNode = new LinkedList();
        int type;//рабочая переменная для хранения типа текущего узла
        int nextType;//рабочая переменнаядля хранения типа следующего узла
        //Рабочая переменная для отсчета блоков состоящих и пары [операция][проверка условия]
        int counts = 0;
        //получить узел BEGIN
        newNode.add(node.get(0));
        //Стрек для хранения условий перехода в цикле
        Stack<Integer> loop = new Stack();
        //Для всего списка узлов
        for (int i = 1; i < node.size(); i++) {
            type = node.get(i).getType();//Определяем тип узла
            //Если текущий тип вершины LOOP, P2, ENDALT, ENDPAR, BEGIN, END
            if (type == Node.LOOP | type == Node.P2 | type == Node.ENDALT | type == Node.ENDPAR | type == Node.BEGIN | type == Node.END) {
                if (counts == 1)//Если предудущая вершина была LIN, то вставляем ALT БП
                    newNode.add(new Node(Node.ALT, new int[]{0}));
                //Если текущая вершина инструкция LOOP, то запоминаем проверяемое условие
                if (type == Node.LOOP)
                    loop.push(node.get(i).getValue()[0]);
                counts = 0;//Обнулить счетчик блоков
                newNode.add(node.get(i));
            }//if
            //Для остальных типов узлов, исключая инструкцию Р
            else if (type != Node.P) {
                if (node.get(i).isOperate())//Если операционная вершина
                {
                    if (counts == 0)
                        counts++;//Если еще не найдено начало новой пары [операция][проверка условия], увеличиваем счетчик
                    else {   //Если уже был найден нечальный компонент пары [операция][проверка условия], то добавляем
                        //вершину безусловного перехода
                        newNode.add(new Node(Node.ALT, new int[]{0}));
                        counts = 1;//счетчик устанавливаем в 1
                    }
                    //Добавить текущий узел в новый список
                    newNode.add(node.get(i));
                }//if
                else//Для вершин ветвления
                {  //Если не было первого компонента пары [операция][проверка условия],
                    //то добавить операционную вершину с пустым множеством управляющих сигналов
                    if (counts == 0)
                        newNode.add(new Node(Node.LIN, new int[]{0}));
                    //счетчик обнулить
                    counts = 0;
                    //Добавить в новый список узлов текущий узел
                    newNode.add(node.get(i));
                    //Если текущая вершина инструкция ENDLOOP
                    if (type == Node.ENDLOOP) {
                        newNode.removeLast();//Удалить узел вставленыйранее
                        //Заменив его на новый с конкретным проверяемым условием
                        newNode.add(new Node(Node.ENDLOOP, new int[]{loop.pop()}));
                    }//if
                }//else
            }//elseif
        }//for
        node = null;//Список узлов более не нужен

        //Выполнение непосредственной генерации МП у общих обозначениях
        int A = 0;//Адрес микропрограммы
        //Таблица для хранения МП
        HashMap<Integer, Integer[][]> mp = new HashMap();//Формат <Адрес текущий x.y> : <М> <Х> <A>
        //Стеки для хранения адресов переходов в инструкциях PAR, LOOP и ALT соответственно
        Stack<Integer> par1 = new Stack();
        Stack<Integer> loop1 = new Stack();
        Stack<Integer> alt1 = new Stack();
        //Для всего списка узлов созданного выше
        for (int i = 1; i < newNode.size(); i++) {
            //Если текщий узел LIN
            if (newNode.get(i).getType() == Node.LIN) {
                //Для LIN + ALT БП (операционная вершина с безусловным переходом)
                if (newNode.get(i + 1).getType() == Node.ALT & newNode.get(i + 1).getValue()[0] == 0) {
                    //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                    mp.put(A * 10, new Integer[][]{getIntMassive(newNode.get(i).getValue()), {0}, {A + 1}});
                    //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате, при её отсутстсвии
                    if (!mp.containsKey(A * 10 + 1))
                        mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});
                    A++;//Увеличить адрес на 1
                    i++;//Так как найдена пара [операция][проверка условия], то пропускаем следующий узел
                }//if
                //Для LIN + ALT с условием
                else if (newNode.get(i + 1).getType() == Node.ALT) {
                    //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                    mp.put(A * 10, new Integer[][]{getIntMassive(newNode.get(i).getValue()), getIntMassive(newNode.get(i + 1).getValue()), {A + 1}});
                    //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате, при её отсутстсвии
                    if (!mp.containsKey(A * 10 + 1))
                        mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});
                    A++;//Увеличить адрес на 1
                    //Сделать запись с адресом "A x+1.1" в таблицу МП в требуемом формате
                    mp.put(A * 10 + 1, new Integer[][]{{0}, {0}, {-1}});//Свободное слово
                    //Запоминаем адрес вершины ALT для последующей корректировки адреса перехода
                    alt1.push(A * 10 + 1);
                }//elseif
                //Для LIN + ENDLOOP
                else if (newNode.get(i + 1).getType() == Node.ENDLOOP) {
                    //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                    mp.put(A * 10, new Integer[][]{getIntMassive(newNode.get(i).getValue()), getIntMassive(newNode.get(i + 1).getValue()), {A + 1}});
                    //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате
                    mp.put((A + 1) * 10 + 1, new Integer[][]{{0}, {0}, {loop1.pop() / 10}});
                    //Сделать запись с адресом "A x+1.1" в таблицу МП в требуемом формате, при её отсутстсвии
                    if (!mp.containsKey(A * 10 + 1))
                        mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});
                    A++;//Увеличить адрес на 1
                }//elseif
                //Для LIN + PAR
                else if (newNode.get(i + 1).getType() == Node.PAR) {
                    //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                    mp.put(A * 10, new Integer[][]{getIntMassive(newNode.get(i).getValue()), getIntMassive(newNode.get(i + 1).getValue()), {A + 1}});
                    //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате, при её отсутстсвии
                    if (!mp.containsKey(A * 10 + 1))
                        mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});
                    A++;//Увеличить адрес на 1
                    //Сделать запись с адресом "A x+1.1" в таблицу МП в требуемом формате
                    mp.put(A * 10 + 1, new Integer[][]{{0}, {0}, {-1}});
                    //Запоминаем адрес вершины PAR для последующей корректировки адреса перехода
                    par1.push(A * 10 + 1);
                }//elseif
            }//if
            //Обработка инструкции ENDALT
            else if (newNode.get(i).getType() == Node.ENDALT) {
                //Извлечь адрес строки микропрограммы, которую надо откорректировать
                //и записываем текущий адрес ENDALT
                mp.get(alt1.pop())[2][0] = A;
            }//elseif
            //Обработка инструкции LOOP
            else if (newNode.get(i).getType() == Node.LOOP) {
                loop1.push(A * 10);//Запоминаем адрес перехода
            }//elseif
            //Обработка инструкции END
            else if (newNode.get(i).getType() == Node.END) {
                //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                mp.put(A * 10, new Integer[][]{{0}, {0}, {0}});
                //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате, при её отсутстсвии
                if (!mp.containsKey(A * 10 + 1))
                    mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});
            }//elseif
            //Обработка инструкции P2
            else if (newNode.get(i).getType() == Node.P2) {   //Корректируем адрес перехода PAR
                mp.get(par1.pop())[2][0] = A + 1;
                //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                mp.put(A * 10, new Integer[][]{{0}, {0}, {-1}});
                //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате, при её отсутстсвии
                if (!mp.containsKey(A * 10 + 1))
                    mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});//Свободное слово
                //Запоминаем текущий адрес, для последующей корректировки перехода
                par1.push(A * 10);
                A++;//Увеличить адрес на 1
            }//elseif
            //Обработка инструкции ENDPAR
            else if (newNode.get(i).getType() == Node.ENDPAR) {
                //Корректируем адрес перехода
                mp.get(par1.pop())[2][0] = A + 1;
                //Сделать запись с адресом "A x.0" в таблицу МП в требуемом формате
                mp.put(A * 10, new Integer[][]{{0}, {0}, {A + 1}});//описание пока не листике
                //Сделать запись с адресом "A x.1" в таблицу МП в требуемом формате, при её отсутстсвии
                if (!mp.containsKey(A * 10 + 1))
                    mp.put(A * 10 + 1, new Integer[][]{{-1}, {-1}, {-1}});//Свободное слово
                //Запоминаем текущий адрес, для последующей корректировки перехода
                par1.push(A * 10);
                A++;//Увеличить адрес на 1
            }//elseif
        }//for
        //Конец построения МП в общих обозначениях
        return mp;
    }

    //Вывод микропрограммы в естественной адресации (общие обозначения)
    public void printMicroprogram(LinkedList<Integer[][]> li) {
        for (int i = 0; i < li.size(); i++) {
            System.out.print("A+" + li.get(i)[0][0] + "\t| " + li.get(i)[1][0] + " | ");
            for (int j = 0; j < li.get(i)[2].length; j++) {
                System.out.print((li.get(i)[1][0] == 0 ? "y" : "x") + li.get(i)[2][j] + " ");
            }
            if (li.get(i).length == 4) System.out.print("| A+" + li.get(i)[3][0]);
            System.out.println();
        }
    }

    //Вывод микропрограммы в принщдительной адресации (общие обозначения)
    public void printMicroprogram(HashMap<Integer, Integer[][]> mp) {
        Integer[][] z;
        int A = mp.size() / 2;
        for (int i = 0; i < A; i++) {
            z = mp.get(i * 10);
            System.out.print("<" + i + ".0>\t");
            if (z[1][0] != -1) {
                for (int j = 0; j < z[0].length; j++)
                    System.out.print("y" + z[0][j] + " ");
            } else
                System.out.print("*");
            System.out.println("\n       " + "\t" + (z[1][0] == -1 ? "*" : "x" + z[1][0]) + "\t" + (z[2][0] == -1 ? "*" : "A<" + z[2][0] + ">"));
            System.out.println("               ----------------");
            z = mp.get(i * 10 + 1);
            System.out.print("<" + i + ".1>\t");
            if (z[1][0] != -1) {
                for (int j = 0; j < z[0].length; j++)
                    System.out.print("y" + z[0][j] + " ");
            } else
                System.out.print("*");
            System.out.println("\n       " + "\t" + (z[1][0] == -1 ? "*" : "x" + z[1][0]) + "\t" + (z[2][0] == -1 ? "*" : "A<" + z[2][0] + ">"));
            System.out.println("-------------------------------");
        }
    }

    private Integer[] getIntMassive(int[] oldArray) {
        Integer[] newArray;//Для хранения масива имен сигналов
        //Если список имен не пуст, то преобразуем список в нужный формат
        if (oldArray != null) {
            newArray = new Integer[oldArray.length];
            int ggg = 0;
            for (int value : oldArray)
                newArray[ggg++] = Integer.valueOf(value);
        }//if
        else //В ином случае вписываем 0, что соотвествует либо безусловному переходу,
            //либо отсуствующим осведомительным сигналам
            newArray = new Integer[]{0};
        return newArray;
    }
}
