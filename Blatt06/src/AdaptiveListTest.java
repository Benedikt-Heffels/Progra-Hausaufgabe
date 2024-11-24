//import static org.junit.jupiter.api.Assertions.*;

class AdaptiveListTest {

    public static void main(String[] args) {
        containsTopPriorityPhil();
    }

    //@org.junit.jupiter.api.Test
    private static void containsAdaptivePhil() {
        AdaptiveList list = rndList(2);
        for (int i = 0; i < 10; i++) {
            int value = random();
            System.out.println("before: " + list.toString() + "\n value: " + value + (list.contains(value) ? "(contained)" : "(not contained)"));
            System.out.println((list.containsAdaptivePhil(value)) ? "true" : "false");
            System.out.println("after: " + list.toString());
        }

    }

    //@org.junit.jupiter.api.Test
    private static void containsTopPriorityPhil() {
        AdaptiveList list = rndList(10);
        for (int i = 0; i < 50; i++) {
            int value = random();
            System.out.println("before: " + list.toString() + "\n value: " + value + (list.contains(value) ? "(contained)" : "(not contained)"));
            System.out.println((list.containsTopPriorityPhil(value)) ? "true" : "false");
            System.out.println("after: " + list.toString());
        }
    }

    private static AdaptiveList rndList(int length){
        AdaptiveList lst = rndElement();
        for(int i = 0; i < length-1; i++){
            lst.append(random());
        }
        return lst;
    }

    private static AdaptiveList rndElement(){
        return new AdaptiveList(random(), null);
    }

    private static int random() {
        return (int)(10 * Math.random());
    }
}