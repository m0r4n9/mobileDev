package ru.mirea.sevostyanov.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import ru.mirea.sevostyanov.mireaproject.databinding.FragmentFileBinding;

public class FileFragment extends Fragment {

    private FragmentFileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTxtToWord();
            }
        });
    }

    private void convertTxtToWord() {
        InputStream inputStream = getResources().openRawResource(R.raw.text);
        try {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String txtContent = new String(buffer);

            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph paragraph = doc.createParagraph();
            paragraph.createRun().setText(txtContent);

            File externalDir = getActivity().getExternalFilesDir(null);
            if (externalDir != null) {
                File wordFile = new File(externalDir, "converted_document.docx");

                FileOutputStream fos = new FileOutputStream(wordFile);
                doc.write(fos);
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
