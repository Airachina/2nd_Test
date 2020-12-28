package fr.exfolyart.exfolyartquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Exfolyart on 22/12/2020.
 */
public class QuestionBank {
    private List<Question1> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question1> questionList){
        mQuestionList = questionList;

        Collections.shuffle(mQuestionList);

        mNextQuestionIndex = 0;
    }
    public Question1 getQuestion(){
    if (mNextQuestionIndex == mQuestionList.size()){
        mNextQuestionIndex = 0;
    }
    return mQuestionList.get(mNextQuestionIndex++);
    }
}
