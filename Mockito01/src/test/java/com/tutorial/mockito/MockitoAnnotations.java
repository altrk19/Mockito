package com.tutorial.mockito;

import com.tutorial.mockito.model.MyDictionary;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class MockitoAnnotations {
    @Mock
    HashMap<String, Integer> mockHashMap;

    @Spy
    HashMap<String, Integer> spyHashMap;

    @Captor
    ArgumentCaptor<String> keyCaptor;

    @Captor
    ArgumentCaptor<Integer> valueCaptor;

    @Mock
    Map<String, String> wordMap;

    @InjectMocks
    MyDictionary dic = new MyDictionary();

    @Test
    public void MockTest() {
        mockHashMap.put("A", 1);

        Mockito.verify(mockHashMap, Mockito.times(1)).put("A", 1);
        Mockito.verify(mockHashMap, Mockito.times(0)).get("A");
        assertEquals(0, mockHashMap.size());

        Mockito.when(mockHashMap.size()).thenReturn(100);
        assertEquals(100, mockHashMap.size());

        //Not-1: Mockito.verify(mockHashMap, Mockito.times(1)).put("A", 1);
        //Burda map'e ekleme yapılmıyor. map'e A elemanı eklenme intreaction'ı oldu mu diye kontrol ediliyor. Burada B olarak degistirsek test fail edecektir.
    }

    @Test
    public void SpyTest() {
        spyHashMap.put("A", 10);

        Mockito.verify(spyHashMap, Mockito.times(1)).put("A", 10);
        Mockito.verify(spyHashMap, Mockito.times(0)).get("A");
        assertEquals(1, spyHashMap.size());
        assertEquals(10, spyHashMap.get("A"));

        Mockito.when(spyHashMap.size()).thenReturn(100);
        assertEquals(100, spyHashMap.size());
    }

    @Test
    public void CaptorTest() {
        mockHashMap.put("C", 20);

        Mockito.verify(mockHashMap).put(keyCaptor.capture(), valueCaptor.capture());
        assertEquals("C", keyCaptor.getValue());
        assertEquals(20, valueCaptor.getValue());
    }

    @Test
    public void InjectMockTest() {
        Mockito.when(wordMap.get("word")).thenReturn("meaning");
        assertEquals("meaning", dic.getMeaning("word"));
        //Not:Test edilen sınıfın metotlarını mocklamamız gerekiyorsa @InjectMock annotation'ını kullanılırız.
    }


    @Test
    public void assertThrowsTest() {
        String s = null;
        assertThrows(NullPointerException.class, () -> s.length());
    }

}
