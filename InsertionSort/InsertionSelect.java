/**
 * Алгоритм сортировки вставками.
 * @author User
 */
public class InsertionSelect {
    
    
    public static void sort( final Comparable[] array ) {
        for ( int i = 1; i<array.length; i++ ) {
            Comparable temp = array[i];
            int j;
            for ( j = i-1; j >= 0 && temp.compareTo( array[j] ) < 0; j-- ) {
                array[j+1] = array[j];
            }
            array[j+1] = temp;
        }
    }
}