import java.util.Arrays;


public class MergeSort {
    // Выбирает порядок сортировки. true - прямой, false - обратный.
    private final int koef;
    
    public MergeSort ( final boolean koef ) { this.koef = (koef)?1:-1; }
    
    /**
     * Запускает процесс сортировки для заданной колекции Array
     * @param array
     * @return
     */
    public Comparable[] sort ( Comparable[] array ) {
        if ( array == null ) return null;
        if ( array.length < 2 ) return array;        
        int mid = array.length >> 1;        // Середина массива.
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

    private Comparable[] merge ( final Comparable[] arrayA, final Comparable[] arrayB ) {
        int positionA = 0;
        int positionB = 0;
        int positionMerge = 0;
        Comparable[] merged = new Comparable [ arrayA.length+arrayB.length ];
        while ( positionA < arrayA.length && positionB < arrayB.length ) {
            if ( arrayA[positionA].compareTo(arrayB[positionB])*koef <= 0 )                 
                merged[positionMerge++] = arrayA[positionA++];
            else {
                merged[positionMerge++] = arrayB[positionB++];
            }
        }
        // Дозапись частей массива.
        while ( positionA < arrayA.length )
            merged[positionMerge++] = arrayA[positionA++];
        while ( positionB < arrayB.length )
            merged[positionMerge++] = arrayB[positionB++];
        return merged;
    }
}