package com.tutorial.mockito;

import com.tutorial.mockito.model.MyDictionary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class ExceptionTest {

    @Test
    public void nonVoidReturnType() {
        MyDictionary dictMock = Mockito.mock(MyDictionary.class);
        Mockito.when(dictMock.getMeaning(ArgumentMatchers.anyString())).thenThrow(NullPointerException.class);

        Executable executable = () -> dictMock.getMeaning("AA");

        NullPointerException npe = Assertions.assertThrows(NullPointerException.class, executable);
        Assertions.assertNull(npe.getMessage());
    }

    @Test
    public void voidReturnType() {
        MyDictionary dictMock = Mockito.mock(MyDictionary.class);
        Mockito.doThrow(IllegalStateException.class)
                .when(dictMock)
                .add(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        Executable executable = () -> dictMock.add("word", "meaning");

        IllegalStateException npe = Assertions.assertThrows(IllegalStateException.class, executable);
        Assertions.assertNull(npe.getMessage());
    }

    @Test
    public void throwExceptionAsObject1NonVoid(){     //thenThrowExample
        MyDictionary dictMock = Mockito.mock(MyDictionary.class);
        Mockito.when(dictMock.getMeaning(ArgumentMatchers.anyString()))
                .thenThrow(new NullPointerException("Error occurred"));

        Executable executable = () -> dictMock.getMeaning("word");

        NullPointerException npe = Assertions.assertThrows(NullPointerException.class, executable);
        Assertions.assertEquals("Error occurred", npe.getMessage());
    }

    @Test
    public void throwExceptionAsObject2Void() {     //doThrowExample
        MyDictionary dictMock = Mockito.mock(MyDictionary.class);
        Mockito.doThrow(new IllegalStateException("Error occurred"))
                .when(dictMock)
                .add(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        Executable executable = () -> dictMock.add("word", "meaning");

        IllegalStateException npe = Assertions.assertThrows(IllegalStateException.class, executable);
        Assertions.assertEquals("Error occurred",npe.getMessage());
    }

    @Test
    public void throwExceptionSpyMock(){    //spyMock with thenThrow
        MyDictionary dict = new MyDictionary();
        MyDictionary spy = Mockito.spy(dict);

        Mockito.when(spy.getMeaning(ArgumentMatchers.anyString())).thenThrow(new NullPointerException("Error"));

        Executable executable = () -> spy.getMeaning("word");

        NullPointerException npe = Assertions.assertThrows(NullPointerException.class, executable);
        Assertions.assertEquals("Error", npe.getMessage());
    }
}
