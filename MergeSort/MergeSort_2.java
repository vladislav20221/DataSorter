import java.util.Arrays;


public class MergeSort {
    
    public void sort ( Comparable[] array ) {
        sort( array, 0, array.length-1 );       
    }
    
    private void sort ( final Comparable[] array, final int Ab, final int Bb ) {
        if ( Ab >= Bb ) return;
        final int mid = (Ab+Bb)>>1;        // Середина массива.
        // Сортировка половин
        sort( array, Ab, mid );
        sort( array, mid+1, Bb );
        // Объединение массивов.
        merge( array, Ab, mid, Bb );
    }
    
    private void merge ( final Comparable[] array, final int Ab, final int mid, final int Bb ) {
        final int size = Bb-Ab+1;
        int positionA = Ab;
        int positionB = mid+1;
        int positionM = 0;
        // максимальная длина буффера - (length-1)-0+1=length
        final Comparable[] buffer = new Comparable[size];
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
        for ( positionM = 0; positionM < size; positionM++ ) {
            array[Ab+positionM] = buffer[positionM];
        }
    }
}