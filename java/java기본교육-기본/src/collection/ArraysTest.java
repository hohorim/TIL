package collection;

import java.util.Arrays;

public class ArraysTest {
    public void test(){
        
        int[] arr = {3,2,0,1,5,7};
        System.out.println(Arrays.binarySearch(arr,2));     // -5
        Arrays.sort(arr);       
        System.out.println(Arrays.binarySearch(arr,2));     // 2 
        // 이진검색은 빠르다. 하지만 정렬을 우선적으로 해야만 값을 얻을 수 있다.

        
    }

}
