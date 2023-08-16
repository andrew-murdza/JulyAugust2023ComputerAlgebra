package util;

import genBool.GenBool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.*;

public class List<T> implements Iterable<T>{

    public ArrayList<T> list=new ArrayList<>();
    public static <T> List<T> of(T... ts){
        return new List<>(new ArrayList<>(Arrays.stream(ts).toList()));
    }
    public List(){

    }
    public List(ArrayList<T> list){
        this.list=new ArrayList<>(list);
    }
    public List(List<T> list){
        this(list.list);
    }
    public T last(){
        return get(size()-1);
    }
    public List<T> removeRepeats(){
        List<T> returnList=new List<>();
        list.forEach(p->{if(!returnList.contains(p)){returnList.push(p);}});
        return returnList;
    }
    public void push(T t){
        list.add(t);
    }
    public void push(int i, T t){
        list.add(i,t);
    }
    public boolean contains(T t){
        return list.contains(t);
    }
    public boolean contains(List<? extends T> t){
        return list.containsAll(t.list);
    }
    public boolean equals(Object o){
        return o instanceof List<?> list1&&list1.list.equals(list);
    }
    public T get(int i){
        return list.get(i);
    }
    public List<T> add(T t){
        return add(0,t);
    }
    public List<T> add(int i,T t){
        return apply(p->p.add(i,t));
    }
    public List<T> addAll(int i,List<T> list1){
        return apply(p->p.addAll(i,new ArrayList<>(list1.list)));
    }
    public List<T> addAll(List<T> list1){
        return addAll(0,list1);
    }
    public void pushAll(int i,List<T> list1){
        list.addAll(i,new ArrayList<>(list1.list));
    }
    public void pushAll(List<T> list1){
        pushAll(0,list1);
    }
    private List<T> apply(Consumer<ArrayList<T>> f){
        ArrayList<T> list1=new ArrayList<>(list);
        f.accept(list1);
        return new List<>(list1);
    }
    public <U>  List<U> fill(Function<T,U> f){
        List<U> returnList=new List<>();
        forEach(p->returnList.push(f.apply(p)));
        return returnList;
    }
    public <U> List<U> fillI(BiFunction<Integer,T,U> f){
        List<U> returnList=new List<>();
        forEachI((i,p)->returnList.push(f.apply(i,p)));
        return returnList;
    }
    public void forEachI(BiConsumer<Integer,T> f){
        for(int i=0;i<size();i++){
            f.accept(i,get(i));
        }
    }
    public static <T> List<T> duplicate(int i, T t){
        return range(i).fill(p->t);
    }
    public <U> List<T> applyForEach(BiConsumer<U,List<T>> f, List<U> list2){
        return applyForEachI((i,t,u)->f.accept(t,u),list2);
    }
    public <U> List<T> applyForEachI(TriConsumer<Integer,U,List<T>> f, List<U> list2){
        List<T> returnList=new List<>(list);
        list2.forEachI((i,p)->f.accept(i,p,returnList));
        return returnList;
    }
    public int size(){
        return list.size();
    }
    public List<Integer> indexOf(T t){
        return List.range(size()).filter(p->list.get(p).equals(t));
    }
    public Integer firstIndexOf(T t){
        return list.indexOf(t);
    }
    public List<T> subList(int i, int j){
        return new List<>(new ArrayList<>(list.subList(i,j)));
    }
    public List<T> subList(int i){
        return subList(i,size());
    }
    public List<T> subListRemoveLast(int i){
        return subList(0,i);
    }
    public boolean isEmpty(){
        return size()==0;
    }
    public Integer lastIndexOf(T t){
        return list.lastIndexOf(t);
    }
    public void remove(T... ts){
        list.removeAll(Arrays.stream(ts).toList());
    }
    public List<T> sort(Comparator<? super T> f){
        return sort(f,false);
    }
    public List<T> sort(Comparator<? super T> f, boolean reverse){
        ArrayList<T> list1=new ArrayList<>(list);
        list1.sort(reverse?f.reversed():f);
        return new List<>(list1);
    }
    public void sortOriginal(Comparator<? super T> f, boolean reverse){
        list.sort(reverse?f.reversed():f);
    }
    public void sortOriginal(Comparator<? super T> f){
        sortOriginal(f,false);
    }
    public static <U extends Comparable<U>> List<U> sort(List<U> list){
        return list.sort(U::compareTo);
    }
    public void removeIsOriginal(List<Integer> is){
        List<Integer> js=sort(is);
        for(int j:js){
            list.remove(j);
        }
    }
    public void removeIs(Integer... is){
        removeIs(of(is));
    }
    public List<T> removeIs(List<Integer> is){
        List<Integer> js=sort(is);
        return applyForEach((p,q)->q.removeIs(p),js);
    }
    public void removeIsOriginal(Integer... is){
        removeIsOriginal(of(is));
    }

    public static List<Integer> range(int i){
        ArrayList<Integer> list=new ArrayList<>();
        for(int j=0;j<i;j++){
            list.add(j);
        }
        return new List<>(list);
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }
    public List<T> filter(Predicate<T> pred){
        List<T> returnList=new List<>();
        forEach(p->{if(pred.test(p))returnList.push(p);});
        return returnList;
    }
    public int countMatches(Predicate<T> f){
        return (int)list.stream().filter(f).count();
    }

    public boolean allTrue(Predicate<T> f){
        return countMatches(f)==size();
    }
    public boolean anyTrue(Predicate<T> f){
        return countMatches(f)>0;
    }
    public boolean allTrueGen(Function<T, GenBool> f){
        return allTrue(p->f.apply(p).isTrue());
    }
    public boolean anyTrueGen(Function<T,GenBool> f){
        return anyTrue(p->f.apply(p).isTrue());
    }
    public <U> List<U> fillCond(Predicate<T> pred, Function<T,U> f) {
        return filter(pred).fill(f);
    }
    public List<T> filterGen(Function<T,GenBool> f) {
        return filter(p->f.apply(p).isTrue());
    }
    public static <T> List<List<T>> filterDeep(List<List<T>> list, Predicate<T> pred) {
        return list.fill(p->p.filter(pred));
    }
    public static <T,U> List<List<U>> fillDeepI(List<List<T>> inputs, BiFunction<Integer,T,U> f) {
        return inputs.fill(p->p.fillI(f));
    }

}
