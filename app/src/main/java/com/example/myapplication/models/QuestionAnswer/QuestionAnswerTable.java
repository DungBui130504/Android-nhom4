package com.example.myapplication.models.QuestionAnswer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.myapplication.database.Database;

import java.util.ArrayList;

public class QuestionAnswerTable {
    private SQLiteDatabase db;
    private Context context;

    public QuestionAnswerTable(Context context) {
        this.context = context;
        try {
            this.db = new Database(context).open();
        } catch (Exception e) {
            Toast.makeText(context, "Có lỗi khi kết nối db tại model QuestionAnswer : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    // Thêm một câu hỏi và câu trả lời mới vào bảng QuestionAnswer
    public boolean addNewQuestionAnswer(String questionContent, String answerContent, String answerUpdateDate,
                                        String questionUpdateDate, int subjectID,  int userID) {
        ContentValues values = new ContentValues();
        values.put("questionContent", questionContent);
        values.put("answerContent", answerContent);
        values.put("answerUpdateDate", answerUpdateDate);
        values.put("questionUpdateDate", questionUpdateDate);
        values.put("subjectID", subjectID);
        values.put("userID", userID);

        try {
            long result = this.db.insert("QuestionAnswer", null, values);
            return result != -1;
        } catch (Exception e) {
            Toast.makeText(this.context, "Có lỗi khi thêm câu hỏi và câu trả lời mới: " + e, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Lấy câu hỏi và câu trả lời theo questionAnswerID
    public QuestionAnswerObject getQuestionAnswerById(int questionAnswerID) {
        QuestionAnswerObject questionAnswer = null;

        String query = "SELECT * FROM QuestionAnswer WHERE questionAnswerID = ?";
        Cursor cursor = null;

        try {
            cursor = this.db.rawQuery(query, new String[]{String.valueOf(questionAnswerID)});

            if (cursor != null && cursor.moveToFirst()) {
                int questionAnswerIdIndex = cursor.getColumnIndex("questionAnswerID");
                int questionContentIndex = cursor.getColumnIndex("questionContent");
                int answerContentIndex = cursor.getColumnIndex("answerContent");
                int answerUpdateDateIndex = cursor.getColumnIndex("answerUpdateDate");
                int questionUpdateDateIndex = cursor.getColumnIndex("questionUpdateDate");
                int subjectIDIndex = cursor.getColumnIndex("subjectID");
                int isAnswerIndex = cursor.getColumnIndex("isAnswer");
                int userIDIndex = cursor.getColumnIndex("userID");

                if (questionAnswerIdIndex >= 0 && questionContentIndex >= 0 && answerContentIndex >= 0) {
                    int id = cursor.getInt(questionAnswerIdIndex);
                    String questionContent = cursor.getString(questionContentIndex);
                    String answerContent = cursor.getString(answerContentIndex);
                    String answerUpdateDate = cursor.getString(answerUpdateDateIndex);
                    String questionUpdateDate = cursor.getString(questionUpdateDateIndex);
                    int subjectID = cursor.getInt(subjectIDIndex);
                    boolean isAnswer = cursor.getInt(isAnswerIndex) == 1;
                    int userID = cursor.getInt(userIDIndex);

                    questionAnswer = new QuestionAnswerObject(id, questionContent, answerContent, answerUpdateDate,
                            questionUpdateDate, subjectID, isAnswer, userID);
                }
            }
        } catch (Exception e) {
            Toast.makeText(this.context, "Có lỗi khi lấy thông tin câu hỏi và câu trả lời: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return questionAnswer;
    }

    // Lấy tất cả câu hỏi và câu trả lời của một subjectID
    public ArrayList<QuestionAnswerObject> getQuestionAnswersOfUserID(int subjectID) {
        ArrayList<QuestionAnswerObject> questionAnswers = new ArrayList<>();
        String query = "SELECT questionAnswerID FROM QuestionAnswer WHERE subjectID = ?";
        Cursor cursor = null;

        try {
            cursor = this.db.rawQuery(query, new String[]{String.valueOf(subjectID)});
            if (cursor == null || !cursor.moveToFirst()) {
                return questionAnswers;
            }

            while (cursor.moveToNext()) {
                int questionAnswerIDIndex = cursor.getColumnIndex("questionAnswerID");
                if (questionAnswerIDIndex >= 0) {
                    int questionAnswerID = cursor.getInt(questionAnswerIDIndex);
                    questionAnswers.add(this.getQuestionAnswerById(questionAnswerID));
                }
            }

        } catch (Exception e) {
            Toast.makeText(this.context, "Có lỗi khi lấy câu hỏi và câu trả lời của userID: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return questionAnswers;
    }

    // Cập nhật câu trả lời của một câu hỏi
    public boolean updateAnswer(int questionAnswerID, String answerContent, String answerUpdateDate, boolean isAnswer) {
        ContentValues values = new ContentValues();
        values.put("answerContent", answerContent);
        values.put("answerUpdateDate", answerUpdateDate);
        values.put("isAnswer", isAnswer ? 1 : 0);

        try {
            int result = this.db.update("QuestionAnswer", values, "questionAnswerID = ?", new String[]{String.valueOf(questionAnswerID)});
            return result > 0;
        } catch (Exception e) {
            Toast.makeText(this.context, "Có lỗi khi cập nhật câu trả lời: " + e, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    // Xóa câu hỏi và câu trả lời theo questionAnswerID
    public boolean deleteQuestionAnswer(int questionAnswerID) {
        try {
            int result = this.db.delete("QuestionAnswer", "questionAnswerID = ?", new String[]{String.valueOf(questionAnswerID)});
            return result > 0;
        } catch (Exception e) {
            Toast.makeText(this.context, "Có lỗi khi xóa câu hỏi và câu trả lời: " + e, Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
