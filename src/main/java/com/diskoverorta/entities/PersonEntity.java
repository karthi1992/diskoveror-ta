package com.diskoverorta.entities;

import com.diskoverorta.osdep.StanfordNLP;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by praveen on 15/10/14.
 */
public class PersonEntity implements BaseEntity
{
    public static void main(String args[])
    {
        PersonEntity temp = new PersonEntity();
        System.out.println(temp.getEntities("Barack Obama is the president of USA"));
    }

    public List<String> getEntities(String sentence)
    {
        StanfordNLP snerTag = new StanfordNLP();
        List<List<CoreLabel>> entityMap =  snerTag.get3NERTaggedOutput(sentence);
        return getEntities(entityMap);
    }

    public List<String> getEntities(List<List<CoreLabel>> sentTags)
    {
        List<String> entityList = new ArrayList<String>();
        for (List<CoreLabel> lcl : sentTags)
        {
            for (int i = 0; i < lcl.size(); i++)
            {
                String key = lcl.get(i).get(CoreAnnotations.AnswerAnnotation.class);
                if(key.equals("PERSON") == true)
                {
                    String temp1 = "";
                    int cnt =0;
                    while(key.equals("PERSON") == true)
                    {
                        if(cnt >= 3)
                            break;
                        cnt++;
                        temp1 = temp1 + lcl.get(i).originalText() + " ";
                        i++;
                        if(i < lcl.size())
                            key = lcl.get(i).get(CoreAnnotations.AnswerAnnotation.class);
                        else
                            break;

                    }
                    temp1 = temp1.trim();
                    entityList.add(temp1);
                }
            }
        }
        return entityList;
    }
}
