import com.progbook.persistence.model.Answer;
import org.junit.Test;

public class TestRandom {
    @Test
    public void printClassNames(){
        System.out.println("getname : "+Answer.class.getName());
        System.out.println("getsimplename : "+Answer.class.getSimpleName());
        System.out.println("getcanonicalname : "+Answer.class.getCanonicalName());
    }
}
