package generics.base;
public class Box<T> {
    // static T staItem; static 멤버에는 터입변수 T 를 사용할 수 없다. 모든 객체에 대해 동일하게 동작해야해서.
    T item;
    
    int[] tmpArr = new int[3];
    T[] itemArr;    // 지네릭 배열 타입의 찹조변수는 가능하지만
    // T[] tmpArr2 = new T[3];  이와 같은 배열생성은 안됨. new연산자 때문. 컴파일시점에 타입이 뭔지 알아야해서.  
    

    // 매개변수에 타입변수<T>를 받는 static 메서드는 불가능하다. 그래서
    // 타입변수 를 선언한(return 타입 앞) 지네릭메서드는 static 키워드를 붙여서 사용가능하다.. 혹은 와일드카드로 매개변수받기<?>
    // 대신 Box 클래스의 타입변수 T 와 지네릭 메서드의 타입변수 T 는 서로 다른 것이다. 
    // 지네릭 메서드에 선언된 타입은 지역변수를 선언한 것과 같기 때문이다.
    // 그래서 제너릭 메서드의 타입변수를 다른 문자로 써도됨. E 같은거..
    
    // public static void getInstance3(FruitBox<T> item) {

    // }
    
    public void getInstance2(FruitBox<T> item) {

    }
    
    public static <T extends Fruit> void getInstance(FruitBox<T> item){    // 다르기 때문에 T -> E 변경가능     
        
    }

    public static void getInstance4(FruitBox<?> item){ 
        
    }

    public void setItem(T item){
        this.item =item;
    }
    T getItem(){return item;}

    Object[] objecItem;
    int index=0;
    {
        this.objecItem = new Object[10];
    }
    // 제너릭타입의 배열을 생성하는 것이 안되지만 꼭해야한다면 이처럼 Object 배열을 T[] 로 형변환하는 방법이 있음.
    public <T> T[] myToArray(T[] a){
        for(int i=0; i<a.length; i++){
            
            this.objecItem[this.index++]= a[i];
        }
        T[] rs = (T[]) objecItem;
        return rs;
    }
}
