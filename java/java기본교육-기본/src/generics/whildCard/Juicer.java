package generics.whildCard;


public class Juicer {
    // static Juice makeJuice(FruitBox3<Fruit3> box){
    //     String tmp = "";
    //     return new Juice();
    // }

    // static Juice makeJuice(FruitBox3<Apple3> box) {
    //     String tmp = "";
    //     return new Juice();
    // }

    // 지네릭 타입이다른 것만으로 오버로딩이 성립되지 않음.
    // 이를 위해 고안된 것이 와일드카드 <?> 

    public static Juice makeJuice(FruitBox3<? extends Fruit3> box){
        String tmp = "";
        return new Juice();
    }
    //<? extends T> 상한제한: T와 그의 자손들
    //<? super T> 하한제한: T와 그의 조상들
    //<?> 제한x: 모두 가능 (= <? extends Object> )


    public static void test(FruitBox3<?> box) {

    }
    public static void test2(PrimTest<?> pri){
        
    }
}
