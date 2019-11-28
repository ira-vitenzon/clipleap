package com.joyfulshark.clipleap;

import android.content.res.AssetManager;

import com.joyfulshark.clipleap.common.ObjectsScores;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void csvTest() throws FileNotFoundException {
        File file = new File("C:\\Users\\Dvir\\Desktop\\Android\\clipleap\\app\\src\\main\\assets\\objects.csv");
        FileReader fileReader = new FileReader(file);
        ObjectsScores.getInstance().build(fileReader);
        System.out.println(ObjectsScores.getObjectsMap());
    }

}