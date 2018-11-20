
### 1、杀人算法<br/>

#### 解释：把犯人围城一圈，每次杀掉第七个，又从第八个开始杀掉第七个，直到剩下最后一个

#### 代码目录（domo0001KillPerson）



```java
package com.hlj.arith.domo0001KillPerson;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、 杀人算法 :
 * 把犯人围城一圈，每次杀掉第七个，又从第八个开始杀掉第七个，直到剩下最后一个
 *
 * @Author HealerJean
 * @Date 2018/10/10  下午1:37.
 */
public class TestMain {

    @Data
    @Accessors(chain = true)
    public class Person {
        private String name ;
        private Integer sort;
    }


    @Test
    public void start(){

        Integer n = 20 ;

        //1、配置人数，名字以及顺序
        List<Person> persons = setPersonBySystem(n);
        prinnt(persons);

        //2、开始杀人,中间使用了递归
        killPerson(n,persons);

    }


    /**
     * 1、系统输入 人数，设置PerSon的数据
     * @return
     */
    public List<Person> setPersonBySystem(Integer n){
        List persons = new ArrayList<>();
        for(Integer i = 1 ; i<=n ; i++){
            persons.add(new Person().setName("healerjean"+i).setSort(i));
        }
        return  persons ;
    }

    /**
     * 杀人之后重新排序
     * @param n
     * @param persons
     * @return
     */
    public List<Person> killPerson(Integer n, List<Person> persons){

            List<Person>  personEnd =  persons.subList(7, persons.size()); //从第8为开始取数据
            List<Person> personStart = persons.subList(0, 6); //从第一位开始取数据

            personEnd.addAll(personStart);//将前面的加到后面去
            prinnt(personEnd);
            if(personEnd.size()>6){ //表示索引最小也得是7
                killPerson(n, personEnd);
            }
            return  personEnd ;

    }

    public void prinnt(List<Person> person){
        person.stream().forEach(s->{
            System.out.print(s.getSort()+",");
        });
        System.out.println();
    }


}


```
