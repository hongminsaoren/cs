package deque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;
public class MaxArrayDeque<T> extends ArrayDeque{
    private Comparator<T> cmp;
    public MaxArrayDeque(Comparator<T> c){
        cmp=c;
    }
    public T max(){
        if(isEmpty())
            return null;
        Iterator<T> iterator=iterator();
        T res=iterator.next();
        while (iterator.hasNext()){
            T element=iterator.next();
            if(cmp.compare(element,res)>0)
                res=element;
        }
        return res;
    }
    public T max(Comparator<T> c){
        if(isEmpty())
            return null;
        Iterator<T> iterator=iterator();
        T res=iterator.next();
        while (iterator.hasNext()){
            T element=iterator.next();
            if(c.compare(element,res)>0)
                res=element;
        }
        return res;
    }

}