import com.google.common.reflect.TypeToken;
import com.mysema.query.types.Predicate;
import com.progbook.FilterCriteria;
import com.progbook.persistence.model.Answer;
import org.junit.Test;

public class TestRandom {
    @Test
    public void printClassNames(){
        System.out.println("getname : "+Answer.class.getName());
        System.out.println("getsimplename : "+Answer.class.getSimpleName());
        System.out.println("getcanonicalname : "+Answer.class.getCanonicalName());
    }

    @Test
    public void printGenericType() throws NoSuchMethodException {
        FilterCriteria predicateFilterCriteria = new FilterCriteria();
//        predicateFilterCriteria.printType();
    }
}
