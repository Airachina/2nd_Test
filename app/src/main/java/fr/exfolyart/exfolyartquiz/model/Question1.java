package fr.exfolyart.exfolyartquiz.model;

import java.util.List;

/**
 * Created by Exfolyart on 22/12/2020.
 */
public class Question1
{
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question1(String question, List<String> choiceList, int answerIndex)
    {

        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setChoiceList(List<String> choiceList) {
        if (choiceList == null){
            throw new IllegalArgumentException("Array cannot be null");
        }
        mChoiceList = choiceList;
    }

    public void setAnswerIndex(int answerIndex) {
        if (answerIndex <0 || answerIndex >= mChoiceList.size()){
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswerIndex = answerIndex;
    }

    @Override
    public String toString() {
        return "Question1{" + "mQuestion='" + mQuestion + '\'' + ", mChoiceList=" + mChoiceList + ", mAnswerIndex=" + mAnswerIndex + '}';
    }
}
