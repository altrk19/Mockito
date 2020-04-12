package com.tutorial.mockito;

import com.tutorial.mockito.model.FlowerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
public class ArgumentMatchersTest {

    @Test
    public void withoutArgumentMatcher1() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.doReturn("Flower").when(mock).analyze("poppy");

        String result = mock.analyze("poppy");

        Mockito.verify(mock, Mockito.times(1)).analyze("poppy");
        Assertions.assertEquals("Flower", result);
    }

    @Test
    public void withoutArgumentMatcher2() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.when(mock.analyze("poppy")).thenReturn("Flower");

        String result = mock.analyze("poppy");

        Mockito.verify(mock, Mockito.times(1)).analyze("poppy");
        Assertions.assertEquals("Flower", result);
    }

    @Test
    public void withoutArgumentMatcher3() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.when(mock.isABigFlower("poppy", 20)).thenReturn(false);

        boolean result = mock.isABigFlower("poppy", 20);

        Mockito.verify(mock).isABigFlower("poppy", 20);
        Assertions.assertFalse(result);
    }

    @Test
    public void withArgumentMatcher1FlowerAnalyze() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.when(mock.analyze(ArgumentMatchers.anyString())).thenReturn("Flower");

        String result = mock.analyze("poppy");

        Mockito.verify(mock, Mockito.times(1)).analyze("poppy");
        Assertions.assertEquals("Flower", result);
    }

    @Test
    public void withArgumentMatcher2FlowerAnalyze() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.when(mock.analyze(ArgumentMatchers.anyString())).thenReturn("Flower");

        String result = mock.analyze(ArgumentMatchers.anyString());

        Mockito.verify(mock, Mockito.times(1)).analyze(ArgumentMatchers.anyString());
        Assertions.assertEquals("Flower", result);
    }

    @Test
    public void withArgumentMatcher1IsABigFlower() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.when(mock.isABigFlower(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(true);
        boolean result = mock.isABigFlower(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt());

        Mockito.verify(mock, Mockito.times(1)).isABigFlower("", 0);
        Assertions.assertTrue(result);
    }

    @Test
    public void withArgumentMatcher2IsABigFlower() {
        FlowerService mock = Mockito.mock(FlowerService.class);
        Mockito.when(mock.isABigFlower(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt())).thenReturn(true);
        boolean result = mock.isABigFlower("AA",12 );

        Assertions.assertTrue(result);
    }

}
