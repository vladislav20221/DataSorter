import java.util.Arrays;


public class MergeSort {

    public Comparable[] sort ( Comparable[] array ) {
        if ( array == null ) return null;
        if ( array.length < 2 ) return array;        
        int mid = array.length>>1;        // Середина массива.
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
        int positionM = 0;
        Comparable[] buffer = new Comparable [ arrayA.length+arrayB.length ];
        while ( positionA < arrayA.length && positionB < arrayB.length ) {
            if ( arrayA[positionA].compareTo(arrayB[positionB]) < 0 ) {                
                buffer[positionM++] = arrayA[positionA++];
            } else {
                buffer[positionM++] = arrayB[positionB++];
            }
        }
        // Дозапись частей массива.
        while ( positionA < arrayA.length )
            buffer[positionM++] = arrayA[positionA++];
        while ( positionB < arrayB.length )
            buffer[positionM++] = arrayB[positionB++];
        return buffer;
    }
}
