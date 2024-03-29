# Алгоритм сортировки слиянием
Данный метод сортировки основан на парадигме "Разделяй и влавствуй". Исходный массив делиться на более мелкие части. Разделение на части завершается, когда размер массива равен 1. Каждая половина сортируются рекурсивно. Полученные отсортированные части соединяются слиянием. 

Базовый случай для выхода из рекурсии: в массиве не осталось элементов.

## Наивная реализация
Первая реализация основывается на определении данного алгоритма. Весь алгоритм можно разделить на две части. В первой части выполняется деление массива на две части, а во второй производится слияние. 
Деление массива на мелкие части выполняется до тех пор пока в массиве имеется хотя бы один элемент. Обе части деляться рекурсивно и в конце мектода сортируются слиянием.

```java
public Comparable[] sort ( Comparable[] array ) {
  if ( array == null ) return null;
  if ( array.length < 2 ) return array;        
  int mid = array.length / 2;        // Середина массива.
  // Левая половина.
  Comparable[] arrayA = Arrays.copyOfRange( array, 0, mid );
  // Правая половина.
  Comparable[] arrayB = Arrays.copyOfRange( array, mid, array.length );
  // Сортировка половин
  arrayA = sort ( arrayA );
  arrayB = sort ( arrayB );
  // Объединение массивов.
  return merge( arrayA, arrayB );
}
```

Во сторой части алгоритма реализована сортировка. Данная часть имеет три позиции:
1. positionA - позиция записи в левой части массива.
2. positionB - позиция записи в правой части массива.
3. positionB - позиция записи в буфере.

Размер буфера равен размеру сумме размерности левой и правой частей массива. Сам цикл слияния можно разделить на три части.
1. Итерации которые заполняют буфер из двух частей массива. Выполняются пока в обоих массивах есть элементы.
2. Итерации дозаписывающие в буфер остаток левой части массива.
3. Итерации дозаписывающие в буфер остаток правой части массива.
```java
private Comparable[] merge ( final Comparable[] arrayA, final Comparable[] arrayB ) {
  int positionA = 0;
  int positionB = 0;
  int positionB = 0;
  Comparable[] buffer = new Comparable [ arrayA.length+arrayB.length ];
  while ( positionA < arrayA.length && positionB < arrayB.length ) {
    if ( arrayA[positionA].compareTo(arrayB[positionB]) < 0 ) {
      buffer[positionB++] = arrayA[positionA++];
    } else {
      buffer[positionB++] = arrayB[positionB++];
    }
  }
  // Дозапись частей массива.
  while ( positionA < arrayA.length )
    buffer[positionB++] = arrayA[positionA++];
  while ( positionB < arrayB.length )
    buffer[positionB++] = arrayB[positionB++];
  return buffer;
}
```
Время сортировки данной реализации алгоритма была протестирована на массиве из 1 000 000 элементов заполненном случайными числами в диапазоне [-1000...1000]. Всего было выполненно 100 итерация сортировки и среднее время составило 210.13. Уже не плохо по крайней мере намного быстрее чем простые алгоритмы сортировки. Но данная реализация имеет несколько недостатков. 

## Улучшеине алгоритма №1
На этапе разделения массива на части создатся два подмассива. Тратиться лишнее время на инициализацию и выделения памяти на сохдание новых массивов. Данная проблема решается введением трёх сщётчиков. 
1. mid - индекс, по которому делиться массив. 
2. Ab - Позиция левой части массива.
3. Bb - Позиция правой части массива.

В данной реализации все отсортированные данные записываются в исходный массив. Не солжно изменить реализация на возвращении копии отсортированного массива. Для этого создаётся копия массива в самом начале и сортируется имеенно она. Затем вызывающий метод возвращает этот массив. Но это уже дело каждого. В рассатриваемой реализации данные будут записываться в иходный массив. При первом запуски в качестве левой части указывается 0, а в качестве правой части указывается размер массива - 1. Можно перегруизть метод программы так чтобы публичный был только с аргументов массива и он уже будет вызывать главный сортирующий методв. Приведены сигнатуры методов.
```java
public void sort ( Comparable[] array );    
private void sort ( Comparable[] array, int Ab, int Bb );
```

Реализация первой части будет выглядить следующим образом:

```java
private void sort ( Comparable[] array, int Ab, int Bb ) {
  if ( Ab >= Bb ) return;
  int mid = (Ab+Bb) / 2;        // Середина массива.
  // Сортировка половин
  sort( array, Ab, mid );
  sort( array, mid+1, Bb );
  // Объединение массивов.
  merge( array, Ab, mid, Bb );
}
```

Во второй части добавляется блок, который записывает буфер в исходный массив.

```java
private void merge ( final Comparable[] array, int Ab, int mid, int Bb ) {
  int positionA = Ab;
  int positionB = mid+1;
  int positionM = 0;
  Comparable[] buffer = new Comparable[Bb-Ab+1];
  while ( positionA <= mid && positionB <= Bb ) {
    if ( array[positionA].compareTo(array[positionB]) < 0 )
      buffer[positionM++] = array[positionA++];
    else {
      buffer[positionM++] = array[positionB++];
    }
  }
  // Дозапись частей массива.
  while ( positionA <= mid )
    buffer[positionM++] = array[positionA++];
  while ( positionB <= Bb )
    buffer[positionM++] = array[positionB++];
  // Копирование буфера в массив array
  for ( positionM = 0; positionM < Bb-Ab+1; positionM++ ) {
    array[Ab+positionM] = buffer[positionM];
  }
}
```

Данная реализация была протестирована аналогичным образом и среднее время составило 223.28. Время сортировки только увеличилось. Удалось немного сократить время выплонения алгоритма создав новую переменную size в которую помещаяется размер текущей области. 

```java
final int size = Bb-Ab+1;
```

Таким образом среднее время составило 215,15, что всё ещё медленее прошлой реализации. 
