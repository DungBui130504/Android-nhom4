package com.example.myapplication.adpaters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.models.Subject.SubjectObject;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends ArrayAdapter<SubjectObject> {
    private final Activity context;
    private final int ID;
    private final ArrayList<SubjectObject> myList;
    private final ArrayList<Boolean> checkedStates; // Danh sách trạng thái của CheckBox

    public SubjectAdapter(Activity context, ArrayList<SubjectObject> myList, int ID) {
        super(context, ID, myList);
        this.context = context;
        this.myList = myList;
        this.ID = ID;

//         Khởi tạo danh sách trạng thái mặc định là false
        checkedStates = new ArrayList<>();
        for (int i = 0; i < myList.size(); i++) {
            checkedStates.add(false);
        }
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(ID, null);

        // Lấy item tại vị trí hiện tại
        SubjectObject sb = myList.get(position);

        // Tham chiếu đến TextView và CheckBox
        TextView subjectItem = convertView.findViewById(R.id.subjectItem);
        CheckBox checkBox = convertView.findViewById(R.id.subjectChk);

        // Hiển thị tên môn học
        subjectItem.setText(sb.getSubjectName());

        // Đảm bảo vị trí không vượt quá kích thước của checkedStates
        if (position < checkedStates.size()) {
            checkBox.setChecked(checkedStates.get(position));
        }

        // Lắng nghe sự kiện thay đổi trạng thái của CheckBox
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (position < checkedStates.size()) {
                checkedStates.set(position, isChecked);
            }
        });

        return convertView;
    }

//     Phương thức lấy danh sách các index của item được check
    public ArrayList<Integer> getCheckedIndexes() {
        ArrayList<Integer> checkedIndexes = new ArrayList<>();
        for (int i = 0; i < checkedStates.size(); i++) {
            if (checkedStates.get(i)) {
                checkedIndexes.add(i);
            }
        }
        return checkedIndexes;
    }
}
