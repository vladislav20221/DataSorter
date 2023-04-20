/**
 * Алгоритм сортировки выбором.
 * @author User
 */
public class SelectionSort {

    public static void sort( final Comparable[] array ) {
        for ( int i = 0; i < array.length-1; i++ ) {
            int min = i;
            for ( int j = i+1; j < array.length; j++ ) {
                if ( array[j].compareTo( array[min] ) < 0 ) {
                    min = j;
                }                
            }
            Comparable temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
    }
}